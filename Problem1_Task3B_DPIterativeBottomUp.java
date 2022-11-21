

public class Problem1_Task3B_DPIterativeBottomUp extends  BuySellStockProblem1{
	
	@Override
	public void calculateMaxProfit() {
		int [][] dp = new int [m][n];
		int maxBuyDay = 0;
		int maxSellDay = 0;
		int maxStockId = 0;
        int maxStockProfit = 0;
        
        for(int i = 0; i < m; i++){
            int maxStockValue  = 0;
            
            int bestSellDay = -1;
            int bestBuyDay = -1;
            int currMaxProfit = -1;
            
            for(int j = n-1; j >=0 ; j--)
            {
            	if(stockPrices[i][j] > maxStockValue)
            	{
            		bestSellDay = j;
            		maxStockValue = stockPrices[i][j];		
            	}
            	dp[i][j] = maxStockValue;
            }
            
            int currentStockMaxProfit = 0;
            
            for(int j = 0; j<n ; j++){
            	int profit = dp[i][j]-stockPrices[i][j];
            	if(profit> currentStockMaxProfit)
            	{
            		bestBuyDay  = j;
            		currMaxProfit = profit;
            		currentStockMaxProfit = profit;
            	}
            }
            
            if(currMaxProfit>maxStockProfit )
            {
            	maxStockId = i;
            	maxBuyDay = bestBuyDay;
            	maxSellDay = bestSellDay;
            	maxStockProfit = currMaxProfit;
            }
        }
        
        
        maxProfit = maxStockProfit;
        stockId = maxStockId;
        buyDay = maxBuyDay;
        sellDay = maxSellDay;
        
	}
      

}
