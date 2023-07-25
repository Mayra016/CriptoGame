package org.example;

/*
Classes: Menu, Game and Buttons

 */

public class Main {
    public static void main(String[] args) {
        Game party = new Game();
        int sum2 = 0;
        int value = 1;
        for (int i = 0; i <= (party.normal_word.length() - 1); i++) {
            Character letter = Character.valueOf(party.normal_word.toUpperCase().charAt(i));
            System.out.println(letter);
            value = party.active_note.get(letter);
            System.out.println(value);
            if (party.active_note.get(letter).equals(0) || party.active_note.get(letter).equals(null)) {
                continue;
            } else {
                value = party.active_note.get(letter).intValue();
                sum2 = sum2 + value;
            }

        }
        System.out.println(party.word);
        System.out.println(party.normal_word);
        System.out.println("Hola");
        System.out.println("Hello world!");
    }

}