public class Float2 {

	public static void main(String[] args) {
		Float f1 = 30.33f;
		Float f2 = 30.33f;
		Float f3 = new Float(30.33f);

		System.out.println("f1 == f2 : " + (f1 == f2));
		System.out.println("f1 == f3 : " + (f1 == f3));

		System.out.println("\nf1.equals(f2) :" + f1.equals(f2));
		System.out.println("f1.equals(f3) :" + f1.equals(f3) + "\n");

		System.out.println(System.identityHashCode(f1));
		System.out.println(System.identityHashCode(f2));
		System.out.println(System.identityHashCode(f3));

		f1 = f2;

		System.out.println("\nf1 == f2 : " + (f1 == f2));

		float primf1 = f1;
		float primf2 = f2;
		Float primf3 = f3;

		System.out.println("\nprintf " + primf1);

	}

}
