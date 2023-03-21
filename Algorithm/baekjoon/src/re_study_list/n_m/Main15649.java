package re_study_list.n_m;

import javax.swing.text.html.StyleSheet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15649 {
    static int N, M;
    static boolean[] visited;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        result = new int[M];

        recursive(0);
    }
    static void recursive(int m){
        if(m == M){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < m; i++){
                sb.append(result[i] + " ");
            }

            System.out.println(sb);
            return;
        }

        for(int i = 0; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                result[m] = i + 1;
                recursive(m + 1);
                visited[i] = false;
            }
        }
    }
}