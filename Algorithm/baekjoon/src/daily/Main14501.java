package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14501 {
    private static int N;
    private static ConsultantWork[] arr;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        initInput();
        processFillDpArr();
        System.out.println(dp[0]);
    }
    private static void processFillDpArr(){
        if(arr[N - 1].getTime() == 1){
            dp[N - 1] = arr[N-1].getPrice();
        }

        for(int i = N - 2; i >= 0; i--){
            if(i + arr[i].getTime() > N){
                // 퇴직 후 일하는 거라 일 할수 없음
                dp[i] = dp[i + 1];
                continue;
            }

            int currPrice = arr[i].getPrice();
            int currSpentTime = arr[i].getTime();
            int beforeMaxPrice = i + currSpentTime >= N ? 0 : dp[i + currSpentTime];

            dp[i] = Math.max(currPrice + beforeMaxPrice, dp[i + 1]);
        }
    }
    private static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new ConsultantWork[N];
        dp = new int[N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            arr[i] = new ConsultantWork(time, price);
        }
    }
}

class ConsultantWork{
    private int time;
    private int price;

    public ConsultantWork(int time, int price) {
        this.time = time;
        this.price = price;
    }

    public int getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }
}