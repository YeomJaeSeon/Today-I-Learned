package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main15787 {
    static int N, M;
    static List<int[]> trains = new ArrayList<>(); // 0: 타지않음, 1: 탐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        initTrains();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int commandNum = Integer.parseInt(st.nextToken());
            int trainNum = Integer.parseInt(st.nextToken());
            int x = -1;
            if(st.hasMoreTokens()){
                x = Integer.parseInt(st.nextToken());
            }

            int[] train = trains.get(trainNum);
            command(train, commandNum, x);
        }

        int passTrainCount = getPassTrainCount();
        System.out.println(passTrainCount);
    }
    static void command(int[] train, int command, int x){
        switch (command){
            case 1:
                train[x] = 1;
                break;
            case 2:
                train[x] = 0;
                break;
            case 3:
                for(int num = 20; num >= 2; num--){
                    train[num] = train[num - 1];
                }
                train[1] = 0;
                break;
            case 4:
                for(int num = 1; num <= 19; num++){
                    train[num] = train[num + 1];
                }
                train[20] = 0;
                break;
        }
    }

    static int getPassTrainCount(){
        Set<String> passTrainSet = new HashSet<>();
        for(int i = 1; i <= N; i++){
            int[] train = trains.get(i);
            StringBuilder trainSb = new StringBuilder();
            for(int j = 1; j <= 20; j++){
                if(train[j] == 1){
                    trainSb.append("1");
                }else{
                    trainSb.append("0");
                }
            }

            passTrainSet.add(trainSb.toString());
        }

        return passTrainSet.size();
    }

    static void initTrains(){
        for(int i = 0; i <= N; i++){
            trains.add(new int[21]);
        }
    }
}
