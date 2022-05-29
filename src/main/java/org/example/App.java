package org.example;

import java.io.*;

public class App {
    public static void main(String[] args) {

        String word = args[0];
        String fileName = args[1];
        String mod = args[2];



        BufferedReader reader = null;

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                throw new RuntimeException("File not found");
            }
            reader = new BufferedReader(new FileReader(file));
            TextUtils tu= new TextUtils();
            tu.findWordInFile(mod, reader, word);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

