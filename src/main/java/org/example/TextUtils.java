package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
    public static final String WORD_PATTERN = "[a-zA-Z]+";
    private final char symbol = '_';
    private final char foundSymbol = '^';

    static void printDirection(int count, int wordLength) {
        StringBuilder sb1 = new StringBuilder();
//        for (int i = 0; i < line; i++) {
//            sb1.append(" ");
//        }
        for (int i = 0; i < count; i++) {
            char symbol = '_';
            sb1.append(symbol);
        }
        for (int i = 0; i < wordLength; i++) {
            char foundSymbol = '^';
            sb1.append(foundSymbol);
        }
        System.out.println(sb1);
    }

    static void findWordInFile(String mod, BufferedReader filescan, String word) {
        checkWordIsValid(word);
        String line;
        int linePosition = 1;
        while (true) {
            linePosition += 1;
            try {
                if ((line = filescan.readLine()) == null) break;
                else if(mod == "part"){
                    findWordInLine(linePosition, line, word);
                }
                else if(mod == "ci"){
                    findWordInLinebyChar(line, word);
                }
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void findWordInLinebyChar(String fileLine, String word) {
        int matches = 0;
        Matcher matcher = Pattern.compile("\\b" + word + "\\b").matcher(fileLine);
        while (matcher.find()) System.out.println(fileLine);
        int position = fileLine.indexOf(word);
        printDirection(position, word.length());
    }

    static void findWordInLine(int lineNumber, String fileLine, String word) {
//        String cleared = fileLine.replaceAll("\\s+","");
        if (fileLine.contains(word)) {
            System.out.println(lineNumber + fileLine);
            int position = fileLine.indexOf(word);
            printDirection(position, word.length());
        }
    }

    static void checkWordIsValid(String wordToCheck) throws IllegalArgumentException {
        if (Objects.equals(wordToCheck, "") || (!wordToCheck.matches(WORD_PATTERN)))
            throw new IllegalArgumentException("Please enter a valid word!");
    }
}
