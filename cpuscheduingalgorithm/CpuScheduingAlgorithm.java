package cpuscheduingalgorithm;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mohamed Magdy
 */
public class CpuScheduingAlgorithm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Some logic are commented to switch to file reading application
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to CPU Schdeuling Program......");
        System.out.println("Please provide the data in the text file like so:");
        System.out.println("(Process ID, arrival time, cpuTime, priorty");
        // Asking User to add number of tasks
        //System.out.print("Please Enter number of tasks:");
        //int numberOfTasks = scanner.nextInt();
        //System.out.println(numberOfTasks);
        // Creating array of Process s
        //Process[] processes = new Process[numberOfTasks];

        // Asking User to Arrival time with this form: 25 4 30 5
        //System.out.println("Please Enter \"Time of Arrival\" for each process one after than another then hit ENTER: ");

        // THE NEW LOGIC: to accept the input from file
        
        String filename;
        List<Process> jobList = new ArrayList<Process>();
        filename = "/home/fastox/Processes.txt";
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(filename));
            //System.out.println("processId arrivalTime cpuTime");
            
            while ((sCurrentLine = br.readLine()) != null) {
                String a[] = sCurrentLine.split(",");
                int arrivalTime = new Integer(a[1]);
                int cpuTime = new Integer(a[2]);
                int priority = new Integer(a[3]);
                Process job = new Process(arrivalTime, cpuTime, priority);
                jobList.add(job);
                //System.out.println(processId+" "+ arrivalTime+" " + cpuTime);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        // Looping through user input updating Process obj with the user input
        // updating objects "Process" with arrival times
        //for (int i = 0; i < numberOfTasks; i++) {
            // Creating a new object and attaching it to the pointer
          //  processes[i] = new Process();
          //  int arrivalTime = scanner.nextInt();
           // processes[i].arrivalTime = arrivalTime;
       // }

        //System.out.println("Please Enter \"Priority\" for each process one after than another then hit ENTER: ");

        // updating objects "Process" with the Priority
//        for (int i = 0; i < numberOfTasks; i++) {
//            int priority = scanner.nextInt();
//            processes[i].priority = priority; // Lowest Input is the largest.
//        }

        //System.out.println("Please Enter \"Burst Time\" for each process one after than another then hit ENTER: ");

        // updating objects "Process" with the Burst time
//        for (int i = 0; i < numberOfTasks; i++) {
//            int burstTime = scanner.nextInt();
//            processes[i].burstTime = burstTime;
//        }
        Process[] processes = new Process[jobList.size()]; 
        processes  = jobList.toArray(processes);
        Queue test = new Queue(processes);
        System.out.println("PLEASE SELECT THE ALGORITHM:");
        System.out.println("1. FCFS");
        System.out.println("2. SJF");
        System.out.println("3. Priority Scheduling");
        System.out.println("4. Round Robin");
        System.out.println("5. Priority Scheduling with round robin");
        System.out.print("Selection: ");    
        int selection = scanner.nextInt();
        switch (selection) {
            case 1:
                test.FCFS();
                break;
            case 2:
                test.SJF();
                break;
            case 3:
                test.prioritySchdeduling();
                break;
            case 4:
                System.out.print("Please Enter Quantum value: ");
                int quantum = scanner.nextInt();
                test.RR(quantum);
                break;
            case 5:
                System.out.print("Please Enter Quantum value: ");
                int quantum2 = scanner.nextInt();
                test.RRwithPriority(quantum2);
                break;
            default:
                throw new AssertionError();
        }
    }

}
