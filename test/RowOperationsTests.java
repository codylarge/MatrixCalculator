import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class RowOperationsTests
{
    private Matrix matrix;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() {
        // Redirect System.out to capture the output
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testMatrixCreation() {
        // Simulate user input for a 2x3 matrix
        String matrixString = "1 2 3, 4 5 6";
        matrix = new Matrix(matrixString); // Make is 2 rows and 3 cols

        assertEquals(2, matrix.getRows());
        assertEquals(3, matrix.getCols());
    }

    @Test
    public void testRowOperation() {
        Map<String, float[]> testMatrix = new HashMap<>();
        testMatrix.put("r1", new float[] { 1, 2, 3 });
        testMatrix.put("r2", new float[] { 4, 5, 6 });

        float[] expectedResult = new float[] { 5, 7, 9 };
        float[] result = MatrixUtils.evaluateRowOperation("r1 = r1 + r2", testMatrix);

        assertArrayEquals(expectedResult, result, (float) 0.001);
    }

    @Test
    public void testInvalidRowOperation() {
        Map<String, float[]> testMatrix = new HashMap<>();
        testMatrix.put("r1", new float[] { 1, 2, 3 });

        float[] result = MatrixUtils.evaluateRowOperation("r2 = r1 + r3", testMatrix);

        assertNull(result);
    }

    @Test
    public void testDivisionByZero() {
        Map<String, float[]> testMatrix = new HashMap<>();
        testMatrix.put("r1", new float[] { 1, 2, 3 });
        testMatrix.put("r2", new float[] { 4, 0, 6 });

        float[] result = MatrixUtils.evaluateRowOperation("r1 = r1 / r2", testMatrix);

        assertNull(result);
    }
}
