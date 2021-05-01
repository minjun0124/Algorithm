package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663_Memo {

	public static int[] memo;
	public static int N;
	public static int ans = 0;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		memo = new int[N];
 
		dfs(0);
		System.out.println(ans);
 
	}
 
	public static void dfs(int qNumbs) {
		// 모든 queen 배치 완료 / 다음 케이스를 위해 return 
		if (qNumbs == N) {
			ans++;
			return;
		}
 
		for (int i = 0; i < N; i++) {
			memo[qNumbs] = i;
			// 배치할 수 있다면 배치 후 다음 배치를 위해 재귀
			if (isPossible(qNumbs)) {
				dfs(qNumbs + 1);
			}
		}
 
	}
 
	public static boolean isPossible(int col) {
		for (int i = 0; i < col; i++) {
			// 같은 행에 존재할 경우 
			if (memo[col] == memo[i]) {
				return false;
			}
			// 대각선에 놓여있는 경우
			else if (Math.abs(col - i) == Math.abs(memo[col] - memo[i])) {
				return false;
			}
		}
		
		return true;
	}
}
