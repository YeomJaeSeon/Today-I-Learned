package 그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * 1: 익은 토마토
 * 0: 익지 않은 토마토
 * -1: 토마토 없음
 *
 * M: 가로
 * N: 세로
 */

public class Main7576 {
    static int M, N;
    static int[][] board;
    static Queue<int[]> completeQueue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        initBoard(br);

        bfs();

        int result = getMaxValueFromBoard();
        System.out.println(result);
    }

    private static int getMaxValueFromBoard() {
        boolean isNotComplete = false;
        int max = -1;

        outer:
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                max = Math.max(max, board[i][j]);
                if(board[i][j] == 0){
                    isNotComplete = true;
                    break outer;
                }
            }
        }

        if(isNotComplete){
            return -1;
        }else{
            return max - 1;
        }
    }

    static void bfs(){
        while (!completeQueue.isEmpty()){
            int[] curr = completeQueue.poll();
            int currX = curr[0];
            int currY = curr[1];

            for(int i = 0; i < 4; i++){
                int nextX = dx[i] + currX;
                int nextY = dy[i] + currY;
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M){
                    continue;
                }

                if(board[nextX][nextY] == 0){
                    board[nextX][nextY] = board[currX][currY] + 1;
                    completeQueue.offer(new int[]{nextX, nextY});
                }

            }
        }
    }

    static void initBoard(BufferedReader br) throws IOException {
        board = new int[N][M];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int tomatoInfo = Integer.parseInt(st.nextToken());
                if(tomatoInfo == 1){
                    completeQueue.offer(new int[]{i, j});
                }
                board[i][j] = tomatoInfo;
            }
        }
    }
    static void printBoard(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
