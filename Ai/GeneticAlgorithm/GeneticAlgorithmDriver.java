
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class GeneticAlgorithmDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
        Algorithm to complete
        1) Randomly initialize populations p
        2) Determine fitness of population
        3) Untill convergence repeat:
            a) Select parents from population
            b) Crossover and generate new population
            c) Perform mutation on new population
            d) Calculate fitness for new population
        */
        
        // Container for time, periods, and courses
        // Put container in a schedule
        // Give schedule fitness
        // make the algorithm
        // run for decided generations
        
        // Inputs
        int N,MaxGen;
        double Pc,Pm;
        if(args.length!=4){
            N = 50;
            MaxGen = 10000;
            Pc = .75;
            Pm = .05;
        }
        else{
            N = Integer.parseInt(args[0]);
            MaxGen = Integer.parseInt(args[1]);
            Pc = Double.parseDouble(args[2]);
            Pm = Double.parseDouble(args[3]);
        }
        
        //create a base course list and room list then clone them for further use
        //initial build
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        String courseFile = "courseFile.txt";
        String roomFile = "roomFile.txt";
        String timeFile = "timeFile.txt";
        ArrayList<Schedule> test = new ArrayList();
        Schedule mostFit = null;
        for(int i = 0;i<N;i++){
            Schedule schedule;
            BuildList lists;
            do{
            lists = new BuildList(courseFile,roomFile,timeFile);
            schedule = new Schedule(lists.getCourseList(),lists.getRoomList(),lists.getTimeList());
            }while(!schedule.BuildSchedule());
            
            if(mostFit == null){
                mostFit = schedule;
            }
            else{
                mostFit = mostFit.compare(schedule);
            }
            test.add(schedule);
            
        }
        
        
        //GA
        int genLoc = 0;
        int check = mostFit.getFitness();
        for(int i = 0; i<MaxGen ; i++){
            GeneticAlgorithm ga = new GeneticAlgorithm(test,Pc,Pm,mostFit);
            test = ga.populate();
            mostFit = ga.getMostFit();
            if(mostFit.getFitness()>check){
                genLoc = i;
                check = mostFit.getFitness();
            }
        }
        System.out.println("Generation: " + genLoc);
        mostFit.Disp();

    }
    
}
