import java.util.Scanner;

//Facade
public class SystemInterface
{
    Scanner sc = new Scanner(System.in);
    public Matrix createMatrix()
    {
        return new Matrix();
    }
    public Matrix createMatrix(String matrixString)
    {
        return new Matrix(matrixString);
    }

    public void matrixMenu(Matrix matrix)
    {
        System.out.println("Your matrix A:\n" + matrix + "\n");

        System.out.println("Matrix Menu");
        System.out.println("1. Row Operations");
        System.out.println("2. Matrix Operations");
        System.out.println("3. Multiply by a Scalar");
        System.out.println("4. Find transpose");
        System.out.println("5. Find Inverse");

        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice)
        {
            case 1:
                matrix.performOperation();
                break;
            case 2:
                //matrix.matrixOperation();
                break;
            case 3:
                System.out.println("Scalar to multiply 'A' by: ");
                float scalar = sc.nextFloat(); sc.nextLine();
                matrix.applyScalar(scalar);
                break;
            case 4:
                System.out.println("Transpose:\n" + matrix.getTranspose());
                break;
            case 5:
                //matrix.getInverse();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

    }

    private void resetScanner()
    {
        this.sc = new Scanner(System.in);
    }

}
