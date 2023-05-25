package 그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2667 {
    static int N;
    static int[][] board;
    static List<Integer> danjiList = new ArrayList<>();
    static boolean[][] bfsVisited;
    static boolean[][] dfsVisited;
    static int dfsStepCount = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        bfsVisited = new boolean[N + 1][N + 1];
        dfsVisited = new boolean[N + 1][N + 1];

        initBoard(br);

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int result = approach(i, j);
                if(result != -1){
                    danjiList.add(result);
                }
            }
        }

        System.out.println(danjiList.size());
        Collections.sort(danjiList);
        for (Integer count : danjiList) {
            System.out.println(count);
        }
    }

    private static int approach(int i, int j) {
        // bfs
        return bfs(i, j);


        // dfs
//        dfsStepCount = 0;
//        dfs(i, j);
//        return dfsStepCount == 0 ? -1 : dfsStepCount;
    }
    private static void dfs(int x, int y){
        if(dfsVisited[x][y] || board[x][y] == 0){
            return;
        }

        dfsVisited[x][y] = true;
        dfsStepCount++;

        for(int i = 0; i < 4; i++){
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N){
                continue;
            }
            if(board[nextX][nextY] == 0){
                continue;
            }
            if(dfsVisited[nextX][nextY]){
                continue;
            }

            dfs(nextX, nextY);
        }
    }
    private static int bfs(int x, int y){
        if(bfsVisited[x][y] || board[x][y] == 0) {
            return -1;
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        bfsVisited[x][y] = true;
        int stepCount = 1;

        while (!q.isEmpty()){
            int[] curr = q.poll();
            for(int i = 0; i < 4; i++){
                int nextX = dx[i] + curr[0];
                int nextY = dy[i] + curr[1];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N){
                    continue;
                }
                if(board[nextX][nextY] == 0){
                    continue;
                }
                if(bfsVisited[nextX][nextY]){
                    continue;
                }

                bfsVisited[nextX][nextY] = true;
                stepCount++;
                q.offer(new int[]{nextX, nextY});
            }
        }

        return stepCount;
    }

    private static void initBoard(BufferedReader br) throws IOException {
        board = new int[N][N];
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }
    }
}
