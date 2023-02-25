package tf1.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1920 {
    static int N, M;
    static int[] arrN;
    static int[] arrM;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arrN = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arrN[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        arrM = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            arrM[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrN);

        for(int i = 0; i < M; i++){
            if(isContainInArrN(arrM[i])){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }
    }
    private static boolean isContainInArrN(int x){
        int start = 0;
        int end = N - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if(arrN[mid] == x) {
                return true;
            } else if(arrN[mid] < x){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        return false;
    }
}
