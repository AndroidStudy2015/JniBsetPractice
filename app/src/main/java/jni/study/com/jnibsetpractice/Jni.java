package jni.study.com.jnibsetpractice;

import android.util.Log;

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
}
