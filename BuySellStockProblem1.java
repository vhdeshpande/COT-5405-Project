
import java.util.Scanner;

public abstract class BuySellStockProblem1 implements BuySellStockTaskIntf {
    // Number of stocks
    public int m;
    // Number of days
    public int n;

    public int[][] stockPrices;
    Scanner scan = new Scanner(System.in);

    int stockId = -1;
    int buyDay = -1;
    int sellDay = -1;
    int maxProfit = 0;

    public void readInput() {
        // System.out.println( "Enter number of stocks and number of days:" );
        m = scan.nextInt();
        n = scan.nextInt();
        // System.out.println( "Number of stocks: " + m );
        // System.out.println( "Number of days: " + n );
        // System.out.println( "Enter stock prices:" );
        stockPrices = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                stockPrices[i][j] = scan.nextInt();
            }
        }
        // for( int i = 0; i < m; i++) {
        // for( int j = 0; j < n; j++) {
        // System.out.print( stockPrices[i][j] + " " );
        // }
        // System.out.println( "\n" );
        // }

        // stockPrices = new int[][]{{7, 1, 5, 3, 6, 4}, {7, 1, 5, 3, 6, 4}, {7, 1, 5,
        // 3, 6, 25}};
        //
        // //stockPrices = new int[][]{{1,7,4,0,9},{4,8,8,2,4},{5,5,1,7,1}};
        // m = stockPrices.length;
        // n = stockPrices[0].length;

        // FileReader fr = new FileReader("input1.txt");
        // Scanner scan = new Scanner(fr);
        //
        // // Read lines from the file till end of file
        // m = scan.nextInt();
        // n = scan.nextInt();
        //
        // System.out.println( "Number of stocks: " + m );
        // System.out.println( "Number of days: " + n );
        // System.out.println( "Enter stock prices:" );
        // stockPrices = new int[m][n];
        // for( int i = 0; i < m; i++) {
        // for( int j = 0; j < n; j++) {
        // stockPrices[i][j] = scan.nextInt();
        // }
        // }

    }

    public void displayResult() {
        System.out.println(String.format("%d %d %d", stockId, buyDay, sellDay));
        // System.out.println( "Maximum Profit:" + maxProfit);
    }
}