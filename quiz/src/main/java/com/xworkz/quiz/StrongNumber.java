package com.xworkz.quiz;

import static com.xworkz.quiz.FactorialRecursion.factorial;

public class StrongNumber {

    public static void main(String[] args) {
        int num = 145;  // test number
        int sum = 0, temp = num;

        while (temp > 0) {
            int digit = temp % 10;
            sum += factorial(digit);
            temp /= 10;
        }

        if (sum == num) {
            System.out.println(num + " is a Strong number.");
        } else {
            System.out.println(num + " is NOT a Strong number.");
        }
    }
}
