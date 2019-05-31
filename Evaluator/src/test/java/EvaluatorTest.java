import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.itmo.rain.terentev.expression.Evaluator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class EvaluatorTest extends Evaluator {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));

        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void simple() {
        System.setIn(new ByteArrayInputStream("1 1 1 1".getBytes()));
        main();
        assertEquals("1.000\n", outContent.toString());
    }

    @Test
    public void doubleSimpleTest() {
        System.setIn(new ByteArrayInputStream("1.0 1.0 1.0 1.0".getBytes()));
        main();
        assertEquals("1.000\n", outContent.toString());
    }

    @Test
    public void doubleTest() {
        System.setIn(new ByteArrayInputStream("1.2 1.3 1.4 1.5".getBytes()));
        main();
        assertEquals("0.863\n", outContent.toString());
    }
    @Test
    public void OwefrowIntTest() {
        System.setIn(new ByteArrayInputStream("100000000000000000000000000000 100000000 1 0".getBytes()));
        main();
        assertEquals("100000000000000000000100000000.000\n", outContent.toString());
    }

    @Test
    public void DivideByZeroTest() {
        System.setIn(new ByteArrayInputStream("1 1 0 0".getBytes()));
        main();
        assertEquals("Invalid arguments: / by zero\n", errContent.toString());
    }


    @Test
    public void invalidArgs() {
        System.setIn(new ByteArrayInputStream("sa1 c1 qa1 s1".getBytes()));
        main();
        assertEquals("Invalid arguments: Number expected\n", errContent.toString());
    }



}
