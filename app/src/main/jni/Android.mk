LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := best
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	/Users/apple/AndroidStudioProjects/GoodDemo/JniBsetPractice/app/src/main/jni/best.c \

LOCAL_C_INCLUDES += /Users/apple/AndroidStudioProjects/GoodDemo/JniBsetPractice/app/src/debug/jni
LOCAL_C_INCLUDES += /Users/apple/AndroidStudioProjects/GoodDemo/JniBsetPractice/app/src/main/jni
LOCAL_LDLIBS += -llog
include $(BUILD_SHARED_LIBRARY)
