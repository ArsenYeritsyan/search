package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
    private static final String WORD_PATTERN = "[a-zA-Z]+";

    public TextUtils() {System.out.println("           --- Welcome ---");}

    private void printDirection(int lineNumber,int count, int wordLength) {
        final StringBuilder sb1 = new StringBuilder();
//        for (int i = lineNumber; i > 1; i %= 10) {
//            sb1.append(' ');
//        }
        for (int i = 0; i < count; i++) {
            char symbol = '-';
            sb1.append(symbol);
        }
        for (int i = 0; i < wordLength; i++) {
            char foundSymbol = '^';
            sb1.append(foundSymbol);
        }
        System.out.println(sb1);
    }

   void findWordInFile(String mod, BufferedReader filescan, String word) {
        checkWordIsValid(word);
        String line;
        int linePosition = 1;
        while (true) {
            linePosition += 1;
            try {
                if ((line = filescan.readLine()) == null) break;
                else if(Objects.equals(mod, "part")){
                    findWordInLine(linePosition, line, word);
                }
                else if(mod.equals("ci")){
                    findWordInLinebyChar(linePosition,line, word);
                }
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

   private void findWordInLinebyChar(int lineNumber,String fileLine, String word) {
        Matcher matcher = Pattern.compile("\\b" + word + "\\b").matcher(fileLine);
        while (matcher.find()) System.out.println(fileLine);
        int position = fileLine.indexOf(word);
        printDirection(lineNumber,position, word.length());
    }

    private void findWordInLine(int lineNumber, String fileLine, String word) {
        if (fileLine.contains(word)) {
            System.out.println(lineNumber + fileLine);
            int position = fileLine.indexOf(word);
            printDirection(lineNumber, position, word.length());
        }
    }

    private void checkWordIsValid(String wordToCheck) throws IllegalArgumentException {
        if (Objects.equals(wordToCheck, "") || (!wordToCheck.matches(WORD_PATTERN)))
            throw new IllegalArgumentException("Please enter a valid word!");
    }
}
