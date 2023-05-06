package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11659 {
    static int N, M;
    static int[] arr;
    static int[] prefixSumArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

//       누적합 배열 초기화
        prefixSumArr = new int[N];
        prefixSumArr[0] = arr[0];
        for(int i = 1; i < N; i++){
            prefixSumArr[i] = prefixSumArr[i - 1] + arr[i];
        }

        for(int v = 0; v < M; v++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            int sum = i > 1 ? prefixSumArr[j - 1] - prefixSumArr[i - 2] : prefixSumArr[j - 1];
            System.out.println(sum);
        }

    }
}
