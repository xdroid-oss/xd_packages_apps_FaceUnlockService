LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := FaceUnlockServiceAndroidStudio
LOCAL_MODULE_CLASS := FAKE
LOCAL_MODULE_SUFFIX := -timestamp

faceunlock_android_framework_deps := $(call java-lib-deps,framework)

faceunlock_system_libs_path := $(abspath $(LOCAL_PATH))/app/system_libs

include $(BUILD_SYSTEM)/base_rules.mk

$(LOCAL_BUILT_MODULE): $(faceunlock_android_framework_deps)
	$(hide) mkdir -p $(faceunlock_system_libs_path)
	$(hide) rm -rf $(faceunlock_system_libs_path)/*.jar
	$(hide) cp $(faceunlock_android_framework_deps) $(faceunlock_system_libs_path)/framework.jar
	$(hide) echo "Fake: $@"
	$(hide) mkdir -p $(dir $@)
	$(hide) touch $@