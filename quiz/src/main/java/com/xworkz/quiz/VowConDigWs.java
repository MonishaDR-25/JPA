package com.xworkz.quiz;

public class VowConDigWs {
    public static void main(String[] args) {
        String str = "Java is a object oriented programming language.jueilkewvqc    FROLDJ2WANHYQfgtp9oiu3jw2aqhk   ;PLKJ G1ELKJ3V OL.MK4E";
        int vowels = 0, consonants = 0, digits = 0, spaces = 0;

        str = str.toLowerCase();

        for (char ch : str.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                if ("aeiou".indexOf(ch) != -1)
                    vowels++;
                else
                    consonants++;
            } else if (ch >= '0' && ch <= '9') {
                digits++;
            } else if (ch == ' ') {
                spaces++;
            }
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Digits: " + digits);
        System.out.println("Whitespaces: " + spaces);
    }
}
