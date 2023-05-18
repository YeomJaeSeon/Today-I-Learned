package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main4396 {
    static int N;
    static char[][] board;
    static char[][] resultBoard;
    static List<int[]> minePos = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new char[N][N];
        resultBoard = new char[N][N];

        // init
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                board[i][j] = str.charAt(j);
                resultBoard[i][j] = '.';
                if(board[i][j] == '*'){
                    // if mine
                    minePos.add(new int[]{i, j});
                }
            }
        }

        // play
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                char state = str.charAt(j);

                if (state == 'x') {
                    if (board[i][j] == '*') {
                        // 지뢰 위치였으면 -> 지뢰가 있는 모든 칸이 *로 열려야함
                        for (int[] minePo : minePos) {
                            resultBoard[minePo[0]][minePo[1]] = '*';
                        }
                    } else {
                        int mineNumber = getMineCount(i, j);
                        resultBoard[i][j] = (char) ('0' + mineNumber);
                    }
                }
            }
        }

        printBoard();
    }
    private static void printBoard(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(resultBoard[i][j]);
            }
            System.out.println();
        }
    }
    private static int getMineCount(int x, int y){
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

        int count = 0;

        for(int i = 0; i < 8; i++){
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;

            if(board[nextX][nextY] == '*') count++;
        }

        return count;
    }
}
