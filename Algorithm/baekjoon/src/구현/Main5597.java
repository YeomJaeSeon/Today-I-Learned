package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main5597 {
    static final int GOOD_STUDENT_COUNT = 28;
    static boolean[] submitAssignment = new boolean[31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < GOOD_STUDENT_COUNT; i++){
            submitAssignment[Integer.parseInt(br.readLine())] = true;
        }

        for(int i = 1; i < 31; i++){
            if(!submitAssignment[i]){
                System.out.println(i);
            }
        }
    }
}
