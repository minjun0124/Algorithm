package sol.boj;

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
		// ��� queen ��ġ �Ϸ� / ���� ���̽��� ���� return 
		if (qNumbs == N) {
			ans++;
			return;
		}
 
		for (int i = 0; i < N; i++) {
			memo[qNumbs] = i;
			// ��ġ�� �� �ִٸ� ��ġ �� ���� ��ġ�� ���� ���
			if (isPossible(qNumbs)) {
				dfs(qNumbs + 1);
			}
		}
 
	}
 
	public static boolean isPossible(int col) {
		for (int i = 0; i < col; i++) {
			// ���� �࿡ ������ ��� 
			if (memo[col] == memo[i]) {
				return false;
			}
			// �밢���� �����ִ� ���
			else if (Math.abs(col - i) == Math.abs(memo[col] - memo[i])) {
				return false;
			}
		}
		
		return true;
	}
}
