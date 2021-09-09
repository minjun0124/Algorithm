package Algorithm.sol.pgmrs.kakao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lotto {
    public static void main(String[] args) {
        int[] lottos = new int[]{44, 1, 0, 0, 31, 25};
        int[] winNumbs = new int[]{31, 10, 45, 1, 6, 19};
        System.out.println(Arrays.toString(solution(lottos, winNumbs)));
        lottos = new int[]{0, 0, 0, 0, 0, 0};
        winNumbs = new int[]{38, 19, 20, 40, 15, 25};
        System.out.println(Arrays.toString(solution(lottos, winNumbs)));
        lottos = new int[]{45, 4, 35, 20, 3, 9};
        winNumbs = new int[]{20, 9, 3, 45, 4, 35};
        System.out.println(Arrays.toString(solution(lottos, winNumbs)));

    }

    private static int[] solution(int[] lottos, int[] winNumbs) {
        int comp = 7;
        int sameCnt = 0;
        int zeroCnt = 0;

        Set<Integer> winNumbSet = new HashSet<>();
        for (int i = 0; i < winNumbs.length; i++) {
            winNumbSet.add(winNumbs[i]);
        }
        for (int i = 0; i < lottos.length; i++) {
            if (winNumbSet.contains(lottos[i])) {
                sameCnt++;
            }
            if (lottos[i] == 0) {
                zeroCnt++;
            }
        }

        int max = comp - (sameCnt + zeroCnt);
        int min = comp - sameCnt;

        int[] answer = {Math.min(max, 6), Math.min(min, 6)};

        return answer;
    }

}
