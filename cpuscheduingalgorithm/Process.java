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
    int arrivalTime;
    int burstTime;
    int priority;

    public Process(int arrivalTime, int burstTime, int priority) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
    
    Process() {
        
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }
}

