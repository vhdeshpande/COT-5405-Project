

import java.util.ArrayList;
import java.util.Arrays;

public class Problem2_Task4_BruteForce extends BuySellStockProblem2 {
	
  	public int profit;
    public ArrayList<ArrayList<Integer>> transactionSequence;
    Problem2_Task4_BruteForce(){ 
    	//DO NOTHING
    }
    
    Problem2_Task4_BruteForce(int profit, ArrayList<ArrayList<Integer>> transactionSequence){ 
        this.profit = profit;
        this.transactionSequence = transactionSequence;
    }
    
	@Override
	public void calculateMaxProfit() {
		
        ArrayList<ArrayList<Integer>> currTransactionSeq = new ArrayList<ArrayList<Integer>>();
        //Recursively calling the function to get the maximum stock price by doing transactions at most k times starting from first and the transaction sequence
        Problem2_Task4_BruteForce output = getMaxProfit( k, 0, false, null, null, currTransactionSeq, 0);
        maxProfit = output.profit;
        transactionSeq = output.transactionSequence;
        
	}
	
    public static Problem2_Task4_BruteForce getMaxProfit(int k, int day, boolean isBuy, Integer stockId, Integer boughtOn, ArrayList<ArrayList<Integer>> currTransactionSeq, int prevProfit){
        int outputProfit = 0;
        ArrayList<ArrayList<Integer>> outputTransactionSeq = new ArrayList<ArrayList<Integer>>();
        
        //Base condition to return the max profit and the maximum transaction sequence if the last day is reached or the k reaches the maximum possible transction values
        if( day >= n|| k == 0){
            return new Problem2_Task4_BruteForce( prevProfit, currTransactionSeq );
        }
        ArrayList<ArrayList<Integer>> clone = (ArrayList<ArrayList<Integer>>) currTransactionSeq.clone();
        //If the stock is bought earlier
		if(isBuy){

            //Hold the cuurent stock and go to the next day with the same value
            Problem2_Task4_BruteForce Problem2_Task4_BruteForceRes = getMaxProfit( k, day+1, isBuy, stockId, boughtOn, clone, prevProfit);
            if(Problem2_Task4_BruteForceRes.profit > outputProfit){
            	outputProfit = Problem2_Task4_BruteForceRes.profit;
            	outputTransactionSeq = Problem2_Task4_BruteForceRes.transactionSequence;
            }
            //Sell the bought stock, set the isBuy to false and update the current profit value
            ArrayList<ArrayList<Integer>> modifiedOutput = clone;
            modifiedOutput.add(new ArrayList<Integer>(Arrays.asList( stockId , boughtOn, day )));
            int currentProfit = prevProfit + (stockPrices[stockId][day] - stockPrices[stockId][boughtOn]);

            //Decrement the k value the calculated profit value
            Problem2_Task4_BruteForce Problem2_Task4_BruteForceSellRes = getMaxProfit( k-1, day, false, null, null, modifiedOutput, currentProfit);
            if(Problem2_Task4_BruteForceSellRes.profit > outputProfit){
            	outputProfit = Problem2_Task4_BruteForceSellRes.profit;
            	outputTransactionSeq = Problem2_Task4_BruteForceSellRes.transactionSequence;
            }
        }
        //If no stock is currently purchased
        else{
            //Skip the day, stock is not purchased and day is incremented with the same  profit
            Problem2_Task4_BruteForce Problem2_Task4_BruteForceRes = getMaxProfit( k, day+1, false, null, null, clone, prevProfit);

            if(Problem2_Task4_BruteForceRes.profit > outputProfit){
            	outputProfit = Problem2_Task4_BruteForceRes.profit;
            	outputTransactionSeq = Problem2_Task4_BruteForceRes.transactionSequence;
            }
            //Buy the stock and, set the isBuy value to true and continue to the same day
            for ( int i = 0; i < m; i++){
                Problem2_Task4_BruteForce Problem2_Task4_BruteForceSBuyRes = getMaxProfit( k, day+1, true, i, day, clone, prevProfit);
                if(Problem2_Task4_BruteForceSBuyRes.profit > outputProfit){
                	outputProfit = Problem2_Task4_BruteForceSBuyRes.profit;
                	outputTransactionSeq = Problem2_Task4_BruteForceSBuyRes.transactionSequence;
                }
            }
        }
        return new Problem2_Task4_BruteForce(outputProfit,outputTransactionSeq);

    }


}
