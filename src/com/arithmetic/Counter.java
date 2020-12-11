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

    /***
     * 对数器 -- 线段树测试
     */
    public static boolean testSegmentTree(){
        int len = 100;
        int max = 100;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for(int i=0;i<testTimes;i++){
            int[] origin = getRandomArray(len,max);
            SegmentTree se = new SegmentTree(origin);
            se.build(1,origin.length,1); //线段树sum 集合
            //测试 add、update 方法
            //todo...
        }

        return true;
    }
}
