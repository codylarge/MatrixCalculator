public class Console
{
    public static void main(String[] args)
    {
        Matrix n = new Matrix("1 2 3 4, 5 6 7 8, 9 10 8 7");
        System.out.println(n);
        n.performOperation();
        System.out.println(n);
//        Matrix m = new Matrix();
//        System.out.println(m);
//        m.performOperation();
//        System.out.println(m);
    }
}
