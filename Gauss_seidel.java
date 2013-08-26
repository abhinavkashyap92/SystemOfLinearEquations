import java.util.*;
import java.io.*;

class Gauss_seidel
{
	 private double[][]a;     
	 private double []b;
	 private double []q;
	 private double []q1;
	 private int neq;
	 private int nvar;
   
	 void InputEquations(String file_name) throws FileNotFoundException
    {
		
	 	Scanner in=new Scanner(System.in);
	   System.out.println("Enter the no of equations");
      neq=in.nextInt();
      a=new double [neq+1][neq+1];
	   
		b=new double[neq+1];
		
		
		
		File file=new File(file_name);
		Scanner f=new Scanner(file);
		for(int i=1;i<=neq;++i)
		{
			for(int j=1;j<=neq && f.hasNext();++j)
			{
				a[i][j]=f.nextInt();
			}
			b[i]=f.nextInt();
		}
		
		 System.out.println("Enter the approximate solution");
		 q=new double[neq+1];
		 for(int i=1;i<=neq;++i)
		 {
			q[i]=in.nextDouble();
	    }

    }
	 void solve()
	 {
		int i,j,flag=1;
		q1=new double[neq+1];
		for(i=1;i<=neq;++i)
		{
			q1[i]=q[i];
		}
		for(int p=1;p<=neq;++p)
		{
			double sum=0;
			for(int q=1;q<=p-1;++q)
			{
				
					sum=sum+Math.abs(a[p][q]);
				
				
			}
			for(int q=p+1;q<=neq;++q)
			{
				sum=sum+Math.abs(a[p][q]);
			}
			if(sum>Math.abs(a[p][p]))
			flag=0;
			break;
		}
		if(flag==1)
		repeat();
		else
		System.out.println("Cannot be solved using Gauss Seidel method");

	}
	void repeat()
	{
		int i,j,m,count=0;
		double p;
		for(i=1;i<=neq;++i)
		{
			p=0;
			for(j=1;j<=i-1;++j)
			{
				p=p+a[i][j]*q[j];
	
			}
			for(m=i+1;m<=neq;++m)
			{
				p=p+a[i][m]*q[m];
			}
			q[i]=(b[i]-p)/a[i][i];
			//System.out.println("q: "+q[i]);
      	//System.out.println("q1: "+q1[i]);
	 
	}
	
	for(i=1;i<=neq;++i)
	{
		if(Math.pow((q1[i]-q[i]),2)<0.001)
		{
			count=count+1;	
		}
		q1[i]=q[i];
		
	}
	if(count==neq)
	{
		System.out.println("The solution is found");
		
		display();
	}
	else
	{
		repeat();
	}


	}
	void display()
	{
		int i;
		for(i=1;i<=neq;++i)
		System.out.printf("%.5f\n",q[i]);
	}
	void Display()
	{
		for(int i=1;i<=neq;++i)
		{
			for(int j=1;j<=neq;++j)
			{
				System.out.printf("%.5f\t",a[i][j]);
			}
			System.out.printf("=\t");
			System.out.printf("%.5f",b[i]);
			System.out.println("");
		}
	}
}
