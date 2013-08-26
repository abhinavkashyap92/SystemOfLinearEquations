
import java.util.Scanner;
import java.io.*;

public class SolveEquations
{

    public static void main(String[] args)  
	{
			int ch;
			
			
			System.out.println("-------------------Solution to the system of linear eqautions------------------");
			Scanner f=new Scanner(System.in);
			System.out.println("Enter the file name of the file having equations");
			String file_name=f.nextLine();
			System.out.println("Methods");
			System.out.println("1.Gaussian Elimination method to solve a system of equations equations");
			System.out.println("2.Gauss seidel method to solve a system of equations");
			System.out.println("3.Gauss Jordan Method to find Inverse of the Matrix");
			System.out.println("4.Exit");
			System.out.println("-------------------------------------------------------------------------------");
			Scanner in=new Scanner(System.in);
			ch=in.nextInt();
			
			switch(ch)
			{
				case 1:
					try
					{
						Gaussian_elimination Equations=new Gaussian_elimination();
						Equations.inputEquations(file_name);
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Equations before elimination");
						Equations.display();
						System.out.println("-------------------------------------------------------------------------------");
						Equations.InitL();
						Equations.elimination();	
						System.out.println("-------------------------------------------------------------------------------");
						
					}
					catch(FileNotFoundException e)
					{
						System.out.println("No such file Exists");
					}
					
					break;
			
				case 2:
						try
						{
							Gauss_seidel Equations=new Gauss_seidel();
							Equations.InputEquations(file_name);
							System.out.println("-------------------------------------------------------------------------------");
							System.out.println("Equations ");
							Equations.Display();
							Equations.solve();
						}
						catch(FileNotFoundException e)
						{
							System.out.println("No such file Exists");
						}
						break;
				case 3:
						try
						{
							Gauss_jordan Inverse=new Gauss_jordan();
							Inverse.InputEquations(file_name);
							Inverse.Find_inverse();
							
						}
						catch(FileNotFoundException e)
						{
							System.out.println("No such file exists");
						}
						break;
				case 4:
						System.exit(0);
						break;
			}
			
                   
           
    }
}
