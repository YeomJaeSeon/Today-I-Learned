package 구현;

import java.io.*;

/**
 * N: 홀수인 자연수 (3 <= N <= 999)
 */
public class Main1913 {
    private static int N, target;
    private static int[][] board;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        target = Integer.parseInt(br.readLine());
        int[] targetPosition = new int[]{-1, -1};

        // init
        int centerX = N / 2 + 1;
        int centerY = N / 2 + 1;

        // target = 1
        if(target == 1){
            targetPosition[0] = centerX;
            targetPosition[1] = centerY;
        }

        int startX = centerX - 1;
        int startY = centerY;

        board[centerX][centerY] = 1;
        int standardNum = centerX;

        int inputNum = 2;
        int count = N / 2;
        for(int i = 0; i < count; i++){
            // count겹의 둘레가 존재함
            ++standardNum;


            // 해당 껍데기의 맨왼쪽위 x, y
            int initX = startX;
            int initY = startY - 1;

            // right
            while (startY <= standardNum){
                board[startX][startY] = inputNum;
                if(inputNum == target && targetPosition[0] == -1) {
                    targetPosition[0] = startX;
                    targetPosition[1] = startY;
                }

                inputNum++;
                startY++;
            }

            inputNum--;
            startY--;
            // down
            while (startX <= standardNum){
                board[startX][startY] = inputNum;
                if(inputNum == target && targetPosition[0] == -1) {
                    targetPosition[0] = startX;
                    targetPosition[1] = startY;
                }

                inputNum++;
                startX++;
            }

            inputNum--;
            startX--;
            // left
            while (startY >= initY){
                board[startX][startY] = inputNum;
                if(inputNum == target && targetPosition[0] == -1) {
                    targetPosition[0] = startX;
                    targetPosition[1] = startY;
                }

                inputNum++;
                startY--;
            }

            inputNum--;
            startY++;
            // up
            while (startX >= initX){
                board[startX][startY] = inputNum;
                if(inputNum == target && targetPosition[0] == -1) {
                    targetPosition[0] = startX;
                    targetPosition[1] = startY;
                }

                inputNum++;
                startX--;
            }
        }
        printBoard();
        bw.write(targetPosition[0] + " " + targetPosition[1]);
        bw.flush();
        bw.close();
    }
    public static void printBoard() throws IOException {
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                bw.write(board[i][j] + " ");
            }
            bw.write("\n");
        }
    }
}

/**
 * 구현은 종이에 써가며 풀면 쉽다.
 *
 * -> target = 1일경우 체크하지 않아서 한번 틀림
 */