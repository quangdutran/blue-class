import java.util.*;

public class IntegerDistribution {
    private String distribution;
    List<Integer> chances = new ArrayList<>();
    List<Integer> coins = new ArrayList<>();
    List<Integer> cummulativeSum = new ArrayList<>();
    Random random = new Random();

    public IntegerDistribution(String distribution) {
        this.distribution = distribution;
    }

    public Integer getRandom() {
        split();
        int randomNumber = random.nextInt(100) + 1;
        for (int i = 0; i < cummulativeSum.size(); i++) {
            if (randomNumber <= cummulativeSum.get(i)) {
                return coins.get(i);
            }
        }
        return coins.get(coins.size() - 1);
    }

    public static void main(String[] args) {
        IntegerDistribution test = new IntegerDistribution("0.5=1000,0.3=5000,0.15=10000,0.05=1000000");
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1000, 0);
        map.put(5000, 0);
        map.put(10000, 0);
        map.put(1000000, 0);
        for (int i = 0; i < 10000; i++) {
            int result = test.getRandom();
            map.put(result, map.get(result) + 1);
        }
        System.out.println(map);
    }

    /**
     * Get the chances and the coins from properties string
     */
    private void split() {
        if (distribution != null) {
            String keyValues [] = distribution.split(",");
            int sum = 0;
            for (String keyValue : keyValues) {
                String values [] = keyValue.split("=");
                int chance = (int) (Double.parseDouble(values[0]) * 100);
                chances.add(chance);
                coins.add(Integer.valueOf(values[1]));
                sum += chance;
                cummulativeSum.add(sum);
            }
        }
    }
}
