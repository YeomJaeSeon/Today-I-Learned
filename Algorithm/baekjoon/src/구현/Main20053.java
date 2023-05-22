package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main20053 {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int max = -1_000_001;
            int min = 1_000_001;
            for(int j = 0; j < N; j++){
                int num = Integer.parseInt(st.nextToken());
                max = Math.max(max, num);
                min = Math.min(min, num);
            }

            System.out.println(min + " " + max);
        }

    }
}
