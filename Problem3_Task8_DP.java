import java.util.ArrayList;
import java.util.Arrays;

public class Problem3_Task8_DP extends  BuySellStockProblem3{

	@Override
	public void calculateMaxProfit() {

        int[] profit = new int[n + 1];
        int[] stockId = new int[n + 1];
        int[] buy = new int[n + 1];
		ArrayList<ArrayList<Integer>> transactionSequnce = new ArrayList<ArrayList<Integer>>();
   
        for (int j = 0; j <= n; j++) {
            profit[j] = 0; 
            stockId[j] = 0; 
            buy[j] = 0; 
        }
            
        int id =-1;
        int b=-1;
        int s = -1;
        for (int j = 1; j < n; j++) { 
            int max_so_far = 0; 
            for (int p = 0; p < j; p++){ 
                for(int x = 0; x < m; x++){ 
                    int currProf = stockPrices[x][j] -stockPrices[x][p] ;
                    if(currProf > max_so_far){
                        max_so_far = currProf;
                        id = x;
                        b = p;
                    }
                    if(p-c-1 >= 0)
                    { 
                        max_so_far = Math.max(max_so_far, stockPrices[x][j] -stockPrices[x][p] + profit[p-c-1]);
                    } 

                    if(profit[j - 1] > max_so_far){
                        stockId[j] = stockId[j-1];
                        buy[j] = buy[j-1];
                    }
                    else{
                        stockId[j] = id;
                        buy[j] = b;
                    }
                    profit[j] = Math.max(profit[j - 1],max_so_far); 
                } 
            } 
        } 

        int j = n-1;
       
        while(j>0){
                while( j-1>=0 && profit[j] == profit[j-1]){
                    j--;
                }
                int sell = j;
                while( j-1>=0 && profit[j] == profit[j-1]){
                    j--;
                }

                int buy1 = buy[j];
                int sid = stockId[j];

                transactionSequnce.add(new ArrayList<Integer>(Arrays.asList(sid+1,buy1+1,sell+1)));
                j= j-c-1;
                if (j>=0){
                    break;
                };
        }
		transactionSeq = transactionSequnce;
        maxProfit = profit[n - 1];


		
	}

}
