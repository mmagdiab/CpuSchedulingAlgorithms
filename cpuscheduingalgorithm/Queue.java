package cpuscheduingalgorithm;

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
            
            
            // Fetch last working time
            
            System.out.println("Last Active time:" + lastActiveTime);
            // Check whether the system was idle
            if (processes[i].arrivalTime > lastActiveTime) {
                // Update total Idle time
                idleTime += processes[i].arrivalTime-lastActiveTime;
                // Add idle time
                lastActiveTime += (processes[i].arrivalTime-lastActiveTime);
            }
            else {
            // Updating watiting time
                System.out.println(lastActiveTime+"-"+processes[i].arrivalTime);
            waitingTime += (lastActiveTime - processes[i].arrivalTime);
            }
           
            // Update last active time
            lastActiveTime += processes[i].burstTime;
        }
        System.out.println("Average waiting time: "+ (waitingTime/numberOfTasks));
        System.out.println("Idle Time: "+ idleTime);
    }
    

}
