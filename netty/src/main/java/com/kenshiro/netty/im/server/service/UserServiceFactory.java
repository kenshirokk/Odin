package com.kenshiro.netty.im.server.service;

public class UserServiceFactory {

    private static final UserService USER_SERVICE;
    static {
        USER_SERVICE = new UserServiceMemoryImpl();
    }

    public static UserService getUserService() {
        return USER_SERVICE;
    }
}
