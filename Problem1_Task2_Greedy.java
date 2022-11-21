

public class Problem1_Task2_Greedy extends  BuySellStockProblem1{

	@Override
	public void calculateMaxProfit() {
		
        for( int i = 0; i < m; i++ ){
        	
            int currentProfit = 0;
            int minPrice = stockPrices[i][0];
            int maxPrice = stockPrices[i][0];
            int buy = 0;
            int sell = 0;
            
            for( int j = 1; j < n; j++ ){
            	
                if( minPrice > stockPrices[i][j] ){
                	minPrice = stockPrices[i][j];
                	maxPrice = stockPrices[i][j];
                	buy = j;
                }
                if( maxPrice < stockPrices[i][j] ){
                	maxPrice = stockPrices[i][j];
                }
                
                if( currentProfit < maxPrice - minPrice ) {
                    currentProfit = maxPrice - minPrice;
                    sell = j;
                }
            }
            
            if( maxProfit < currentProfit ) {
                stockId = i + 1; 
                buyDay = buy + 1;
                sellDay = sell + 1;
                maxProfit = currentProfit;
            }
        }
	}
		

}
