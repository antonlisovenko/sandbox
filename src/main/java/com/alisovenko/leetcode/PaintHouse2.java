package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/28/16.
 */
public class PaintHouse2 {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;

        // paint number -> total cost. Leader and "second place" for previous house and current one.
        int prevHouseFirstIdx = 0, prevHouseFirstCost = 0, prevHouseSecondCost = 0;
        int k = costs[0].length;

        for (int[] cost : costs) {
            int curHouseFirstIdx = 0, curHouseFirstCost = Integer.MAX_VALUE, curHouseSecondCost = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                int c = cost[j];
                if (prevHouseFirstIdx != j) c += prevHouseFirstCost;
                else c += prevHouseSecondCost;

                if (curHouseFirstCost > c) {
                    curHouseSecondCost = curHouseFirstCost;
                    curHouseFirstIdx = j;
                    curHouseFirstCost = c;
                } else if (curHouseSecondCost > c) {
                    curHouseSecondCost = c;
                }
            }
            prevHouseFirstIdx = curHouseFirstIdx;
            prevHouseFirstCost = curHouseFirstCost;
            prevHouseSecondCost = curHouseSecondCost;
        }
        return prevHouseFirstCost;
    }

    public static void main(String[] args) {
        System.out.println(new PaintHouse2().minCostII(new int[][]{{1, 5, 3}, {2, 9, 4}}));
    }
}
