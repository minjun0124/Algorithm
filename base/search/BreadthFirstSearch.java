package base.search;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

	static int[][] adj;
	static int n;

	public static void main(String[] args) {
		n = 10;
		adj = new int[n + 1][n + 1];
		BFS(0);
	}

	private static void BFS(int v) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] check = new boolean[v + 1];
		int curr, size;
		check[v] = true;
		q.offer(v);

		while (!q.isEmpty()) {
			size = q.size();
			for (int i = 1; i <= size; i++) {
				curr = q.poll();
				for (int j = 1; j <= v; j++) {
					if (adj[curr][j] == 1 && !check[j]) {
						check[j] = true;
						q.offer(j);
					}
				}
			}
		}
	}

}
