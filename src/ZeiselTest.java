import java.util.ArrayList;

public class ZeiselTest {

    public boolean isZeiselNumberWithFactorQuantityX(ArrayList<Long> factors, int quantity){

        if (factors.size() != quantity)
            return false;

         for (int i = 0; i < factors.size() - 1; i++){
            if (factors.get(i) == factors.get(i+1))
                return false;
        }

        for (int a = 1; a*factors.get(0) < factors.get(1); a++){
             for (int b = 0; b <= factors.get(1) - factors.get(0); b++){
                 if (b == 0)
                     continue;
                 if (quantity == 3 && checkZeiselFor3Factors(factors,a,b)){
                     return true;
                 }
                 if (quantity == 4 && checkZeiselFor4Factors(factors,a,b)){
                     return true;
                 }
                 if (quantity == 5 && checkZeiselFor5Factors(factors,a,b)){
                     return true;
                 }
                 if (quantity == 6 && checkZeiselFor6Factors(factors,a,b)){
                     return true;
                 }
             }
        }
         return false;
    }

    private boolean checkZeiselFor3Factors(ArrayList<Long> factors, int a, int b){
        return factors.get(0) * a + b == factors.get(1) && factors.get(1) * a + b == factors.get(2);
    }
    private boolean checkZeiselFor4Factors(ArrayList<Long> factors, int a, int b){
        return factors.get(0) * a + b == factors.get(1) && factors.get(1) * a + b == factors.get(2) && factors.get(2) * a + b == factors.get(3);
    }
    private boolean checkZeiselFor5Factors(ArrayList<Long> factors, int a, int b){
        return factors.get(0) * a + b == factors.get(1) && factors.get(1) * a + b == factors.get(2) && factors.get(2) * a + b == factors.get(3) && factors.get(3) * a + b == factors.get(4);
    }
    private boolean checkZeiselFor6Factors(ArrayList<Long> factors, int a, int b){
        return factors.get(0) * a + b == factors.get(1) && factors.get(1) * a + b == factors.get(2) && factors.get(2) * a + b == factors.get(3) && factors.get(3) * a + b == factors.get(4) && factors.get(4) * a + b == factors.get(5);
    }
}
