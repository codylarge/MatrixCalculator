import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// This class represents a matrix object
public class Matrix
{
    Map<String, float[]> matrix; //Hashmap
    final int rows;
    Scanner sc;

    public Matrix()
    {
        this.sc = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        this.rows = sc.nextInt();
        sc.nextLine(); // Consumes the newline character because nextInt doesn't do that

        this.matrix = new HashMap<>();
        fillMatrixFromInput();
    }

    public Matrix(String matrixInput)
    {
        this.matrix = new HashMap<>();
        String[] commaSeparated = matrixInput.split(","); // Split by commas
        this.rows = commaSeparated.length;
        fillMatrixFromArray(commaSeparated);
    }

    private void fillMatrixFromInput()
    {
        for (int i = 1; i < this.rows + 1; i++)
        {
            System.out.print("Enter row " + i + ": ");
            String rowString = sc.nextLine();
            float[] row = Utils.stringToFloatArray(rowString);
            matrix.put("r" + i, row);
        }
    }

    private void fillMatrixFromArray(String[] commaSeparatedMatrix)
    {
        int rowIndex = 1;
        for (String rowString : commaSeparatedMatrix)
        {
            // Remove leading and trailing whitespace, and then split by spaces
            String[] rowArray = rowString.trim().split(" ");
            float[] row = new float[rowArray.length];
            for (int i = 0; i < rowArray.length; i++)
            {
                // Parse the individual numbers as floats and store them in the row array
                row[i] = Float.parseFloat(rowArray[i]);
            }
            matrix.put("r" + rowIndex, row);
            rowIndex++; // Increment the row index
        }
    }

    public void performOperation(String operation)
    {
        //String operation = "r2 = r3 + r4 / 2";// - Doesn't work, divides by 2 AFTER
        float[] result = MatrixUtils.evaluateRowOperation(operation, this);

        String targetRow = operation.split("=")[0].trim();
        if (result != null && targetRow.startsWith("r")) {
            this.matrix.put(targetRow, result);
        } else {
            System.out.println("Invalid operation.");
        }
    }
    public void applyScalar(float scalar)
    {
        for (int i = 1; i < this.getRows() + 1; i++)
        {
            for (int j = 0; j < this.getCols(); j++)
            {
                this.getRow("r" + i)[j] *= scalar;
            }
        }
    }

    public void replaceRow(String targetRow, float[] newRow)
    {
        this.matrix.put(targetRow, newRow);
    }
    public float[] getRow(String targetRow)
    {
        return this.matrix.get(targetRow);
    }
    public float[] getColumn(int targetColumn)
    {
        float[] column = new float[this.getRows()];
        for (int i = 1; i < this.getRows() + 1; i++)
        {
            column[i - 1] = this.getRow("r" + i)[targetColumn];
        }
        return column;
    }
    public boolean containsRow(String targetRow)
    {
        return this.matrix.containsKey(targetRow);
    }
    public int getRows()
    {
        return matrix.size();
    }
    public int getCols()
    {
        return matrix.get("r1").length;
    }

    public Matrix clone()
    {
        Matrix clone = new Matrix();
        clone.matrix = this.matrix;
        return clone;
    }

    public Matrix getTranspose()
    {
        return MatrixUtils.calculateTranspose(this);
    }

    public Map<String, float[]> getMatrix()
    {
        return this.matrix;
    }

    public String toString()
    {
        StringBuilder matrixString = new StringBuilder();
        for(int i = 1; i < this.rows + 1; i++)
        {
            matrixString.append(java.util.Arrays.toString(matrix.get("r" + i))).append("\n");
        }
        return matrixString.toString();
    }
}
