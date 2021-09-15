package Algorithm.sol.pgmrs.scorekit;

import java.util.*;

public class HashRetire {
    public static void main(String[] args) {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};

        solution(participant, completion);
    }

    private static String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> compleMap = new HashMap<>();
        for (String name : completion) {
            compleMap.put(name, Optional.ofNullable(compleMap.get(name)).orElse(0) + 1);
        }

        for (String name : participant) {
            if (compleMap.containsKey(name)) {
                Integer numb = compleMap.get(name);
                if (numb == 1) {
                    compleMap.remove(name);
                } else {
                    compleMap.replace(name, numb - 1);
                }
            } else {
                answer = name;
                return answer;
            }
        }

        return answer;
    }
}
