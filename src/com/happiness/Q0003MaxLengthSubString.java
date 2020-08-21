package com.happiness;

import java.util.*;

public class Q0003MaxLengthSubString {

    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.equals("")) {
            return 0;
        }

        int maxLenght = 1;
        for (int i = 0; i < s.length(); i++) {

            int j = i + 1;
            Set<Character> characters = new HashSet<>();
            for (; j < s.length(); j++) {

                if (characters.contains(s.charAt(j))) {
                    if (j - i > maxLenght) {
                        maxLenght = j - i;
                    }
                    break;
                } else {
                    characters.add(s.charAt(j));
                }

                if (s.charAt(i) == s.charAt(j)) {

                    if (j - i > maxLenght) {
                        maxLenght = j - i;
                    }

                    break;
                }
            }
            if (j >= s.length() - 1 && j - i > maxLenght) {
                maxLenght = j - i;
            }
        }

        return maxLenght;
    }

    public int lengthOfLongestSubstringV2(String s) {

        if (s == null || s.equals("")) {
            return 0;
        }

        int maxLenght = 0;


        Set<Character> characterSet = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {

            characterSet.clear();

            characterSet.add(s.charAt(i));

            int ri = i + 1;
            while (ri < n && !characterSet.contains(s.charAt(ri))) {
                characterSet.add(s.charAt(ri));
                ri += 1;
            }
            maxLenght = Math.max(maxLenght, ri - i);
        }


        return maxLenght;
    }

    public int lengthOfLongestSubstringV3(String s) {

        if (s == null || s.equals("")) {
            return 0;
        }

        int maxLenght = 0;

        Set<Character> characterSet = new HashSet<>();
        int n = s.length();
        int i = 0;
        int j = 0;
        while (i < n && j < n) {

            if (!characterSet.contains(s.charAt(j))) {
                characterSet.add(s.charAt(j));
                j++;
                maxLenght = Math.max(maxLenght, j - i);
            } else {
                characterSet.remove(s.charAt(i));
                i++;
            }
        }
        return maxLenght;
    }

    public int lengthOfLongestSubstringV4(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start +1);
            map.put(s.charAt(end), end +1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Q0003MaxLengthSubString q3 = new Q0003MaxLengthSubString();
        System.out.println("args1 = " + q3.lengthOfLongestSubstringV4("abcabcbb"));
        System.out.println("args2 = " + q3.lengthOfLongestSubstringV4("bbbbb"));
        System.out.println("args3 = " + q3.lengthOfLongestSubstringV4("pwwkew"));
        System.out.println("args4 = " + q3.lengthOfLongestSubstringV4("au"));

    }
}
