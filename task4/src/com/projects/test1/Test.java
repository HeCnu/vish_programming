package com.projects.test1;
import java.util.ArrayList;
import java.util.Collections;

public class Test {

    public static void main(String args[]) {

        ArrayList<Integer> randInt = new ArrayList<Integer>();

        for (int i = 0; i <= 100; i++){
            randInt.add(randNum());
        }

        fifo(randInt);

    }

    static int randNum(){
        int num = (int)(Math.random()*10);
        return num;
    }

    static void fifo(ArrayList<Integer> fifoList){

        int num_of_window = 9;
        ArrayList<Integer> window = new ArrayList<Integer>();
        ArrayList<Integer> sorted;
        float mediane;

        for (int numoffifo : fifoList) {
            if(window.size() >= num_of_window){
                window.remove(0);
            }
            window.add(numoffifo);

            sorted = (ArrayList<Integer>)window.clone();

            if(window.size() == num_of_window){
                sorted = sort(sorted);
                if(num_of_window%2 == 0){
                    int center_window = num_of_window/2;
                    mediane = (float)(sorted.get(center_window-1) + sorted.get(center_window))/2;
                }else{
                    mediane = (float)(sorted.get((int)(num_of_window/2)));
                }
                System.out.println(window + " -> " + sorted + " -> [" + mediane + "]");
            }

        }
    }

    public static ArrayList<Integer> sort(ArrayList<Integer> unsortedList){

        ArrayList<Integer> sortedList = unsortedList;

        for(int out = sortedList.size() - 1; out >= 1; out--){
            for(int in = 0; in < out; in++){
                if(sortedList.get(in) > sortedList.get(in+1)){
                    int var = sortedList.get(in);
                    sortedList.set(in, sortedList.get(in+1));
                    sortedList.set(in+1, var);
                }
            }
        }
        return sortedList;
    }

    /*public static float Mediane(ArrayList<Integer> list){
        float mediane = 0;
        int var = 0;

        for(int i = 0; i < list.size(); i++){
            var += list.get(i);
        }

        mediane = (float)var/list.size();
        return mediane;
    }*/
}
