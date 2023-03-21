package tf1.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main15656 {
    static int N, M;
    static int[] result;
    static List<Integer> list = new ArrayList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new int[M];

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()){
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        recursive(0);
        bw.flush();
        bw.close();
    }
    static void recursive(int m) throws IOException {
        if(m == M){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < M; i++){
                sb.append(list.get(result[i]) + " ");
            }
            bw.write(sb + "\n");

            return;
        }
        for(int i = 0; i < N; i++){
            result[m] = i;
            recursive(m + 1);
        }
    }

}
