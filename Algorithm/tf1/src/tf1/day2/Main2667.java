package tf1.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2667 {
    static int N;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0 ,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int result = bfs(i, j);
                if(result > 0){
                    list.add(result);
                }
            }
        }

        Collections.sort(list);

        System.out.println(list.size());
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
    static int bfs(int startX, int startY){
        if(board[startX][startY] == 0){
            return 0;
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY});
        board[startX][startY] = 0;
        int count = 0;

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];
            count++;

            for(int i = 0; i < 4; i++){
                int nextX = currX + dx[i];
                int nextY = currY + dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N){
                    continue;
                }
                if(board[nextX][nextY] == 0){
                    continue;
                }
                board[nextX][nextY] = 0;
                q.offer(new int[]{nextX, nextY});
            }

        }
        return count;
    }
}
