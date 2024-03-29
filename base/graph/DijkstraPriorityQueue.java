package Algorithm.base.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraPriorityQueue {

    public static void main(String[] args) {
        GraphDijkstraPQ g = new GraphDijkstraPQ(8);
        g.input(0, 1, 3);
        g.input(0, 4, 4);
        g.input(0, 3, 4);
        g.input(1, 2, 2);
        g.input(2, 3, 1);
        g.input(3, 4, 2);
        g.input(4, 5, 4);
        g.input(3, 6, 6);
        g.input(6, 5, 3);
        g.input(2, 7, 3);
        g.input(5, 7, 2);
        g.dijkstra(0);
    }

}

class GraphDijkstraPQ {
    private int n; // 노드들의 수
    // List<Edge>가 원소인 adj 배열 선언
    private List<Edge> adj[];

    public GraphDijkstraPQ(int n) {
        this.n = n;
        this.adj = new List[n + 1];
        this.adjInit();
    }

    private void adjInit() {
        for (int i = 1; i <= n; i++) {
            this.adj[i] = new ArrayList<Edge>();
        }
    }

    // 무방향 간선
    public void input(int u, int v, int w) {
        this.adj[u].add(new Edge(v, w));
        this.adj[v].add(new Edge(u, w));
    }

    public void dijkstra(int start) {
        int distance[] = new int[n]; // 최단 거리를 저장할 변수
        boolean[] visited = new boolean[n]; // 해당 노드를 방문했는지 체크할 변수

        // distance값 초기화.
        Arrays.fill(distance, Integer.MAX_VALUE >> 1);

        // 시작노드값 초기화.
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int d = curr.dest;

            if (!visited[d]) {
                visited[d] = true;
                for (Edge next : adj[d]) {
                    if (distance[next.dest] >= distance[d] + next.weight) {
                        distance[next.dest] = distance[d] + next.weight;
                        pq.offer(next);
                    }
                }
            }
        }

        // 결과값 출력
        for (int i = 0; i < n; i++) {
            System.out.print(distance[i] + " ");
        }
    }

    private static class Edge implements Comparable<Edge> {
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        // compareTo 는 새로 들어온 값에 대해 return 값이 양수일 때, 순서를 바꾼다.(우선순위를 올린다.)
        // 따라서 기존(this)보다 큰 값이 들어왔을 때, 우선 순위를 올리고 싶다? -> return o - this (내림차순)
        // 반대로 작은 값이 들어왔을 떄, 우선 순위를 올리고 싶다? -> return this - o (오름차순)
        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }
}
