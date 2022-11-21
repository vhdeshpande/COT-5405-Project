

import java.util.ArrayList;
import java.util.Arrays;

public class Problem1_Task5_DP extends  BuySellStockProblem2{

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

 
        for (int j = 0; j <= n; j++){
            profit[0][j] = 0;
            stockId[0][j] = 0;
            buy[0][j] = 0;
        }
            
 

        for (int i = 1; i <= k; i++)
        {
            int id =-1;
            int b=-1;
            int s = -1;
            for (int j = 1; j < n; j++)
            {
                int max_so_far = 0;
                int maxProf =0;
                
                for (int p = 0; p < j; p++){
                    for(int x = 0; x < m; x++){
                        int currProf = stockPrices[x][j] - stockPrices[x][p] + profit[i - 1][p];
                        if(currProf > max_so_far){
                            max_so_far = currProf;
                            id = x;
                            b = p;
                        }
                        if(profit[i][j - 1] > max_so_far){
                            stockId[i][j] = stockId[i][j-1];
                            buy[i][j] = buy[i][j-1];
                        }
                        else{
                            stockId[i][j] = id;
                            buy[i][j] = b;
                        }
                        profit[i][j] = Math.max(profit[i] [j - 1],max_so_far);
                        maxProf = Math.max(profit[i][j] ,maxProf);
                    }
                   
                }
              
               
                }
                

            }
        
        int i =k;
        int j = n-1;
        while(i>0 && j>0){
                int s = j;
                while( j-1>=0 && profit[i][j] == profit[i][j-1]){
                    j--;
                }

                int b = buy[i][j];
                int id = stockId[i][j];

                transactionSequnce.add(new ArrayList<Integer>(Arrays.asList(id,b,s)));
                if( j-1>=0){
                    j--;
                }
                
            i--;

        }
        
        transactionSeq = transactionSequnce;
        maxProfit = profit[k][n - 1];
		
	}
}
