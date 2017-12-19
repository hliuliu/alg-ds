import java.util.*;
public class Prime {
	public static void main (String[]args) {
		Scanner test=new Scanner(System.in);
		System.out.println("Enter a whole number.");
		int num=test.nextInt();
		if (isPrime(num))
			System.out.println("your number is prime.");
		else
			System.out.println("your number is not prime.");
		factorize(num);
	}
	public static boolean isPrime(int num) {
		int divisor=2;
		if (num<2)
			return false;
		while(divisor<num) {
			if (num%divisor==0)
				return false;
			divisor++;
		}
		return true;
	}
	public static void factorize(int num) {
		System.out.print(num+"=");
		int divisor=2;
		while(divisor<num) {
			if (num%divisor==0) {
				num/=divisor;
				System.out.print(divisor+"*");
				divisor=1;
			}
			divisor++;
		}
		if(num==divisor)
			System.out.print(divisor);
	}
}