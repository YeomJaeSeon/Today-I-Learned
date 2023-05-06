package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11441 {
    static int N;
    static int[] arr;
    static int M;

    public static void main(String[] args) throws IOException {
//        notUsePrefixSum();
        usePrefixSum();
    }

//    O(M). 이미 누적합에 대한 prefixSumArr이 있으니 M번의 시간복잡도로 문제해결가능
    public static void usePrefixSum() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 여기서 누적합을 구해야함
        int[] prefixSumArr = new int[N];
        prefixSumArr[0] = arr[0];
        for(int i = 1; i < N; i++){
            prefixSumArr[i] = arr[i] + prefixSumArr[i - 1];
        }

        M = Integer.parseInt(br.readLine());
        for(int v = 0; v < M; v++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            int sum = 0;
            sum = i - 2 >= 0 ? prefixSumArr[j - 1] - prefixSumArr[i - 2] : prefixSumArr[j - 1];
            System.out.println(sum);
        }
    }

    /**
     * 최악의 경우 O(NM)의 시간복잡도 -> 100_000 * 100_000 -> 10_000_000_000(100억)
     */
    public static void notUsePrefixSum() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        for(int v = 0; v < M; v++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            int sum = 0;
            for(int k = i; k <= j; k++){
                sum += arr[k - 1];
            }

            System.out.println(sum);
        }
    }
}
