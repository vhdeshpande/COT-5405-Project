import java.util.ArrayList;
import java.util.Arrays;

public class Problem3_Task9A_DPRecursiveMemoization extends BuySellStockProblem3{
    public int[][] dpBuy;
	public int[][] dpSell;
	public int[] maxProfitDay;
    public int[] maxProfitStockId;

	@Override
	public void calculateMaxProfit() {

        Problem3_Task9A_DPRecursiveMemoization buySell = new Problem3_Task9A_DPRecursiveMemoization();
		buySell.dpBuy = new int [m][n+1];
		buySell.dpSell = new int [m][n+1];
		buySell.maxProfitDay = new int [n+1];
        buySell.maxProfitStockId = new int [n+1];
        ArrayList<ArrayList<Integer>> transactionSequnce = new ArrayList<ArrayList<Integer>>();
			
		int outputProfit = 0;
		int res = buySell.ComputeDayProfit(0, c, 0);
		maxProfit = Math.max(res, outputProfit);        

        int buyDay = -1,sellDay = -1;
		int x = buySell.maxProfitStockId[0], y = 0;
		while( x < m && y < n ){
			//Getting the sell index for the transcations
			while( y < n && buySell.dpBuy[x][y] == buySell.dpBuy[x][y+1]){
				y++;
			}
			//Getting the buy index for the transcations
			buyDay = y;
			if ( y < n ){
				y++;
			};
			while( y < n && buySell.dpSell[x][y] == buySell.dpSell[x][y+1] ){
				y++;
			}
			sellDay = y;

            //Adding the eligible transactions to the final results
			transactionSequnce.add( new ArrayList<Integer>( Arrays.asList( x, buyDay, sellDay ) ) );
			//Increment the value with cooldown
			y = y + c + 1;
			if ( y >= n){
				break;
			};
			x=buySell.maxProfitStockId[y];
		}
		
        transactionSeq = transactionSequnce;
		
	}

	public int ComputeDayProfit(int day, int c, int maxValueDay)
	{
		//Last day and last stock
		if(day == stockPrices[0].length-1)
		{
            maxValueDay = 0;
			for(int stockId=0; stockId<stockPrices.length;stockId++)
			{
				//Calculating the skip sell and sell to obtain the maximum profits
				int skipSell = this.dpSell[stockId][day+1];
                int maxprof = 0;
                if(day+c+1 <= n) {
					maxprof = this.maxProfitDay[day + c + 1];
				}
				int sell =  maxprof + stockPrices[stockId][day];
				dpSell[stockId][day] = Math.max(skipSell, sell);	
				
				//Calculating the skip buy and buy to obtain the maximum profits
				int skipBuy = dpBuy[stockId][day+1];
				int profit = dpSell[stockId][day+1] - stockPrices[stockId][day];
				dpBuy[stockId][day] = Math.max(skipBuy, profit);	
                if(maxValueDay <= dpBuy[stockId][day]){
                    maxProfitStockId[day] = stockId;
                }
				//Return the maximum value for the day and update the table
                maxValueDay = Math.max(maxValueDay, dpBuy[stockId][day]);		
				this.maxProfitDay[day] = Math.max(this.maxProfitDay[day], dpBuy[stockId][day]);	
			}
			
		}
		else
		{
			int maxValueDayNextStock = ComputeDayProfit( day+1, c, maxValueDay );
			for(int stockId=0; stockId<stockPrices.length; stockId++)
			{
				//Calculating the skip sell and sell to obtain the maximum profits
				int skipSell = this.dpSell[stockId][day+1];
                int maxprof = 0;
                if(day+c+1 <= n) {
					maxprof = this.maxProfitDay[day + c + 1];
				}
				int sell =  maxprof + stockPrices[stockId][day];
				dpSell[stockId][day] = Math.max(skipSell, sell);	
				
				//Calculating the skip buy and buy to obtain the maximum profits
				int skipBuy = dpBuy[stockId][day+1];
                maxValueDayNextStock = Math.max(maxValueDayNextStock, skipBuy);
				int profit = dpSell[stockId][day+1] - stockPrices[stockId][day];
				dpBuy[stockId][day] = Math.max(skipBuy, profit);	
                if(maxValueDay <= dpBuy[stockId][day]){
                    maxProfitStockId[day] = stockId;
                }
				//Return the maximum value for the day and update the table
                maxValueDay = Math.max(maxValueDay, dpBuy[stockId][day]);			
				this.maxProfitDay[day] = Math.max(this.maxProfitDay[day], dpBuy[stockId][day]);
			}	
		}
  		//Return maximum profit
		return this.maxProfitDay[day];
	}	
	

}
