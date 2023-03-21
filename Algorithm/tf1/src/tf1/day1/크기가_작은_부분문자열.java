package tf1.day1;

public class 크기가_작은_부분문자열 {
    public static void main(String[] args) {
        크기가_작은_부분문자열 instance = new 크기가_작은_부분문자열();
        System.out.println(instance.solution("10203", "15"));

    }

    public int solution(String t, String p) {
        int answer = 0;
        long pNum = Long.parseLong(p);

        for(int i = 0; i <= t.length() - p.length(); i++){
            String subStr = t.substring(i, i + p.length());

            if(Long.parseLong(subStr) <= pNum){
                answer++;
            }
        }
        return answer;
    }
}
/**
 * 1 <= p길이 <= 18
 * -> long
 */
