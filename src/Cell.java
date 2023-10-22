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

    public void multiplyCell(Cell cell)
    {
        this.numerator *= cell.getNumerator();
        this.denominator *= cell.getDenominator();
    }
    public void divideCell(Cell cell)
    {
        this.numerator *= cell.getDenominator();
        this.denominator *= cell.getNumerator();
    }

    public void addCell(Cell cell) {
        // Find a common denominator for addition
        int commonDenominator = this.denominator * cell.denominator;

        // Adjust the numerators to have the same denominator
        int newNumerator1 = this.numerator * cell.denominator;
        int newNumerator2 = cell.numerator * this.denominator;

        // Add the adjusted numerators
        int resultNumerator = newNumerator1 + newNumerator2;

        // Set the result to the current cell
        this.numerator = resultNumerator;
        this.denominator = commonDenominator;

        // Simplify the result by finding the greatest common divisor (GCD)
        int gcd = gcd(resultNumerator, commonDenominator);

        if (gcd != 1) {
            this.numerator /= gcd;
            this.denominator /= gcd;
        }
    }

    public void subtractCell(Cell cell) {
        // Find a common denominator for subtraction
        int commonDenominator = this.denominator * cell.denominator;

        // Adjust the numerators to have the same denominator
        int newNumerator1 = this.numerator * cell.denominator;
        int newNumerator2 = cell.numerator * this.denominator;

        // Subtract the adjusted numerators
        int resultNumerator = newNumerator1 - newNumerator2;

        // Set the result to the current cell
        this.numerator = resultNumerator;
        this.denominator = commonDenominator;

        // Simplify the result by finding the greatest common divisor (GCD)
        int gcd = gcd(resultNumerator, commonDenominator);

        if (gcd != 1) {
            this.numerator /= gcd;
            this.denominator /= gcd;
        }
    }

    public Cell convertToFraction(float value)
    {
        int denominator = 100; // You can adjust this to control the precision of the fraction
        int numerator = Math.round(value * denominator);
        int gcd = gcd(numerator, denominator);
        return new Cell(numerator / gcd, denominator / gcd);
    }

    public int gcd(int a, int b)
    {
        if (b == 0)
        {
            return a;
        }
        return gcd(b, a % b);
    }

    @Override
    public String toString()
    {
        if(denominator == 1) return String.valueOf(numerator);
        else if(numerator == 0) return "0";
        else return numerator + "/" + denominator;
    }
}
