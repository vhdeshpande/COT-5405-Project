

public class Problem1_Task2_Greedy extends  BuySellStockProblem1{

	@Override
	public void calculateMaxProfit() {
		
        //Iterating over all the stocks
        for( int i = 0; i < m; i++ ){
        	
            int currentProfit = 0;
            int minPrice = stockPrices[i][0];
            int maxPrice = stockPrices[i][0];
            int buy = 0;
            int sell = 0;
            
            //Iterating over the stock each day to get maximum stock price and minimum stock price for that stock
            for( int j = 1; j < n; j++ ){
            	
                //Update new minimum if new minimum value if found
                //Reset previous maximum value
                if( minPrice > stockPrices[i][j] ){
                	minPrice = stockPrices[i][j];
                	maxPrice = stockPrices[i][j];
                	buy = j;
                }
                //Update new maximum value
                if( maxPrice < stockPrices[i][j] ){
                	maxPrice = stockPrices[i][j];
                }
                
                //Update maximum profit if current profit is greater
                if( currentProfit < maxPrice - minPrice ) {
                    currentProfit = maxPrice - minPrice;
                    sell = j;
                }
            }
            
            if( maxProfit < currentProfit ) {
                stockId = i; 
                buyDay = buy;
                sellDay = sell;
                maxProfit = currentProfit;
            }
        }
	}
		

}
