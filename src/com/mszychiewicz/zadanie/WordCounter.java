package com.mszychiewicz.zadanie;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) throws IOException {
        final String text = readFile("zadanie.txt", Charset.defaultCharset());
        final var wordsMap = countWordOccurrences(text);
        printResults(wordsMap);
    }

    private static Map<String, List<Integer>> countWordOccurrences(String text) {
        final String[] words = text.split("\\s");
        final Map<String, List<Integer>> wordsMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            if (!wordsMap.containsKey(word)) {
                wordsMap.put(word, new ArrayList<>(Arrays.asList(i + 1)));
            } else {
                wordsMap.get(word).add(i + 1);
            }
        }
        return wordsMap;
    }

    private static void printResults(Map<String, List<Integer>> wordsMap) {
        final Set<String> sortedWords = new TreeSet<>(wordsMap.keySet());
        for (String word : sortedWords) {
            System.out.println(word + " -> " + wordsMap.get(word).toString());
        }
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
