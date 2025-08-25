package com.xworkz.quiz;

public class LargestSmallestDigit {
    public static void main(String[] args) {
        int num = 58342;  // test number
        int largest = 0, smallest = 9, temp = num;

        while (temp > 0) {
            int digit = temp % 10;
            if (digit > largest) largest = digit;
            if (digit < smallest) smallest = digit;
            temp /= 10;
        }

        System.out.println("Number: " + num);
        System.out.println("Largest digit = " + largest);
        System.out.println("Smallest digit = " + smallest);
    }
}
