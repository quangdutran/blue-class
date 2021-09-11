package com.company.alg.orange.dynamicprogramming3;


import java.util.*;

public class TheTowerOfBabylon {
    private static class Brick implements Comparable<Brick> {
        int x;
        int y;
        int z;

        public Brick(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(Brick o) {
            if (x * y > o.x*o.y) {
                return 1;
            } else if (x*y == o.x * o.y) {
                return 0;
            } else return -1;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = 0;
        while(true) {
            int bricks = input.nextInt();
            if (bricks == 0) {
                break;
            }

            List<Brick> brickList = new ArrayList<>();
            for (int i = 0; i < bricks; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                int third = input.nextInt();

                //First, second as length and width
                if (first >= second) {
                    brickList.add(new Brick(first, second, third));
                } else {
                    brickList.add(new Brick(second, first, third));
                }

                //First and third
                if (first >= third) {
                    brickList.add(new Brick(first, third, second));
                } else {
                    brickList.add(new Brick(third, first, second));
                }

                //second and third
                if (second >= third) {
                    brickList.add(new Brick(second, third, first));
                } else {
                    brickList.add(new Brick(third, second, first));
                }
            }

            brickList.sort(Collections.reverseOrder());
            System.out.println("Case "+ ++cases +": maximum height = " + LIS_(brickList));
        }
    }


    private static ArrayList<Integer> dp;
    static int path [];
    static int last;

    private static int LIS_(List<Brick> bricks) {
        dp = new ArrayList<>();
        for (int i = 0; i < bricks.size(); i++) {
            dp.add(bricks.get(i).z);
        }
        for (int i = 1; i < bricks.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (bricks.get(i).x < bricks.get(j).x && bricks.get(i).y < bricks.get(j).y) {
                    dp.set(i, Math.max(dp.get(i), dp.get(j) + bricks.get(i).z));
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < bricks.size(); i++) {
            ans = Math.max(ans, dp.get(i));
        }
        return ans;
    }

    private static int LIS(List<Brick> bricks) {
        int length = 0;
        dp = new ArrayList<>();
        for (int i = 0; i < bricks.size(); i++) {
            dp.add(1);
        }
        path = new int[bricks.size()];
        Arrays.fill(path, -1);
        for (int i = 1; i < bricks.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (bricks.get(i).x < bricks.get(j).x && bricks.get(i).y < bricks.get(j).y && dp.get(i) < dp.get(j) + 1) {
                    dp.set(i, dp.get(j) + 1);
                    path[i] = j;
                }
            }
        }

        for (int i = 0; i < bricks.size(); i++) {
            if (length < dp.get(i)) {
                length = dp.get(i);
                last = i;
            }
        }
        return length;
    }

    private static int getHeight(List<Brick> bricks) {
        ArrayList<Brick> result = new ArrayList<>();
        int i = last;
        while (i >= 0) {
            result.add(bricks.get(i));
            i = path[i];
        }

        return result.stream().map(b -> b.z).reduce(0, Integer::sum);
    }


}
