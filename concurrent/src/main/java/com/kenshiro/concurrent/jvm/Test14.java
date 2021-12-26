package com.kenshiro.concurrent.jvm;

public class Test14 {

    public static void main(String[] args) {
        try (MyResource m = new MyResource()) {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyResource implements AutoCloseable {

    @Override
    public void close() throws Exception {
        throw new Exception("close异常");
    }
}
