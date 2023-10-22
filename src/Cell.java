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

    @Override
    public String toString()
    {
        if(denominator == 1) return String.valueOf(numerator);
        else if(numerator == 0) return "0";
        else return numerator + "/" + denominator;
    }
}
