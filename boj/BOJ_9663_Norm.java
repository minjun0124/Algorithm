package baekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9663_Norm {

	static int N, ans;
	static int[][] map;
	static int[] dr = { 0, -1, -1, -1 };
	static int[] dc = { -1, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		ans = 0;
		dfs(0, 0);
		System.out.println(ans);
	}

	private static void dfs(int row, int qNumbs) {
		// 모든 queen 배치 완료 / 다음 케이스를 위해 return 
		if (qNumbs == N) {
			ans++;
			return;
		}
		// 모든 행 탐색 완료 return
		if (row == N) {
			return;
		}
		// row행에 대한 열 검사 
		for (int c = 0; c < N; c++) {
			if (isPossible(row, c)) {
				set_queen(row, c);
				dfs(row + 1, qNumbs + 1);
				del_queen(row, c);
			}
		}
	}

	// 배치 가능성 검사
	private static boolean isPossible(int r, int c) {
		// 위협 가능한 Queen이 배치되어 있는지 방향을 바꿔가며 검사
		for (int i = 0; i < 4; i++) {
			if (checkInline(r, c, dr[i], dc[i])) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkInline(int r, int c, int dir_r, int dir_c) {
		while (true) {
			if (isIn(r, c)) {
				if (map[r][c] != 0) {
					return true;
				}
			} else {
				return false;
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

	private static void del_queen(int r, int c) {
		map[r][c] = 0;
		return;
	}

	private static void set_queen(int r, int c) {
		map[r][c] = 1;
		return;
	}

}
