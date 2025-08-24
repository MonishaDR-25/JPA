package com.xworkz.quiz;

public class GCDLCM {
    public static void main(String[] args) {
        int a = 12, b = 18;

        // Find GCD
        int gcd = 1;
        for (int i = 1; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }

        // Find LCM
        int lcm = (a * b) / gcd;

        System.out.println("GCD of " + a + " and " + b + " = " + gcd);
        System.out.println("LCM of " + a + " and " + b + " = " + lcm);
    }
}
