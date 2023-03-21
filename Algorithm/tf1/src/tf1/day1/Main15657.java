package tf1.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main15657 {
    static int N, M;
    static List<Integer> list = new ArrayList<>();
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M];

        st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()){
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        recursive(0, 0);
    }
    static void recursive(int m, int start){
        if(m == M){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < M; i++){
                sb.append(list.get(result[i]) + " ");
            }
            System.out.println(sb);

            return;
        }

        for(int i = start; i < N; i++){
            result[m] = i;
            recursive(m + 1, i);
        }
    }
}
