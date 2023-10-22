import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MatrixUtils
{
    public static float[] evaluateRowOperation(String operation, Matrix matrix)
    {
        String[] tokens = operation.split("=");

        if (tokens.length != 2) {
            if(tokens.length == 1) {
                // TODO: Add way to do operation such as r2 + 5r1, assuming r2 as the target row
            } else return null;
        }

        String targetRow = tokens[0].trim();
        String expression = tokens[1].trim();

        if (!matrix.containsRow(targetRow)) {
            return null; // Target row doesn't exist in the matrix
        }

        float[] result = evaluateExpression(expression, matrix);

        matrix.replaceRow(targetRow, result);

        return result;
    }

    public static float[] evaluateExpression(String expression, Matrix matrix)
    {
        int rowLength = matrix.getRow("r2").length;

        // Gets the first token in the expression (target row)
        String[] tokens = expression.split(" ");
        //String[] tokens = expression.split("[+\\-*/]");
        float[] result = new float[matrix.getRow(tokens[0]).length];
        // Copies the target row into the result array
        System.arraycopy(matrix.getRow(tokens[0]), 0, result, 0, result.length);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            String sourceRow = tokens[i + 1];
            float [] rowValues = new float[rowLength];

            if(sourceRow.startsWith("r")) //if r has no scalar applied (ie: r2 = r3 + r1)
            {
                rowValues = matrix.getRow(sourceRow);
            }
            else if(sourceRow.contains("r")) //if r has a scalar applied (ie: r2 = r2 + 5r1)
            {
                float scalar = Float.parseFloat(sourceRow.substring(0, sourceRow.indexOf("r")));
                sourceRow = sourceRow.substring(sourceRow.indexOf("r")); // remove scalars from in from of sourceRow for
                rowValues = applyScalarToRow(matrix, sourceRow, scalar);
            }
            else //if sourceRow is a scalar (ie: r2 = r2 * 5)
            {
                for (int j = 0; j < rowValues.length; j++)
                {
                    rowValues[j] = Float.parseFloat(sourceRow);
                }
            }

            switch (operator) {
                case "+":
                    for (int j = 0; j < result.length; j++) {
                        result[j] += rowValues[j];
                    }
                    break;
                case "-":
                    for (int j = 0; j < result.length; j++) {
                        result[j] -= rowValues[j];
                    }
                    break;
                case "*":
                    for (int j = 0; j < result.length; j++) {
                        result[j] *= rowValues[j];
                    }
                    break;
                case "/":
                    for (int j = 0; j < result.length; j++) {
                        if (rowValues[j] != 0) {
                            result[j] /= rowValues[j];
                        } else {
                            return null; // Division by zero
                        }
                    }
                    break;
                default:
                    return null; // Invalid operator
            }
        }
        return result;
    }

    public static float[] applyScalarToRow(Matrix m, String targetRow, float scalar)
    {
        float[] originalRow = m.getRow(targetRow);
        float[] resultRow = new float[originalRow.length]; // Do this to avoid altering original row values

        for (int i = 0; i < originalRow.length; i++) {
            resultRow[i] = originalRow[i] * scalar;
        }

        return resultRow;
    }

    public static Matrix calculateTranspose(Matrix m)
    {
        StringBuilder transposeString = new StringBuilder();
        int transposeRows = m.getCols();
        int transposeCols = m.getRows();

        for (int i = 0; i < transposeRows; i++)
        {
            float[] column = m.getColumn(i);
            for (int j = 0; j < transposeCols; j++)
            {
                transposeString.append(column[j]).append(" ");
            }
            transposeString.append(", ");
        }

        return new Matrix(transposeString.toString().trim());
    }

    public static void formattingMenu()
    {
        System.out.println("Format: r2 = 0.2r3 + 3r4 with spaces between each token, starting with the target row. " +
        "Scalar operations should be performed in front of the row number.");
    }
}

