import java.util.ArrayList;
import java.util.Arrays;

public class Problem2_Task6A_DPRecursiveMemoization extends BuySellStockProblem2{
    public int[][][] dpBuy;
	public int[][][] dpSell;
	public int[][] maxProfitDay;
    public int[][] maxProfitStockId;

	@Override
	public void calculateMaxProfit() {

        Problem2_Task6A_DPRecursiveMemoization buySell = new Problem2_Task6A_DPRecursiveMemoization();
		buySell.dpBuy = new int [m][n+1][k+1];
		buySell.dpSell = new int [m][n+1][k+1];
		buySell.maxProfitDay = new int [n+1][k+1];
        buySell.maxProfitStockId = new int [n+1][k+1];
        ArrayList<ArrayList<Integer>> transactionSequnce = new ArrayList<ArrayList<Integer>>();
			
		int outputProfit = 0;
		for(int z = 1;z<=k;z++)
		{
			int res = buySell.ComputeDayProfit(0, z, 0);
			outputProfit = Math.max(res, outputProfit);
		}        
        maxProfit = outputProfit;
        //Backtracking to get the transcation sequence for obtaining the maximum profit for at most k transactions

        int b=-1,s=-1;
        int x = buySell.maxProfitStockId[0][k], y=0, z=k;
        while(z > 0 && x< m && y < n){
            while(y<n && buySell.dpBuy[x][y][z] == buySell.dpBuy[x][y+1][z]){
                y++;
            }
            //Getting the buy index for the transcations
            b = y;
            if (y<n){
                y++;
            };
            //Getting the sell index for the transcations

            while(y<n && buySell.dpSell[x][y][z] == buySell.dpSell[x][y+1][z]){
                y++;
            }
            s=y;
            if (y>=n){
                break;
            };
            transactionSequnce.add( new ArrayList<Integer>( Arrays.asList( x, b, s ) ) );
            //Decrement the num of transcations after adding the transaction to the sequence
            z--;
            x=buySell.maxProfitStockId[y][z];
        }
        
        //Update final transaction sequence
        transactionSeq = transactionSequnce;
		
	}

    //Compute the days profit
	public int ComputeDayProfit(int day, int z, int maxValueDay)
	{
		//Last day and last stock
		if(day == stockPrices[0].length-1)
		{
            maxValueDay = 0;
			for(int stockId=0; stockId<stockPrices.length;stockId++)
			{
				//Calculating the skip sell and sell to obtain the maximum profits
				int skipSell = this.dpSell[stockId][day+1][z];
				int sell = this.maxProfitDay[day][z-1] + stockPrices[stockId][day];
				dpSell[stockId][day][z] = Math.max(skipSell, sell);	
				
				//Calculating the skip buy and buy to obtain the maximum profits
				int skipBuy = dpBuy[stockId][day+1][z];
				int profit = dpSell[stockId][day+1][z] - stockPrices[stockId][day];
				dpBuy[stockId][day][z] = Math.max(skipBuy, profit);	
                if(maxValueDay <= dpBuy[stockId][day][z]){
                    maxProfitStockId[day][z] = stockId;
                }
                //Return the maximum value for the day and update the table
                maxValueDay = Math.max(maxValueDay, dpBuy[stockId][day][z]);		
				this.maxProfitDay[day][z] = Math.max(this.maxProfitDay[day][z], dpBuy[stockId][day][z]);	
			}
			
		}
		else
		{
			int maxValueDayNextStock = ComputeDayProfit( day+1, z, maxValueDay );
			for(int stockId=0; stockId<stockPrices.length; stockId++)
			{
				//Calculating the skip sell and sell to obtain the maximum profits
				int skipSell = this.dpSell[stockId][day+1][z];
				int sell = this.maxProfitDay[day][z-1] + stockPrices[stockId][day];
				dpSell[stockId][day][z] = Math.max(skipSell, sell);	
				
				//Calculating the skip buy and buy to obtain the maximum profits
				int skipBuy = dpBuy[stockId][day+1][z];
                maxValueDayNextStock = Math.max(maxValueDayNextStock, skipBuy);
				int profit = dpSell[stockId][day+1][z] - stockPrices[stockId][day];
				dpBuy[stockId][day][z] = Math.max(skipBuy, profit);	
                if(maxValueDay <= dpBuy[stockId][day][z]){
                    maxProfitStockId[day][z] = stockId;
                }
                //Return the maximum value for the day and update the table
                maxValueDay = Math.max(maxValueDay, dpBuy[stockId][day][z]);			
				this.maxProfitDay[day][z] = Math.max(this.maxProfitDay[day][z], dpBuy[stockId][day][z]);
			}	
		}

        //Return maximum profit
		return this.maxProfitDay[day][z];
	}	
	

}
