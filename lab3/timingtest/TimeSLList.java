package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        // add the N of numbers ;
        AList<Integer> Ns  = new AList<>();
        // add the time of program
        AList<Double>times = new AList<>();
        // add the number of getlast
        AList<Integer> opCount = new AList<>();

        final int call_times = 10000 ;
        for(int j = 0 ; j < 8 ; j++ ) {
            // create a SLList
            SLList<Integer> temp  = new SLList<>();
            int len = 1000 * (int)Math.pow(2 , j);
            for (int i = 0; i < len; i++) {
                temp.addLast(i);
            }
            Stopwatch sw = new Stopwatch();
            for(int i = 0; i < call_times ; i++ )
            {
                temp.getLast() ;
            }
            double timeInseconds= sw.elapsedTime();

            Ns.addLast(len);
            times.addLast(timeInseconds);
            opCount.addLast(call_times);
        }
        printTimingTable(Ns, times ,opCount);
    }

}
