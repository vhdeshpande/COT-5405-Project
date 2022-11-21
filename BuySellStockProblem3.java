
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
        c = scan.nextInt();
        m = scan.nextInt();
        n = scan.nextInt();

        stockPrices = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                stockPrices[i][j] = scan.nextInt();
            }
        }
    }

    public void displayResult() {

        for (ArrayList<Integer> i : transactionSeq) {
            System.out.println(String.format("%d %d %d", i.get(0), i.get(1), i.get(2)));
        }
        Utils.show("Maximum Profit:" + maxProfit);
    }
}