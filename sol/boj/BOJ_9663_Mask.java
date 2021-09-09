package Algorithm.sol.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9663_Mask {

	static int N, ans;
	static int[][] map;
	static int[] dr = { 0, 1, 1, 1};
	static int[] dc = { 1, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		ans = 0;
		dfs(0, 0);
		System.out.println(ans);
	}

	private static void dfs(int r, int qNumbs) {
		// ��� queen ��ġ �Ϸ� / ���� ���̽��� ���� return 
		if (qNumbs == N) {
			ans++;
			return;
		}
		// ��� �� Ž�� �Ϸ� return
		if (r == N) {
			return;
		}
		for (int c = 0; c < N; c++) {
			if (isPossible(r, c)) {
				set_queen(r, c);
				dfs(r + 1, qNumbs + 1);
				del_queen(r, c);
			}
		}
	}
	
	// ��ġ ���ɼ� �˻�
	private static boolean isPossible(int r, int c) {
		if (map[r][c] == 0) {
			return true;
		}
		return false;
	}

	private static void set_queen(int r, int c) {
		// Queen�� ���� ������ ����ŷ
		for (int i = 0; i < 4; i++) {
			masking(r, c, dr[i], dc[i]);
		}
	}

	private static void del_queen(int r, int c) {
		// ����ŷ ����
		for (int i = 0; i < 4; i++) {
			demasking(r, c, dr[i], dc[i]);
		}
	}

	private static void demasking(int r, int c, int dir_r, int dir_c) {
		while (true) {
			if (isIn(r, c)) {
				map[r][c]--;
			} else {
				return;
			}
			r += dir_r;
			c += dir_c;
		}
	}

	private static void masking(int r, int c, int dir_r, int dir_c) {
		while (true) {
			if (isIn(r, c)) {
				map[r][c]++;
			} else {
				return;
			}
			r += dir_r;
			c += dir_c;
		}
	}

	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}
}
