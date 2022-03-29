package cpuscheduingalgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Mohamed Magdy
 */
public class Queue {

    int currentTime;
    double waitingTime;
    int lastActiveTime;
    int idleTime;
    int tasksFinished;
    Process[] processes;

    Queue(Process[] processes) {
        this.processes = processes;
    }

    public void FCFS() {
        int numberOfTasks = this.processes.length;

        // init. lastActiveTime
        lastActiveTime = processes[0].burstTime;

        // Loop through the processes
        for (int i = 1; i < numberOfTasks; i++) {
            // Check whether the system was idle
            if (processes[i].arrivalTime > lastActiveTime) {
                // Update total Idle time
                idleTime += processes[i].arrivalTime - lastActiveTime;
                // Add idle time
                lastActiveTime += (processes[i].arrivalTime - lastActiveTime);
            } else {
                // Updating watiting time
                waitingTime += (lastActiveTime - processes[i].arrivalTime);
            }

            // Update last active time
            lastActiveTime += processes[i].burstTime;
        }

        // GET RESULTS
        System.out.println("Average waiting time: " + (waitingTime / numberOfTasks));
        System.out.println("Idle Time: " + idleTime);
    }

    public void SJF() {
        // Sort array based on arrival tme 
        Arrays.sort(this.processes, Comparator.comparing(Process::getArrivalTime).thenComparing(Process::getBurstTime));

        // Run the nomral First come First Served algorithm
        FCFS();
    }

    public void prioritySchdeduling() {
        // Sort array based on Priority from highest to lowest
        Arrays.sort(this.processes, Comparator.comparing(Process::getPriority));

        int numberOfTasks = processes.length;

        while (numberOfTasks > tasksFinished) {
            // Check all processes for the highest priorty readyOne
            boolean noProcessTaken = true;
            for (Process process : processes) {
                if (!process.finished && currentTime >= process.arrivalTime) {
                    waitingTime += (currentTime - process.arrivalTime);
                    currentTime += process.burstTime;
                    process.finished = true;
                    noProcessTaken = false;
                    lastActiveTime = currentTime;
                    tasksFinished++;
                    break;
                }

            }
            if (noProcessTaken) {
                idleTime++;
                currentTime++;
            }
        }

        // GET RESULTS
        System.out.println("Average waiting time: " + (waitingTime / numberOfTasks));
        System.out.println("Idle Time: " + idleTime);

    }

    // ROUND ROBIN ALGORITHM
    public void RR(int Quantum) {
        int numberOfTasks = processes.length;

        // Check whether all process are finished
        while (tasksFinished < numberOfTasks) {
            // LOOP THROUGH EACH PROCESS
            boolean processPicked = false;
            for (Process process : processes) {
                // MAKE SURE PROCESS ARRIVED AND NOT FINISHED YET
                if ((process.arrivalTime <= currentTime) && process.burstTime > 0) {
                    // PROCESS PICKED
                    processPicked = true;
                    // UPDATE WAITING TIME
                    waitingTime += (currentTime - process.arrivalTime);
                    // CPU WILL PROCESS AMOUNT OF TIME = MIN(Quantum, remaining burst time)
                    int amountToBeProccessed = Math.min(Quantum, process.burstTime);
                    // UPDATE CURRENT TIME AND Remaining burst time 
                    currentTime += amountToBeProccessed;
                    process.burstTime -= amountToBeProccessed;
                    // UPDATE NEW ARRIVAL TIME
                    process.arrivalTime = currentTime;
                    // IF PROCESS DONE, UPDATE NUMBER OF FINISHED TASKS
                    if (process.burstTime == 0) {
                        tasksFinished++;
                    }
                }
            }
            // IF no process arrived yet, increase idle time and current time
            if (!processPicked) {
                idleTime++;
                currentTime++;
            }
        }
        // GET RESULTS
        System.out.println("Average waiting time: " + (waitingTime / numberOfTasks));
        System.out.println("Idle Time: " + idleTime);
    }

    public void RRwithPriority(int quantum) {
        // Sort array based on Priority from highest to lowest
        Arrays.sort(this.processes, Comparator.comparing(Process::getPriority));

        int numberOfTasks = processes.length;

        // Check whether all process are finished
        while (tasksFinished < numberOfTasks) {
            // LOOP THROUGH EACH PROCESS
            boolean processPicked = false;
            for (int i = 0; i < numberOfTasks; i++) {
                Process process = this.processes[i];
                // MAKE SURE PROCESS ARRIVED AND NOT FINISHED YET
                if ((process.arrivalTime <= currentTime) && process.burstTime > 0) {
                    // PROCESS PICKED
                    processPicked = true;
                    // UPDATE WAITING TIME
                    waitingTime += (currentTime - process.arrivalTime);
                    // CPU WILL PROCESS AMOUNT OF TIME = MIN(Quantum, remaining burst time)
                    int amountToBeProccessed = Math.min(quantum, process.burstTime);
                    // UPDATE CURRENT TIME AND Remaining burst time 
                    currentTime += amountToBeProccessed;
                    process.burstTime -= amountToBeProccessed;
                    // UPDATE NEW ARRIVAL TIME
                    process.arrivalTime = currentTime;
                    // IF PROCESS DONE, UPDATE NUMBER OF FINISHED TASKS
                    if (process.burstTime == 0) {
                        tasksFinished++;
                    } else if (i+1 < numberOfTasks && (processes[i+1].priority == process.priority)) {
                            continue;
                    } else {
                        break;
                    }
                }
            }
            // IF no process arrived yet, increase idle time and current time
            if (!processPicked) {
                idleTime++;
                currentTime++;
            }
        }
        // GET RESULTS
        System.out.println("Average waiting time: " + (waitingTime / numberOfTasks));
        System.out.println("Idle Time: " + idleTime);
    }

}
