package cpuscheduingalgorithm;

import java.util.Scanner;

/**
 *
 * @author Mohamed Magdy
 */
public class CpuScheduingAlgorithm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to CPU Schdeuling Program......");
        
        // Asking User to add number of tasks
        System.out.print("Please Enter number of tasks:");
        int numberOfTasks = scanner.nextInt();
        System.out.println(numberOfTasks);
        // Creating array of Process s
        Process[] processes = new Process[numberOfTasks];
        
        // Asking User to Arrival time with this form: 25 4 30 5
        System.out.println("Please Enter \"Time of Arrival\" for each process one after than another then hit ENTER: ");
        
        
        // Looping through user input updating Process obj with the user input
        // updating objects "Process" with arrival times
        for (int i = 0; i < numberOfTasks; i++) {
            // Creating a new object and attaching it to the pointer
            processes[i] = new Process();
            int arrivalTime = scanner.nextInt();
            processes[i].arrivalTime = arrivalTime;
        }
        
        System.out.println("Please Enter \"Priority\" for each process one after than another then hit ENTER: ");
        
        // updating objects "Process" with the Priority
        for (int i = 0; i < numberOfTasks; i++) {
            int priority = scanner.nextInt();
            processes[i].priority = priority;
        }
        
        
        System.out.println("Please Enter \"Burst Time\" for each process one after than another then hit ENTER: ");
        
        // updating objects "Process" with the Burst time
        for (int i = 0; i < numberOfTasks; i++) {
            int burstTime = scanner.nextInt();
            processes[i].burstTime = burstTime;
        }
        
        
       
    }
    
}
