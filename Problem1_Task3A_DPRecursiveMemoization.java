

public class Problem1_Task3A_DPRecursiveMemoization extends  BuySellStockProblem1{
	
	@Override
	public void calculateMaxProfit() {
		
		int [][] dp = new int [ m ][ n ];
		maxProfit = maximizeProfitDPRecursive( stockPrices, dp, 0 );
        for( int i = 0; i < m; i++) {
      	for( int j = 0; j < n; j++) {
      		System.out.print( dp[i][j] + " " );		
          }
      	System.out.println( "\n" );
      }
	}
	
    public int maximizeProfitDPRecursive( int[][] stockPrices, int[][] dp, int index){
        
    	if ( index == stockPrices.length - 1 ){
    		
            return calculateMaxProfitForAStock( stockPrices[ index ], dp[ index ], 0, stockPrices[ index ][0]);
        }
    	
        int currentMax = calculateMaxProfitForAStock( stockPrices[ index ], dp[ index ], 0, stockPrices[ index ][0]);
        int currentProfit = maximizeProfitDPRecursive( stockPrices, dp, index + 1);
        return Math.max( currentProfit, currentMax );

    }
    
    public int calculateMaxProfitForAStock( int[] stockPriceArr, int[] dp, int index, int max ){
        
    	if ( index == stockPriceArr.length - 1 ){
            dp[ index ] = stockPriceArr[ index ];
            return 0;
        }
    	
        int maxProfitTillDate = calculateMaxProfitForAStock( stockPriceArr, dp, index + 1, max);
        dp[ index ] = Math.max( stockPriceArr[ index ], dp[ index + 1 ]);
        return Math.max( dp[ index ]-stockPriceArr[ index ], maxProfitTillDate);

    }

}
