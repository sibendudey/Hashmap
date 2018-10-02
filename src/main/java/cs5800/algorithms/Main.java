package cs5800.algorithms;

import cs5800.algorithms.table.HashMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    private static final String FILENAME = "src\\main\\resources/alice_in_wonderland.txt";

    public static void main(String args[]) {
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());
        String data = getText();
        String words[] = data.split(" ");
        //String words[] = data.split("([\".,\\/#!$%\\?\\^&\\*;:{}=_`\'~()\\[\\]])( *)([\".,\\/#!$%\\?\\^&\\*;:{}=_`\'~()\\[\\]])");
        final HashMap<String, Integer> count = new HashMap<>();
        Arrays.stream(words).forEach(word -> {
            try {
                Integer prevValue = count.find(word);
                count.insert(word, prevValue == null ? 1 : prevValue + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Print the counts

        count.listAllKeys().forEach( key -> {
            try {
                System.out.printf("Word: %s, Count: %d \n", key, count.find(key));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static String getText() {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(FILENAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
