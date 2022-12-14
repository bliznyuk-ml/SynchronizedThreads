package org.example;

import java.io.*;

/**
 * Створити 10 обчислювальних потоків (Thread), що повинні працювати одночасно.
 * Кожен потік буде зчитувати порцію даних з потоку даних InputStream, і записувати в OutputStream.
 * Задача полягає в тому, щоб усі потоки працювали по черзі.
 * Для цього застосовуйте синхронізацію або через synchronized або за допомогою ReentrantLock
 * <p>
 * Продемонструвати роботу потоків на прикладі копіювання великого текстового файлу, наприклад, alica.txt.
 * <p>
 * Вихідний файл повинен мати той самий зміст, що і оригінальний.
 */


public class App {

    public static void main(String[] args) throws IOException, FileNotFoundException {

        try (
                InputStream in = new FileInputStream("alice.txt");
                OutputStream out = new FileOutputStream("alice_copy.txt");
        ) {
            ReWriter[] threads = new ReWriter[10];
            for (int i = 0; i < 10; i++) {
                threads[i] = new ReWriter("ReWriter #" + i, in, out);
            }

            for (ReWriter th : threads) {
                th.start();
            }

            for (ReWriter th : threads) {
                try {
                    th.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
