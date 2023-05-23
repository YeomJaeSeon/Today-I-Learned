package 그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 무방향 Graph에서 u -> v로 가는 최소비용 구하려면 bfs를 이용하면 된다.
 */

public class Main2178 {
    static int N, M;
    static int[][] board;
    static BufferedReader br;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        initBoard();

        bfs();

        System.out.println(board[N - 1][M - 1]);
    }
    private static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];

            for(int i = 0; i < 4; i++){
                int nextX = currX + dx[i];
                int nextY = currY + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                // 1이면서 둘다 인덱스가 0이 아니면
                if(board[nextX][nextY] == 1 && !(nextX == 0 && nextY == 0)){
                    board[nextX][nextY] = board[currX][currY] + 1;
                    q.offer(new int[]{nextX, nextY});
                }
            }
        }
    }

    private static void initBoard() throws IOException {
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(str.charAt(j) + "");
            }
        }
    }
}
