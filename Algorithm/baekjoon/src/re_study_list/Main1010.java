package re_study_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1010 {
    static int T;
    static int N, M; // 0 < N <= M < 30
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        initArr();

        for(int i = 0; i < T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            System.out.println(arr[N][M]);
        }
    }

    private static void initArr() {
        arr = new int[30][30];
        for(int i = 1; i < 30; i++){
            arr[i][i] = 1;
            arr[1][i] = i;
        }

        for(int i = 2; i < 30; i++){
            for(int j = i + 1; j < 30; j++){
                arr[i][j] = arr[i][j - 1] + arr[i - 1][j - 1];
            }
        }
    }
}
