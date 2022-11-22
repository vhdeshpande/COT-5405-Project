public class Problem1_Task1_BruteForce extends BuySellStockProblem1 {

    @Override
    public void calculateMaxProfit() {

        //Iterating over number of stocks
        for (int k = 0; k < m; k++) {

            int[] prices = stockPrices[k];
            int currentProfit = 0;
            int buy = 0;
            int sell = 0;

            //Iterating over number of days and comparing every stock value to get maximum profit per stock
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {

                    //Update maximum profit value if new maximum id greater than current maximum
                    if (prices[j] > prices[i] && prices[j] - prices[i] > currentProfit) {
                        currentProfit = prices[j] - prices[i];
                        buy = i;
                        sell = j;
                    }
                }
            }
            //Update the maximum profit, buy day, sell day and stock id
            if (maxProfit < currentProfit) {
                stockId = k;
                buyDay = buy;
                sellDay = sell;
                maxProfit = currentProfit;

            }
        }

    }

}
