package base.math;

public class DisjointSet {
	static int[] parent = new int[11];

	public static void main(String[] args) {
		for (int i = 0; i < parent.length; i++) {
			makeSet(i);
		}
		printSet();
		union(1, 2);
		printSet();
		union(3, 2);
		printSet();
		union(3, 4);
		printSet();
		union(5, 6);
		printSet();
		union(6, 7);
		printSet();
		union(7, 8);
		printSet();
		union(9, 10);
		printSet();
	}

	private static void printSet() {
		System.out.print("index : ");
		for (int i = 1; i < parent.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		System.out.print("parent : ");
		for (int i = 1; i < parent.length; i++) {
			System.out.print(parent[i] + " ");
		}
		System.out.println();
		System.out.println("-----------------------------");
	}

	private static void makeSet(int x) {
		parent[x] = x;
	}

	private static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px < py) {
			parent[py] = px;
		} else {
			parent[px] = py;
		}
	}

	private static int findSet(int x) {
		if (x == parent[x]) {
			return x;
		}
		return findSet(parent[x]);
	}
}
