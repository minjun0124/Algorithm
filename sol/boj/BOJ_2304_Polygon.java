package Algorithm.sol.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2304_Polygon {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokens.nextToken());
        int[] map = new int[1001];
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        int largest = Integer.MIN_VALUE;
        int largestIdx = 0;
        int L, H;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            L = Integer.parseInt(tokens.nextToken());
            H = Integer.parseInt(tokens.nextToken());
            start = Math.min(start, L);
            end = Math.max(end, L);
            if (H > largest) {
                largestIdx = L;
                largest = H;
            }
            map[L] = H;
        }

        int answer = largest;
        int cnt = 1;
        int maxValue = map[start];
        for (int i = start + 1; i <= largestIdx; i++) {
            if (map[i] >= maxValue) {
                answer += cnt * maxValue;
                maxValue = map[i];
                cnt = 1;
            } else {
                cnt++;
            }
        }

        cnt = 1;
        maxValue = map[end];
        for (int i = end - 1; i >= largestIdx; i--) {
            if (map[i] >= maxValue) {
                answer += cnt * maxValue;
                maxValue = map[i];
                cnt = 1;
            } else {
                cnt++;
            }
        }

        System.out.println(answer);

    }
}
