package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17276 {
    static int T, N, d;
    static int[][] board;
    static final int DEGREE = 45;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            // test case
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            initBoard(br);

            int count = d / DEGREE;
            for(int c = 0; c < ((count < 0) ? count * -1 : count); c++){
                int[][] currentCopiedBoard = copyBoard();
                if(count > 0){
                    // 시계
                    turnRight(currentCopiedBoard);
                }else if(count < 0){
                    // 반시계
                    turnLeft(currentCopiedBoard);
                }
            }
            printBoard();
        }
    }
    static void turnRight(int[][] currentCopiedBoard){
        // 시계
        // step1
        for(int v = 0; v < N; v++){
            board[v][N / 2] = currentCopiedBoard[v][v];
        }

        // step2
        for(int v = 0; v < N; v++){
            board[v][N - v - 1] = currentCopiedBoard[v][N / 2];
        }

        // step3
        for(int v = 0; v < N; v++){
            board[N / 2][v] = currentCopiedBoard[N - v - 1][v];
        }

        // step4
        for(int v = 0; v < N; v++){
            board[v][v] = currentCopiedBoard[N / 2][v];
        }
    }
    static void turnLeft(int[][] currentCopiedBoard){
        // 반시계
        // step1
        for(int v = 0; v < N; v++){
            board[v][v] = currentCopiedBoard[v][N / 2];
        }

        // step2
        for(int v = 0; v < N; v++){
            board[v][N / 2] = currentCopiedBoard[v][N - v - 1];
        }

        // step3
        for(int v = 0; v < N; v++){
            board[N - v - 1][v] = currentCopiedBoard[N / 2][v];
        }

        // step4
        for(int v = 0; v < N; v++){
            board[N / 2][v] = currentCopiedBoard[v][v];
        }
    }
    static void printBoard(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    static int[][] copyBoard(){
        int[][] dst = new int[N][N];

        for(int i = 0; i < N; i++){
            System.arraycopy(board[i], 0, dst[i], 0, N);
        }

        return dst;
    }
    static void initBoard(BufferedReader br) throws IOException {
        board = new int[N][N];

        for(int j = 0; j < N; j++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int k = 0; k < N; k++){
                board[j][k] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
