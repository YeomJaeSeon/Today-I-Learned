package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main9020 {
    private static int T;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            N = Integer.parseInt(br.readLine());
            int[] goldBachPartition = getGoldBachPartition(N);
            System.out.println(goldBachPartition[0] + " " + goldBachPartition[1]);
        }
    }
    private static int[] getGoldBachPartition(int N){
        List<int[]> list = new ArrayList<>();
        for(int num = 2; num <= N / 2; num++){
            if(isPrime(num) && isPrime(N - num)){
                list.add(new int[]{num, N - num});
            }
        }
        Collections.sort(list, Comparator.comparingInt(o -> Math.abs(o[0] - o[1])));

        return new int[]{list.get(0)[0], list.get(0)[1]};
    }
    private static boolean isPrime(int number){
        if(number < 2) return false;
        for(int i = 2; i <= (int) Math.sqrt(number); i++) {
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}
