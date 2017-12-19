import java.util.*;
public class Palindrome {
	public static void main(String[]args) {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter a word:");
		String entry=input.nextLine();
		if(isPalindrome(entry))
			System.out.println("it is a palindrome");
		else
			System.out.println("it is not a palindrome");
	}
	public static boolean isPalindrome(String s) {
		if (s.length() < 2) {
			return true; // base case
		} else {
			char first = s.charAt(0);
			char last = s.charAt(s.length() - 1);
			if (first != last) {
				return false;
			} // recursive case
			String middle = s.substring(1, s.length() - 1);
			return isPalindrome(middle);
		}
	}
}