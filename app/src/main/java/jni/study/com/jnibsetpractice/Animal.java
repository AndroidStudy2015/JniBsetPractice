package jni.study.com.jnibsetpractice;

import android.util.Log;

/**
 * 描述当前版本功能
 *
 * @Project: JniBsetPractice
 * @author: cjx
 * @date: 2019-06-16 17:15  星期日
 */
public class Animal {


    public String say(String s) {
        Log.e("tag", s);
        return s;
    }
}
