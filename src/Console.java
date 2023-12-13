public class Console
{
    // TODO: ADD FRACTION CLASS AND MAKE MATRIX AN ARRAY OF FRACTIONS INSTEAD OF FLOATS
    public static void main(String[] args)
    {
        SystemInterface sys = new SystemInterface();
        Matrix m = sys.createMatrix("1 2 3, 4 5 6, 7 8 9");
        Matrix n = sys.createMatrix("1 2, 4 5");
        sys.matrixMenu(n);
    }
}
