package com.arithmeticDesign.Stack.Test;

import com.arithmeticDesign.Stack.ITwoStackQueue;
import com.arithmeticDesign.Stack.Imp.TowStackQueue;
import sun.awt.windows.ThemeReader;

public class StackT {


    public static void main(String[] args){
        //TwoStackQueueTest1();
        TwoSQueue();
    }

    public static void TwoStackQueueTest1(){
        ITwoStackQueue twoStackQueue = new TowStackQueue();
        for (int i = 1 ; i < 101;i += i){
            twoStackQueue.add(i);
            System.out.print(i+" ");
        }
        System.out.println();
        Object k = twoStackQueue.poll();
        while (k != null){
            System.out.print(k + " ");
            k = twoStackQueue.poll();
        }

        System.out.println();
    }

    public static void TwoSQueue(){
        ITwoStackQueue twoStackQueue = new TowStackQueue();
        Thread k = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i < 101;i++){
                        twoStackQueue.add(i);
                        System.out.print(i+" add;");
                    }
                }
                catch (Exception e){

                }
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 200;i *= 2){
                    twoStackQueue.add(i);
                    System.out.print(i+" mul;");
                }
            }
        });

        k.start();
        t.start();


        Thread p = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(10);
                    System.out.println("________");
                }
                catch (Exception e){

                }
                Object k = twoStackQueue.poll();
                while (k != null){
                    System.out.print(k + " poll;");
                    k = twoStackQueue.poll();
                }
            }
        });
        p.start();

    }


}
