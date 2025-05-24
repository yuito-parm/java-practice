public class Step09_NameScorePair {
    public static void main(String[] args) {
        String[] names = {"たかし", "さくら", "ゆうた", "まなみ", "けんじ"};
        int[] scores = {72, 85, 90, 68, 79};
        int total = 0;

        for (int score : scores) {
            total += score;
        }
        double average = (double) total / scores.length;
        System.out.println("平均点以上の人だけを表示");
        System.out.println("平均点: " + average + "点");
        for (int i = 0; i < names.length; i++) {
            if (scores[i] >= average) {
                System.out.println(names[i] + "さんの点数: " + scores[i] + "点");
            }
            if (scores[i] >= 80) {
                System.out.println("よくできました！");
            }
        }

    }
}
