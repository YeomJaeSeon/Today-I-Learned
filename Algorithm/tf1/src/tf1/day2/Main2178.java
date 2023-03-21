package tf1.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2178 {
    static int N, M;
    static int[][] board;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        System.out.println(bfs(0, 0));
    }
    static int bfs(int startX, int startY){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY});
        board[startX][startY]++;

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];

            for(int i = 0; i < 4; i++){
                int nextX = currX + dx[i];
                int nextY = currY + dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M){
                    continue;
                }
                if(board[nextX][nextY] != 1){
                    continue;
                }
                board[nextX][nextY] = board[currX][currY] + 1;
                q.offer(new int[]{nextX, nextY});
            }
        }

        return board[N - 1][M - 1] - 1;
    }
}
