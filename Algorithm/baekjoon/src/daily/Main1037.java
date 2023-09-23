package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 2 3 4 - N = 12
// 3 5 8 10
public class Main1037 {
    private static int T;
    private static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        initInput();
        Collections.sort(list);
        printResult();
    }
    private static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < T; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
    }
    private static void printResult(){
        if(list.size() == 1){
            System.out.println(list.get(0) * list.get(0));
        }else{
            System.out.println(list.get(0) * list.get(list.size() - 1));
        }
    }
}
