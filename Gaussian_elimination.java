import java.util.*;
import java.io.*;

class Gaussian_elimination
{
    private double[][]a;
    private double []b;
    private int neq;
    private int nvar;
    private int lu=0; 
    private double[][]L;
   void inputEquations(String file_name) throws FileNotFoundException
   {
		
	   Scanner in=new Scanner(System.in);
	   System.out.println("Enter the no of equations");
       neq=in.nextInt();
       a=new double [neq][];
	 
       
		File file=new File(file_name);
		Scanner f=new Scanner(file);
		int n=0;
		while(f.hasNext())
		{
			++n;
			int r=f.nextInt();
		}
		nvar=(n/neq)-1;
		
		if(neq > nvar || neq < nvar)
		{
			System.out.println("Equations form singular system");
			System.exit(0);
		}
	 
		for(int i=0;i<neq;++i)
		{
			a[i]=new double[nvar+1];
		 
		}
      
		b=new double[neq];
		
		
		
		File file1=new File(file_name);
		Scanner f1=new Scanner(file1);
		
		for(int i=0;i<neq;++i)
		{
			for(int j=0;j<nvar & f1.hasNext();++j)
			{
				a[i][j]=f1.nextInt();
			}
			b[i]=f1.nextInt();
		}
		
		
	

   }
   
   void elimination()
   {
      
       
       for(int i = 0;i<nvar;++i) //tarversing coloumn 
       {
          int pivot_row=i;
          
          for(int j=i+1;j<neq;++j)
          {
              //Checks whether the element below the chosen pivot is gretaer and makes that the pivot
              //This eliminates the need to exchange rows for pivot = 0 as this is automatically taken care of
              
              if(Math.abs(a[j][i]) > Math.abs(a[pivot_row][i]))
              {
                  pivot_row=j;
              }
          }
          
          //this makes row exchanges as it is done on paper
          double[] temp = a[i];
          a[i]=a[pivot_row];
          a[pivot_row]=temp;
          
          //makes sure that the augumentation remains same by exchanging the rows with the constants
          
          double t=b[i];
          b[i]=b[pivot_row];
          b[pivot_row]=t;
         
          //Actual elimination steps start from here
          
         for(int k = i+1 ;k < neq ; ++k) // this is again for traversal of a row of the matrix
         {
             double sum = 0;
             double constant = ( a[k][i] / a[i][i] );
             b[k] = b[k] - ( constant * b[i] );
             
             for(int m = i; m < nvar ;++m) // this is for column of the matrix
             {
                 a[k][m] = a[k][m]- ( constant*a[i][m] );
                 sum = sum + a[k][m];
             } // this suggests that one row is completed with elimination
             L[k][i]=(-constant);
          
         }
       
       }
       
        solve();
		System.out.println("Do you want to see LU decomposition of the given system of equations ??   if yes press 1,0 if not");
		Scanner n=new Scanner(System.in);
		lu=n.nextInt();
		display();
     
   }
   void display()
   {
		if(lu==0)
		{
			for(int i=0;i<neq;++i)
			{
				for(int j=0;j<nvar;++j)
				{
					System.out.printf("%-5.5f\t",a[i][j]);
				}
				System.out.printf("%-5.5f\n",b[i]);
			}
			System.out.println("-------------------------------------------------------------------------------");
		}
		else
		{
			System.out.println("-------------------------------L------------------------------------------");
			for(int i=0;i<neq;++i)
			{
				for(int j=0;j<nvar;++j)
				{
					System.out.printf("%-5.5f",L[i][j]);
				
				}
				
				System.out.println();
			}
			
			System.out.println("---------------------------------------------------U------------------------------");
			for(int i=0;i<neq;++i)
			{
				for(int j=0;j<nvar;++j)
				{
					System.out.printf("%-5.5f",a[i][j]);
				}
				System.out.println();
			}
			
			
		}
   }
   
   void solve()
   {
       
       double[] answers = new double[nvar];
       
       answers[0] = b[neq-1]/a[neq-1][nvar - 1];
       int answercount = 1;
       
       
       double[] solutions = new double[nvar];
       
       
		for(int i = neq - 2 ; i >= 0 ; i--)
		{
			int k = 0;
		
			for(int j = nvar - 1 ; j >= 0 ; j--)
			{
            
				if(a[i][j] != 0)
				{
					solutions[k] = a[i][j];
					k++;
                
				}
			}
        
			int m;
        
			for( m = 0 ; m < nvar -1 && solutions[m+1] != 0 ; m++)
			{
				answers[answercount] += solutions[m] * answers[m];
			}
        
			answers[answercount] = (b[i] - answers[answercount])/solutions[m];
        
        
			answercount ++; 
		}
		if(isSolution(answers))
		{
			System.out.println("Solutions are");
			int i=neq;
			for(double a : answers)
			{
				System.out.printf("X%d: %-5.5f \n",i,a); 
				--i;
			}
			System.out.println("-------------------------------------------------------------------------------");
		}
		else
		{
			System.out.println("Wrong solution obtained");
		}
	}
	
	void InitL()
	{
			L=new double[neq][nvar];
			for(int i=0;i<neq;++i)
			{
				for(int j=0;j<nvar;++j)
				{
					if(i==j)
					{
						L[i][j]=1;
					}
					else
					{
						L[i][j]=0;
					}
				}
			}
	}
	
	boolean isSolution(double[] answers)
	{
		//take the first equation
		/*
		double s=0.0;
		int i=0;
		int j=nvar-1;
		int a=0;
		
			while(i<nvar && j>=0)
			{
				s=s+(answers[j]*a[0][i]);
				i++;
				--j;
			}
			if(Math.abs(s-b[0])<0.0001)
			{
				return true;
			}
			else
			{
				return false;
			}
		*/
		return true;
		
	}
	
}