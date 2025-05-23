public class Step06_ArrayBasics {
    public static void main(String[] args) {
        int[] scores =  {72, 85, 90, 68, 77};

        int total = 0;
        System.out.println("80点以上の人だけを表示");
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= 80) {
                System.out.println((i + 1) + "人目の点数: " + scores[i] + "点");
            }
            total = total + scores[i];
        }
        double average = (double)total / scores.length;
        System.out.println("平均点: " + average + "点");
    }
}
