package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2578 {
    static int[] boardPoint = new int[26]; // idx: number, value: position
    static int[][] board = new int[5][5];
    static int[][] indicateBoard = new int[5][5];
    static final int MATCH_NUMBER = -1;
    public static void main(String[] args) throws IOException {
        int answer = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for(int i = 0; i < 5; i++){
            // board setting
            // boardPoint setting
            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 0; j < 5; j++){
                int number = Integer.parseInt(st.nextToken());
                board[i][j] = number;
                boardPoint[number] = i * 5 + j;
            }
        }

        for(int i = 0; i < 5; i++){
            // indicateBoard setting
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 5; j++){
                int number = Integer.parseInt(st.nextToken());
                indicateBoard[i][j] = number;
            }
        }

        // indicateBoard loop
        int count = 0;
        outer:
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                count++;
                int indicateNumber = indicateBoard[i][j];
                int point = boardPoint[indicateNumber];
                board[point / 5][point % 5] = MATCH_NUMBER; // check
                if(getMatchCount() >= 3){
                    answer = count;
                    break outer;
                }
            }
        }

        System.out.println(answer);
    }
    static int getMatchCount(){
        int count = 0;
        // 가로
        for(int i = 0; i < 5; i++){
            List<Integer> rowList = new ArrayList<>();
            List<Integer> columnList = new ArrayList<>();
            for(int j = 0; j < 5; j++){
                rowList.add(board[i][j]);
                columnList.add(board[j][i]);
            }

            boolean isRowMatched = rowList.stream().allMatch(v -> v == MATCH_NUMBER);
            boolean isColumnMatched = columnList.stream().allMatch(v -> v == MATCH_NUMBER);

            if(isRowMatched) count++;
            if(isColumnMatched) count++;
        }

        // 대각선 두개
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            leftList.add(board[i][i]);
            rightList.add(board[i][ 5 - i - 1]);
        }
        boolean leftMatched = leftList.stream().allMatch(v -> v == MATCH_NUMBER);
        boolean rightMatched = rightList.stream().allMatch(v -> v == MATCH_NUMBER);
        if(leftMatched) count++;
        if(rightMatched) count++;

        return count;
    }
    static void printBoard(int[][] board){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
