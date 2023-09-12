package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main1929 {
    private static int M, N;
    private static final List<Integer> primeList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        initInput();
        for(int number = M; number <= N; number++){
            if(isPrime(number)){
                primeList.add(number);
            }
        }
        Collections.sort(primeList);
        printResult();
    }
    private static boolean isPrime(int number){
        if(number < 2){
            return false;
        }
        double sqrt = Math.sqrt(number);
        for(int i = 2; i <= (int)sqrt; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
    private static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
    }
    private static void printResult(){
        for (Integer primeNumber : primeList) {
            System.out.println(primeNumber);
        }
    }

}

/**
 * prime 찾아서 정렬
 * prime 찾기 : 2 ~ sqrt(number)까지 나머지 0인 경우가 있는지 체크해서 없으면 prime number
 */