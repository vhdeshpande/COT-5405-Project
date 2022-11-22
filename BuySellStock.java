public class BuySellStock {
    public static final String PROBLEM1_TASK1 = "1";
    public static final String PROBLEM1_TASK2 = "2";
    public static final String PROBLEM1_TASK3A = "3a";
    public static final String PROBLEM1_TASK3B = "3b";
    public static final String PROBLEM2_TASK4 = "4";
    public static final String PROBLEM2_TASK5 = "5";
    public static final String PROBLEM2_TASK6A = "6a";
    public static final String PROBLEM2_TASK6B = "6b";
    public static final String PROBLEM3_TASK7 = "7";
    public static final String PROBLEM3_TASK8 = "8";
    public static final String PROBLEM3_TASK9A = "9a";
    public static final String PROBLEM3_TASK9B = "9b";

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Input Empyty");
            System.exit(-1);
        }

        String buySellStockTask = args[0];
        BuySellStockTaskIntf buySellStockTaskIntf = null;

        switch (buySellStockTask) {

            case PROBLEM1_TASK1:
                buySellStockTaskIntf = new Problem1_Task1_BruteForce();
                break;

            case PROBLEM1_TASK2:
                buySellStockTaskIntf = new Problem1_Task2_Greedy();
                break;

            case PROBLEM1_TASK3A:
                buySellStockTaskIntf = new Problem1_Task3A_DPRecursiveMemoization();
                break;

            case PROBLEM1_TASK3B:
                buySellStockTaskIntf = new Problem1_Task3B_DPIterativeBottomUp();
                break;

            case PROBLEM2_TASK4:
                buySellStockTaskIntf = new Problem2_Task4_BruteForce();
                break;

            case PROBLEM2_TASK5:
                buySellStockTaskIntf = new Problem2_Task5_DP();
                break;

            case PROBLEM2_TASK6A:
                buySellStockTaskIntf = new Problem2_Task6A_DPRecursiveMemoization();
                break;

            case PROBLEM2_TASK6B:
                buySellStockTaskIntf = new Problem2_Task6B_DPIterativeBottomUp();
                break;

            case PROBLEM3_TASK7:
                buySellStockTaskIntf = new Problem3_Task7_BruteForce();
                break;

            case PROBLEM3_TASK8:
                buySellStockTaskIntf = new Problem3_Task8_DP();
                break;

            case PROBLEM3_TASK9A:
                buySellStockTaskIntf = new Problem3_Task9A_DPRecursiveMemoization();
                break;

            case PROBLEM3_TASK9B:
                buySellStockTaskIntf = new Problem3_Task9B_DPIterativeBottomUp();
                break;

            default:
                System.out.println("Invalid Input!!");
                System.exit(-1);
        }

        buySellStockTaskIntf.readInput();

        long startTime = System.nanoTime();

        buySellStockTaskIntf.calculateMaxProfit();

        long endTime = System.nanoTime();

        buySellStockTaskIntf.displayResult();
        Utils.show("Start Time: " + startTime + " ms\n");
        Utils.show("End Time: " + endTime + " ms\n");
        Utils.show("Execution Time: " + (endTime - startTime)/1000 + " ms\n");
    }

}
