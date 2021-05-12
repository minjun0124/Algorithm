package sol.pgmrs;

public class PGMRS_NormSquare {

	public static void main(String[] args) {
		int w = 6;
		int h = 10;
		System.out.println(solution(w, h));
	}

	private static long solution(int w, int h) {
		long answer = 0;
		double angle = (double)h / (double)w;
		for (long i = 0; i < w; i++) {
			answer += (long)Math.floor(angle * i);
		}
		return answer * 2;
	}

}
