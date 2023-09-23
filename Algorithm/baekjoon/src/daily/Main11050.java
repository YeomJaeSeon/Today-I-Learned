package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//nCk
//n! / (n-k)!k!
public class Main11050 {
    private static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int answer = getFactorial(N) / (getFactorial(N - K) * getFactorial(K));
        System.out.println(answer);
    }
    private static int getFactorial(int n){
        int result = 1;
        while(n > 0){
            result *= n--;
        }

        return result;
    }
}