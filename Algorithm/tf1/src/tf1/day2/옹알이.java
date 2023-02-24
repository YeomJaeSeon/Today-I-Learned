package tf1.day2;

public class 옹알이 {
    static String[] LANGUAGE = new String[]{
            "aya",
            "ye",
            "woo",
            "ma"
    };
    public static void main(String[] args) {
        옹알이 instance = new 옹알이();
        System.out.println(instance.solution(new String[]{
                "ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"
        }));

    }
    public int solution(String[] babbling) {
        int answer = 0;
        for(String ba: babbling){
            StringBuilder sb = new StringBuilder();
            String before = "init";

            for(int i = 0; i < ba.length(); i++){
                char ch = ba.charAt(i);
                sb.append(ch);

                if(sb.length() == 2){
                    if(sb.toString().equals("ye") && !before.equals("ye")){
                        before = "ye";
                        sb.setLength(0);
                    }else if(sb.toString().equals("ma") && !before.equals("ma")){
                        before = "ma";
                        sb.setLength(0);
                    }

                }else if(sb.length() == 3){
                    if(sb.toString().equals("aya") && !before.equals("aya")){
                        before = "aya";
                        sb.setLength(0);
                    }else if(sb.toString().equals("woo") && !before.equals("woo")){
                        before = "woo";
                        sb.setLength(0);
                    }
                }
            }

            if(sb.length() == 0){
                answer++;
            }
        }
        return answer;
    }
}

/**
 * stringBuilder를 이용.
 * String equals인지 찾으면됨
 *
 * 유의-> 연속해서 같은발음을 못하니 기존에 발음한 문자열을 저장하고있으면됨
 */