package com.arithmetic;

import java.util.ArrayList;

public class SortArithmetic {

    private static int[] arr = {12,3,8,17,4,9,22,7,10,1,2};

    /**
    * 选择排序--简单选择排序
    * 循环对比次数为 (n-1 + 1)*(n-1) /2
    * 复杂度 O(n*n)
    * 依次对比将最小数放到当前 i-1 位置
    */
    public int[] selectSort(){
        for(int i = 1;i<arr.length;i++){
            for(int j = i;j<arr.length;j++){
                if(arr[i-1] > arr[j]){
                    int temp = arr[i-1];
                    arr[i-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }

    /**
     * 交换排序--冒泡排序
     * 循环对比次数为 (n-1 + 1)*(n-1) /2
     * 复杂度 O(n*n)
     * 左右两个数对比 取小的数交换到前面，每一轮确定最大一个数放最后
     * */
    public int[] bubbleSort(){
        for(int i = 1;i<arr.length;i++){
             for(int j = 1;j<=arr.length-i;j++){
                 if(arr[j-1]>arr[j]){
                     int temp = arr[j-1];
                     arr[j-1] = arr[j];
                     arr[j] = temp;
                 }
             }
        }

        return arr;
    }

    /**
     * 交换排序--冒泡排序改进
     * 循环对比次数为 (n-1 + 1)*(n-1) /2
     * 复杂度 O(n*n)
     * 左右两个数对比 取小的数交换到前面，每一轮确定最大一个数放最后
     * 增加变量flag，如果一轮下来没有交换，说明已经替换完成，无需循环
     * */
    public int[] bubbleSort2(){


        return arr;
    }

    /**
     * 插入排序--直接插入排序
     * 对比最大次数： ((n-1) + 1)(n-1) /2
     * 复杂度 ： O(n*n)
     * 将数据插入到一个有序排列中，依次对比右边的数，直到大于右边的数为止
     * */
    public int[] insertSort(){
        for(int i = 1;i<arr.length;i++){
            for(int j = i;j > 0;j--){
                if(arr[j] < arr[j-1]){
                    /*int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;*/
                    //上面操作等于
                    arr[j] = arr[j]^arr[j-1];
                    arr[j-1] = arr[j]^arr[j-1];
                    arr[j] = arr[j]^arr[j-1];
                }
                else{
                    continue;
                }
            }
        }

        return arr;
    }

    /**
     * 插入排序--希尔排序
     *
     * 选择一个增量的序列 t1,t2...tk,tk=1，一共k趟 顺序插入排序
     * 其中每趟的增量为 自己设定>=2
     * */
    public int[] shellSort(){

        return arr;
    }

    /**
     * 选择排序--二元选择排序
     *
     * 每趟确定最大和最小数
     * */
    public int[] binarySort(){

        return arr;
    }

    /**
     * 选择排序--堆排序
     *
     * 堆分为 大顶堆序列：（96, 83,27,38,11,09) 、 小顶堆序列：（12，36，24，85，47，30，53，91）
     * 堆的最顶元素为最大或最小值，每次输出最顶元素，再调整成新堆，继续输出最小或最大元素
     * 1. 如何将n 个待排序的数建成堆；
     * 2. 输出堆顶元素后，怎样调整剩余n-1 个元素，使其成为一个新堆。
     *
     * 调整小顶堆的方法：
     * 1）设有m 个元素的堆，输出堆顶元素后，剩下m-1 个元素。将堆底元素送入堆顶（（最后一个元素与堆顶进行交换），堆被破坏，其原因仅是根结点不满足堆的性质。
     * 2）将根结点与左、右子树中较小元素的进行交换。
     * 3）若与左子树交换：如果左子树堆被破坏，即左子树的根结点不满足堆的性质，则重复方法 （2）.
     * 4）若与右子树交换，如果右子树堆被破坏，即右子树的根结点不满足堆的性质。则重复方法 （2）.
     * 5）继续对不满足堆性质的子树进行上述交换操作，直到叶子结点，堆被建成
     * */
    public int[] heapSort(){

        return arr;
    }

    /**
     * 交换排序--快速排序
     * 以第一个元素为准，依次将 n-1 --> 1 --> n-2 --> 2 ...... 直到 k>= n-k,其中当 n-k位置的数大于校准元素，则 继续取n-k-1，直到有数小于校准元素
     *
    */
    public int[] quickSort(){

        return arr;
    }

    /**
     * 快排算法改进
     * 在本改进算法中,只对长度大于k的子序列递归调用快速排序,让原序列基本有序，然后再对整个基本有序序列用插入排序算法排序。
     * 实践证明，改进后的算法时间复杂度有所降低，且当k取值为 8 左右时,改进算法的性能最佳
     * */
    public int[] quickSort2(){
        return arr;
    }

    /**
     * 归并排序
     * 归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
     *
     * @return
     */
    public int[] mergeSort(){
        return arr;
    }

    /**
     * 冒泡排序 改进3
     * 每趟找到最小、最大者
     *
     * */
    void Bubble_2 ( int r[], int n){
        int low = 0;
        int high= n -1; //设置变量的初始值
        int tmp,j;
        while (low < high) {
            for (j= low; j< high; ++j) //正向冒泡,找到最大者
                if (r[j]> r[j+1]) {
                    tmp = r[j]; r[j]=r[j+1];r[j+1]=tmp;
                }
            --high;                 //修改high值, 前移一位
            for ( j=high; j>low; --j) //反向冒泡,找到最小者
                if (r[j]<r[j-1]) {
                    tmp = r[j]; r[j]=r[j-1];r[j-1]=tmp;
                }
            ++low;                  //修改low值,后移一位
        }
    }

}
