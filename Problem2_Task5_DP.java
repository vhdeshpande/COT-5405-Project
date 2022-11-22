
import java.util.ArrayList;
import java.util.Arrays;

public class Problem2_Task5_DP extends BuySellStockProblem2 {

    @Override
    public void calculateMaxProfit() {

        // the profit table keeps track of the maximum profit subproblems keeping track
        // of k atmost transactions and maximum profit
        int[][] profit = new int[k + 1][n + 1];
        // The stock id table maintains the maximum stock for getting the maximum stock
        // value
        int[][] stockId = new int[k + 1][n + 1];
        // the profit table keeps track of the stock bought according keeping track of k
        // transactions
        int[][] buy = new int[k + 1][n + 1];
        ArrayList<ArrayList<Integer>> transactionSequnce = new ArrayList<ArrayList<Integer>>();

        // Intialize the first day values to 0 for profit, stokc is and buy tables
        for (int i = 0; i <= k; i++) {
            profit[i][0] = 0;
            stockId[i][0] = 0;
            buy[i][0] = 0;
        }

        for (int i = 0; i <= n; i++) {
            profit[0][i] = 0;
            stockId[0][i] = 0;
            buy[0][i] = 0;
        }

        // Iterating over the maximum number of trasactions
        for (int i = 1; i <= k; i++) {
            int currMaxStockId = -1;
            int currentMaxBuy = -1;
            // Calculating the current maximum profit value
            for (int j = 1; j < n; j++) {
                int currentMaxProfit = 0;
                for (int p = 0; p < j; p++) {

                    // Updating the profit, stock id and buy table for every stock using bottum up
                    // approack
                    for (int x = 0; x < m; x++) {
                        int currProf = stockPrices[x][j] - stockPrices[x][p] + profit[i - 1][p];
                        if (currProf > currentMaxProfit) {
                            currentMaxProfit = currProf;
                            currMaxStockId = x;
                            currentMaxBuy = p;
                        }
                        if (profit[i][j - 1] > currentMaxProfit) {
                            stockId[i][j] = stockId[i][j - 1];
                            buy[i][j] = buy[i][j - 1];
                        } else {
                            stockId[i][j] = currMaxStockId;
                            buy[i][j] = currentMaxBuy;
                        }
                        profit[i][j] = Math.max(profit[i][j - 1], currentMaxProfit);
                    }

                }

            }

        }
        //Starting from last transactions and the last day for backtracking
        int i = k;
        int j = n - 1;

        // Backtracking to get the transcation sequence for obtaining the maximum profit
        // for at most k transactions
        while (i > 0 && j > 0) {

            // Getting the sell index for the transcations
            while (j - 1 >= 0 && profit[i][j] == profit[i][j - 1]) {
                j--;
            }
            int sellDay = j;
            // Getting the buy index for the transcations
            while (j - 1 >= 0 && profit[i][j] == profit[i][j - 1]) {
                j--;
            }

            int buyDay = buy[i][j];
            int currentStockId = stockId[i][j];

            // Adding the eligible transactions to the final results
            transactionSequnce.add(new ArrayList<Integer>(Arrays.asList(currentStockId, buyDay, sellDay)));
            if (j - 1 >= 0) {
                j--;
            }

            //Decrement the num of transcations after adding the transaction to the sequence
            i--;

        }

        // Return the transcation sequnce and max profit
        transactionSeq = transactionSequnce;
        maxProfit = profit[k][n - 1];

    }
}
