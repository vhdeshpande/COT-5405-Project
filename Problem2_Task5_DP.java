

import java.util.ArrayList;
import java.util.Arrays;

public class Problem2_Task5_DP extends  BuySellStockProblem2{

	@Override
	public void calculateMaxProfit() {
		int[][] profit = new int[k + 1][n + 1];
        int[][] stockId = new int[k + 1][n + 1];
        int[][] buy = new int[k + 1][n + 1];
        ArrayList<ArrayList<Integer>> transactionSequnce = new ArrayList<ArrayList<Integer>>();
        
        for (int i = 0; i <= k; i++){
            profit[i][0] = 0;
            stockId[i][0] = 0;
            buy[i][0] = 0;
        }

 
        for (int i = 0; i <= n; i++){
            profit[0][i] = 0;
            stockId[0][i] = 0;
            buy[0][i] = 0;
        }
            
        for (int i = 1; i <= k; i++)
        {
            int currMaxStockId =-1;
            int currentMaxBuy=-1;
            for (int j = 1; j < n; j++)
            {
                int currentMaxProfit = 0;
                for (int p = 0; p < j; p++){
                    for(int x = 0; x < m; x++){
                        int currProf = stockPrices[x][j] - stockPrices[x][p] + profit[i - 1][p];
                        if(currProf > currentMaxProfit){
                            currentMaxProfit = currProf;
                            currMaxStockId = x;
                            currentMaxBuy = p;
                        }
                        if(profit[i][j - 1] > currentMaxProfit){
                            stockId[i][j] = stockId[i][j-1];
                            buy[i][j] = buy[i][j-1];
                        }
                        else{
                            stockId[i][j] = currMaxStockId;
                            buy[i][j] = currentMaxBuy;
                        }
                        profit[i][j] = Math.max(profit[i] [j - 1],currentMaxProfit);
                    }
                   
                }
              
            }
                

            }
            int i =k;
            int j = n-1;
    
            while(i>0 && j>0){
                    while( j-1>=0 && profit[i][j] == profit[i][j-1]){
                        j--;
                    }
                    int sellDay = j;
                    while( j-1>=0 && profit[i][j] == profit[i][j-1]){
                        j--;
                    }
    
                    int buyDay = buy[i][j];
                    int currentStockId = stockId[i][j];
    
                    transactionSequnce.add( new ArrayList<Integer>( Arrays.asList( currentStockId, buyDay, sellDay )));
                    if( j-1 >= 0){
                        j--;
                    }
                    
                i--;
    
            }
        
        transactionSeq = transactionSequnce;
        maxProfit = profit[k][n - 1];
		
	}
}
