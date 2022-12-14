package org.example;

import java.io.*;

public class ReWriter extends Thread{

    private final InputStream in;
    private final OutputStream out;

    public static final int VALUE = 1024;

    public ReWriter(String name, InputStream in, OutputStream out) {
        super(name);
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        while (true){
            byte [] buffer = new byte[VALUE];
            synchronized (in) {
                try {
                    int leng = in.read(buffer);
                    if (leng > 0) {
                        out.write(buffer, 0, leng);
                        System.out.println("Thread " + getName() + " writes " + leng + " bytes");
                    } else {
                        break;
                    }
                } catch (IOException e) {
                    break;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
