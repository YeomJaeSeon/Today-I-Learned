package problems1;

import java.util.Stack;

public class 삼진법_뒤집기 {
    public static void main(String[] args) {
        삼진법_뒤집기 instance = new 삼진법_뒤집기();
        System.out.println(instance.solution(125));

    }
    public int solution(int n) {
        String str = make3(n);
        String reverseStr = reverse(str);
        return make10(reverseStr);
    }
    private String make3(int n){
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            sb.append(n % 3);
            n /= 3;
        }

        return reverse(sb.toString());
    }
    private String reverse(String str){
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++){
            stack.push(str.charAt(i));
        }

        StringBuilder reverseSb = new StringBuilder();
        while (!stack.isEmpty()){
            reverseSb.append(stack.pop());
        }

        return reverseSb.toString();
    }
    private int make10(String str){
        int sum = 0;
        int v = 0;
        for(int i = str.length() - 1; i >= 0; i--){
            int num = Integer.parseInt(String.valueOf(str.charAt(i)));
            sum += num * (int)Math.pow(3, v);
            v++;
        }

        return sum;
    }
}
