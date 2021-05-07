package sol.pgmrs;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PGMRS_LandMove {

	static final int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] grpInfo;
	static int N;
	static int numb;
	static int[] parent;
	static PriorityQueue<LandMoveEdge> pq;
	static int answer = 0;

	public static void main(String[] args) {
		int[][] land = { { 1, 4, 8, 10 }, { 5, 5, 5, 5 }, { 10, 10, 10, 10 }, { 10, 10, 10, 20 } };
		int height = 3;
		System.out.println(solution(land, height));
	}

	private static int solution(int[][] land, int height) {
		N = land[0].length;
		grpInfo = new int[N][N];
		pq = new PriorityQueue<LandMoveEdge>();

		grouping(land, height);
		findEdge(land);
		Kruskal();
		return answer;
	}

	private static void Kruskal() {
		parent = new int[numb + 1];
		for (int i = 1; i <= numb; i++) {
			parent[i] = i;
		}
		int v1, v2, cost;
		int eCnt = 0;
		LandMoveEdge e;
		while (eCnt != numb-2) {
			e = pq.poll();
			v1 = e.v1;
			v2 = e.v2;
			cost = e.cost;
			if (checkAndUnion(v1, v2)) {
				answer += cost;
				eCnt++;
			}
		}
	}

	private static boolean checkAndUnion(int v1, int v2) {
		int p1, p2;
		p1 = find(v1);
		p2 = find(v2);
		
		if (p1 == p2) {
			return false;
		}
		
		if (p1 < p2) {
			parent[p2] = p1;
		}else {
			parent[p1] = p2;
		}
		
		return true;
	}

	private static void union(int v1, int v2) {
		if (v2 < v1) {
			parent[v2] = v1;
		} else {
			parent[v1] = v2;
		}
	}

	private static int find(int v) {
		if (parent[v] == v) {
			return v;
		}
		parent[v] = find(parent[v]);
		return parent[v];
	}

	private static void findEdge(int[][] land) {
		int tmpR, tmpC;
		int v1, v2, dist;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d < dir.length; d++) {
					tmpR = i + dir[d][0];
					tmpC = j + dir[d][1];
					if (isPossible(tmpR, tmpC)) {
						v1 = grpInfo[i][j];
						v2 = grpInfo[tmpR][tmpC];
						if (v1 != v2) {
							dist = Math.abs(land[tmpR][tmpC] - land[i][j]);
							pq.offer(new LandMoveEdge(v1, v2, dist));
						}
					}
				}
			}
		}
	}

	private static void grouping(int[][] land, int height) {
		numb = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grpInfo[i][j] == 0) {
					bfs(land, height, i, j, numb++);
				}
			}
		}
	}

	private static void bfs(int[][] land, int height, int r, int c, int n) {
		Queue<Integer> nodeQ = new LinkedList<Integer>();
		nodeQ.offer(r * 301 + c);
		int curr, size, tmpR, tmpC;
		grpInfo[r][c] = n;

		while (!nodeQ.isEmpty()) {
			size = nodeQ.size();
			for (int i = 0; i < size; i++) {
				curr = nodeQ.poll();
				r = curr / 301;
				c = curr % 301;
				for (int d = 0; d < dir.length; d++) {
					tmpR = r + dir[d][0];
					tmpC = c + dir[d][1];
					if (isPossible(tmpR, tmpC) && grpInfo[tmpR][tmpC] == 0) {
						if (Math.abs(land[tmpR][tmpC] - land[r][c]) <= height) {
							nodeQ.offer(tmpR * 301 + tmpC);
							grpInfo[tmpR][tmpC] = n;
						}
					}
				}
			}
		}
	}

	private static boolean isPossible(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}

}

class LandMoveEdge implements Comparable<LandMoveEdge> {
	public int v1;
	public int v2;
	public int cost;

	public LandMoveEdge(int v1, int v2, int cost) {
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}

	@Override
	public int compareTo(LandMoveEdge o) {
		return this.cost - o.cost;
	}
}
