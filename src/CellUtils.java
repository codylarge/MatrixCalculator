public class CellUtils
{
    public static Cell[] stringToCellArray(String rowArray)
    {
        String[] row = rowArray.trim().split(" ");
        Cell[] cellRow = new Cell[row.length];
        for (int i = 0; i < row.length; i++)
        {
            int numerator, denominator;

            String[] fraction = row[i].split("/");
            if(fraction.length == 1) // In case whole number
            {
                denominator = 1;
            }
            else if (fraction.length == 2)
            {
                denominator = Integer.parseInt(fraction[1]);
            }
            else
            {
                System.out.println("Invalid fraction.");
                return null;
            }
            numerator = Integer.parseInt(fraction[0]); // Set numerator after to avoid duplicate code

            cellRow[i] = new Cell(numerator, denominator);
        }
        return cellRow;
    }

    public static Cell stringToCellWithRow(String rowString, int index)
    {
        Cell[] row = stringToCellArray(rowString);
        assert row != null; // Change
        return row[index];
    }

    public static Cell stringToFraction(String fraction) {
        String[] parts = fraction.split("/");
        int numerator, denominator;

        if(parts.length == 1) denominator = 1; // IF whole number
        else if (parts.length == 2) denominator = Integer.parseInt(parts[1]); // if fraction
        else throw new IllegalArgumentException("Input string should be in the format 'numerator/denominator'.");
        numerator = Integer.parseInt(parts[0]); // Numerator the same for both whole number & fraction

        return new Cell(numerator, denominator);
    }


    /* These methods are repetitive from Cell class but these return a new cell as opposed to changing the cell directly */
    public static Cell multiplyCells(Cell a, Cell b)
    {
        int numerator = a.getNumerator() * b.getNumerator();
        int denominator = a.getDenominator() * b.getDenominator();
        return new Cell(numerator, denominator);
    }
    public static Cell divideCells(Cell a, Cell b)
    {
        int numerator = a.getNumerator() * b.getDenominator();
        int denominator = a.getDenominator() * b.getNumerator();
        return new Cell(numerator, denominator);
    }

    public static Cell addCell(Cell a, Cell b)
    {
        return performOperation(a, b, true);
    }

    public static Cell subtractCell(Cell a, Cell b)
    {
        return performOperation(a, b, false);
    }

    private static Cell performOperation(Cell a, Cell b, boolean isAddition)
    {
        // Find a common denominator
        int commonDenominator = a.getDenominator() * b.getDenominator();

        // Adjust the numerators to have the same denominator
        int newNumerator1 = a.getNumerator() * b.getDenominator();
        int newNumerator2 = b.getNumerator() * a.getDenominator();

        // Perform the operation on adjusted numerators
        int resultNumerator = isAddition ? newNumerator1 + newNumerator2 : newNumerator1 - newNumerator2;

        // Set the result to the current cell
        int finalNumerator = resultNumerator;
        int finalDenominator = commonDenominator;

        // Simplify the result by finding the greatest common divisor (GCD)
        int gcd = gcd(resultNumerator, commonDenominator);

        if (gcd != 1) {
            finalNumerator /= gcd;
            finalDenominator /= gcd;
        }
        return new Cell(finalNumerator, finalDenominator);
    }
    public static int gcd(int a, int b)
    {
        if (b == 0)
        {
            return a;
        }
        return gcd(b, a % b);
    }
}
