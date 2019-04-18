package JAVA_GA.JAVA_GA;

public interface GeneticAlgorithmInterface {
void solve(String name);
void initializePopulation();
double calculateFitness(Object code);
void calculateSelectProb();
void doSelection();
void doCrossover(int a1,int a2);
void doMutation(int a);
}
