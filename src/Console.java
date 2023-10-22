public class Console
{
    public static void main(String[] args)
    {
        SystemInterface sys = new SystemInterface();
        Matrix m = sys.createMatrix("1 2 3, 4 5 6");
        sys.matrixMenu(m);
    }
}
