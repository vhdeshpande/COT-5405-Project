
import java.util.ArrayList;
import java.util.Scanner;

public abstract class BuySellStockProblem3 implements BuySellStockTaskIntf {
    // Number of stocks
    public static int m;
    // Number of days
    public static int n;

    public int c;

    public static int[][] stockPrices;
    Scanner scan = new Scanner(System.in);

    public static ArrayList<ArrayList<Integer>> transactionSeq;

    public int maxProfit = 0;

    public void readInput() {
        // System.out.println( "Enter k:" );
        c = scan.nextInt();
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

        // c = 2;
        // stockPrices = new int[][]{{7, 1, 5, 3, 6, 4}, {7, 1, 5, 3, 6, 4}, {7, 1, 5,
        // 3, 6, 25}};
        // stockPrices = new int[][]{{2,3,1,8},{1,6,6,7},{2,1,4,3}};

        // //stockPrices = new int[][]{{1,7,4,0,9},{4,8,8,2,4},{5,5,1,7,1}};

        // stockPrices = new int[][]{{1,7,4,0,9,1},{4,8,8,2,4,6},{5,5,1,7,1,1}};
        // m = stockPrices.length;
        // n = stockPrices[0].length;
    }

    public void displayResult() {

        for (ArrayList<Integer> i : transactionSeq) {
            System.out.println(String.format("%d %d %d", i.get(0), i.get(1), i.get(2)));
        }
        System.out.println("Maximum Profit:" + maxProfit);
    }
}