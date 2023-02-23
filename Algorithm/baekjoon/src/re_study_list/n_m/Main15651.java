package re_study_list.n_m;

import java.io.*;
import java.util.StringTokenizer;

public class Main15651 {
    static int N, M;
    static int[] result;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M];

        recursive(0);

        bw.flush();
        bw.close();
    }
    static void recursive(int m) throws IOException {
        if(m == M){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < M; i++){
                sb.append(result[i] + " ");
            }
            bw.write(sb.toString() + "\n");
            return;
        }

        for(int i = 0; i < N; i++){
            result[m] = i + 1;
            recursive(m + 1);
        }
    }
}
