package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main11653 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N == 1){
            return;
        }
        int modNum = 2;
        while(N > 1){
            if(N % modNum == 0){
                N /= modNum;
                System.out.println(modNum);
            }else{
                modNum++;
            }
        }
    }
}
