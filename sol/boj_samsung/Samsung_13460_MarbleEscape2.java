package Algorithm.sol.boj_samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Samsung_13460_MarbleEscape2 {
    static int n , m;
    static char map[][];
    static boolean visited[][][][]; // red , blue
    static int target[];
    static int red[];
    static int blue[];
    static int dy[] = {-1,0,1,0};
    static int dx[] = {0,1,0,-1};
    static int[] move(int nowy, int nowx , int oy, int ox , int dir) {
        int ny = nowy + dy[dir];
        int nx = nowx + dx[dir];

        while(ny >=0 && ny < n && nx >= 0 && nx < m  && (map[ny][nx] == '.' || map[ny][nx] == 'O' ) ) {
            if((map[oy][ox] != 'O') && (ny == oy && nx == ox)) {
                break;
            }
            if(map[ny][nx] == 'O') {
                ny += dy[dir];
                nx += dx[dir];
                break;
            }
            ny += dy[dir];
            nx += dx[dir];
        }
        ny -= dy[dir];
        nx -= dx[dir];
        return new int[] {ny,nx};
    }
    static int bfs() {
        int cnt = 0 ;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {red[0],red[1],blue[0],blue[1]});
        visited = new boolean[n][m][n][m];
        visited[red[0]][red[1]][blue[0]][blue[1]] = true;
        while(!q.isEmpty()) {
            int size = q.size();
            cnt++;
            if(cnt > 10) break;
            for(int s = 0 ; s < size ; s++) {
                int [] now = q.poll();
                int ry = now[0];
                int rx = now[1];
                int by = now[2];
                int bx = now[3];
                //		System.out.printf("%d %d %d %d \n",ry,rx,by,bx);
                for(int i = 0 ; i < 4 ; i++) {
                    int[] nRed;
                    int[] nBlue;
                    if(i == 0) {
                        if(ry <= by) {
                            nRed = move(ry,rx,by,bx, i);
                            nBlue = move(by,bx,nRed[0],nRed[1],i);
                        }else {
                            nBlue = move(by,bx,ry,rx,i);
                            nRed = move(ry, rx, nBlue[0], nBlue[1], i);
                        }
                    }else if(i == 1) {
                        if(rx >= bx) {
                            nRed = move(ry,rx,by,bx, i);
                            nBlue = move(by,bx,nRed[0],nRed[1],i);
                        }else {
                            nBlue = move(by,bx,ry,rx,i);
                            nRed = move(ry, rx, nBlue[0], nBlue[1], i);
                        }
                    }else if(i == 2) {
                        if(ry >= by) {
                            nRed = move(ry,rx,by,bx, i);
                            nBlue = move(by,bx,nRed[0],nRed[1],i);
                        }else {
                            nBlue = move(by,bx,ry,rx,i);
                            nRed = move(ry, rx, nBlue[0], nBlue[1], i);
                        }
                    }else {
                        if(rx <= bx) {
                            nRed = move(ry,rx,by,bx, i);
                            nBlue = move(by,bx,nRed[0],nRed[1],i);
                        }else {
                            nBlue = move(by,bx,ry,rx,i);
                            nRed = move(ry, rx, nBlue[0], nBlue[1], i);
                        }
                    }
                    //			System.out.printf("%d %d %d %d \n",nRed[0],nRed[1],nBlue[0],nBlue[1]);
                    if(nRed[0] == target[0] && nRed[1] == target[1] &&
                            (nBlue[0] != target[0] || nBlue[1] != target[1]))
                        return cnt;
                    if(nBlue[0] == target[0] && nBlue[1] == target[1]) {
                        continue;
                    }
                    if(!visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]]) {
                        visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]] = true;
                        q.add(new int[] {nRed[0],nRed[1],nBlue[0],nBlue[1]});
                    }
                }
            }

        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for(int i = 0 ; i < n ; i++) {
            String command = br.readLine();
            for(int j = 0 ; j < m ; j++) {
                map[i][j] = command.charAt(j);
                if(map[i][j] == 'R') {
                    red = new int[] {i,j};
                    map[i][j] = '.';
                }
                if(map[i][j] == 'B') {
                    blue = new int[] {i,j};
                    map[i][j] = '.';
                }
                if(map[i][j] == 'O') {
                    target = new int[] {i,j};
                }
            }
        }

        //	System.out.println(Arrays.deepToString(map));
        System.out.println(bfs());
    }
}
