package jni.study.com.jnibsetpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button bt_1 = findViewById(R.id.bt_1);
        Button bt_2 = findViewById(R.id.bt_2);
        Button bt_3 = findViewById(R.id.bt_3);
        Button bt_4 = findViewById(R.id.bt_4);
        Button bt_5 = findViewById(R.id.bt_5);
        Button bt_6 = findViewById(R.id.bt_6);
        Button bt_7 = findViewById(R.id.bt_7);


        final Jni jni = new Jni();

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hello = jni.sayHello();
                Toast.makeText(MainActivity.this, hello, Toast.LENGTH_LONG).show();
            }
        });

        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int add = jni.add(1, 2);
                Toast.makeText(MainActivity.this, "1+2=" + add, Toast.LENGTH_LONG).show();
            }
        });

        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = jni.transe_string("abc");
                Toast.makeText(MainActivity.this, "abc   ====c转换=====>   " + str, Toast.LENGTH_LONG).show();
            }
        });
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] intArray = new int[]{1, 2, 3};
                int[] intArrayTranse = jni.transeIntArray(intArray);

                for (int i = 0; i < intArray.length; i++) {
                    //发现在这里遍历旧的数组，打印出来也是加过100后的数字，101，102，103
                    // 因为C是通过指针操作内存地址，改变了intArray的内存地址的值，
                    // 所以C里面不返回int[]也是可以实现目的的
                    Log.e("abc", intArray[i] + "old");
                }

                for (int i = 0; i < intArrayTranse.length; i++) {
                    Log.e("abc", intArrayTranse[i] + "new");
                }//这里打印的是101，102，103

            }
        });


        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 获取字节码对象
                Class<Animal> clazz = Animal.class;
                // 2. 获取method方法的对象
                try {
                    Method say = clazz.getDeclaredMethod("say", String.class);
                    // 3. 通过字节码获取Animal的实例
                    Animal animal = clazz.newInstance();
                    //  4.通过实例调用方法
                    say.invoke(animal, "hello!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        bt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jni.callBackMethodVoid();
            }
        });


        bt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = jni.callBackMethodInt();
                Toast.makeText(MainActivity.this, "C——》java" + i, Toast.LENGTH_LONG).show();

            }
        });
    }
}
