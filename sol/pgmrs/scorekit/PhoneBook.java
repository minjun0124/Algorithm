package Algorithm.sol.pgmrs.scorekit;

import java.util.HashSet;
import java.util.Set;

public class PhoneBook {
    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        String test = "asdfjkl";
        solution(phone_book);
    }

    private static boolean solution(String[] phone_book) {
        Set<String> phoneSet = new HashSet<>();

        int minLen = Integer.MIN_VALUE;
        for (String numb : phone_book) {
            minLen = Math.min(minLen, numb.length());
        }

        int numbLen = 0;
        for (String numb : phone_book) {
            numbLen = numb.length();
            for (int i = minLen; i < numbLen; i++) {
                phoneSet.add(numb.substring(0, i));
            }
        }

        for (String numb : phone_book) {
            if (phoneSet.contains(numb)) {
                return false;
            }
        }

        return true;
    }
}
