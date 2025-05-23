public class Step07_ArraySummary {
    public static void main(String[] args) {
        int[] scores = {72, 85, 90, 68, 77};

        int sum = 0;
        int max = scores[0];
        int min = scores[0];
        int count = 0;
        boolean hasPerfect = false;
        for (int score : scores) {
            sum += score;

            if (score >= max) {
                max = score;
            }
            if (min >= score) {
                min = score;
            }

            if (score >= 90) {
                // over++;
                hasPerfect = true;
            }
            if (score >= 80) {
                count++;                
            }
        }

        double average = (double)sum / scores.length;

        if (hasPerfect) {
            System.out.println("90点以上の人がいるよ!すごい!");
        }
        System.out.println("平均点: " + average + "点");
        System.out.println("最高点: " + max + "点");
        System.out.println("最低点: " + min + "点");
        System.out.println("80点以上の人数: " + count + "人");

    }
}
