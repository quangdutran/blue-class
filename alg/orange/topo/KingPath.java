package com.company.alg.orange.topo;
import java.util.*;

public class KingPath {

    final static int MAX = (int) Math.pow(10,9);
    static HashMap<Integer, Set<Integer>> visited = new HashMap<>();
    static HashMap<Integer, RowCol> movableCells = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        RowCol start = new RowCol(input.nextInt() - 1, input.nextInt() - 1);
        RowCol end = new RowCol(input.nextInt() - 1, input.nextInt() - 1);
        int count = input.nextInt();
        for (int i = 0; i < count; i++) {
            //minus 1 to use index 0 base
            int row = input.nextInt() - 1;
            int startCol = input.nextInt() - 1;
            int endCol = input.nextInt() - 1;
            if (movableCells.containsKey(row)) {
                int movableStartPoint = Math.min(movableCells.get(row).row, startCol);
                int movableEndPoint = Math.max(movableCells.get(row).col, endCol);
                movableCells.put(row, new RowCol(movableStartPoint, movableEndPoint));
            } else {
                movableCells.put(row, new RowCol(startCol, endCol));
            }
        }

        LinkedList<RowCol> queue = new LinkedList<>();
        queue.add(start);
        start.visited = true;
        while(!queue.isEmpty()) {
            RowCol top = queue.pop();
            for (RowCol neighbor : getMovableUnvisitedNeighbors(top)) {
                queue.add(neighbor);
                neighbor.distance = top.distance + 1;
                if (neighbor.row == end.row && neighbor.col == end.col) {
                    System.out.println(neighbor.distance);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    public static List<RowCol> getMovableUnvisitedNeighbors(RowCol cell) {
        List<RowCol> result = new ArrayList<>();
        //up
        if (cell.row - 1 >= 0 && cell.row - 1 < MAX //valid coordinate
            && movableCells.containsKey(cell.row - 1) && movableCells.get(cell.row - 1).row <= cell.col && cell.col <= movableCells.get(cell.row - 1).col //movable
            && (!visited.containsKey(cell.row - 1) || (visited.containsKey(cell.row - 1) && !visited.get(cell.row - 1).contains(cell.col)))) {
            result.add(new RowCol(cell.row - 1, cell.col));
            if (visited.containsKey(cell.row - 1)) {
                visited.get(cell.row - 1).add(cell.col);
            } else {
                visited.put(cell.row -1, new HashSet<>(Arrays.asList(cell.col)));
            }
        }
        //down
        if (cell.row + 1 >= 0 && cell.row + 1 < MAX //valid coordinate
                && movableCells.containsKey(cell.row + 1) && movableCells.get(cell.row + 1).row <= cell.col && cell.col <= movableCells.get(cell.row + 1).col //movable
                && (!visited.containsKey(cell.row + 1) || (visited.containsKey(cell.row + 1) && !visited.get(cell.row + 1).contains(cell.col)))) {
            result.add(new RowCol(cell.row + 1, cell.col));
            if (visited.containsKey(cell.row + 1)) {
                visited.get(cell.row + 1).add(cell.col);
            } else {
                visited.put(cell.row +1, new HashSet<>(Arrays.asList(cell.col)));
            }
        }
        //right
        if (cell.col + 1 >= 0 && cell.col + 1 < MAX //valid coordinate
                && movableCells.containsKey(cell.row) && movableCells.get(cell.row).row <= cell.col + 1 && cell.col + 1 <= movableCells.get(cell.row).col //movable
                && (!visited.containsKey(cell.row) || (visited.containsKey(cell.row) && !visited.get(cell.row).contains(cell.col + 1)))) {
            result.add(new RowCol(cell.row, cell.col + 1));
            if (visited.containsKey(cell.row)) {
                visited.get(cell.row).add(cell.col + 1);
            } else {
                visited.put(cell.row, new HashSet<>(Arrays.asList(cell.col + 1)));
            }
        }
        //left
        if (cell.col - 1 >= 0 && cell.col - 1 < MAX //valid coordinate
                && movableCells.containsKey(cell.row) && movableCells.get(cell.row).row <= cell.col - 1 && cell.col - 1 <= movableCells.get(cell.row).col //movable
                && (!visited.containsKey(cell.row) || (visited.containsKey(cell.row) && !visited.get(cell.row).contains(cell.col - 1)))) {
            result.add(new RowCol(cell.row, cell.col - 1));
            if (visited.containsKey(cell.row)) {
                visited.get(cell.row).add(cell.col - 1);
            } else {
                visited.put(cell.row, new HashSet<>(Arrays.asList(cell.col - 1)));
            }
        }
        //diagonal up left
        if (cell.col - 1 >= 0 && cell.col - 1 < MAX && cell.row - 1 >= 0 && cell.row - 1 < MAX //valid coordinate
                && (!visited.containsKey(cell.row - 1) || (visited.containsKey(cell.row - 1) && !visited.get(cell.row - 1).contains(cell.col - 1)))
                && movableCells.containsKey(cell.row - 1) && movableCells.get(cell.row - 1).row <= cell.col - 1 && cell.col - 1 <= movableCells.get(cell.row - 1).col //movable
                ) {
            result.add(new RowCol(cell.row - 1, cell.col - 1));
            if (visited.containsKey(cell.row - 1)) {
                visited.get(cell.row - 1).add(cell.col - 1);
            } else {
                visited.put(cell.row - 1, new HashSet<>(Arrays.asList(cell.col - 1)));
            }
        }
        //diagonal up right
        if (cell.col + 1 >= 0 && cell.col + 1 < MAX && cell.row - 1 >= 0 && cell.row - 1 < MAX //valid coordinate
                && (!visited.containsKey(cell.row - 1) || (visited.containsKey(cell.row - 1) && !visited.get(cell.row - 1).contains(cell.col + 1)))
                && movableCells.containsKey(cell.row - 1) && movableCells.get(cell.row - 1).row <= cell.col + 1 && cell.col + 1 <= movableCells.get(cell.row - 1).col //movable
        ) {
            result.add(new RowCol(cell.row - 1, cell.col + 1));
            if (visited.containsKey(cell.row - 1)) {
                visited.get(cell.row - 1).add(cell.col + 1);
            } else {
                visited.put(cell.row - 1, new HashSet<>(Arrays.asList(cell.col + 1)));
            }
        }

        //diagonal down right
        if (cell.col + 1 >= 0 && cell.col + 1 < MAX && cell.row + 1 >= 0 && cell.row + 1 < MAX //valid coordinate
                && (!visited.containsKey(cell.row + 1) || (visited.containsKey(cell.row + 1) && !visited.get(cell.row + 1).contains(cell.col + 1)))
                && movableCells.containsKey(cell.row + 1) && movableCells.get(cell.row + 1).row <= cell.col + 1 && cell.col + 1 <= movableCells.get(cell.row + 1).col //movable
        ) {
            result.add(new RowCol(cell.row + 1, cell.col + 1));
            if (visited.containsKey(cell.row + 1)) {
                visited.get(cell.row + 1).add(cell.col + 1);
            } else {
                visited.put(cell.row + 1, new HashSet<>(Arrays.asList(cell.col + 1)));
            }
        }
        //diagonal down left
        if (cell.col - 1 >= 0 && cell.col - 1 < MAX && cell.row + 1 >= 0 && cell.row + 1 < MAX //valid coordinate
                && (!visited.containsKey(cell.row + 1) || (visited.containsKey(cell.row + 1) && !visited.get(cell.row+1).contains(cell.col - 1)))
                && movableCells.containsKey(cell.row + 1) && movableCells.get(cell.row + 1).row <= cell.col - 1 && cell.col - 1 <= movableCells.get(cell.row + 1).col //movable
        ) {
            result.add(new RowCol(cell.row + 1, cell.col - 1));
            if (visited.containsKey(cell.row + 1)) {
                visited.get(cell.row + 1).add(cell.col - 1);
            } else {
                visited.put(cell.row + 1, new HashSet<>(Arrays.asList(cell.col - 1)));
            }
        }

        return result;
    }
}

class RowCol {
    int row;
    int col;
    boolean visited;
    int distance;

    public RowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public RowCol(int row, int col, boolean visited) {
        this.row = row;
        this.col = col;
        this.visited = visited;
    }
}