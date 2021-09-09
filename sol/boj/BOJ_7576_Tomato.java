package Algorithm.sol.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_Tomato {
	static int[][] tBox;
	static int ans, M, N, immature;
	static final int maxLen = 1001;
	static Queue<Integer> matureQ;

	public static void main(String[] args) throws IOException {
		paramInit();
		bfs();
		printAns();
	}

	private static void bfs() {
		if (immature == 0)
			return;

		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		int size;
		int tomato, r, c, tmpR, tmpC;
		ans--;
		while (!matureQ.isEmpty()) {
			ans++;
			size = matureQ.size();
			for (int i = 0; i < size; i++) {
				tomato = matureQ.poll();
				r = tomato / maxLen;
				c = tomato % maxLen;
				for (int j = 0; j < dir.length; j++) {
					tmpR = r + dir[j][0];
					tmpC = c + dir[j][1];
					if (isImmature(tmpR, tmpC)) {
						tBox[tmpR][tmpC] = 1;
						matureQ.offer(tmpR * maxLen + tmpC);
						immature--;
					}
				}
			}
		}
	}

	private static boolean isImmature(int tmpR, int tmpC) {
		if (tmpR >= 0 && tmpR < N && tmpC >= 0 && tmpC < M && tBox[tmpR][tmpC] == 0) {
			return true;
		}
		return false;
	}

	private static void printAns() {
		if (immature != 0) {
			ans = -1;
		}
		System.out.println(ans);
	}

	private static void paramInit() throws IOException {
		ans = 0;
		immature = 0;
		matureQ = new LinkedList<Integer>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());

		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		tBox = new int[N][M];

		int input = 0;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				input = Integer.parseInt(tokens.nextToken());
				tBox[i][j] = input;
				if (input == 0) {
					immature++;
				} else if (input == 1) {
					matureQ.offer(i * maxLen + j);
				}
			}
		}
	}

}
