package Algorithm.base.search;

public class DepthFirstSearch {

	static int[][] adj;
	static boolean[] visited;
	static int n;

	public static void main(String[] args) {
		n = 10;
		adj = new int[n + 1][n + 1];
		visited = new boolean[n + 1];
		dfs(0);
	}

	private static void dfs(int x) {
		visited[x] = true;

		for (int i = 1; i <= n; i++) {
			if (adj[x][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}

}
