package base.math;

import java.util.Arrays;

/**
 * 
 * Permutation with repetition (nπr = n^r)
 * 
 */
public class PermutationDuple {

	static int n, r, c;
	static int[] rslt;

	public static void main(String[] args) {
		n = 5;
		r = 3;
		c = 0;
		rslt = new int[r];
		permuDupl(0);
		System.out.println("total : " + c);
	}

	private static void permuDupl(int cnt) {
		if (cnt == r) {
			c++;
			System.out.println(Arrays.toString(rslt));
			return;
		}

		for (int i = 1; i <= n; i++) {
			rslt[cnt] = i;
			permuDupl(cnt + 1);
		}
	}

}
