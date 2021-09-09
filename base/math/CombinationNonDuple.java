package Algorithm.base.math;

import java.util.Arrays;

/**
 * 
 * Combination (nCr = n!/((n-r)!r!))
 * 
 */
public class CombinationNonDuple {

	static int n, r, c;
	static int[] rslt;

	public static void main(String[] args) {
		n = 5;
		r = 3;
		c = 0;
		rslt = new int[r];
		combNonDupl(1, 0);
		System.out.println("total : " + c);
	}

	private static void combNonDupl(int start, int cnt) {
		if (cnt == r) {
			c++;
			System.out.println(Arrays.toString(rslt));
			return;
		}

		for (int i = start; i <= n; i++) {
			rslt[cnt] = i;
			combNonDupl(i + 1, cnt + 1);
		}
	}

}
