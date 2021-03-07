
public class Long2 {

	public static void main(String[] args) {
		Long l1 = (long)100000000;
		Long l2 = (long)100000000;
		Long l3 = new Long (100000000);

		System.out.println("l1 == l2 : " + (l1 == l2));
		System.out.println("l1 == l3 : " + (l1 == l3));

		System.out.println("\nf1.equals(l2) :" + l1.equals(l2));
		System.out.println("f1.equals(l3) :" + l1.equals(l3) + "\n");

		System.out.println(System.identityHashCode(l1));
		System.out.println(System.identityHashCode(l2));
		System.out.println(System.identityHashCode(l3));

		l1 = l2;

		System.out.println("\nf1 == f2 : " + (l1 == l2));

		Long primf1 = l1;
		Long primf2 = l2;
		Long primf3 = l3;

		System.out.println("\nprintf " + primf1);

	}

}
