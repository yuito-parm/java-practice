public class Step08_NameFilter {
    public static void main(String[] args) {
        String[] names = {"たかし", "さくら", "ゆうた", "まなみ", "けんじ", "さいとう", "よみん"};

        for (String name : names) {
            if (name.length() >= 3) {
                System.out.println(name + "(3文字以上)");
            }
            if (name.startsWith("さ")) {
                System.out.println(name + "(「さ」から始まる)");
            }
            if (name.contains("ん")) {
                System.out.println(name + "「ん」が含まれる");
            }
        }
    }
}
