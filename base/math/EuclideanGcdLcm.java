package base.math;

public class EuclideanGcdLcm {

	public static void main(String[] args) {
		int n1 = 12;
		int n2 = 15;
		int gcdRet, lcmRet;
		gcdRet = gcd(n1, n2);
		lcmRet = lcm(n1, n2);

		System.out.println("GCD : " + gcdRet);
		System.out.println("LCM : " + lcmRet);

	}

	private static int lcm(int n1, int n2) {
		return n1 * n2 / gcd(n1, n2);
	}

	private static int gcd(int n1, int n2) {
		int r;
		while (n2 != 0) {
			r = n1 % n2;
			n1 = n2;
			n2 = r;
		}
		return n1;
	}

}
