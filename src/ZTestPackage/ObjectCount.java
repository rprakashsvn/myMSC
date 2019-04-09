package ZTestPackage;

public class ObjectCount {

	static int numOfObj;

	ObjectCount() {
		numOfObj = numOfObj + 1;
		System.out.println("Total Number of object created: " + numOfObj);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ObjectCount obj1 = new ObjectCount();
		ObjectCount obj2 = new ObjectCount();
		ObjectCount obj3 = new ObjectCount();
	}
}