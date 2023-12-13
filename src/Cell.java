// Represents a matrix cell
public class Cell
{
    private int numerator;
    private int denominator;

    public Cell(int numerator, int denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Cell(Cell cell)
    {
        this.numerator = cell.getNumerator();
        this.denominator = cell.getDenominator();
    }

    public int getNumerator()
    {
        return numerator;
    }

    public int getDenominator()
    {
        return denominator;
    }

    public float getDecimalValue()
    {
        return (float) (numerator / denominator);
    }

    public void multiply(Cell cell)
    {
        this.numerator *= cell.getNumerator();
        this.denominator *= cell.getDenominator();
    }

    public void divide(Cell cell)
    {
        this.numerator *= cell.getDenominator();
        this.denominator *= cell.getNumerator();
    }

    public void add(Cell cell)
    {
        performOperation(cell, true);
    }

    public void subtract(Cell cell)
    {
        performOperation(cell, false);
    }

    private void performOperation(Cell cell, boolean isAddition)
    {
        // Find a common denominator
        int commonDenominator = this.denominator * cell.denominator;

        // Adjust the numerators to have the same denominator
        int newNumerator1 = this.numerator * cell.denominator;
        int newNumerator2 = cell.numerator * this.denominator;

        // Perform the operation on adjusted numerators
        int resultNumerator = isAddition ? newNumerator1 + newNumerator2 : newNumerator1 - newNumerator2;

        // Set the result to the current cell
        this.numerator = resultNumerator;
        this.denominator = commonDenominator;

        // Simplify the result by finding the greatest common divisor (GCD)
        int gcd = CellUtils.gcd(resultNumerator, commonDenominator);

        if (gcd != 1) {
            this.numerator /= gcd;
            this.denominator /= gcd;
        }
    }

    public Cell convertToFraction(float value)
    {
        int denominator = 100; // You can adjust this to control the precision of the fraction
        int numerator = Math.round(value * denominator);
        int gcd = CellUtils.gcd(numerator, denominator);
        return new Cell(numerator / gcd, denominator / gcd);
    }

    @Override
    public String toString()
    {
        if(denominator == 1) return String.valueOf(numerator);
        else if(numerator == 0) return "0";
        else return numerator + "/" + denominator;
    }
}
