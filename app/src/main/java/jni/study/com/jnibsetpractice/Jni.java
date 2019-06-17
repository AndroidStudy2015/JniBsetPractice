package jni.study.com.jnibsetpractice;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 描述当前版本功能
 *
 * @Project: JniBsetPractice
 * @author: cjx
 * @date: 2019-06-14 09:01  星期五
 */
public class Jni {
    static {
        System.loadLibrary("best");
    }

    Context mContext;

    public Jni(Context context) {
        mContext = context;
    }

    public native String sayHello();

    public native int add(int x, int y);

    public native String transe_string(String str);

    public native int[] transeIntArray(int[] intArray);

    //    这个方法要去调用C代码
    public native void callBackMethodVoid();

    //  我是java实现的方法，等待C里的callBackMethodVoid方法回调我
    public void helloFromJava() {
        Log.e("tag", "hello from java");
    }


    //    这个方法要去调用C代码
    public native int callBackMethodInt();

    //  我是java实现的方法，等待C里的callBackMethodInt方法回调我
    public int addInJava(int x, int y) {
        return x + y;
    }


    //    这个方法要去调用C代码

    public native void callbackMethodString();


    //我是java实现的方法，等待C里的callbackMethodString方法回调我
    // C 是无法直接打印log的，我们可以，通过此方法，在C里打印log（当然在C里通过配置也是可以打印log的，以后再说）
    public void printString(String s) {
        Log.e("tag", s);
    }




    //    这个方法要去调用C代码

    public native void callbackMethodToast();

    //我是java实现的方法，等待C里的callbackMethodToast方法回调我
    public void toast(String s) {
        Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();

    }


}

