import java.util.ArrayList;
import java.util.Arrays;

public class Problem3_Task8_DP extends  BuySellStockProblem3{

	@Override
	public void calculateMaxProfit() {

        int[] profit = new int[n + 1];
        int[] stockId = new int[n + 1];
        int[] buy = new int[n + 1];
		ArrayList<ArrayList<Integer>> transactionSequnce = new ArrayList<ArrayList<Integer>>();
   
        for (int j = 0; j <= n; j++) {
            profit[j] = 0; 
            stockId[j] = 0; 
            buy[j] = 0; 
        }
            
		int currMaxStockId =-1;
		int currentMaxBuy=-1;

        for (int j = 1; j < n; j++) { 
            int currentMaxProfit = 0; 
            for (int p = 0; p < j; p++){ 
                for(int x = 0; x < m; x++){ 
                    int currProf = stockPrices[x][j] -stockPrices[x][p] ;
                    if(currProf > currentMaxProfit){
                        currentMaxProfit = currProf;
                        currMaxStockId = x;
                        currentMaxBuy = p;
                    }
                    if(p-c-1 >= 0)
                    { 
                        currentMaxProfit = Math.max(currentMaxProfit, stockPrices[x][j] -stockPrices[x][p] + profit[p-c-1]);
                    } 

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

        int j = n-1;
       
        while(j>0){
                while( j-1 >= 0 && profit[j] == profit[j-1]){
                    j--;
                }
                int sellDay = j;
                while( j-1 >= 0 && profit[j] == profit[j-1]){
                    j--;
                }

                int buyDay = buy[j];
                int currentStockId = stockId[j];

                transactionSequnce.add( new ArrayList<Integer>( Arrays.asList( currentStockId, buyDay, sellDay ) ) );
                j = j - c - 1;
                if ( j >= 0 ){
                    break;
                };
        }
		transactionSeq = transactionSequnce;
        maxProfit = profit[ n - 1 ];


		
	}

}
