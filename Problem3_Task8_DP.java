import java.util.ArrayList;
import java.util.Arrays;

public class Problem3_Task8_DP extends  BuySellStockProblem3{

	@Override
	public void calculateMaxProfit() {

		// the profit table keeps track of the maximum profit subproblems keeping track
        int[] profit = new int[n + 1];
		// The stock id table maintains the maximum stock for getting the maximum stock
        // value
        int[] stockId = new int[n + 1];
		// the profit table keeps track of the stock bought according keeping track of k
        // transactions
        int[] buy = new int[n + 1];
		ArrayList<ArrayList<Integer>> transactionSequnce = new ArrayList<ArrayList<Integer>>();
   
		// Intialize the first day values to 0 for profit, stokc is and buy tables
        for (int j = 0; j <= n; j++) {
            profit[j] = 0; 
            stockId[j] = 0; 
            buy[j] = 0; 
        }
           
		//Initialize the current stock id and maximum buy value to -1
		int currMaxStockId =-1;
		int currentMaxBuy=-1;

		// Iterating over the maximum number of trasactions
        for (int j = 1; j < n; j++) { 
            int currentMaxProfit = 0; 
            for (int p = 0; p < j; p++){ 
                for(int x = 0; x < m; x++){ 
                    int currProf = stockPrices[x][j] -stockPrices[x][p] ;
					//Update current maximum profit
                    if(currProf > currentMaxProfit){
                        currentMaxProfit = currProf;
                        currMaxStockId = x;
                        currentMaxBuy = p;
                    }
					//Check if the cooldown value plus the day value exceed the days available and calculate the max profit taking the cooldown value
                    if(p-c-1 >= 0)
                    { 
                        currentMaxProfit = Math.max(currentMaxProfit, stockPrices[x][j] -stockPrices[x][p] + profit[p-c-1]);
                    } 

					//Update the stock id and the buy values according the calculated max profit
                    if(profit[j - 1] > currentMaxProfit){
                        stockId[j] = stockId[j-1];
                        buy[j] = buy[j-1];
                    }
                    else{
                        stockId[j] = currMaxStockId;
                        buy[j] = currentMaxBuy;
                    }
                    profit[j] = Math.max( profit[ j - 1 ], currentMaxProfit ); 
                } 
            } 
        } 

		//Starting form last day
        int j = n-1;
       
		// Backtracking to get the transcation sequence for obtaining the maximum profit
        // for at most k transactions
        while(j>0){
			  	// Getting the sell index for the transcations
                while( j-1 >= 0 && profit[j] == profit[j-1]){
                    j--;
                }
                int sellDay = j;
				// Getting the buy index for the transcations

                while( j-1 >= 0 && profit[j] == profit[j-1]){
                    j--;
                }
                int buyDay = buy[j];
                int currentStockId = stockId[j];

                //Adding the eligible transactions to the final results
                transactionSequnce.add( new ArrayList<Integer>( Arrays.asList( currentStockId, buyDay, sellDay ) ) );
                //Sell transaction following c cooldown days 
				j = j - c - 1;
                if ( j >= 0 ){
                    break;
                };
        }
		// Return the transcation sequnce and max profit

		transactionSeq = transactionSequnce;
        maxProfit = profit[ n - 1 ];


		
	}

}
