package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1436 {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int count = 0;
        int start = 666;

        while (count < N){
            if(String.valueOf(start).contains("666")){
                count++;
            }
            start++;
        }

        System.out.println(start - 1);
    }
}

/**
 * 666
 *
 * 1666
 * 2666
 * 3666
 * 4666
 * 5666
 * 6660
 * 6666
 * 6667
 * 6668
 * 6669
 *
 * 10666
 * 11666
 *
 * ...
 * 546661
 * 556661
 * 566661
 * ...
 *
 *
 * 99666
 *
 */
