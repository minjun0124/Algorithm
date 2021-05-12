package sol.pgmrs;

public class PGMRS_NormSquareGCD {

	public static void main(String[] args) {
		int w = 21;
		int h = 28;
		System.out.println(solution(w, h));
	}

	private static long solution(int w, int h) {
		long answer = 0;
		long gcd = (long)calGcd(w, h);
		long wl = (long)w;
		long hl = (long)h;
		answer = (wl * hl) - (wl + hl) + gcd;
		return answer;
	}

	private static int calGcd(int w, int h) {
		if (w % h == 0) {
			return h;
		}

		return calGcd(h, w % h);
	}

}
