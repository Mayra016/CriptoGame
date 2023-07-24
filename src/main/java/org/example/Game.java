package org.example;
import com.sun.source.tree.SwitchExpressionTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Game {
    final String word;
    String total_sum;
    String vocals_sum;
    String consonants_sum;
    final String underscores;
    final Character firstLetter;
    final Character thirdLetter;
    final Character lastLetter;
    boolean data_basis;

    // Possible notes to select randomly each party
    Hashtable<Character, Integer> note1 = new Hashtable<>();
    Hashtable<Character, Integer> note2 = new Hashtable<>();
    Hashtable<Character, Integer> note3 = new Hashtable<>();
    Hashtable<Character, Integer> note4 = new Hashtable<>();
    Hashtable<Character, Integer> note5 = new Hashtable<>();

    Hashtable<Character, Integer> active_note = new Hashtable<>(); // Contains the value reference for this party

    public Game() {
        this.word = "table";
        this.data_basis = setData();
        this.vocals_sum = getVocals_sum();
        this.total_sum = getTotal_sum();
        this.consonants_sum = getConsonants_num();
        this.active_note = getActive_note();
        this.underscores = getUnderscores();
        this.firstLetter = this.word.charAt(0);
        this.thirdLetter = this.word.charAt(2);
        this.lastLetter = this.word.charAt(this.word.length()-1);
    }

    private boolean setData() {
        Character[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int[] values2 = {2, 5, 7, 13, 21, 324, 27, 9, 36, 6, 15, 14, 25, 4, 10, 1, 12, 26, 9, 21, 23, 18, 3, 20, 16, 29};
        int[] values3 = {5, 27, 2, 6, 23, 10, 4, 22, 13, 28, 8, 17, 27, 15, 11, 19, 9, 1, 6, 25, 3, 23, 14, 12, 21, 0, 7};
        int[] values4 = {22, 25, 5, 2, 3, 26, 19, 4, 27, 4, 17, 12, 14, 21, 1, 24, 11, 18, 26, 0, 15, 13, 25, 12, 7, 20, 6};
        int[] values5 = {11, 6, 21, 2, 8, 12, 10, 13, 5, 16, 7, 17, 15, 20, 22, 3, 18, 9, 1, 4, 25, 14, 19, 27, 26, 23, 24};

        // Insert note 1 values
        for (int i = 0; i <= 25; i++) {
            this.note1.put(alphabet[i], i + 1);
        }

        // Insert note 2 values
        for (int i = 0; i <= 25; i++) {
            this.note2.put(alphabet[i], values2[i]);
        }

        // Insert note 3 values
        for (int i = 0; i <= 25; i++) {
            this.note3.put(alphabet[i], values3[i]);
        }

        // Insert note 4 values
        for (int i = 0; i <= 25; i++) {
            this.note4.put(alphabet[i], values4[i]);
        }

        // Insert note 5 values
        for (int i = 0; i <= 25; i++) {
            this.note5.put(alphabet[i], values5[i]);
        }

        return true;
    }

    // Sum each word comparing it with the active note values
    private String getTotal_sum() {
        Integer sum2 = 0;
        Integer value2 = 0;
        Integer response = 0;
        String message = "__";
        for (int i = 0; i <= (this.word.length() - 1); i++) {
            Character letter2 = Character.valueOf(this.word.toUpperCase().charAt(i));
            /*
            if (this.active_note.get(letter2).equals(0) || this.active_note.get(letter2).equals(null)){
                continue;
            }
            else {
                value2 = this.active_note.get(letter2).intValue();
                sum2 = sum2 + value2;
            }*/

            try {

                response = this.active_note.get(letter2);
                if (response.equals(0) || response.equals(null)){
                }
                else {
                    sum2 = sum2 + response;
                }

            }
            catch (Exception e){
                message = " Catch";

                System.out.print(e);

            }

        }

        return "The sum of all letters is " + sum2 + message;
    }

    // Compare each character of the word with the table and sum the vocals.
    public String getVocals_sum() {
        int sum3 = 0;
        Character[] vocals_list = {'A', 'E', 'I', 'O', 'U'};
        for (int i = 0; i <= (this.word.length() - 1); i++) {
            Character letter3 = Character.valueOf(this.word.charAt(i));
            boolean test = Arrays.asList(vocals_list).contains(letter3);
            if (test) {
                int value3 = this.active_note.get(Character.toUpperCase(letter3)).intValue();
                sum3 = sum3 + value3;
                System.out.println("sum3" + Integer.toString(sum3));
                System.out.println("value3" + Integer.toString(value3));

            }

        }

        return "Vocals sum is equals "+ Integer.toString(sum3);
    }

    // Get the numbers of underscores needed to write the word
    private String getUnderscores() {
        int length_word = this.word.length() - 1;
        String underscore = new String();
        char under = '_';
        for (int i = 0; i <= length_word; i++) {
            if (i == 0) {
                underscore = "_";
            }
            else {
                underscore = underscore.concat(Character.toString(under));
            }
        }
        return underscore;
    }

    // Select randomly a note for each level played
    private Hashtable getActive_note() {
        Random generator = new Random();
        int index = generator.nextInt(0, 3);

        if (index == 0) {
            this.active_note = note1;
        }
        else if (index == 1) {
            this.active_note = note2;
        }
        else if (index == 2) {
            this.active_note = note3;
        }
        else {
            this.active_note = note4;
        }
        return this.active_note;
    }

    // If the character isn't a vocal, then the method will look for its value in the active note
    private String getConsonants_num() {
        int sum1 = 0;
        Character[] vocals = {'A', 'E', 'I', 'O', 'U'};
        for (int i = 0; i <= (this.word.length() - 1); i++) {
            Character letter = this.word.toUpperCase().charAt(i);
            boolean test = Arrays.asList(vocals).contains(letter);
            if (test = false) {
                int value = this.active_note.get(letter);
                sum1 = sum1 + value;
            }
        }

        return "The consonants sum " + sum1;
    }
}