package com.unisinsight.framework.uuv.common.utils;

public class UserHandler {
    private static ThreadLocal<User> userCode = new ThreadLocal<>();

    public static void set(User code) {
        userCode.set(code);
    }

    public static User get(){
        return userCode.get();
    }

    public static String userCode(){
        if (userCode.get() == null){
            return null;
        }
        return userCode.get().getUserCode();
    }

    public static void remove(){
        userCode.remove();
    }
}
