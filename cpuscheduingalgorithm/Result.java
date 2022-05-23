/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpuscheduingalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fastox
 */
public class Result {

    List<Integer> ids;
    List<Double> waitingTimes;
    List<Double> turnAroundTime;
    double avgWaitingTime;
    double idleTime;

    Result() {
        ids = new ArrayList<Integer>();
        waitingTimes = new ArrayList<Double>();
        turnAroundTime = new ArrayList<Double>();
    }

    public void printResult() {
        System.out.println("ID          WT          TAT");
        System.out.println("---------------------------");

        for (int i = 0; i < ids.size(); i++) {
            
            if (i != ids.size()-1 && ids.get(i+1) == ids.get(i)) continue;
            System.out.println(ids.get(i) + "          " + waitingTimes.get(i) + "          " + turnAroundTime.get(i));
        }

        System.out.println("AVG WAITING TIME: " + avgWaitingTime);
        System.out.println("IDLE TIME: " + idleTime);
    }

    public static void main(String args[]) {

        Result rs = new Result();
        rs.printResult();
    }

}
