import java.util.*;
import java.io.*;

class Gauss_jordan
{	
	private double [][]matrix;
	private double [][]identity;
	private int n;
	void InputEquations(String file_name) throws FileNotFoundException
	{	
	   	
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the no of rows of the matrix in the file");
		n=in.nextInt();
		File file=new File(file_name);
		Scanner f=new Scanner(file);
		matrix=new double[n][n];
		//reading form the file
		
		for(int i=0;i<n;++i)					
		{
			for(int j=0;j<n;++j)
			{
				matrix[i][j]=f.nextInt();
			}
			int k=f.nextInt();
		}
		System.out.println("Mtrix in the file is");
		display(matrix);
	}
	public void Find_inverse()
    {
        identity = new double [matrix.length][matrix.length];
        //----------------------------------------------------------------------
        for(int i = 0;i < matrix.length;++i)
        {
            for(int j = 0;j < matrix.length; ++j)
            {
                if(i == j)
                identity[i][j] = 1;
                else
                identity[i][j] = 0;
            }
        }
        //----------------------------------------------------------------------
        for(int i = 0; i < matrix.length; ++i)
        {
            for(int j = i + 1 ; j < matrix.length; ++j)
            {
                double constant = matrix[j][i]/matrix[i][i];
                
                for(int k = 0; k < matrix.length; ++k)
                {
                    matrix[j][k] = matrix[j][k] - (constant * matrix[i][k]);
                     identity[j][k] = identity[j][k] - (constant * identity[i][k]);
                }
            }
        }
        //----------------------------------------------------------------------
        for(int i = matrix.length - 1;i >= 0; --i)
        {
            for(int j = i - 1; j >= 0; --j)
            {
                double constant1 = matrix[j][i]/matrix[i][i];
                for(int k = matrix.length - 1 ; k >= 0; k--)
                {
                    matrix[j][k] = matrix[j][k] - constant1 * matrix[i][k];
                    identity[j][k] = identity[j][k] - constant1 * identity[i][k];
                }
            }
        }
        //----------------------------------------------------------------------
        for(int i = 0 ; i < matrix.length; ++i)
        {
            for(int j = 0;j < matrix.length; ++j)
            {
                identity[i][j]=identity[i][j]/matrix[i][i];
            }
        }
        System.out.println("The inverse matrix is");
		display(identity);
                            
    }
    void display(double [][] arr)
    {
        System.out.println("----------------------------------------------------");
        for(int i = 0; i < arr.length; ++i)
        {
            for(int j = 0; j < arr.length ;++j)
            {
                System.out.printf("%-5.5f\t",arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------");
    }
}
