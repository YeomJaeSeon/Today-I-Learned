package tf1.day2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 햄버거_만들기 {
//    1 -> 2 -> 3 -> 1
    static String ORDER = "1231";
    static Stack<String> stack = new Stack<>();
    public static void main(String[] args) {
        햄버거_만들기 instance = new 햄버거_만들기();
        System.out.println(instance.solution(new int[]{2, 1, 1, 2, 3, 1, 2, 3, 1}));
    }
    public int solution(int[] ingredient) {
        int answer = 0;
        for(int v : ingredient){
            String input = String.valueOf(v);
            if(stack.size() < 3){
                stack.push(input);
            }else{
                String last1 = stack.pop();
                String last2 = stack.pop();
                String last3 = stack.pop();

                if(!(last3 + last2 + last1 + input).equals(ORDER)){
                    stack.push(last3);
                    stack.push(last2);
                    stack.push(last1);
                    stack.push(input);
                }else{
                    answer++;
                }
            }
        }

        return answer;
    }
}
/**
 * stack이용
 */