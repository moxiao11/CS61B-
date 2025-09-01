package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns  = new AList<>();
        AList<Double>times = new AList<>();
        AList<Integer> opCount = new AList<>();
        for(int j = 0 ; j < 8 ; j++ ) {
            AList<Integer> temp  = new AList<>();
            int len = 1000 * (int)Math.pow(2 , j);
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < len; i++) {
                temp.addLast(i);
            }
            double timeInseconds= sw.elapsedTime();
            Ns.addLast(len);
            times.addLast(timeInseconds);
            opCount.addLast(len);
        }
        printTimingTable(Ns, times ,opCount);
    }
}
