package Algorithm.base.math;

import java.util.Arrays;

/**
 * 
 * Permutation (nPr = n!/(n-r)!)
 * 
 */
public class PermutationNonDuple {

	static int n, r, c;
	static int[] rslt;
	static boolean[] visited;

	public static void main(String[] args) {
		n = 5;
		r = 3;
		c = 0;
		rslt = new int[r];
		visited = new boolean[n+1];
		permuNonDupl(0);
		System.out.println("total : " + c);
	}

	private static void permuNonDupl(int cnt) {
		if (cnt == r) {
			c++;
			System.out.println(Arrays.toString(rslt));
			return;
		}

		for (int i = 1; i <= n; i++) {
			rslt[cnt] = i;
			if (!visited[i]) {
				visited[i] = true;
				permuNonDupl(cnt + 1);
				visited[i] = false;
			}
		}
	}

}