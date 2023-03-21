package tf1.day1;

import java.util.LinkedList;
import java.util.Queue;

public class 카드뭉치 {
    public static void main(String[] args) {
        카드뭉치 instance = new 카드뭉치();
        System.out.println(instance.solution(new String[]{"i", "water", "drink"},
                new String[]{"want", "to"},
                new String[]{"i", "want", "to", "drink", "water"}
                ));
    }

    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Queue<String> card1Queue = makeQueueFromArray(cards1);
        Queue<String> card2Queue = makeQueueFromArray(cards2);

        for (String goalStr : goal) {
            String card1 = card1Queue.peek();
            String card2 = card2Queue.peek();
            if (card1 != null && card1.equals(goalStr)) {
                card1Queue.poll();
            } else if (card2 != null && card2.equals(goalStr)) {
                card2Queue.poll();
            }else{
                return "No";
            }
        }

        return "Yes";
    }
    private Queue<String> makeQueueFromArray(String[] cards){
        Queue<String> cardQueue = new LinkedList<>();
        for (String s : cards) {
            cardQueue.offer(s);
        }

        return cardQueue;
    }
}
/**
 * 카드A, 카드B 모두에서 goal의 특정 문자열과 같은게 없으면 No이다.
 */
