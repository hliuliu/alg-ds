import java.util.*;
public class NewtonMethod {
	public static void main(String[]args) {
		Scanner nton=new Scanner(System.in);
		System.out.println("Welcome to newton's Method,please enter the vale that correspond to the function you want to estimate.");
		System.out.println("1 for square root.\n2 for square.");
		int entry=nton.nextInt();
		if(entry==1) {
			sqrtApprox(nton);
		}else if(entry==2) {
			squareApprox(nton);
		}
	}
	public static void sqrtApprox(Scanner root) {
		System.out.println("Enter a positive value to approximate it's square root");
		double a=root.nextDouble();
		System.out.print("Enter a guess value to approximate x^2-"+a+"=0. ");
		double x0=root.nextDouble();
		double x1=x0-((x0*x0-a)/2/x0);
		double x2=x1-((x1*x1-a)/2/x1);
		double x3=x2-((x2*x2-a)/2/x2);
		double x4=x3-((x3*x3-a)/2/x3);
		double x5=x4-((x4*x4-a)/2/x4);
		System.out.println("The first Newton's Approximation is: "+x1);
		System.out.println("The Second Newton's Approximation is: "+x2);
		System.out.println("The third Newton's Approximation is: "+x3);
		System.out.println("The fourth Newton's Approximation is: "+x4);
		System.out.println("The fifth Newton's Approximation is: "+x5);
		System.out.println("The actual value is: "+Math.sqrt(a));
	}
	public static void squareApprox(Scanner sq) {
		System.out.println("Enter a value to approximate it's square");
		double a=sq.nextDouble();
		a=Math.abs(a);
		System.out.print("Enter a guess value to approximate sqrt(x)-"+a+"=0. ");
		double x0=sq.nextDouble();
		double x1=x0-(Math.sqrt(x0)-a)*2*(Math.sqrt(x0));
		double x2=x1-(Math.sqrt(x1)-a)*2*(Math.sqrt(x1));
		double x3=x2-(Math.sqrt(x2)-a)*2*(Math.sqrt(x2));
		double x4=x3-(Math.sqrt(x3)-a)*2*(Math.sqrt(x3));
		double x5=x4-(Math.sqrt(x4)-a)*2*(Math.sqrt(x4));
		System.out.println("The first Newton's Approximation is: "+x1);
		System.out.println("The Second Newton's Approximation is: "+x2);
		System.out.println("The third Newton's Approximation is: "+x3);
		System.out.println("The fourth Newton's Approximation is: "+x4);
		System.out.println("The fifth Newton's Approximation is: "+x5);
		System.out.println("The actual value is: "+a*a);
	}
}