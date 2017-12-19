import java.util.*;
public class RomanNumerals {
 public static void main(String[]args) {
  Scanner use=new Scanner(System.in);
  System.out.print("Enter a number to convert into a Roman Numeral: ");
  int num=use.nextInt();
  System.out.print("\nThe value is: ");
  printRomanNum(num);
 }
 public static void printRomanNum(int x) {
  int dif;
  while (x>0) {
   if (x>=1000) {
    dif=1000;
    System.out.print("M");
   }else if (x>=900) {
    dif=900;
    System.out.print("CM");
   }else if (x>=500) {
    dif=500;
    System.out.print("D");
   }else if (x>=400) {
    dif=400;
    System.out.print("CD");
   }else if (x>=100) {
    dif=100;
    System.out.print("C");
   }else if (x>=90) {
    dif=90;
    System.out.print("XC");
   }else if (x>=50) {
    dif=50;
    System.out.print("L");
   }else if (x>=40) {
    dif=40;
    System.out.print("XL");
   }else if (x>=10) {
    dif=10;
    System.out.print("X");
   }else if (x==9) {
    dif=9;
    System.out.print("IX");
   }else if (x>=5) {
    dif=5;
    System.out.print("V");
   }else if (x==4) {
    dif=4;
    System.out.print("IV");
   }else{
    dif=1;
    System.out.print("I");
   }
   x-=dif;
  }
  System.out.println();
 }
}