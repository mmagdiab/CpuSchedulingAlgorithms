package cpuscheduingalgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Mohamed Magdy
 */
public class Queue {

    double currentTime;
    double waitingTime;
    double lastActiveTime;
    int idleTime;
    int tasksFinished;
    Process[] processes;

    Queue(Process[] processes) {
        this.processes = processes;
    }

    public void FCFS() {

        // Sort array based on Arrival.
        Arrays.sort(this.processes, Comparator.comparing(Process::getArrivalTime));

        // Performal normal primitive logic refactored.
        calculatePrimitive();

    }

    public void SJF() {
        int numberOfTasks = processes.length;

        Result rs = new Result();

        // Sort array based on arrival tme 
        Arrays.sort(this.processes, Comparator.comparing(Process::getBurstTime).thenComparing(Process::getArrivalTime));

        System.out.println("Please Enter (1) For non-premetive OR (2) For premetive.");
        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();

        if (selection == 1) {
            // Call normal primitive prosedure 
            
            calculatePrimitive();

        } else {
            // SORT BY BURST_TIME THEN ARRIVAL_TIME
            // (TAKE A MINUTE) ......
            // SORT AGAIN
            // TAKE THE CURRENT LEAST BURST_TIME

            // Check whether all process are finished
            while (tasksFinished < numberOfTasks) {
                // LOOP THROUGH EACH PROCESS
                boolean processPicked = false;
                // SORT AGAIN
                Arrays.sort(this.processes, Comparator.comparing(Process::getBurstTime).thenComparing(Process::getArrivalTime));

                for (Process process : processes) {
                    // MAKE SURE PROCESS ARRIVED AND NOT FINISHED YET
                    if ((process.arrivalTime <= currentTime) && process.burstTime > 0) {
                        // PROCESS PICKED
                        processPicked = true;
                        // UPDATE WAITING TIME (edited)
                        // Insert a new slice
                        rs.ids.add(process.processId);

                        // Calculating the waiting time for this particular slice processing
                        double sliceWait = currentTime - Math.max(process.lastTimeJobProcessed, process.arrivalTime);

                        // Update "process" total waiting time
                        process.JobWaitingTime += sliceWait;

                        // Add the cumlative waiting time to be printed
                        rs.waitingTimes.add(process.JobWaitingTime);

                        // 1. update process waiting time.
                        // 2. add new slice to be printed.
                        // 3. update total waiting time, += "wait"
                        waitingTime += (sliceWait);
                        // CPU WILL PROCESS AMOUNT OF TIME = MIN(Quantum, remaining burst time)
                        int amountToBeProccessed = 1;
                        // UPDATE CURRENT TIME AND Remaining burst time 
                        currentTime += amountToBeProccessed;

                        // UPDATE LAST TIME JOB PROCESSED WITHT THE CURRENT TIME
                        process.lastTimeJobProcessed = currentTime;

                        // UPDATE TURN AROUND TIME
                        rs.turnAroundTime.add(currentTime - process.arrivalTime);
                        process.burstTime -= amountToBeProccessed;

                        // IF PROCESS DONE, UPDATE NUMBER OF FINISHED TASKS
                        if (process.burstTime == 0) {
                            tasksFinished++;
                        }
                        break;
                    }
                }
                // IF no process arrived yet, increase idle time and current time
                if (!processPicked) {
                    idleTime++;
                    currentTime++;
                }
            }
            // GET RESULTS
            rs.avgWaitingTime = (waitingTime / numberOfTasks);
            rs.idleTime = idleTime;

            rs.printResult();
        }
    }

    public void prioritySchdeduling() {

        // Sort array based on Priority from highest to lowest
        Arrays.sort(this.processes, Comparator.comparing(Process::getPriority).thenComparing(Process::getArrivalTime));

        // Calculate as a normal primitive problem 
        calculatePrimitive();
    }

    // REFACTORING REDUNDANT 
    public void calculatePrimitive() {
        
        // ETERATE THROUGH EACH PROCESS, 
        // FIRST ARRIAVAL = FIRST PROCESS
        // NON PRIMITIVE 
        // JUST FOCUS ON ARRIVAL TIME... 
        // MAKE SURE CURRENT_TIME > ARRIVAL_TIME
        
        // creating object for printing results;
        Result rs = new Result();
        int numberOfTasks = processes.length;

        while (numberOfTasks > tasksFinished) {
            // Check all processes for the highest priorty readyOne
            boolean noProcessTaken = true;
            for (Process process : processes) {
                if (!process.finished && currentTime >= process.arrivalTime) {
                    // Edits to be implemented:
                    // id, waiting time/process, TAT

                    // ADDING PROCESS ID TO THE TABLE
                    rs.ids.add(process.processId);

                    // Update waiting time for the process
                    double jobWaitingTime = currentTime - process.arrivalTime;
                    process.JobWaitingTime = jobWaitingTime;

                    rs.waitingTimes.add(jobWaitingTime);

                    // UPDATE TOTAL WAITING TIME
                    waitingTime += (jobWaitingTime);

                    // PROCESS FINISHED, INCREASE CURRENT TIME
                    currentTime += process.burstTime;

                    // ADD TAT
                    rs.turnAroundTime.add(currentTime - process.arrivalTime);

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
        rs.avgWaitingTime = waitingTime / numberOfTasks;
        rs.idleTime = idleTime;
        rs.printResult();
    }

    // ROUND ROBIN ALGORITHM
    public void RR(int Quantum) {
        int numberOfTasks = processes.length;
        Result rs = new Result();
        // Check whether all process are finished
        while (tasksFinished < numberOfTasks) {
            // LOOP THROUGH EACH PROCESS
            boolean processPicked = false;
            for (Process process : processes) {
                // MAKE SURE PROCESS ARRIVED AND NOT FINISHED YET
                if ((process.arrivalTime <= currentTime) && process.burstTime > 0) {
                    // PROCESS PICKED
                    processPicked = true;
                    // UPDATE WAITING TIME (edited)
                    // Insert a new slice
                    rs.ids.add(process.processId);

                    // Calculating the waiting time for this particular slice processing
                    double sliceWait = currentTime - Math.max(process.lastTimeJobProcessed, process.arrivalTime);

                    // Update "process" total waiting time
                    process.JobWaitingTime += sliceWait;

                    // Add the cumlative waiting time to be printed
                    rs.waitingTimes.add(process.JobWaitingTime);

                    // 1. update process waiting time.
                    // 2. add new slice to be printed.
                    // 3. update total waiting time, += "wait"
                    waitingTime += (sliceWait);
                    // CPU WILL PROCESS AMOUNT OF TIME = MIN(Quantum, remaining burst time)
                    int amountToBeProccessed = Math.min(Quantum, process.burstTime);
                    // UPDATE CURRENT TIME AND Remaining burst time 
                    currentTime += amountToBeProccessed;

                    // UPDATE LAST TIME JOB PROCESSED WITHT THE CURRENT TIME
                    process.lastTimeJobProcessed = currentTime;

                    // UPDATE TURN AROUND TIME
                    rs.turnAroundTime.add(currentTime - process.arrivalTime);
                    process.burstTime -= amountToBeProccessed;

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
        rs.avgWaitingTime = (waitingTime / numberOfTasks);
        rs.idleTime = idleTime;

        rs.printResult();
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
                    } else if (i + 1 < numberOfTasks && (processes[i + 1].priority == process.priority)) {
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
