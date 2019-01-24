import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class Application {

    CyclicBarrier cyclicBarrier;
    ArrayList<FindZeiselService> findZeiselServices;
    protected static long threadID = 1;

    private static long finalZeiselNumber = Long.MAX_VALUE;

    public static synchronized void setFinalZeiselNumber(long number){
        finalZeiselNumber = number;
    }

    public static synchronized long getFinalZeiselNumber(){
        return finalZeiselNumber;
    }

    public static synchronized void setThreadID(){
        threadID++;
    }

    public Application() {
        cyclicBarrier = new CyclicBarrier(Configuration.instance.maximumNumberOfThreads);
        findZeiselServices = new ArrayList<>();
    }

     public void build(int factorQuantity) {
        System.out.println("phase 01 - build");
         long minimum = Configuration.instance.minimun;
         long stepSize = (Configuration.instance.maximum - minimum) / Configuration.instance.maximumNumberOfThreads;
         long maximum = minimum + stepSize;

         for (int i = 0;i < Configuration.instance.maximumNumberOfThreads;i++) {
             findZeiselServices.add(new FindZeiselService(cyclicBarrier,minimum,maximum,factorQuantity));
             minimum = maximum + 1;
             maximum = minimum + stepSize;
             }
     }

    public void execute() {
        System.out.println("phase 02 - execute");
        for (FindZeiselService findZeiselService : findZeiselServices)
            new Thread(findZeiselService).start();
    }

    public static void main(String... args) {
        //Even after Hours of trying i couldn't find a solution with a better performance for a Zeisel number with 6 factors.
        //My Laptop from my company did block VisualVM...
        //Therefore I couldn't decide wether my Zeisel Test or the Pollard Rho Replacement is the Problem.
        //If it is my Zeisel Test then the Performance loss is found in lines 15 to 32. But on the other Hand I believe it has to be tested for those as and bs.
        Application app = new Application();
        app.build(4);
        app.execute();
        //  app.build(5);
        // app.execute();
        //  app.build(6);
        //  app.execute();
    }
}