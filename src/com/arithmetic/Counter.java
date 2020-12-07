package com.arithmetic;

public class Counter {

    /***
     * 随机生成int数组
     * @param size 数量大小
     * @param maxValue 随机数的最大值
     * @return int数组
     */
    public static int[] getRandomArray(int size,int maxValue){
        int[] arr = new int[size];
        for(int i = 0;i<arr.length;i++){
            arr[i] = (int)((maxValue+1)*Math.random());
        }

        return arr;
    }
}
