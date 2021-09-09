package Algorithm.sol.pgmrs.kakao;

public class IdRecommend {

    public static void main(String[] args) {
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        System.out.println("answer : " + solution_regex(new_id));
        new_id = "z-+.^.";
        System.out.println("answer : " + solution_regex(new_id));
        new_id = "=.=";
        System.out.println("answer : " + solution_regex(new_id));
        new_id = "123_.def";
        System.out.println("answer : " + solution_regex(new_id));
        new_id = "abcdefghijklmn.p";
        System.out.println("answer : " + solution_regex(new_id));
    }

    public static String solution_regex(String new_id) {
        String answer = "";
        String temp = new_id.toLowerCase();
        temp = temp.replaceAll("[^-_.a-z0-9]", "");
//        System.out.println(temp);
        temp = temp.replaceAll("[.]{2,}", ".");
        temp = temp.replaceAll("^[.]|[.]$", "");
//        System.out.println(temp.length());
        if (temp.equals("")) {
            temp += "a";
        }
        if (temp.length() >= 16) {
            temp = temp.substring(0, 15);
            temp = temp.replaceAll("^[.]|[.]$", "");
        }
        if (temp.length() <= 2) {
            while (temp.length() < 3)
                temp += temp.charAt(temp.length() - 1);
        }

        answer = temp;
        return answer;
    }

    private static String solution(String new_id) {

        new_id = stepOne(new_id);
//        System.out.println("step1 : " + new_id);

        new_id = stepTwoThree(new_id);
//        System.out.println("step2, 3 : " + new_id);

        new_id = stepFour(new_id);
//        System.out.println("step4 : " + new_id);

        new_id = stepFive(new_id);
//        System.out.println("step5 : " + new_id);

        new_id = stepSix(new_id);
//        System.out.println("step6 : " + new_id);

        new_id = stepSeven(new_id);
//        System.out.println("step7 : " + new_id);

        return new_id;
    }

    private static String stepSeven(String new_id) {
        while (new_id.length() <= 2) {
            new_id += new_id.substring(new_id.length() - 1, new_id.length());
        }
        return new_id;
    }

    private static String stepSix(String new_id) {
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if (new_id.charAt(new_id.length() - 1) == '.') {
                new_id = new_id.substring(0, new_id.length() - 1);
            }
        }
        return new_id;
    }

    private static String stepFive(String new_id) {
        if (new_id.length() == 0) {
            new_id = "a";
        }
        return new_id;
    }

    private static String stepFour(String new_id) {
        if (new_id.length() == 0) {
            return new_id;
        }
        if (new_id.charAt(0) == '.') {
            new_id = new_id.substring(1);
        }
        if (new_id.length() == 0) {
            return new_id;
        }
        if (new_id.charAt(new_id.length() - 1) == '.') {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        return new_id;
    }

    private static String stepTwoThree(String new_id) {
        StringBuffer changedId = new StringBuffer();
        final String allowed = "abcdefghijklmnopqrstuvwxyz1234567890-_.";
        String prev = "";
        final String dot = ".";
        int strLen = new_id.length();

        for (int i = 0; i < strLen; i++) {
            String currStr = new_id.substring(i, i + 1);
//            System.out.println(currStr);
            if (allowed.contains(currStr)) {
                if (currStr.equals(dot)) {
                    if (!prev.equals(dot)) {
                        changedId.append(new_id.charAt(i));
                    }
                } else {
                    changedId.append(new_id.charAt(i));
                }
                prev = currStr;
            }
        }
        return changedId.toString();
    }

    private static String stepOne(String new_id) {
        String changed = new_id.toLowerCase();

        return changed;
    }

}
