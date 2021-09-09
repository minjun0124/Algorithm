package Algorithm.base.graph;

import java.util.PriorityQueue;

public class Kruskal {
	public static void main(String[] args) {
		GraphKruskal g = new GraphKruskal(7);

		g.input(1, 4, 4);
		g.input(1, 2, 6);
		g.input(2, 3, 5);
		g.input(2, 4, 3);
		g.input(2, 5, 7);
		g.input(2, 6, 8);
		g.input(3, 6, 8);
		g.input(4, 5, 9);
		g.input(5, 6, 11);

		g.kruskal();
	}

}

class GraphKruskal {
	private int[] parent;
	private PriorityQueue<EdgeKruskal> edgeQ;

	public GraphKruskal(int n) {
		this.parent = new int[n];
		this.edgeQ = new PriorityQueue<EdgeKruskal>();
	}

	public void kruskal() {
		makeSet(parent.length);
		int sum = 0;
		while (!edgeQ.isEmpty()) {
			EdgeKruskal edge = edgeQ.poll();
			if (!isSameParent(edge.v1, edge.v2)) {
				sum += edge.cost;
				union(edge.v1, edge.v2);
			}
		}
		System.out.println(sum);
	}

	public void input(int v1, int v2, int w) {
		edgeQ.offer(new EdgeKruskal(v1, v2, w));
	}

	private void makeSet(int len) {
		for (int i = 0; i < len; i++) {
			parent[i] = i;
		}
	}

	private void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px < py) {
			parent[py] = px;
		} else {
			parent[px] = py;
		}
	}

	private int findSet(int x) {
		if (x == parent[x]) {
			return x;
		}
		parent[x] = findSet(parent[x]);
		return parent[x];
	}

	public boolean isSameParent(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x == y)
			return true;
		else
			return false;
	}
}

class EdgeKruskal implements Comparable<EdgeKruskal> {
	int v1;
	int v2;
	int cost;

	EdgeKruskal(int v1, int v2, int cost) {
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}

	@Override
	public int compareTo(EdgeKruskal o) {
		return this.cost - o.cost;
	}
}