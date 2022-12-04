package problems1;

public class 숫자_카드_나누기 {
    public static void main(String[] args) {
        숫자_카드_나누기 instance = new 숫자_카드_나누기();
        System.out.println(instance.solution(new int[]{10, 17}, new int[]{5, 20}));
    }
    public int solution(int[] arrayA, int[] arrayB) {
        //arrayA 모두 나눌수있고 arrayB 모두 나눌수없는 최대값 maxA
        //2 ~ arrayA의 제일 작은값
        int minOfArrayA = getMinFromArray(arrayA);
        int maxA = check(minOfArrayA, arrayA, arrayB);


        //arrayA 모두 나눌수 없고 arrayB 모두 나눌 수 있는 최대값 maxB
        //2 ~ arrayB의 제일 작은값
        int minOfArrayB = getMinFromArray(arrayB);
        int maxB = check(minOfArrayB, arrayB, arrayA);

        if(maxB == -1 && maxA == -1){
            return 0;
        }

        return Math.max(maxA, maxB);
    }
    private int getMinFromArray(int[] array){
        int min = Integer.MAX_VALUE;
        for (int value : array) {
            min = Math.min(min, value);
        }

        return min;
    }

    private int check(int minValue, int[] canDivideArr, int[] cannotDivideArr){
        outer:
        for(int i = minValue; i >= 2; i--){
            for (int can : canDivideArr) {
                if(can % i != 0){
                    continue outer;
                }
            }

            for (int cannot : cannotDivideArr) {
                if(cannot % i == 0){
                    continue outer;
                }
            }

            return i;
        }

        return -1;
    }
}
