package cpuscheduingalgorithm;

/**
 *
 * @author Mohamed Magdy
 */
public class Process {
     boolean finished = false;
     int timeRemaining;

    public int getPriority() {
        return priority;
    }
    int processId;
    double arrivalTime;
    int burstTime;
    int priority;
    
    // 2 new added entities (last time the process was processed, waiting time for this process)
    double lastTimeJobProcessed; 
    double JobWaitingTime;

    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
    
    Process() {
        
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }
}

