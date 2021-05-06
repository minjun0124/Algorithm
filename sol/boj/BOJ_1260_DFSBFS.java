package sol.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_DFSBFS {
	static int N, M, V;
	static List<Integer> adj[];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		paramInit();
		dfs(V);
		System.out.println();
		bfs(V);
	}

	private static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		for (Integer next : adj[v]) {
			if (!visited[next]) {
				dfs(next);
			}
		}
	}

	private static void bfs(int v) {
		visited = new boolean[N + 1];
		Queue<Integer> store = new LinkedList<Integer>();
		visited[v] = true;

		store.offer(v);

		int curr;
		while (!store.isEmpty()) {
			curr = store.poll();
			System.out.print(curr + " ");
			for (Integer next : adj[curr]) {
				if (!visited[next]) {
					visited[next] = true;
					store.offer(next);
				}
			}
		}

	}

	private static void paramInit() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		V = Integer.parseInt(tokens.nextToken());
		visited = new boolean[N + 1];
		adj = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		int s, e;
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			s = Integer.parseInt(tokens.nextToken());
			e = Integer.parseInt(tokens.nextToken());
			adj[s].add(e);
			adj[e].add(s);
		}
		for (int i = 1; i <= N; i++) {
			Collections.sort(adj[i]);
		}
	}

}
