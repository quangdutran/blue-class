package com.company.bst;

import java.util.*;

public class TanyaAndPostCard {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line1 = input.nextLine();
        String line2 = input.nextLine();

        TreeMap<Character, Integer> line1TreeMap = new TreeMap<>();
        TreeMap<Character, Integer> line1TreeMapCp = new TreeMap<>();
        TreeMap<Character, Integer> line2TreeMap = new TreeMap<>();

        for (int i = 0; i < line1.length(); i++) {
            if (!line1TreeMap.containsKey(line1.charAt(i))) {
                line1TreeMap.put(line1.charAt(i), 1);
                line1TreeMapCp.put(line1.charAt(i), 1);
            } else {
                line1TreeMap.put(line1.charAt(i), line1TreeMap.get(line1.charAt(i)) + 1);
                line1TreeMapCp.put(line1.charAt(i), line1TreeMap.get(line1.charAt(i)) + 1);
            }
        }

        for (int i = 0; i < line2.length(); i++) { //O(t.length)
            if (!line2TreeMap.containsKey(line2.charAt(i))) {
                line2TreeMap.put(line2.charAt(i), 1);
            } else {
                line2TreeMap.put(line2.charAt(i), line2TreeMap.get(line2.charAt(i)) + 1);
            }
        }

        int yay = 0;
        int whoop = 0;
        for (Map.Entry<Character, Integer> e : line1TreeMap.entrySet()) { //O(26*2 * log(t.length))
            char key = e.getKey();
            int count = e.getValue();

            Integer textInNewspaper = line2TreeMap.get(key);
            //32
            if (!Objects.isNull(textInNewspaper)) {
                if (count < textInNewspaper.intValue()) {
                    yay += count;
                    line2TreeMap.put(key, textInNewspaper - count);
                    line1TreeMapCp.remove(key);
                } else if (count == textInNewspaper.intValue()) {
                    yay += count;
                    line1TreeMapCp.remove(key);
                    line2TreeMap.remove(key);
                } else {
                    yay += textInNewspaper.intValue();
                    line2TreeMap.remove(key);
                }
            }
        }

        for (Map.Entry<Character, Integer> e : line1TreeMapCp.entrySet()) {
            char key = e.getKey();
            int count = e.getValue();
            char oppositeCase = (char) (key <= 'Z' ? (int) key + 32 : (int) key - 32);

            if (line2TreeMap.containsKey(oppositeCase)) {
                whoop+= line2TreeMap.get(oppositeCase) <= count ? line2TreeMap.get(oppositeCase) : count;
            }
        }

        System.out.println(yay + " " + whoop);
    }
}
