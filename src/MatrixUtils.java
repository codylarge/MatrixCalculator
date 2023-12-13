import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MatrixUtils
{
    /**
     * Evaluates a row operation in the form of "r2 = r3 + r4 / 2"
     * @param operation
     * @param matrix
     * @return
     */
    public static Cell[] evaluateRowOperation(String operation, Matrix matrix)
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

        Cell[] result = evaluateExpression(expression, matrix);

        matrix.replaceRow(targetRow, result);

        return result;
    }

    /**
     * Evaluates basic mathematical expressions
     * @param expression
     * @param matrix
     * @return
     */
    public static Cell[] evaluateExpression(String expression, Matrix matrix)
    {
        int rowLength = matrix.getRow("r2").length;

        // Gets the first token in the expression (target row)
        String[] tokens = expression.split(" ");
        //String[] tokens = expression.split("[+\\-*/]"); // TODO: Allow expression without requiring spaces

        Cell[] result = new Cell[matrix.getRow(tokens[0]).length];
        // Copies the target row into the result array
        System.arraycopy(matrix.getRow(tokens[0]), 0, result, 0, result.length);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            String sourceRow = tokens[i + 1];
            Cell [] rowValues = new Cell[rowLength];

            if(sourceRow.startsWith("r")) //if r has no scalar applied (ie: r2 = r3 + r1)
            {
                rowValues = matrix.getRow(sourceRow);
            }
            else if(sourceRow.contains("r")) //if r has a scalar applied (ie: r2 = r2 + 5r1)
            {
                Cell scalar = CellUtils.stringToFraction(sourceRow.substring(0, sourceRow.indexOf("r")));
                sourceRow = sourceRow.substring(sourceRow.indexOf("r")); // remove scalars from in from of sourceRow for
                rowValues = applyScalarToRow(matrix, sourceRow, scalar);
            }
            else //if sourceRow is a scalar (ie: r2 = r2 * 5)
            {
                for (int j = 0; j < rowValues.length; j++)
                {
                    rowValues[j] = CellUtils.stringToFraction(sourceRow);
                }
            }

            switch (operator) {
                case "+":
                    for (int j = 0; j < result.length; j++) {
                        result[j].add(rowValues[j]);
                    }
                    break;
                case "-":
                    for (int j = 0; j < result.length; j++) {
                        result[j].subtract(rowValues[j]);
                    }
                    break;
                case "*":
                    for (int j = 0; j < result.length; j++) {
                        result[j].multiply(rowValues[j]);
                    }
                    break;
                case "/":
                    for (int j = 0; j < result.length; j++) {
                        if (rowValues[j].getNumerator() != 0) {
                            result[j].divide(rowValues[j]);
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

    public static Cell[] applyScalarToRow(Matrix m, String targetRow, Cell scalar)
    {
        Cell[] originalRow = m.getRow(targetRow);
        Cell[] resultRow = new Cell[originalRow.length]; // Do this to avoid altering original row values

        for (int i = 0; i < originalRow.length; i++) {
            resultRow[i] = originalRow[i];
            resultRow[i].multiply(scalar);
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
            Cell[] column = m.getColumn(i);
            for (int j = 0; j < transposeCols; j++)
            {
                transposeString.append(column[j]).append(" ");
            }
            transposeString.append(", ");
        }

        return new Matrix(transposeString.toString().trim());
    }

    public static float calculateDeterminant(Matrix m)
    {
        if(m.getRows() != m.getCols()) throw new IllegalArgumentException("Cannot take determinant of non square matrix"); // Matrix must be square
        if(m.getRows() != 2) throw new IllegalArgumentException("Only 2x2 matrix implemented"); // TODO: Implement determinant for matrices larger than 2x2

        Cell[] row1 = m.getRow("r1");
        Cell[] row2 = m.getRow("r2");

        Cell ad = new Cell(CellUtils.multiplyCells(row1[0], row2[1]));
        Cell bc = new Cell(CellUtils.multiplyCells(row2[0], row1[1]));
        Cell determinantCell = CellUtils.subtractCell(ad, bc);

        float determinant = determinantCell.getDecimalValue();
        return determinant;
    }

    public static void formattingMenu()
    {
        System.out.println("Format: r2 = r2 + 0.5r4. starting with 'target row =' and spaces between each token.  " +
        "Scalar operations should be performed in front of the row number.");
    }
}

