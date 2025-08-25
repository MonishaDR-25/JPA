package com.xworkz.quiz;

public class PrimeNumbersRange {
    public static void main(String[] args) {
        int n = 50;  // print primes till 50
        System.out.print("Prime numbers between 1 and " + n + ": ");

        for (int num = 2; num <= n; num++) {
            boolean isPrime = true;
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(num + " ");
            }
        }
    }
}
