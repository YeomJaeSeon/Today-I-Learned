package tf1.day1;

import java.util.*;

public class 개인정보_수집_유효기간 {
    static int DAYS_PER_MONTH = 28;
    static int MONTHS_PER_YEAR = 12;
    static Map<String, Integer> termsMap = new HashMap<>();
    public static void main(String[] args) {
        개인정보_수집_유효기간 instance = new 개인정보_수집_유효기간();
        System.out.println(Arrays.toString(instance.solution(
                "2020.01.01",
                new String[]{"Z 3", "D 5"},
                new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"})));

    }
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            termsMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            StringTokenizer st = new StringTokenizer(privacy);
            String startDate = st.nextToken();
            String termNum = st.nextToken();

            Integer dueMonth = termsMap.get(termNum);
            int dueDays = dueMonth * DAYS_PER_MONTH;
            String endDate = makeDateStr(startDate, dueDays);

            if(isExpire(today, endDate)){
                answer.add(i + 1);
            }

        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    private String makeDateStr(String startDate, int dueDays){
        StringTokenizer st = new StringTokenizer(startDate, ".");
        String year = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();
        dueDays--;

        int plusYear = dueDays / (DAYS_PER_MONTH * MONTHS_PER_YEAR);
        dueDays %= (DAYS_PER_MONTH * MONTHS_PER_YEAR);
        int plusMonth = dueDays / DAYS_PER_MONTH;
        dueDays %= DAYS_PER_MONTH;
        int plusDay = dueDays;

        int newYear = Integer.parseInt(year) + plusYear;
        int newMonth = Integer.parseInt(month) + plusMonth;
        int newDay = Integer.parseInt(day) + plusDay;

        StringBuilder sb = new StringBuilder();

        sb.append(padLeft(String.valueOf(newYear), 4))
                .append(".")
                .append(padLeft(String.valueOf(newMonth), 2))
                .append(".")
                .append(padLeft(String.valueOf(newDay), 2));

        return sb.toString();
    }

    private boolean isExpire(String today, String endDate){
        StringTokenizer st1 = new StringTokenizer(today, ".");
        int todayYear = Integer.parseInt(st1.nextToken());
        int todayMonth = Integer.parseInt(st1.nextToken());
        int todayDay = Integer.parseInt(st1.nextToken());
        int todayAbsoluteDay = todayYear * (DAYS_PER_MONTH * MONTHS_PER_YEAR) + todayMonth * (DAYS_PER_MONTH) + todayDay;

        StringTokenizer st2 = new StringTokenizer(endDate, ".");
        int endYear = Integer.parseInt(st2.nextToken());
        int endMonth = Integer.parseInt(st2.nextToken());
        int endDay = Integer.parseInt(st2.nextToken());
        int endAbsoluteDay = endYear * (DAYS_PER_MONTH * MONTHS_PER_YEAR) + endMonth * (DAYS_PER_MONTH) + endDay;

        return todayAbsoluteDay > endAbsoluteDay;
    }

    private String padLeft(String s, int n) {
        return String.format("%" + n + "s", s).replace(' ', '0');
    }
}

/**
 * 빡구현 문제
 *
 * 구현만하면 쉽다.
 * 날짜를 계산하는데 좀어려웠다. 애초에 day기준으로 바꿧으면 쉬웠을 듯.
 */