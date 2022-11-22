

import java.util.ArrayList;
import java.util.Arrays;

public class Problem3_Task7_BruteForce extends BuySellStockProblem3{

  	public static int profit;
    public static ArrayList<ArrayList<Integer>> transactionSequence;
    Problem3_Task7_BruteForce(){ 
    	//DO NOTHING
    }
    
    Problem3_Task7_BruteForce(int profit, ArrayList<ArrayList<Integer>> transactionSequence){ 
        this.profit = profit;
        this.transactionSequence = transactionSequence;
    }
    
	@Override
	public void calculateMaxProfit() {
		
        ArrayList<ArrayList<Integer>> currTransactionSeq = new ArrayList<ArrayList<Integer>>();
        Problem3_Task7_BruteForce output = getMaxProfit( c, 0, false, null, null, currTransactionSeq, 0);
        maxProfit = output.profit;
        transactionSeq = output.transactionSequence;
        
	}
	
    public static Problem3_Task7_BruteForce getMaxProfit(int c, int day, boolean isBuy, Integer stockId, Integer boughtOn, ArrayList<ArrayList<Integer>> currTransactionSeq, int prevProfit){
        int outputProfit = 0;
        ArrayList<ArrayList<Integer>> outputTransactionSeq = new ArrayList<ArrayList<Integer>>();

        if( day >= n){
            return new Problem3_Task7_BruteForce( prevProfit, currTransactionSeq );
        }

        ArrayList<ArrayList<Integer>> clone = (ArrayList<ArrayList<Integer>>) currTransactionSeq.clone();
		if(isBuy){
            //Hold -> increment day
			Problem3_Task7_BruteForce Problem2_Task4_BruteForceRes = getMaxProfit( c, day+1, isBuy, stockId, boughtOn, clone, prevProfit);
            if(Problem3_Task7_BruteForce.profit > outputProfit){
            	outputProfit = Problem3_Task7_BruteForce.profit;
            	outputTransactionSeq = Problem3_Task7_BruteForce.transactionSequence;
            }

            //Sell -> k decrement
            ArrayList<ArrayList<Integer>> modifiedOutput = clone;
            modifiedOutput.add(new ArrayList<Integer>(Arrays.asList( stockId, boughtOn, day )));
            int currentProfit = prevProfit + (stockPrices[stockId][day] - stockPrices[stockId][boughtOn]);
            Problem3_Task7_BruteForce Problem2_Task4_BruteForceSellRes = getMaxProfit( c , day + c + 1, false, null, null, modifiedOutput,currentProfit);
          
            if(Problem2_Task4_BruteForceSellRes.profit > outputProfit){
            	outputProfit = Problem2_Task4_BruteForceSellRes.profit;
            	outputTransactionSeq = Problem2_Task4_BruteForceSellRes.transactionSequence;
            }
        }
        else{
            //Skip -> increment day
        	Problem3_Task7_BruteForce Problem2_Task4_BruteForceRes = getMaxProfit( c, day+1, false, null, null, clone,prevProfit);

            if(Problem2_Task4_BruteForceRes.profit > outputProfit){
            	outputProfit = Problem2_Task4_BruteForceRes.profit;
            	outputTransactionSeq = Problem2_Task4_BruteForceRes.transactionSequence;
            }

            //Buy -> increment day, buy flag true
            for ( int i = 0; i < m; i++){
            	Problem3_Task7_BruteForce Problem2_Task4_BruteForceSBuyRes = getMaxProfit( c, day+1, true, i, day, clone,prevProfit);
                if(Problem2_Task4_BruteForceSBuyRes.profit > outputProfit){
                	outputProfit = Problem2_Task4_BruteForceSBuyRes.profit;
                	outputTransactionSeq = Problem2_Task4_BruteForceSBuyRes.transactionSequence;
                }
            }
        }
        return new Problem3_Task7_BruteForce(outputProfit,outputTransactionSeq);

    }



}
