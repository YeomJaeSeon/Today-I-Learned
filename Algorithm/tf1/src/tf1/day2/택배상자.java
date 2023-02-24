package tf1.day2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 택배상자 {
    public static void main(String[] args) {
        택배상자 instance = new 택배상자();
        System.out.println(instance.solution(new int[]{4, 3, 1, 2, 5}));
    }
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> subBeltStack = new Stack<>();

        int i = 0; // 기사님이 필요한 인덱스
        for(int boxNum = 1; boxNum <= order.length; boxNum++){
            if(boxNum != order[i]){
                subBeltStack.push(boxNum);
                continue;
            }

            i++;
            answer++;

            while (!subBeltStack.isEmpty() && subBeltStack.peek() == order[i]){
                subBeltStack.pop();
                i++;
                answer++;
            }
        }

        return answer;
    }
}
