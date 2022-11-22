class Output{
	public int stockId;
	public int buy;
	public int sell;
	public int maxProfit;
}
public class Problem1_Task3A_DPRecursiveMemoization extends  BuySellStockProblem1{
	
	@Override
	public void calculateMaxProfit() {
        Output buySell = maximizeProfitDPRecursiveMem(stockPrices);
        stockId = buySell.stockId;
        buyDay = buySell.buy;
        sellDay = buySell.sell;
        maxProfit = buySell.maxProfit;
	}

    //Initialize the dp table
    public static Output maximizeProfitDPRecursiveMem(int[][] stockPrices){
        int [][] dp = new int [stockPrices.length][stockPrices[0].length];
        return maximizeProfitDPRecursive(stockPrices,dp,0);
    }
    
    public static Output maximizeProfitDPRecursive(int[][] stockPrices,int[][] dp, int index){
        if (index == stockPrices.length-1){
            return calculateMaxProfit(stockPrices[index],dp[index],0,stockPrices[index][0]);
        }

        //Calculate maximun profit for the stock
        Output currentMax = calculateMaxProfit(stockPrices[index],dp[index],0,stockPrices[index][0]);
        currentMax.stockId = index;
        Output currentProfit = maximizeProfitDPRecursive(stockPrices,dp,index+1);
        currentProfit.stockId = index+1;
        if(currentProfit.maxProfit > currentMax.maxProfit)
        {
            return currentProfit;
        }
        else
        {
            return currentMax;
        }
    }
    
    public static Output calculateMaxProfit(int[] stockPriceArr, int[] dp, int index, int max){
        
        //Calculate maximum profit for the stock keeping track of the earlier maximum values
        if (index == stockPriceArr.length-1){
            Output op = new Output();
            op.maxProfit = 0;
            op.sell = index;
            dp[index] = stockPriceArr[index];
            return op;
        }
        //Calculate maximum profit vlaue till date
        Output maxProfitTillDate = calculateMaxProfit(stockPriceArr,dp,index+1,max);
        
        //Update the maximum value if current proce is greater than maximum value till date else continue the same value
        if(stockPriceArr[index] > dp[index+1])
        {
            maxProfitTillDate.sell = index;
            dp[index] = stockPriceArr[index];
        }
        else
        {
            dp[index] = dp[index+1];
        }
        
        //Computing the maximum profits
        if(dp[index]-stockPriceArr[index] > maxProfitTillDate.maxProfit)
        {
            maxProfitTillDate.buy = index;
            maxProfitTillDate.maxProfit = dp[index]-stockPriceArr[index];
        }        
        return maxProfitTillDate;

    }

}
