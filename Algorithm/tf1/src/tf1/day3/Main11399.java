package tf1.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main11399 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        int sum = 0;
        for(int i = 0; i < N; i++){
            for(int j = i; j >= 0; j--){
                sum += list.get(j);
            }
        }

        System.out.println(sum);
    }
}
