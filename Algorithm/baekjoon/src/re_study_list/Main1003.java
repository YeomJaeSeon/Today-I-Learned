package re_study_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 0과 1이 출력되는 수를 구하기
 */
public class Main1003 {
    static int T;
    static int[] zeroCountArr;
    static int[] oneCountArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            int n = Integer.parseInt(br.readLine());

            printZeroAndOneCount(n);
        }
    }
    static void printZeroAndOneCount(int n){
        //초기화
        zeroCountArr = new int[n + 1];
        oneCountArr = new int[n + 1];

        if(n == 0){
            System.out.println("1 0");
        }else if(n == 1){
            System.out.println("0 1");
        }else{
            zeroCountArr[0] = 1;
            oneCountArr[1] = 1;

            for(int j = 2; j <= n; j++){
                zeroCountArr[j] = zeroCountArr[j - 1] + zeroCountArr[j - 2];
                oneCountArr[j] = oneCountArr[j - 1] + oneCountArr[j - 2];
            }
            System.out.println(zeroCountArr[n] + " " + oneCountArr[n]);
        }
    }
}
