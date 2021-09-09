package Algorithm.sol.pgmrs.kakao;

import java.util.*;

public class MenuRenewal {
    public static void main(String[] args) {

        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "DCAEH"};
        int[] course = {2, 3, 4};

        solution(orders, course);
    }


    static StringBuilder builder = new StringBuilder();
    static List<String> orderList;
    static Map<String, Integer> map;

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        orderList = new ArrayList<>();
        map = new HashMap<>();

        sortOrders(orders, orderList);
        for (String order : orderList) {
            int length = order.length();
            for (int max = 2; max <= course[course.length - 1]; max++) {
                combNonDupl(0, 0, order, max, length);
            }
        }

        int[] maxCheck = maxCheck();
        List<String> ansList = extractAns(maxCheck);
        answer = ansList.toArray(new String[ansList.size()]);
        Arrays.sort(answer);

        return answer;
    }

    private static List<String> extractAns(int[] maxCheck) {
        List<String> ansList = new ArrayList<>();
        for (String key : map.keySet()) {
            int idx = key.length();
            if (maxCheck[idx] == map.get(key)) {
                ansList.add(key);
            }
        }
        return ansList;
    }

    private static int[] maxCheck() {
        int[] maxCheck = new int[11];
        for (String key : map.keySet()) {
            int idx = key.length();
            maxCheck[idx] = Math.max(maxCheck[idx], map.get(key));
        }
        return maxCheck;
    }

    private static void sortOrders(String[] orders, List<String> orderList) {
        for (String order : orders) {
            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            orderList.add(new String(chars));
        }
    }

    private static void combNonDupl(int start, int cnt, String order, int max, int length) {
        if (cnt == max) {
            checkContain(builder.toString());
            return;
        }

        for (int i = start; i < length; i++) {
            builder.append(order.charAt(i));
            combNonDupl(i + 1, cnt + 1, order, max, length);
            builder.deleteCharAt(cnt);
        }
    }

    private static void checkContain(String menu) {
        if (map.containsKey(menu)) {
            return;
        }
        char[] menuChars = menu.toCharArray();
        boolean containChk = true;
        int cnt = 0;
        for (String order : orderList) {
            containChk = true;
            for (char menuChar : menuChars) {
                if (!order.contains(String.valueOf(menuChar))) {
                    containChk = false;
                }
            }
            if (containChk) {
                cnt++;
            }
        }
        if (cnt >= 2) {
            map.put(menu, cnt);
        }
    }
}
