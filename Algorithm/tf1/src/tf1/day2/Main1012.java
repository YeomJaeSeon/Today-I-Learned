package tf1.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1012 {
    static int[][] board;
    static int T;
    static int M, N, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new int[N][M];
            int answer = 0;

            for(int j = 0; j < K; j++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                board[x][y] = 1;
            }

            for(int j = 0; j < N; j++){
                for(int k = 0; k < M; k++){
                    if(bfs(j, k)){
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }
    static boolean bfs(int startX, int startY){
        if(board[startX][startY] == 0){
            return false;
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY});

        while(!q.isEmpty()){
            int[] curr = q.poll();

            int currX = curr[0];
            int currY = curr[1];
            for(int i = 0; i < 4; i++){
                int nextX = currX + dx[i];
                int nextY = currY + dy[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    continue;
                }
                if(board[nextX][nextY] == 0){
                    continue;
                }
                board[nextX][nextY] = 0;
                q.offer(new int[]{nextX, nextY});
            }
        }

        return true;
    }
}
