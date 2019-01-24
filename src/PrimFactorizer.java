import java.util.ArrayList;
import java.util.Collections;

public class PrimFactorizer {

    private ArrayList<Long> factors = new ArrayList<>();

    public ArrayList<Long> factorize(long number) {
        long n = number;

        for (long i = 2; i * i <= n; i++)
            while (n % i == 0) {
                factors.add(i);
                n = n / i;
            }

        if (n > 1) {
            factors.add(n);

        }
        Collections.sort(factors);
        return factors;
    }
}
