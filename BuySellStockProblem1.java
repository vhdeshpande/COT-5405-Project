
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
        System.out.println(String.format("%d %d %d", stockId, buyDay, sellDay));
        Utils.show("Maximum Profit:" + maxProfit);
    }
}