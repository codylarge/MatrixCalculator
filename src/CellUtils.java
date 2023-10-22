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
}
