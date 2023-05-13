package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main20546 {
    private static final String BNP = "BNP";
    private static final String TIMING = "TIMING";
    private static final String SAME = "SAMESAME";
    private static final int DAYS = 14;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(br.readLine());
        int[] stocks = new int[DAYS];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < DAYS; i++){
            stocks[i] = Integer.parseInt(st.nextToken());
        }

        int bnp = bnp(money, stocks);
        int timing = timing(money, stocks);

        if(bnp > timing){
            System.out.println(BNP);
        }else if(bnp < timing){
            System.out.println(TIMING);
        }else{
            System.out.println(SAME);
        }
    }
    public static int bnp(int money, int[] stocks){
        int stockCount = 0;
        for (int stock : stocks) {
            if(money >= stock){
                int canBuyCount = money / stock;
                stockCount += canBuyCount;
                money -= (stock * canBuyCount);
            }
        }

        return money + stockCount * stocks[DAYS - 1];
    }

    public static int timing(int money, int[] stocks){
        int stockCount = 0;

        for(int i = 3; i < 14; i++){
            if(stocks[i - 3] < stocks[i - 2] && stocks[i - 2] < stocks[i - 1] && stocks[i - 1] < stocks[i]){
                // 상승 -> 전량 매도
                if(stockCount > 0){
                    money += (stockCount * stocks[i]);
                    stockCount = 0;
                }
            }else if(stocks[i - 3] > stocks[i - 2] && stocks[i - 2] > stocks[i - 1] && stocks[i - 1] > stocks[i]){
                // 하락 -> 전량 매수
                if(money >= stocks[i]){
                    int canBuyCount = money / stocks[i];
                    stockCount += canBuyCount;
                    money -= (stocks[i] * canBuyCount);
                }
            }
        }

        return money + stockCount * stocks[DAYS - 1];
    }
}
