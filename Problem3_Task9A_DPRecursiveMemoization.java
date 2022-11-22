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
		int x = maxProfitStockId[0], y = 0;
		while( x < m && y < n ){
			while( y < n && dpBuy[x][y] == dpBuy[x][y+1]){
				y++;
			}
			buyDay = y;
			if ( y < n ){
				y++;
			};
			while( y < n && dpSell[x][y] == dpSell[x][y+1] ){
				y++;
			}
			sellDay = y;

			transactionSequnce.add( new ArrayList<Integer>( Arrays.asList( x, buyDay, sellDay ) ) );
			y = y + c + 1;
			if ( y >= n){
				break;
			};
			x=maxProfitStockId[y];
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
				//Sell
				int skipSell = this.dpSell[stockId][day+1];
				int sell = this.maxProfitDay[day] + stockPrices[stockId][day];
				dpSell[stockId][day] = Math.max(skipSell, sell);	
				
				//Buy
				int skipBuy = dpBuy[stockId][day+1];
				int profit = dpSell[stockId][day+1] - stockPrices[stockId][day];
				dpBuy[stockId][day] = Math.max(skipBuy, profit);	
                if(maxValueDay <= dpBuy[stockId][day]){
                    maxProfitStockId[day] = stockId;
                }
                maxValueDay = Math.max(maxValueDay, dpBuy[stockId][day]);		
				this.maxProfitDay[day] = Math.max(this.maxProfitDay[day], dpBuy[stockId][day]);	
			}
			
		}
		else
		{
			int maxValueDayNextStock = ComputeDayProfit( day+1, c, maxValueDay );
			for(int stockId=0; stockId<stockPrices.length; stockId++)
			{
				//Sell
				int skipSell = this.dpSell[stockId][day+1];
				int sell = this.maxProfitDay[day] + stockPrices[stockId][day];
				dpSell[stockId][day] = Math.max(skipSell, sell);	
				
				//Buy
				int skipBuy = dpBuy[stockId][day+1];
                maxValueDayNextStock = Math.max(maxValueDayNextStock, skipBuy);
				int profit = dpSell[stockId][day+1] - stockPrices[stockId][day];
				dpBuy[stockId][day] = Math.max(skipBuy, profit);	
                if(maxValueDay <= dpBuy[stockId][day]){
                    maxProfitStockId[day] = stockId;
                }
                maxValueDay = Math.max(maxValueDay, dpBuy[stockId][day]);			
				this.maxProfitDay[day] = Math.max(this.maxProfitDay[day], dpBuy[stockId][day]);
			}	
		}

		return this.maxProfitDay[day];
	}	
	

}
