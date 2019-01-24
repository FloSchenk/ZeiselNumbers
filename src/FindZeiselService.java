import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class FindZeiselService implements Runnable  {

    private CyclicBarrier cyclicBarrier;
    private long zeiselNumber;
    private long minimum;
    private long maximum;
    private int factorQuantity;
    private PrimFactorizer primFactorizer;
    private ZeiselTest zeiselTest;
    private ArrayList<Long> tempFactors;
    private boolean isZeiselFound = false;

    public FindZeiselService(CyclicBarrier cyclicBarrier, long minimum, long maximum, int factorQuantity){
        this.cyclicBarrier = cyclicBarrier;
        this.maximum = maximum;
        this.minimum = minimum;
        this.factorQuantity = factorQuantity;
        primFactorizer = new PrimFactorizer();
        zeiselTest = new ZeiselTest();
        tempFactors = new ArrayList<>();
        zeiselNumber = Long.MAX_VALUE;
    }

    @Override
    public void run() {
        try {
            long runtimeStart = System.currentTimeMillis();

            for (long i = minimum; i <= maximum; i++) {
                if (i > Application.getFinalZeiselNumber()) {
                    isZeiselFound = true;
                    break;
                } else {
                    tempFactors.clear();
                    tempFactors = primFactorizer.factorize(i);

                    if (zeiselTest.isZeiselNumberWithFactorQuantityX(tempFactors, factorQuantity)) {
                        if (i < zeiselNumber) {
                            zeiselNumber = i;
                            break;
                        }
                    }
                }
            }

            if (zeiselNumber < Application.getFinalZeiselNumber())
                Application.setFinalZeiselNumber(zeiselNumber);

            if (isZeiselFound) {
                System.err.println(Application.threadID + " : Zeisel number already found!");
            } else {
                if (zeiselNumber == Long.MAX_VALUE) {
                System.out.println(Application.threadID + " : [" + minimum + "," + maximum + "] finished - time needed: " + (System.currentTimeMillis() - runtimeStart + " ms - " + //
                        "no Zeisel Number found!!"));
                 } else {
                System.out.println(Application.threadID + " : [" + minimum + "," + maximum + "] finished - time needed: " + (System.currentTimeMillis() - runtimeStart + " ms - " + //
                        zeiselNumber + " as Zeisel number, with " + factorQuantity + " factors, found"));
                }
            }

                Application.setThreadID();
                cyclicBarrier.await();
        } catch (InterruptedException iex) {
            System.out.println(iex.getMessage());
        } catch (BrokenBarrierException bbex) {
            System.out.println(bbex.getMessage());
        }
    }
}
