package tf1.day3;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main2751 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        for (int i : arr) {
            bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
    }
}
