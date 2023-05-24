package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main14467 {
    static int N;
    static List<Cow> cows = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // cows init
        for(int num = 0; num <= 10; num++){
            cows.add(new Cow(num));
        }

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cowNum = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());

            Cow currentCow = cows.get(cowNum);
            processCowSearch(pos, currentCow);
        }

        int sum = cows.stream().filter(i -> i.pos != -1).mapToInt(i -> i.cnt).sum();

        System.out.println(sum);
    }

    private static void processCowSearch(int pos, Cow currentCow) {
        switch (currentCow.pos){
            case -1:
                // default
                currentCow.pos = pos;
                break;
            case 0:
                if(pos == 1){
                    currentCow.cnt++;
                    currentCow.pos = 1;
                }
                break;
            case 1:
                if(pos == 0){
                    currentCow.cnt++;
                    currentCow.pos = 0;
                }
                break;
            default:
                break;
        }
    }
}
class Cow{
    int pos = -1; // default
    int num;
    int cnt = 0;

    public Cow(int num) {
        this.num = num;
    }
}