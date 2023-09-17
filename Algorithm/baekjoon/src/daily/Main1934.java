package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 유클리드 호제법
 *
 * a, b 최대 공약수
 * a % b = r
 * b % r = r'
 * r % r' = r''
 * ...
 *
 * if(r, r', r'')가 0이될떄까지
 *
 * a, b 최소 공배수: (a * b) / gcd
 */
public class Main1934 {
    private static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int max = Math.max(a, b);
            int min = Math.min(a, b);
//            최대공약수
            int minMultipleNum = getMinMultipleNum(max, min);
//            최소공배수
            int answer = (a * b) / minMultipleNum;
            System.out.println(answer);
        }
    }
//    a >= b
    private static int getMinMultipleNum(int a, int b){
        if(a % b == 0){
            return b;
        }

        return getMinMultipleNum(b, a % b);
    }
}
