import java.util.Scanner;

public class SwapNumbers {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        System.out.println("Enter first number:");
        int a=sc.nextInt();

        System.out.println("Enter a second number");
        int b=sc.nextInt();

        System.out.println("Numbers before Swapping are: a="+a+",b="+b);

        //swapping without using third variable
        a=a+b;
        b=a-b;
        a=a-b;

        System.out.println("After Swapping: a="+a+",b="+b);
    }
}
