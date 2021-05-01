package base.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {

	public static void main(String[] args) {
		GraphPrim g = new GraphPrim(8);
		g.input(1, 2, 3);
		g.input(1, 5, 4);
		g.input(1, 4, 4);
		g.input(2, 3, 2);
		g.input(3, 4, 1);
		g.input(4, 5, 2);
		g.input(5, 6, 4);
		g.input(4, 7, 6);
		g.input(7, 6, 3);
		g.input(3, 8, 3);
		g.input(6, 8, 2);
		g.prim(1);
	}

}

class GraphPrim {
	private int n;
	private List<EdgePrim> adj[];

	public GraphPrim(int n) {
		this.n = n;
		this.adj = new List[n + 1];
		this.adjInit();
	}

	private void adjInit() {
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<EdgePrim>();
		}
	}

	public void input(int i, int j, int k) {
		adj[i].add(new EdgePrim(j, k));
		adj[j].add(new EdgePrim(i, k));
	}

	public void prim(int start) {
		int sum = 0;
		boolean[] visited = new boolean[n + 1];
		PriorityQueue<EdgePrim> pq = new PriorityQueue<>();
		pq.offer(new EdgePrim(start, 0));

		while (!pq.isEmpty()) {
			EdgePrim curr = pq.poll();
			int d = curr.dest;

			if (!visited[d]) {
				visited[d] = true;
				sum += curr.weight;
				for (EdgePrim next : adj[d]) {
					if (!visited[next.dest]) {
						pq.offer(next);
					}
				}
			}
		}
		System.out.println(sum);
	}

}

class EdgePrim implements Comparable<EdgePrim> {
	public int dest;
	public int weight;

	public EdgePrim(int d, int w) {
		super();
		this.dest = d;
		this.weight = w;
	}

	@Override
	public int compareTo(EdgePrim e) {
		return this.weight - e.weight;
	}
}