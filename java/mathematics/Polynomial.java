import java.util.*;
public class Polynomial {
	public static void main(String[]args) {
		Scanner poly=new Scanner(System.in);
		System.out.println("Welcome, please create your polynomial.");
		System.out.println("What is the degree of your polynomial?");
		int deg=poly.nextInt();
		int[]function=new int[deg+1];
		while(deg>=0) {
			System.out.print("Enter a integer coefficient for x^"+deg+". ");
			function[deg]=poly.nextInt();
			deg--;
		}
		int[]deriv=slope(function);
		int[]conc=slope(deriv);
		System.out.print("The function you entered is: ");
		printFunction(function);
		System.out.print("The first derivative is: ");
		printFunction(deriv);
		System.out.print("The second derivative is: ");
		printFunction(conc);
		String decide;
		do{
			System.out.print("Enter a value for x. ");
			double value=poly.nextDouble();
			System.out.print("at x="+value+" the function is: ");
			if(isPositive(function,value))
				System.out.print("positive");
			else if(isNegative(function,value))
				System.out.print("negative");
			else
				System.out.print("Equal to 0");
			System.out.print(", ");
			if(isPositive(deriv,value))
				System.out.print("increasing");
			else if(isNegative(deriv,value))
				System.out.print("decreasing");
			else
				System.out.print("horizontal");
			System.out.print(", and ");
			if(isPositive(conc,value))
				System.out.print("concave up");
			else if(isNegative(conc,value))
				System.out.print("concave down");
			else
				System.out.print("an inflection point");
			System.out.println(".");
			System.out.print("Enter another value for x? (yes[all lower case] to proceed, anything else to exit) ");
			decide=poly.next();
		}while(decide.equals("yes"));
	}
	public static int[]slope(int[]a) {
		if(a.length==1) {
			int[]d=new int[1];
			d[0]=0;
			return d;
		}
		int[]d=new int[a.length-1];
		for(int i=0;i<d.length;i++) {
			d[i]=a[i+1]*(i+1);
		}
		return d;
	}
	public static void printFunction(int[]a) {
		if(a.length==2)
			System.out.print(a[1]+"*X");
		else if(a.length==1)
			System.out.print(a[0]);
		else
			System.out.print(a[a.length-1]+"*X^"+(a.length-1));
		for(int i=a.length-2;i>=0;i--) {
			if(i>1) {
				if(a[i]>0)
					System.out.print("+"+a[i]+"*X^"+i);
				else if(a[i]<0)
					System.out.print("-"+(0-a[i])+"*X^"+i);
			}
			if(i==1) {
				if(a[i]>0)
					System.out.print("+"+a[i]+"*X");
				else if(a[i]<0)
					System.out.print("-"+(0-a[i])+"*X");
			}
			if(i==0) {
				if(a[i]>0)
					System.out.print("+"+a[i]);
				else if(a[i]<0)
					System.out.print("-"+(0-a[i]));
			}
		}
		System.out.println();
	}
	public static boolean isPositive(int[]a,double x) {
		double sum=0;
		for(int i=0;i<a.length;i++) {
			sum+=a[i]*Math.pow(x,i);
		}
		return sum>0;
	}
	public static boolean isNegative(int[]a,double x) {
		double sum=0;
		for(int i=0;i<a.length;i++) {
			sum+=a[i]*Math.pow(x,i);
		}
		return sum<0;
	}
}