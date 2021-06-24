package com.company.bst;

        import java.util.*;

public class MinimumLoss {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        TreeSet<Long> treeSet = new TreeSet<>();
        treeSet.add(input.nextLong());
        long min = Integer.MAX_VALUE;
        for (int i = 1; i < size; i++) {
            long price = input.nextLong();
            Long higher = treeSet.higher(price);
            if (Objects.nonNull(higher)) {
                min = Math.min(min, higher - price);
            }
            treeSet.add(price);
        }
        System.out.println(min);
    }

}
