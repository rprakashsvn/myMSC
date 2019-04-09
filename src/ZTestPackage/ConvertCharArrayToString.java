package ZTestPackage;

public class ConvertCharArrayToString {

	public static void main(String[] args) {

		/* Declare an array. */
		char chaArrObj[] = { 'i', ' ', 'l', 'o', 'v', 'e', ' ', 'j', 'a', 'v', 'a' };
		//char newCharArray[] = { 'P', 'R', 'A', 'K', 'A', 'S', 'H' };

		// ConvertCharArrayToString.charArrryToString1(chaArrObj);

		ConvertCharArrayToString.charArrryToString2(chaArrObj);

		// ConvertCharArrayToString.charArrryToString3(chaArrObj);

	}

	/* Use constructor to create directly from the array. */
	public static String charArrryToString1(char chaArr[]) {
		String retStr = new String(chaArr);
		System.out.println(retStr);
		return retStr;
	}

	/* Use StringBuffer. */
	public static String charArrryToString2(char chaArr[]) {
		/* Declare a StringBuffer obect. */
		StringBuffer strBuf = new StringBuffer();

		if (chaArr != null) {
			/*
			 * Loop in the array, append each char to the StringBuffer object. Because only
			 * one StringBuffer object is created, so this method will cost less.
			 */
			int len = chaArr.length;
			System.out.println(len);
			for (int i = 0; i < len; i++) {
				char cha = chaArr[i];
				strBuf.append(cha);
			}
		}

		/* Translate strBuf object to retStrObj. */
		String retStrObj = strBuf.toString();
		System.out.println(retStrObj);

		return retStrObj;
	}

	/* Use "+" operator. */
	public static String charArrryToString3(char chaArr[]) {
		/* Declare return variable. */
		String ret = "";
		if (chaArr != null) {
			/* Loop in the array. */
			int len = chaArr.length;
			for (int i = 0; i < len; i++) {
				/*
				 * Concat the char to the return object, each operation will crate a new String
				 * object.
				 */
				char cha = chaArr[i];
				/*
				 * Because each "+" action will create a new object, so this method will cost
				 * more memory and time.
				 */
				ret += cha;
			}
		}

		System.out.println(ret);

		return ret;
	}
}