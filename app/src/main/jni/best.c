//
// Created by Apple on 2019/6/14.
//
#include <jni.h>

#include <stdlib.h>
# include "jni_study_com_jnibsetpractice_Jni.h"


#include <android/log.h>

#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

JNIEXPORT jstring JNICALL Java_jni_study_com_jnibsetpractice_Jni_sayHello
        (JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "Hello from C");
}


JNIEXPORT jint JNICALL Java_jni_study_com_jnibsetpractice_Jni_add
        (JNIEnv *env, jobject instance, jint x, jint y) {
    return x + y;
}


/**
 * 把一个jstring转换成一个c语言的char* 类型.
 */
char *_JString2CStr(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
    jclass clsstring = (*env)->FindClass(env, "java/lang/String");
    jstring strencode = (*env)->NewStringUTF(env, "GB2312");
    jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray) (*env)->CallObjectMethod(env, jstr, mid,
                                                            strencode); // String .getByte("GB2312");
    jsize alen = (*env)->GetArrayLength(env, barr);
    jbyte *ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
    if (alen > 0) {
        rtn = (char *) malloc(alen + 1); //"\0"
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    (*env)->ReleaseByteArrayElements(env, barr, ba, 0);
    return rtn;
}


JNIEXPORT jstring JNICALL Java_jni_study_com_jnibsetpractice_Jni_transe_1string
        (JNIEnv *env, jobject instance, jstring jstr) {

    //把一个jstring转换成一个c语言的char* 类型
    char *cStr = _JString2CStr(env, jstr);
    //c语言拼接字符串
    char *cNewStr = strcat(cStr, "简单加密一下哈哈哈!!!");
    // 把c语言里的char* 字符串转成java认识的字符串
    return (*env)->NewStringUTF(env, cNewStr);
}

JNIEXPORT jintArray JNICALL Java_jni_study_com_jnibsetpractice_Jni_transeIntArray
        (JNIEnv *env, jobject instance, jintArray jArray) {

//    得到从java传递来的int[]的长度
    jsize length = (*env)->GetArrayLength(env, jArray);
    LOGD("length = %d", length);

//    得到数组指针
    int *arrayPointer = (*env)->GetIntArrayElements(env, jArray, NULL);
//    开始遍历数组，把每个元素加100
    int i;
    for (i = 0; i < length; i++) {
        *(arrayPointer + i) += 100;
        LOGD("length = %d", *(arrayPointer + i));
    }
// ★★★★将arrayPointer这个int *中值复制到jArray数组中，别忘了这一步骤★★★
    (*env)->SetIntArrayRegion(env, jArray, 0, length, arrayPointer);

//    返回数组
    return jArray;
}


JNIEXPORT void JNICALL Java_jni_study_com_jnibsetpractice_Jni_callBackMethodVoid
        (JNIEnv *env, jobject instance) {

    // 1. 获取字节码对象

    jclass clazz = (*env)->FindClass(env, "jni/study/com/jnibsetpractice/Jni");

    // 2. 获取method方法的对象
    jmethodID methodID = (*env)->GetMethodID(env, clazz, "helloFromJava", "()V");
    // ★因为java方法有重载，所以只是靠方法名找不到这个唯一的函数，所以需要最后一个参数
    // 注意最后一个参数"()V",这个代表了方法的参数，这里无参数用()，V代表了方法的返回值，这里是void，所以是V

    //   ★★★★★★★★★★★重点理解  第二个参数 instance★★★★★★★★★★★★★
    // 3. 通过字节码获取Animal的实例，
    // 这里不要创建这个实例，因为上面的第二个参数 jobject instance，
    // 代表的就是调用该方法的对象
    // 在本例中是通过 jni.callBackMethodVoid()调用Java_jni_study_com_jnibsetpractice_Jni_callBackMethodVoid的
    //那么instance就是jni（Jni的对象）
    //   ★★★★★★★★★★★重点理解  第二个参数 instance★★★★★★★★★★★★★


    //  4.通过实例调用方法
    (*env)->CallVoidMethod(env, instance, methodID);


}


JNIEXPORT jint JNICALL Java_jni_study_com_jnibsetpractice_Jni_callBackMethodInt
        (JNIEnv *env, jobject instance) {


    // 1. 获取字节码对象

    jclass clazz = (*env)->FindClass(env, "jni/study/com/jnibsetpractice/Jni");

    // 2. 获取method方法的对象
    jmethodID methodID = (*env)->GetMethodID(env, clazz, "addInJava", "(II)I");

    // 3. 通过字节码获取调用者的实例，  这里不需要，因为就是instance

    // 4.通过实例调用方法
    jint add = (*env)->CallIntMethod(env, instance, methodID, 9, 9);

    return add;
}



