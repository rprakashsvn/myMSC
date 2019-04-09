package ZTestPackage;

import java.io.IOException;

public class StringBasics {

	public static void main(String[] args) throws IOException {
		StringBasics.StringBufferConcepts();
		/*StringBasics.StringConcepts();
		StringBasics.StringBufferAddNewValues();*/
	}

	public static void StringConcepts() {
		System.out.println("String Concepts");
		String name1 = new String("Prakash");
		String name2 = new String("Prakash");
		System.out.println(System.identityHashCode(name1));
		System.out.println(System.identityHashCode(name2));
		System.out.println();
		
		String name3 = "Prakash";
		String name4 = "Prakash";
		System.out.println(System.identityHashCode(name3));
		System.out.println(System.identityHashCode(name4));
		String str3 = new String("Selenium-webdriver.com");
		String str4 = new String("Selenium-webdriver.com");
		System.out.println(System.identityHashCode(str3));
		System.out.println(System.identityHashCode(str4));
	}

	public static void StringBufferConcepts() {
		System.out.println("StringBuffer Concepts");
		StringBuffer name1 = new StringBuffer("Prakash");
		StringBuffer name2 = new StringBuffer("Pandian");
		System.out.println(name1.hashCode());
		System.out.println(name2.hashCode());
		System.out.println();
		System.out.println(name1.capacity());
	}

	public static void StringBufferAddNewValues() {
		System.out.println("String Buffer AddNew Values");
		StringBuffer name1 = new StringBuffer("Prakash");
		System.out.println("Before APPEND : " + name1);
		name1.append("Pandian");
		System.out.println("After  APPEND : " + name1);
		name1.append(" Rajaram");
		System.out.println("After  APPEND : " + name1);
		name1.append(10);
		System.out.println("After  APPEND : " + name1);
		name1.append(false);
		System.out.println("After  APPEND : " + name1);
		System.out.println("Length Of String : " + name1.length());
		name1.delete(22, 29);
		System.out.println("Delete  String : " + name1);

	}
}

