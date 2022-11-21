

import java.util.ArrayList;
import java.util.Arrays;

public class Problem3_Task9B_DPIterativeBottomUp extends BuySellStockProblem3{
	@Override
	public void calculateMaxProfit() {
		int[][] dpBuy = new int [m][n+1];
        int[][] dpSell = new int [m][n+1];
        int[] maxProfitDay = new int [n+1];
        int[] maxProfitStockId = new int [n+1];
        ArrayList<ArrayList<Integer>> transactionSequnce = new ArrayList<ArrayList<Integer>>();
        
		int j=n-1;
		while(j>=0 )
		{
			int maxValueDay = 0;
			for(int i=0;i<m;i++)
			{
				int skipSell = dpSell[i][j+1];
				int maxprof = 0;
				if(j+2+1<=n) {
					maxprof = maxProfitDay[j+2+1];
				}
				int sell =  maxprof + stockPrices[i][j];
				dpSell[i][j] = Math.max(skipSell, sell);
				
				int skipBuy = dpBuy[i][j+1];
				int profit = dpSell[i][j+1] - stockPrices[i][j];
				dpBuy[i][j] = Math.max(skipBuy, profit);			

				
				if(maxValueDay <= dpBuy[i][j]){
					maxProfitStockId[j] = i;
				}
				maxValueDay = Math.max(maxValueDay, dpBuy[i][j]);
				maxProfitDay[j] = maxValueDay;
									
			}
			j--;
		}

        maxProfit = maxProfitDay[0];
        
		int b=-1,s=-1;
		int x=maxProfitStockId[0], y=0;
		while(x<m && y<n){
			while(y<n && dpBuy[x][y] == dpBuy[x][y+1]){
				y++;
			}
			b = y;
			if (y<n){
				y++;
			};
			while(y<n && dpSell[x][y] == dpSell[x][y+1]){
				y++;
			}
			s=y;

			transactionSequnce.add(new ArrayList<Integer>(Arrays.asList(x,b,s)));
			y = y+c+1;
			if (y>=n){
				break;
			};
			x=maxProfitStockId[y];
		}
		
        transactionSeq = transactionSequnce;
		
	}

}
