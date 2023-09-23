package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2563 {
//    false: white, true: black
    private static final int BACKGROUND_BOARD_LENGTH = 100;
    private static final int COLOR_PAPER_LENGTH = 10;
    private static boolean[][] board = new boolean[BACKGROUND_BOARD_LENGTH][BACKGROUND_BOARD_LENGTH];
    private static int N;
    private static List<ColorPaper> colorPapers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        initInput();
        fillBlack();
        int filledBlackCount = getfilledBlackCount();
        System.out.println(filledBlackCount);
    }
    private static void fillBlack(){
        for (ColorPaper colorPaper : colorPapers) {
            int startColorPaperX = colorPaper.getX();
            int startColorPaperY = colorPaper.getY();
            for(int x = startColorPaperX; x < startColorPaperX + COLOR_PAPER_LENGTH; x++){
                if(x >= 100){
                    continue;
                }
                for(int y = startColorPaperY; y < startColorPaperY + COLOR_PAPER_LENGTH; y++){
                    if(y >= 100){
                        continue;
                    }
                    board[x][y] = true;
                }
            }
        }
    }
    private static int getfilledBlackCount(){
        int filledBlackCount = 0;
        for(int i = 0; i < BACKGROUND_BOARD_LENGTH; i++){
            for(int j = 0; j < BACKGROUND_BOARD_LENGTH; j++){
                boolean filledBlack = board[i][j];
                if(filledBlack == true){
                    filledBlackCount++;
                }
            }
        }
        return filledBlackCount;
    }
    private static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            colorPapers.add(new ColorPaper(x, y));
        }
    }
}

class ColorPaper{
    private int x;
    private int y;

    public ColorPaper(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}