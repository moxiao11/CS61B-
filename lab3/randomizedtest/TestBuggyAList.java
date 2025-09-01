package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove()
    {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();
        for(int i = 4 ; i<= 6 ; i ++ )
        {
            a.addLast(i);
            b.addLast(i);
        }
        assertEquals(a.removeLast(), b.removeLast());
        assertEquals(a.removeLast(), b .removeLast());
        assertEquals(a.removeLast(), b .removeLast());
    }
    @Test
    public void randomizedTest()
    {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(L.size() , B.size());
                System.out.println("size: " + size);

            }
            if(L.size() == 0) continue ;
            if(operationNumber == 2)
            {
               int val = L.getLast();
               assertEquals(L.getLast(), B.getLast());
                System.out.println("getLast: " + val);
            }
            else if(operationNumber == 3)
            {
                int val = L.removeLast();
                assertEquals(val , (int) B.removeLast());
                System.out.println("removeLast: " + val);
            }
        }
    }
}
