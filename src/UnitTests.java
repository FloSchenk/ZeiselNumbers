import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UnitTests {
    ArrayList<Long> zeiselFactors;
    long number;

    @Before
    public void init(){
        number = 105;
        zeiselFactors = new ArrayList<>();
        zeiselFactors.add((long)3);
        zeiselFactors.add((long)5);
        zeiselFactors.add((long)7);
    }

    @Test
    public void testServices(){
        Application app = new Application();
        app.build(3);
        for (FindZeiselService s: app.findZeiselServices) {
            Assert.assertNotNull(s);
        }

    }
    @Test
    public void testPrimFactorizer(){
        PrimFactorizer primFactorizer = new PrimFactorizer();
        assertEquals(zeiselFactors,primFactorizer.factorize(number));
    }

    @Test
    public void  testZeiselTest(){
        ZeiselTest zeiselTest = new ZeiselTest();
        assertEquals(true,zeiselTest.isZeiselNumberWithFactorQuantityX(zeiselFactors, 3));
    }

    @Test
    public void testCombo(){
        PrimFactorizer primFactorizer = new PrimFactorizer();
        ZeiselTest zeiselTest = new ZeiselTest();
        ArrayList zeisel = primFactorizer.factorize(number);
        assertEquals(true,zeiselTest.isZeiselNumberWithFactorQuantityX(zeisel, 3));
    }
}
