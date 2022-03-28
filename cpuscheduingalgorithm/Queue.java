package cpuscheduingalgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Mohamed Magdy
 */
public class Queue {
    double waitingTime;
    int lastActiveTime;
    int idleTime;
    Process[] processes;
    Queue (Process[] processes) {
        this.processes = processes;
    }
    
    public void FCFS() {
        int numberOfTasks = this.processes.length;
        
        // init. lastActiveTime
        lastActiveTime = processes[0].burstTime;
        
        // Loop through the processes
        for (int i = 1 ; i < numberOfTasks; i++) {
            // Check whether the system was idle
            if (processes[i].arrivalTime > lastActiveTime) {
                // Update total Idle time
                idleTime += processes[i].arrivalTime-lastActiveTime;
                // Add idle time
                lastActiveTime += (processes[i].arrivalTime-lastActiveTime);
            }
            else {
            // Updating watiting time
            waitingTime += (lastActiveTime - processes[i].arrivalTime);
            }
           
            // Update last active time
            lastActiveTime += processes[i].burstTime;
        }
        
        // GET RESULTS
        System.out.println("Average waiting time: "+ (waitingTime/numberOfTasks));
        System.out.println("Idle Time: "+ idleTime);
    }
    
    public void SJF() {
        // Sort array based on arrival tme 
        Arrays.sort(this.processes, Comparator.comparing(Process::getBurstTime));
       
        // Run the nomral First come First Served algorithm
        FCFS();
    }
}
