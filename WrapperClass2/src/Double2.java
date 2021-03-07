
public class Double2 {

	public static void main(String[] args) {
		Double d1 = 40.44;
		Double d2 = 40.44;
		Double d3 = new Double (40.44);

		System.out.println("f1 == f2 : " + (d1 == d2));
		System.out.println("f1 == f3 : " + (d1 == d3));

		System.out.println("\nf1.equals(f2) :" + d1.equals(d2));
		System.out.println("f1.equals(f3) :" + d1.equals(d3) + "\n");

		System.out.println(System.identityHashCode(d1));
		System.out.println(System.identityHashCode(d2));
		System.out.println(System.identityHashCode(d3));

		d1 = d2;

		System.out.println("\nf1 == f2 : " + (d1 == d2));

		double primf1 = d1;
		double primf2 = d2;
		double primf3 = d3;

		System.out.println("\nprintf " + primf1);


	}

}
