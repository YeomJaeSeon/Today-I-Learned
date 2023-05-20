package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main1212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String octal = br.readLine();
        String result = convert8to2(octal);
        System.out.println(result);
    }
    private static String convert8to2(String octal){
        if(octal.equals("0")){
            return "0";
        }

        StringBuilder resultSb = new StringBuilder();

        Stack<StringBuilder> stack = new Stack<>();

        for(int i = octal.length() - 1; i >= 0; i--){
            int octalNum = Integer.parseInt(octal.charAt(i) + "");

            StringBuilder str = new StringBuilder();
            while(octalNum > 0){
                str.append(octalNum % 2);
                octalNum /= 2;
            }
            if (i != 0) {
                if (str.length() < 3) {
                    int plusPadNumber = 3 - str.length();
                    str.append("0".repeat(Math.max(0, plusPadNumber)));
                }
            }

            stack.push(str);
        }
        while (!stack.isEmpty()){
            resultSb.append(stack.pop().reverse());
        }

        return resultSb.toString();
    }

}
