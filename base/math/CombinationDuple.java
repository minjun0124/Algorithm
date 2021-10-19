package Algorithm.base.math;

import java.util.Arrays;

/**
 * 
 * Combination with Repetition (nHr = (n+r-1)Cr)
 * 
 */
public class CombinationDuple {

	static int n, r, c;
	static int[] rslt;

	public static void main(String[] args) {
		n = 5;
		r = 3;
		c = 0;
		rslt = new int[r];
		combDupl(1, 0);
		System.out.println("total : " + c);
	}

	private static void combDupl(int start, int cnt) {
		if (cnt == r) {
			c++;
			System.out.println(Arrays.toString(rslt));
			return;
		}

		for (int i = start; i <= n; i++) {
			rslt[cnt] = i;
			combDupl(i, cnt + 1);
		}
	}

}
