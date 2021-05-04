package sol.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779_gerry {
	static int N, Ans, x, y, d1, d2, sTotal;
	static int map[][];
	static int[] sumArr;
	static int[] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(tokens.nextToken().trim());
		map = new int[N][N];
		sumArr = new int[5];
		distance = new int[2];
		Ans = Integer.MAX_VALUE >> 1;

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken().trim());
				sTotal += map[i][j];
			}
		}
		xySearch();
		System.out.println(Ans);
	}

	private static void xySearch() {
		for (x = 0; x < N - 2; x++) {
			for (y = 1; y < N - 1; y++) {
				selectD(0);
			}
		}
	}

	private static void selectD(int cnt) {
		if (cnt == 2) {
			if (isPossible(distance[0], distance[1])) {
				d1 = distance[0];
				d2 = distance[1];
				sumOfPlace();
				getMinDiff();
			}
			return;
		}
		for (int i = 1; i < N; i++) {
			distance[cnt] = i;
			selectD(cnt + 1);
		}
	}

	private static void getMinDiff() {
		Arrays.sort(sumArr);
		Ans = Math.min(Ans, sumArr[4] - sumArr[0]);
	}

	private static void sumOfPlace() {
		sumOfPlaceInit();
		sumOfPlaceOne(x, y, d1);
		sumOfPlaceTwo(x, y, d2);
		sumOfPlaceThree(x, y, d1, d2);
		sumOfPlaceFour(x, y, d1, d2);
		sumOfPlaceFive();
	}

	private static void sumOfPlaceInit() {
		for (int i = 0; i < 5; i++) {
			sumArr[i] = 0;
		}
	}

	private static boolean isPossible(int dOne, int dTwo) {
		if ((x + dOne + dTwo < N) && (y - dOne >= 0) && (y + dTwo < N)) {
			return true;
		}
		return false;
	}

	private static void sumOfPlaceOne(int r, int c, int dOne) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j <= c; j++) {
				sumArr[0] += map[i][j];
			}
		}
		for (int i = r; i < r + dOne; i++) {
			for (int j = 0; j < c; j++) {
				sumArr[0] += map[i][j];
			}
			c--;
		}
		return;
	}

	private static void sumOfPlaceTwo(int r, int c, int dTwo) {
		for (int i = 0; i < r; i++) {
			for (int j = c + 1; j < N; j++) {
				sumArr[1] += map[i][j];
			}
		}
		for (int i = r; i <= r + dTwo; i++) {
			for (int j = c + 1; j < N; j++) {
				sumArr[1] += map[i][j];
			}
			c++;
		}
		return;
	}

	private static void sumOfPlaceThree(int r, int c, int dOne, int dTwo) {
		for (int i = N - 1; i > r + dOne + dTwo; i--) {
			for (int j = c - dOne + dTwo -1; j >= 0; j--) {
				sumArr[2] += map[i][j];
			}
		}
		for (int i = r + dOne + dTwo; i >= r + dOne; i--) {
			for (int j = c - dOne + dTwo - 1; j >= 0; j--) {
				sumArr[2] += map[i][j];
			}
			c--;
		}
		return;
	}

	private static void sumOfPlaceFour(int r, int c, int dOne, int dTwo) {
		for (int i = N - 1; i > r + dOne + dTwo; i--) {
			for (int j = c - dOne + dTwo; j < N; j++) {
				sumArr[3] += map[i][j];
			}
		}
		for (int i = r + dOne + dTwo; i > r + dTwo; i--) {
			for (int j = c - dOne + dTwo + 1; j < N; j++) {
				sumArr[3] += map[i][j];
			}
			c++;
		}
		return;
	}

	private static void sumOfPlaceFive() {
		sumArr[4] = sTotal - (sumArr[0] + sumArr[1] + sumArr[2] + sumArr[3]);
		return;
	}

}
