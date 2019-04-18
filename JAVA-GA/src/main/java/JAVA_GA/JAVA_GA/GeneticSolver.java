package JAVA_GA.JAVA_GA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public abstract class GeneticSolver implements GeneticAlgorithmInterface{
	public int populationSize;
	public double probMutation;
	public Object bestpattern;
	public double probCrossOver;
	public String name;
	public int numofGeneration;
	public double bestfitness;
	public int currentGeneration;
	public Object[] population;
	protected Set<Object> patterns = new HashSet<Object>();
    protected Map<Object, Double> probabilityMap = new HashMap<Object, Double>();
	public GeneticSolver() {}
	
	public void solve(String name) {
		// TODO Auto-generated method stub
		this.name=name;
		Logger logger=Logger.getLogger("Genetic Logger");
		initializePopulation();
		for(int i=0;i<numofGeneration;i++) {
			currentGeneration=i;
			doSelection();
			Integer[] arr=new Integer[populationSize];
			for(int j=0;j<arr.length;j++) {
				arr[j]=j;
			}Collections.shuffle(Arrays.asList(arr));
			for(int k=0;k<populationSize;k+=2) {
				doCrossover(arr[k],arr[k+1]);
			}
			for(int k=0;k<populationSize;k++) {
				doMutation(k);
			}
			
			
		}
//		 System.out.println("Name:" + name + " Prob of Mutation: " + probMutation);
//	     System.out.println("Generation: " + currentGeneration );
//	     System.out.println("Best fitness: " + bestfitness);
//	     System.out.println("Best pattern: " + bestpattern + "\n");
	}

	public abstract void initializePopulation(); 
	public abstract double calculateFitness(Object code);
	public void calculateSelectedProbability() {
		// TODO Auto-generated method stub
		patterns.clear();
		probabilityMap.clear();
		for(int i=0;i<populationSize;i++) {
			patterns.add(population[i]);
		}
		double sumFitness=0;
		for(Object pattern:patterns) {
			double patternFitness=calculateFitness(pattern);
			sumFitness+=patternFitness;
			if(patternFitness>bestfitness) {
				bestfitness=patternFitness;
				bestpattern=pattern;
			}
		} 
		double averageFitness=sumFitness/patterns.size();
		for(Object pattern:patterns) {
			probabilityMap.put(pattern,calculateFitness(pattern)/sumFitness);
	//		System.out.println(pattern);
			}
	//	System.out.println("Name:" + name + " Prob of Mutation: " + probMutation);
	//     System.out.println("Generation: " + currentGeneration );
	//     System.out.println("Best fitness: " + bestfitness);
	    // System.out.println("Best pattern: " + bestpattern + "\n");
		
	}
	public void doSelection() {
		// TODO Auto-generated method stub
		calculateSelectedProbability();
		LinkedHashMap<Object,Double> sortedProbabilityMap=doSort(probabilityMap);
		List<Object>keylinks=new ArrayList<>(sortedProbabilityMap.keySet());
		List<Double>valueLinks=new ArrayList<>(sortedProbabilityMap.values());
int numValues=valueLinks.size();
double[]probabilitySum=new double[numValues];
probabilitySum[0]=valueLinks.get(0);
		for(int i=1;i<numValues;i++) {
			probabilitySum[i]=probabilitySum[i-1]+valueLinks.get(i);
		}
		for(int i=0;i<populationSize;i++) {
			double randomNumbers=Math.random();
			for(int j=0;j<numValues;j++) {
				if(randomNumbers<probabilitySum[j]) {
					population[i]=keylinks.get(j);
					break;
				}
			}
		}
	}
	LinkedHashMap<Object, Double> doSort(Map<Object, Double> passedMap) {
		// TODO Auto-generated method stub
		 List<Object> keyMaps = new ArrayList<>(passedMap.keySet());
	        List<Double>ValueMaps = new ArrayList<>(passedMap.values());
	        Collections.sort(ValueMaps);
	        LinkedHashMap<Object,Double>linkedhashmap=new LinkedHashMap<>();
	        Iterator<Double> valueIterator=ValueMaps.iterator();
	        while(valueIterator.hasNext()) {
	        	Double value=valueIterator.next();
	        	Iterator<Object>keyIterator=keyMaps.iterator();
	        	while(keyIterator.hasNext()) {
	        	Object key=keyIterator.next();
	        	Double compareValue1=passedMap.get(key);
	        	Double compareValue2=value;
	        	if(compareValue1.equals(compareValue2)) {
	        		keyIterator.remove();
	        		linkedhashmap.put(key,value);
	        		break;
	        	}
	        	}
	        }
		return linkedhashmap;
	}

	public abstract void doCrossover(int index1, int index2);
	public abstract void doMutation(int index);
		
	
	

}
