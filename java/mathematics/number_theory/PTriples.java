import java.util.*;
public class PTriples {
	public static void main(String []args) {
		System.out.println("\nWelcome!!!");
		Scanner triangle=new Scanner(System.in);
		System.out.println("\nPlease type 2 integers to find a pythagorean triple.");
		int a=triangle.nextInt();
		int b=triangle.nextInt();
		int x=2*a*b;
		int y=Math.abs(a*a-b*b);
		int z=a*a+b*b;//neat little trick
		System.out.println("\nA possible set of pythagorean tripes are "+x+", "+y+", and "+z+".");
		System.out.println("so "+x+"^2+"+y+"^2="+z+"^2.");
		boolean wow=(x*x+y*y==z*z);//this will be true.
		System.out.println("This is "+wow+".");
	}
}