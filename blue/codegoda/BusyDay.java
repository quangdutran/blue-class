package com.company.blue.codegoda;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BusyDay {
    public static void main(String[] args) {
        System.out.println(LocalDate.parse("1-02-01", DateTimeFormatter.ofPattern("Y-mm-dd")));
        String keyMax = "";
        int max = 0;
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        input.nextLine();
        Map<String, Integer> frequency = new HashMap<>();
        for (int i = 0; i < num; i++) {
            String line = input.nextLine();
            String [] date = line.split(" ");

            String [] firstDate = date[0].split("-");
            String [] secondDate = date[1].split("-");

            int firstYear = Integer.parseInt(firstDate[0]);
            int firstMonth = Integer.parseInt(firstDate[1]);
            int firstDay = Integer.parseInt(firstDate[2]);
            int secondYear = Integer.parseInt(secondDate[0]);
            int secondMonth = Integer.parseInt(secondDate[1]);
            int secondDay = Integer.parseInt(secondDate[2]);

            Month fMonth = Month.of(firstMonth);
            MonthDay first = MonthDay.of(fMonth, firstDay);
            MonthDay second = MonthDay.of(secondMonth, secondDay);


            while (firstYear <= secondYear && first.isBefore(second)) {
                String fullDate = firstYear + getMonthDayString(first);
                if (frequency.containsKey(fullDate)) {
                    frequency.put(fullDate, frequency.get(fullDate) + 1);
                    if (frequency.get(fullDate) > max || (max == frequency.get(fullDate) && isFirstBeforeSecond(fullDate, keyMax))) {
                        max = frequency.get(fullDate);
                        keyMax = fullDate;
                    }
                } else {
                    frequency.put(fullDate, 1);
                    if (frequency.get(fullDate) > max || (max == frequency.get(fullDate) && isFirstBeforeSecond(fullDate, keyMax))) {
                        max = frequency.get(fullDate);
                        keyMax = fullDate;
                    }
                }

                firstDay++;
                try {
                    first = MonthDay.of(fMonth, firstDay);
                } catch (DateTimeException e) {
                    firstDay = 1;
                    fMonth = fMonth.plus(1);
                    first = MonthDay.of(fMonth, firstDay);
                    if (first.equals(MonthDay.of(1,1))) {
                        firstYear++;
                    }
                }

            }


        }
        System.out.println(keyMax);
    }

    private static String getMonthDayString(MonthDay monthDay) {
        String month = monthDay.getMonthValue() < 10 ? "0" + monthDay.getMonthValue() : "" + monthDay.getMonthValue();
        String day = monthDay.getDayOfMonth() < 10 ? "0" + monthDay.getDayOfMonth() : "" + monthDay.getDayOfMonth();
        return "-" + month + "-" + day;
    }

    private static boolean isFirstBeforeSecond(String first, String second) {
        int firstYear = Integer.parseInt(first.split("-")[0]);
        int firstMonth = Integer.parseInt(first.split("-")[1]);
        int firstDay = Integer.parseInt(first.split("-")[2]);
        int secondYear = Integer.parseInt(second.split("-")[0]);
        int secondMonth = Integer.parseInt(second.split("-")[1]);
        int secondDay = Integer.parseInt(second.split("-")[2]);

        if (firstYear < secondYear) {
            return true;
        } else if (firstYear == secondYear) {
            return MonthDay.of(firstMonth, firstDay).isBefore(MonthDay.of(secondMonth, secondDay));
        } else return false;

    }
}
