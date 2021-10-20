package Algorithm.sol.boj_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Samsung_13460_MarbleEscape {
    static int answer;
    static final int answerInit = Integer.MAX_VALUE >> 1;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        int rRow = 0;
        int rCol = 0;
        int bRow = 0;
        int bCol = 0;
        char[][] map = new char[N][M];
        answer = answerInit;

        String inputStr = "";
        for (int i = 0; i < N; i++) {
            inputStr = "";
            inputStr = br.readLine();
//            while (inputStr.length() == 0) {
//                inputStr = br.readLine();
//                System.out.println(inputStr);
//            }
            for (int j = 0; j < M; j++) {
                map[i][j] = inputStr.charAt(j);
                if (map[i][j] == 'B') {
                    bRow = i;
                    bCol = j;
                } else if (map[i][j] == 'R') {
                    rRow = i;
                    rCol = j;
                }

            }
        }
        process(map, rRow, rCol, bRow, bCol, 1);

        System.out.println(answer);

    }

    private static void process(char[][] map, int rRow, int rCol, int bRow, int bCol, int cnt) {
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println("---------------------");
//        System.out.println();
        if (cnt >= 11) {
            if (answer == answerInit) {
                answer = -1;
            }
            return;
        }
        if (cnt >= answer && answer != -1) {
            return;
        }

        int rRowCopy, rColCopy, bRowCopy = 0, bColCopy = 0;
        char[][] copyMap = new char[N][M];

        int plusConst = 0;
        boolean inline = false;
        boolean escape = false;
        boolean isPossible = true;
        for (int d = 0; d < 4; d++) {
//            System.out.println("d : " + d + " cnt : " + cnt + " answer : " + answer);
            plusConst = 1;
            inline = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }
            escape = false;
            isPossible = true;
            while (copyMap[rRow + dir[d][0] * plusConst][rCol + dir[d][1] * plusConst] != '#') {
                if (copyMap[rRow + dir[d][0] * plusConst][rCol + dir[d][1] * plusConst] == 'B') {
                    inline = true;
                } else if (copyMap[rRow + dir[d][0] * plusConst][rCol + dir[d][1] * plusConst] == 'O') {
                    if (inline) {
                        isPossible = false;
                        break;
                    } else {
                        escape = true;
                    }
                }
                plusConst++;
            }
            if (isPossible) {
                if (inline) {
                    bRowCopy = rRow + dir[d][0] * (plusConst - 1);
                    bColCopy = rCol + dir[d][1] * (plusConst - 1);
                    rRowCopy = rRow + dir[d][0] * (plusConst - 2);
                    rColCopy = rCol + dir[d][1] * (plusConst - 2);
                    copyMap[rRow][rCol] = '.';
                    copyMap[bRow][bCol] = '.';
                    copyMap[bRowCopy][bColCopy] = 'B';
                    copyMap[rRowCopy][rColCopy] = 'R';
                } else {
                    rRowCopy = rRow + dir[d][0] * (plusConst - 1);
                    rColCopy = rCol + dir[d][1] * (plusConst - 1);
                    copyMap[rRow][rCol] = '.';
                    copyMap[rRowCopy][rColCopy] = 'R';
                    if (escape) {
                        copyMap[rRowCopy][rColCopy] = 'O';
                    }

                    plusConst = 1;

                    while (copyMap[bRow + dir[d][0] * plusConst][bCol + dir[d][1] * plusConst] != '#'
                            && copyMap[bRow + dir[d][0] * plusConst][bCol + dir[d][1] * plusConst] != 'R') {
                        if (copyMap[bRow + dir[d][0] * plusConst][bCol + dir[d][1] * plusConst] == 'O') {
                            copyMap[rRowCopy][rColCopy] = '.';
                            copyMap[rRow][rCol] = 'R';
                            isPossible = false;
                            break;
                        }
                        plusConst++;
                    }

                    if (isPossible) {
                        bRowCopy = bRow + dir[d][0] * (plusConst - 1);
                        bColCopy = bCol + dir[d][1] * (plusConst - 1);
                        copyMap[bRow][bCol] = '.';
                        copyMap[bRowCopy][bColCopy] = 'B';
                    }
                }

                if (isPossible) {
                    if (escape) {
                        if (answer == -1) {
                            answer = cnt;
                        } else {
                            answer = Math.min(answer, cnt);
//                            System.out.println(answer);
                        }
                        return;
                    }

                    process(copyMap, rRowCopy, rColCopy, bRowCopy, bColCopy, cnt + 1);
                }
            }
        }

    }
}
