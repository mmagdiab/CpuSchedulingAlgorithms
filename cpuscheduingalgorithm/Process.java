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
    
    Process() {
        
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }
}

