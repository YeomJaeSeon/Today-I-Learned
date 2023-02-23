package re_study_list.n_m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15652 {
    static int N, M;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M];

        recursive(0, 0);
    }
    static void recursive(int m, int start){
        if(m == M){
            return;
        }

        for(int i = start; i < N; i++){
            result[m] = i + 1;
            recursive(m + 1, );
        }
    }
}
