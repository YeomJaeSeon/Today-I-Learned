package tf1.day2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class 성격_유형_검사하기 {
    static Map<String, Integer> scores = new HashMap<>();
    public static void main(String[] args) {
        성격_유형_검사하기 instance = new 성격_유형_검사하기();
        System.out.println(instance.solution(
                new String[]{"AN", "CF", "MJ", "RT", "NA"},
                new int[]{5, 3, 2, 7, 5}
        ));
    }
    public String solution(String[] survey, int[] choices) {
        initScores();

        for(int i = 0; i < survey.length; i++){
            String thisSurvey = survey[i];
            int thisChoice = choices[i];

            String notAgree = String.valueOf(thisSurvey.charAt(0));
            String agree = String.valueOf(thisSurvey.charAt(1));

            if(thisChoice >= 1 && thisChoice <= 3){
                //비동의
                int giveScore = 0;
                if(thisChoice == 1){
                    giveScore = 3;
                }else if(thisChoice == 2){
                    giveScore = 2;
                }else {
                    giveScore = 1;
                }
                Integer alreadyScore = scores.get(notAgree);
                scores.put(notAgree, alreadyScore + giveScore);
            }else if(thisChoice >= 5 && thisChoice <= 7){
                //동의
                int giveScore = 0;
                if(thisChoice == 5){
                    giveScore = 1;
                }else if(thisChoice == 6){
                    giveScore = 2;
                }else {
                    giveScore = 3;
                }
                Integer alreadyScore = scores.get(agree);
                scores.put(agree, alreadyScore + giveScore);
            }
        }


        return formatResult();
    }
    private void initScores(){
        scores.put("A", 0);
        scores.put("N", 0);
        scores.put("J", 0);
        scores.put("M", 0);
        scores.put("C", 0);
        scores.put("F", 0);
        scores.put("R", 0);
        scores.put("T", 0);
    }
    String formatResult(){
        StringBuilder sb = new StringBuilder();

        // RT
        Integer rScore = scores.get("R");
        Integer tScore = scores.get("T");
        if(rScore > tScore){
            sb.append("R");
        }else if(rScore < tScore){
            sb.append("T");
        }else{
            sb.append((char)Math.min('R', 'T'));
        }

        // CF
        Integer cScore = scores.get("C");
        Integer fScore = scores.get("F");
        if(cScore > fScore){
            sb.append("C");
        }else if(cScore < fScore){
            sb.append("F");
        }else{
            sb.append((char)Math.min('C', 'F'));
        }

        // JM
        Integer jScore = scores.get("J");
        Integer mScore = scores.get("M");
        if(jScore > mScore){
            sb.append("J");
        }else if(jScore < mScore){
            sb.append("M");
        }else{
            sb.append((char)Math.min('J', 'M'));
        }

        // AN
        Integer aScore = scores.get("A");
        Integer nScore = scores.get("N");
        if(aScore > nScore){
            sb.append("A");
        }else if(aScore < nScore){
            sb.append("N");
        }else{
            sb.append((char)Math.min('A', 'N'));
        }
        return sb.toString();
    }
}
