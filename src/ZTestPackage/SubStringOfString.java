package ZTestPackage;

import java.util.Scanner;

public class SubStringOfString {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the string for which you want substring: ");
		String s = in.next();

		int l = s.length();	
		String sub = "";
		for (int i = 0; i < l; i++) 
		{
			for (int j = i; j < l; j++) 
			{
				sub = s.substring(i, j + 1);
				System.out.println(sub);
			}
		}
	}
}