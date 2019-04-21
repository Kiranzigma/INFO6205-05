package JAVA_GA.JAVA_GA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * Hello world!
 *
 */
public class App 
{
	private static double maxTime;
	private static int totalnumberofwords;
	private static int[] time;
	private static int[] points;
    public static void main( String[] args )
    {
    	solveKnapsack();
        //System.out.println( "Hello World!" );
    }
private static void solveKnapsack() {
	// TODO Auto-generated method stub
	readKnapsackInput("knapsack.txt");
	randomSolver(maxTime,totalnumberofwords,time,points,"Ran.Bot-1");
//	for(int i=0;i<totalnumberofwords;i++) {
//		System.out.println("time at random solver---->"+time[i]+"points at randomsolver--->"+points[i]);
//	}
	randomSolver(maxTime,totalnumberofwords,time,points,"Ran.Bot-2");
	randomSolver(maxTime,totalnumberofwords,time,points,"Ran.Bot-3");
	randomSolver(maxTime,totalnumberofwords,time,points,"Ran.Bot-4");
	randomSolver(maxTime,totalnumberofwords,time,points,"Ran.Bot-5");

	GeneticKnapsack solver1=new GeneticKnapsack(maxTime,totalnumberofwords,time,points,0.5,0.00001,1000,1000);
	solver1.solve("GA.Bot-1");
	
	GeneticKnapsack solver2=new GeneticKnapsack(maxTime,totalnumberofwords,time,points,0.5,0.000000025,1000,1000);
	solver2.solve("GA.Bot-2");	
	GeneticKnapsack solver3=new GeneticKnapsack(maxTime,totalnumberofwords,time,points,0.5,0.0030,1000,1000);
	solver3.solve("GA.Bot-3");
	GeneticKnapsack solver4=new GeneticKnapsack(maxTime,totalnumberofwords,time,points,0.5,0.09,1000,1000);
	solver4.solve("GA.Bot-4");
	GeneticKnapsack solver5=new GeneticKnapsack(maxTime,totalnumberofwords,time,points,0.5,0.5,1000,1000);
	solver5.solve("GA.Bot-5");
}
private static void randomSolver(double maxTime,int totalnumberofwords,int [] time,int [] points,String name) {
	int totalTime=0;
	int totalPoints=0;
	Integer[] arr=new Integer[totalnumberofwords];
	for(int k=0;k<arr.length;k++) {
		arr[k]=k;
	}
	Collections.shuffle(Arrays.asList(arr));
	for(int i=0;i<totalnumberofwords;i++) {
		if(maxTime>totalTime+time[arr[i]]) {
			totalTime+=time[arr[i]];
			totalPoints+=points[arr[i]];
		
		}
	}
	System.out.println(name+"Time:"+totalTime+"Points:"+totalPoints+"\n");
	
}
private static void readKnapsackInput(String filepath) {
	// TODO Auto-generated method stub
	try {
		File file=new File(filepath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		String string=reader.readLine();
		String stringarray[]=string.split("/");
		maxTime=Double.parseDouble(stringarray[1]);
		totalnumberofwords=Integer.parseInt(stringarray[0]);
		time=new int[totalnumberofwords];
		points=new int[totalnumberofwords];
		for(int i=0;i<totalnumberofwords-1;i++) {
			string=reader.readLine();
			stringarray=string.split("/");
			time[i]=Integer.parseInt(stringarray[1]);
			//points[i]=Integer.parseInt(stringarray[0]);
			points[i]=stringarray[0].length();
			//System.out.println("time-->"+time[i]);

		//	System.out.println("points-->"+points[i]);
			
		}reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
}
}
