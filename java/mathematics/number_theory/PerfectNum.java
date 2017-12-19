import java.util.*;
public class PerfectNum {
	public static void main(String[]args){
		Scanner entry=new Scanner(System.in);
		System.out.println("Enter a Number!");
		int num=entry.nextInt();
		if(isPerfect(num))
			System.out.println("Your number is perfect");
		else
			System.out.println("Your number is not perfect");
		System.out.println("The next least perfect number is "+findNextPerfect(num));
	}
	public static boolean isPerfect(int n) {
		int sum=0;
		int divisor=1;
		while(divisor<n) {
			if(n%divisor==0)
				sum+=divisor;
			divisor++;
		}
		return sum==n;
	}
	public static int findNextPerfect(int n) {
		do{
			n++;
		}while(!isPerfect(n));
		return n;
	}
}