package org.example;

import java.io.*;

public class MyReader {

    static String str = "";
    static char[] buffer;
    static int count;

    public static void reWriter() {
        try (Reader rdr = new FileReader("alice.txt");
             Writer wr = new FileWriter("alice_new.txt")) {
            char[] buffer = new char[1024];
            while ((count = rdr.read(buffer)) > 0) {
//                str = new String(buffer, 0, count);
//                System.out.print(str);
                wr.write(buffer, 0, count);
            }
            System.out.print(str);
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }


}
