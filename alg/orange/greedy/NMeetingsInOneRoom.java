package com.company.alg.orange.greedy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * GeeksForGeeks: greedy algorithm, sort by the end time, pick meetings not overlapping
 */
public class NMeetingsInOneRoom {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while (--cases >= 0) {
            int meetings = input.nextInt();
            long start [] = new long [meetings];
            long end [] = new long [meetings];
            List<Time> meetingTime = new ArrayList<>();
            for (int i = 0; i < meetings; i++) {
                start[i] = input.nextInt();
            }
            for (int i = 0; i < meetings; i++) {
                end[i] = input.nextInt();
            }

            for (int i = 0; i < meetings; i++) {
                meetingTime.add(new Time(start[i], end[i], i+1));
            }

            Collections.sort(meetingTime);

            List<Time> selectedList = new ArrayList<>();
            selectedList.add(meetingTime.get(0));
            Time selected = meetingTime.get(0);
            for (int i = 1; i < meetings; i++) {
                if (selected.end < meetingTime.get(i).start) {
                    selected = meetingTime.get(i);
                    selectedList.add(meetingTime.get(i));
                }
            }
            System.out.println(selectedList.stream().map(Time::getIndex).
                    map(Object::toString).collect(Collectors.joining(" ")));
        }
    }


    static class Time implements Comparable<Time> {
        Long start;
        Long end;
        int index;

        public Time(long start, long end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }

        @Override
        public int compareTo(Time o) {

            if (end.compareTo(o.end) != 0) {
                return end.compareTo(o.end);
            } else {
                return start.compareTo(o.start);
            }
        }
        public int getIndex() {
            return this.index;
        }
    }
}
