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
                this.sc = new Scanner(System.in);
                System.out.println("Enter 0 at any point to exit row operations");
                String operation = "";
                while(!operation.equals("0"))
                {
                    operation = "?";
                    while(operation.equals("?"))
                    {
                        System.out.print("Enter the operation(? for formatting): ");
                        operation = sc.nextLine();
                        if (operation.equals("?")) MatrixUtils.formattingMenu();
                    }

                    matrix.performOperation(operation);
                    System.out.println("result:\n" + matrix);
                }
                break;
            case 2:
                //matrix.matrixOperation();
                break;
            case 3:
                System.out.println("Scalar to multiply 'A' by: ");
                float scalar = sc.nextFloat(); sc.nextLine();
                matrix.applyScalar(scalar);
                System.out.println("result: " + matrix);
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
