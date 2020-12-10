package ua.edu.ucu;

import ua.edu.ucu.stream. * ;
import org.junit.Test;
import static org.junit.Assert. * ;
import org.junit.Before;

/**
 *
 * @author andrii
 */
public class StreamAppTest {

    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = { - 1,
                0,
                1,
                2,
                3
        };
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = { - 1,
                0,
                1,
                2,
                3
        };
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamSum() {
        System.out.println("streamSum");
        int expResult = 5;
        int actResult = StreamApp.streamSum(intStream);
        assertEquals(expResult, actResult);
    }

    @Test
    public void testStreamMinMax() {
        System.out.println("streamSum");
        int expResultMin = -1;
        int expResultMax = 3;
        int actResultMin = StreamApp.streamMin(intStream);
        int actResultMax = StreamApp.streamMax(intStream);
        assertEquals(expResultMin, actResultMin);
        assertEquals(expResultMax, actResultMax);
    }

    @Test
    public void testStreamAverage() {
        System.out.println("streamSum");
        double expResult = 1.0;
        double actResult = StreamApp.streamAvg(intStream);
        assertEquals(expResult, actResult, 0.0001);
    }

}