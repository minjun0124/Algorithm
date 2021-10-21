package Algorithm.sol.boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_21610_MagicianSharkAndRain {
    static int N;
    static int[][] map;
    static boolean[][] preCloud;
    static int[][] cmd;
    static int[][] dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] crossDir = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    static Queue<Cloud> cloudQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());
        cloudQueue = new LinkedList<>();

        map = new int[N][N];
        preCloud = new boolean[N][N];
        cmd = new int[M][2];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            cmd[i][0] = Integer.parseInt(tokens.nextToken());
            cmd[i][1] = Integer.parseInt(tokens.nextToken());
        }

        cloudQueue.offer(new Cloud(N - 1, 0));
        cloudQueue.offer(new Cloud(N - 1, 1));
        cloudQueue.offer(new Cloud(N - 2, 0));
        cloudQueue.offer(new Cloud(N - 2, 1));

        for (int i = 0; i < M; i++) {
            moveCloud(i);
            rain();
            copyWater();
            makeCloud();
        }

        int answer = calAnswer();

        System.out.println(answer);

    }

    private static int calAnswer() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += map[i][j];
            }
        }

        return cnt;
    }

    private static void makeCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!preCloud[i][j]) {
                    if (map[i][j] >= 2) {
                        cloudQueue.offer(new Cloud(i, j));
                        map[i][j] -= 2;
                    }
                } else {
                    preCloud[i][j] = false;
                }
            }
        }
    }

    private static void copyWater() {
        Cloud cloud;
        int cnt = 0;
        while (!cloudQueue.isEmpty()) {
            cloud = cloudQueue.poll();
            cnt = checkCross(cloud);
            map[cloud.r][cloud.c] += cnt;
        }
    }

    private static int checkCross(Cloud cloud) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (isWater(crossDir[i], cloud)) {
                cnt++;
            }
        }

        return cnt;
    }

    private static boolean isWater(int[] dir, Cloud cloud) {
        int r = cloud.r + dir[0];
        int c = cloud.c + dir[1];
        if (r >= 0 && r < N && c >= 0 && c < N && map[r][c] > 0) {
            return true;
        }
        return false;
    }

    private static void rain() {
        Cloud cloud;
        int size = cloudQueue.size();
        for (int i = 0; i < size; i++) {
            cloud = cloudQueue.poll();
            map[cloud.r][cloud.c]++;
            preCloud[cloud.r][cloud.c] = true;
            cloudQueue.offer(cloud);
        }
    }

    private static void moveCloud(int idx) {
        int d = cmd[idx][0] - 1;
        int inning = cmd[idx][1] % N;
        int size = cloudQueue.size();
        Cloud cloud;

        for (int i = 0; i < size; i++) {
            cloud = cloudQueue.poll();

            cloud.r += dir[d][0] * inning;
            cloud.c += dir[d][1] * inning;

            if (cloud.r > N - 1) {
                cloud.r -= N;
            } else if (cloud.r < 0) {
                cloud.r += N;
            }

            if (cloud.c > N - 1) {
                cloud.c -= N;
            } else if (cloud.c < 0) {
                cloud.c += N;
            }

//            System.out.println("r : " + cloud.r + " c : " + cloud.c);

            cloudQueue.offer(cloud);
        }
    }

    private static class Cloud {
        int r, c;

        public Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
