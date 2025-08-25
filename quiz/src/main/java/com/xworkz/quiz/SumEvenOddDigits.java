package com.xworkz.quiz;

public class SumEvenOddDigits {
    public static void main(String[] args) {
        int num = 58342;  // test number
        int evenSum = 0, oddSum = 0, temp = num;

        while (temp > 0) {
            int digit = temp % 10;
            if (digit % 2 == 0) {
                evenSum += digit;
            } else {
                oddSum += digit;
            }
            temp /= 10;
        }

        System.out.println("Number: " + num);
        System.out.println("Sum of Even digits = " + evenSum);
        System.out.println("Sum of Odd digits = " + oddSum);
    }
}
