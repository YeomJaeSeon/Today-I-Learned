package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main1018 {
    static private int N, M;
    static private char[][] board;
    static char[][] whiteFirstChess = new char[8][8];
    static char[][] blackFirstChess = new char[8][8];
    public static void main(String[] args) throws IOException {
        int min = 65;
        initInput();
        for(int i = 0; i <= N - 8; i++){
            for(int j = 0; j <= M - 8; j++){
                int firstX = i;
                int firstY = j;
                char[][] subBoard = new char[8][8];
                for(int x = 0; x < 8; x++){
                    for(int y = 0; y < 8; y++){
                        subBoard[x][y] = board[firstX + x][firstY + y];
                    }
                }
                min = Math.min(min, getMinValueFromChess(subBoard));
            }
        }
        System.out.println(min);
    }
    private static boolean isChess(char[][] board){
        Set<Character> whiteSet = new HashSet<>();
        Set<Character> blackSet = new HashSet<>();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i + j) % 2 == 0){
                    whiteSet.add(board[i][j]);
                }else{
                    blackSet.add(board[i][j]);
                }
            }
        }

        if(whiteSet.size() == 1 && blackSet.size() == 1){
            Iterator<Character> whiteSetIterator = whiteSet.iterator();
            Iterator<Character> blackSetIterator = blackSet.iterator();
            Character whiteValue = whiteSetIterator.next();
            Character blackValue = blackSetIterator.next();
            if(whiteValue != blackValue){
                return true;
            }
        }

        return false;
    }
    private static int getMinValueFromChess(char[][] board){
        if(isChess(board)){
            return 0;
        }
        int whiteFirstChessDiffCount = 0;
        int blackFirstChessDiffCount = 0;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                char ch = board[i][j];
                if(ch != whiteFirstChess[i][j]) whiteFirstChessDiffCount++;
                if(ch != blackFirstChess[i][j]) blackFirstChessDiffCount++;
            }
        }

        return Math.min(whiteFirstChessDiffCount, blackFirstChessDiffCount);
    }
//    3. isChess -> return 0
//    4. 아니면 MIN(맨왼쪽 위 검, 맨왼쪽 위 흰인 경우)
//    5. return value
    private static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = str.charAt(j);
            }
        }

//        chess init
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    whiteFirstChess[i][j] = 'W';
                    blackFirstChess[i][j] = 'B';
                } else {
                    whiteFirstChess[i][j] = 'B';
                    blackFirstChess[i][j] = 'W';
                }
            }
        }

    }
}
