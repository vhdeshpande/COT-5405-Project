

import java.util.ArrayList;
import java.util.Arrays;

public class Problem2_Task6B_DPIterativeBottomUp extends BuySellStockProblem2{

	@Override
	public void calculateMaxProfit() {
        //Dp table maintain the profit value for k transcations
		int[][][] dpBuy = new int [m][n+1][k+1];
        int[][][] dpSell = new int [m][n+1][k+1];
        int[][] maxProfitDay = new int [n+1][k+1];
        int[][] maxProfitStockId = new int [n+1][k+1];
        ArrayList<ArrayList<Integer>> transactionSequnce = new ArrayList<ArrayList<Integer>>();
        
        for( int z = 1; z <= k; z++ )
        {
            for( int j = n-1; j >= 0; j-- )
            {
                int maxValueDay = 0;
                for( int i = 0; i < m; i++)
                {
                    int skipSell = dpSell[i][j+1][z];
                    int sell = maxProfitDay[j][z-1] + stockPrices[i][j];
                    dpSell[i][j][z] = Math.max(skipSell, sell);
                    
                    int skipBuy = dpBuy[i][j+1][z];
                    int profit = dpSell[i][j+1][z] - stockPrices[i][j];
                    dpBuy[i][j][z] = Math.max(skipBuy, profit);         
                    
                    if(maxValueDay <= dpBuy[i][j][z]){
                        maxProfitStockId[j][z] = i;
                    }
                    maxValueDay = Math.max(maxValueDay, dpBuy[i][j][z]);
                    maxProfitDay[j][z] = maxValueDay;
                                        
                }
            }
        }
        maxProfit = maxProfitDay[0][k];

        //Backtracking to get the transcation sequence for obtaining the maximum profit for at most k transactions

        int b=-1,s=-1;
        int x=maxProfitStockId[0][k], y=0, z=k;
        while(z>0 && x<m && y<n){
            //Getting the buy index for the transcations
            while(y<n && dpBuy[x][y][z] == dpBuy[x][y+1][z]){
                y++;
            }
            b = y;
            if (y<n){
                y++;
            };
            //Getting the sell index for the transcations
            while(y<n && dpSell[x][y][z] == dpSell[x][y+1][z]){
                y++;
            }
            s=y;
            if (y>=n){
                break;
            };

            //Adding the eligible transactions to the final results
            transactionSequnce.add( new ArrayList<Integer>( Arrays.asList( x, b, s ) ) );
            //Decrement the number of transcations
            z--;
            //Moving to the next stock id to obtain the transcation sequence
            x=maxProfitStockId[y][z];
        }
        
        transactionSeq = transactionSequnce;
		
	}
	
}
