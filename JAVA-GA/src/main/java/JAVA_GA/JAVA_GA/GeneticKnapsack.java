package JAVA_GA.JAVA_GA;

public class GeneticKnapsack extends GeneticSolver{
  Double maxTime;
  int totalnumberofwords;
  int[] time;
  int[] points;
  public GeneticKnapsack(double maxTime,int totalnumberofwords,int[] time,int[] points,double probCrossOver,double probMutation,int populationSize,int numofGeneration) {
	  this.maxTime=maxTime;
	  this.totalnumberofwords=totalnumberofwords;
	  this.time=time;
	  this.points=points;
	  this.probCrossOver=probCrossOver;
	  this.probMutation=probMutation;
	  this.populationSize=populationSize;
	  this.numofGeneration=numofGeneration;
  }
  public GeneticKnapsack(double maxTime,int time[],int points[]) {
	  this.maxTime=maxTime;
	  this.time=time;
	  this.points=points;
  }
  public GeneticKnapsack() {}
	@Override
	public void calculateSelectProb() {
		// TODO Auto-generated method stub
		population=new String[populationSize];
		for(int i=0;i<populationSize;i++) {
			population[i]=generateRandomString(totalnumberofwords);
		}
	}
public String generateRandomString(int length) {
	String string="";
	for(int i=0;i<length;i++) {
		double randomNum=Math.random();
		//System.out.println("Random Number"+randomNum);
		if(randomNum>=0.8) {
			string+=1;
		}else {
			string+=0;
		}
	}
	//System.out.println("String:"+string);
	return string;
}
	@Override
	public void initializePopulation() {
		// TODO Auto-generated method stub
		population=new String[populationSize];
		for(int i=0;i<populationSize;i++) {
			population[i]=generateRandomString(totalnumberofwords);
		}
	}

	@Override
	public double calculateFitness(Object code) {
		// TODO Auto-generated method stub
		String codeString=(String)code;
		if(sumTime(codeString)>maxTime) {	
		//	System.out.println("Maximum time:"+maxTime);
		//	System.out.println("code string:"+codeString);
			return 0;
			}
		else {
			//System.out.println("time taken for fitness calculation:"+maxTime);
			//System.out.println("code string:"+codeString);
			return sumValue(codeString);
		}
	}

	double sumValue(String codeString) {
		// TODO Auto-generated method stub
		double totalValue=0;
		for(int i=0;i<codeString.length();i++) {
			if(codeString.charAt(i)=='1') {
				totalValue+=points[i];
			}
		}
		//System.out.println("points of String:"+totalValue);
		return totalValue;
	}
	private Double sumTime(String codeString) {
		// TODO Auto-generated method stub
		double totalTime=0;
		for(int i=0;i<codeString.length();i++) {
			if(codeString.charAt(i)=='1') {
				totalTime+=time[i];
			}
		}
	//	System.out.println("time to type:"+totalTime);
		return totalTime;
	}
	@Override
	public void doCrossover(int index1, int index2) {
		// TODO Auto-generated method stub
		String newString1="",newString2="";
		for(int i=0;i<totalnumberofwords;i++) {
			double randomNumber1=Math.random();
			if(randomNumber1<probCrossOver) {
				newString1+=((String)population[index2]).charAt(i);
				newString2+=((String)population[index1]).charAt(i);
				//System.out.println("probability of crossover"+probCrossOver);
			}else {
				newString1+=((String)population[index1]).charAt(i);
				newString2+=((String)population[index2]).charAt(i);
				//System.out.println("less probability of crossover"+probCrossOver);
			}}population[index1]=newString1;
			population[index2]=newString2;
	//	System.out.println("population at index1:"+population[index1]);
//		System.out.println("population at index 2:"+population[index2]);
		
	}

	@Override
	public void doMutation(int index) {
		// TODO Auto-generated method stub
		String newString="";
		for(int i=0;i<totalnumberofwords;i++) {
			double randomNumber=Math.random();
			if(randomNumber<probMutation) {
				newString+=flip(((String)population[index]).charAt(i));
			}else {
				newString+=((String)population[index]).charAt(i);
			}
		}population[index]=newString;
	//	System.out.println("REsult of mutation:"+population[index]);
	}
	private char flip(char gene) {
		// TODO Auto-generated method stub
		if(gene=='0') {
			return '1';
			}else {
				return '0';
			}
	}

}
