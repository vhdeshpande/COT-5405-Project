public class Problem1_Task1_BruteForce extends BuySellStockProblem1 {

    @Override
    public void calculateMaxProfit() {

        for (int k = 0; k < m; k++) {

            int[] prices = stockPrices[k];
            int currentProfit = 0;
            int buy = 0;
            int sell = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {

                    if (prices[j] > prices[i] && prices[j] - prices[i] > currentProfit) {
                        currentProfit = prices[j] - prices[i];
                        buy = i;
                        sell = j;
                    }
                }
            }
            if (maxProfit < currentProfit) {
                stockId = k;
                buyDay = buy;
                sellDay = sell;
                maxProfit = currentProfit;

            }
        }

    }

}
