package com.arithmetic;

import java.util.PriorityQueue;

/***
 * 堆
 */
public class Heap {
    private static int[] BigArr;
    private static int[] SmallArr;
    private static int[] SortHeapArr;
    public Heap(){

    }

    /***
     * 建堆优化：
     * 默认给的数组为完全二叉树，循环遍历从最下面遍历数组，倒叙遍历，依次为对比其左右子节点，将最大的数换到父节点位置上，
     * 其时间复杂度为：Tn = N/2*1 + N/4*2 + N/8*3 +...+N/N*(log2^N) => 2Tn = N*1 + N/2*2 + N/4*3 +N/8*4 ...+N/2^(N) *(N-1)
     *                => Tn = N + N/2 + N/4 + N/8 + ... + log2^N => O(N)
     * 任意数组排序成二叉树结构 大根堆
     * 时间复杂度 O(N*logN)
     * @param arr 随机数组
     */
    public int[] buildBig(int[] arr){
        for(int i = 1;i<arr.length;i++){
            build(i,arr);
        }

        //优化堆排序
        // todo...
        int heapSize = arr.length;
        for(int i = arr.length -1;i>=0;i--){
            //比较交换值
            ReSortHeap(arr,i,heapSize);
            heapSize--;
        }

        return arr;
    }

    /***
     * 任意数组排序成二叉树结构 小根堆
     * 时间复杂度 O(N*logN)
     * @param arr 随机数组
     */
    public int[] buildSmall(int[] arr){
        for(int i = 1;i<arr.length;i++){
            build(i,arr);
        }

        return arr;
    }

    /**
     * 递归比较交换值
     * @param o 当前树节点
     */
    public void build(int o,int[] arr){
        int f = (o-1) >> 1;
        while (arr[o] > arr[f]){
            exchange(o,f,arr);
            o = f;
            f = (o - 1) >> 1;
        }
    }

    /**
     * 交换树节点位置值
     * @param o 原来的位置
     * @param f 需要交换的位置
     * @param arr 数组
     */
    public void exchange(int o,int f,int[] arr){
        arr[o] ^= arr[f];
        arr[f] ^= arr[f];
        arr[o] ^= arr[f];
    }

    /**
     * 堆排序
     * 1.取出根节点；2.将最后节点放在根节点；3.重新排列堆
     * 停止条件 ：无子节点或子节点比当前节点值小
     * 时间复杂度：1建堆 ：O(NlogN) + 2.取数+排序堆：O(NlogN)  = O(2NlogN)
     * @return
     */
    public int[] Sort(int[] arr){
        int heapSize = arr.length;
        int i = 0;
        while(heapSize > 0){
            SortHeapArr[i] = arr[0];
            ++i;
            --heapSize;
            arr[0] = arr[heapSize-1];
            //重新排列堆
            ReSortHeap(arr,0,heapSize);
        }

        return BigArr;
    }

    /**
     * 重新排列堆
     * 将节点与左右子节点对比，谁大与谁交换，并继续下沉对比左右子节点
     * 停止条件：左子节点不存在或当前节点大于其子节点数
     */
    public void ReSortHeap(int[] arr,int node,int heapSize){
        int left = node << 1 | 1;
        while (left < heapSize){

            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left +1 : left;
            largest = arr[largest] > arr[node] ? largest:node;
            if(largest == node){
                break;
            }
            exchange(node,largest,arr);
            node = largest;
            left = node << 1 | 1;
        }
    }

    /**
     * 已知每次移动不超过k个位置能排成最小顺序序列，算法实现
     * 时间复杂度 O(NlogK)
     * @param arr
     * @param k
     */
    public void sortedArrDist(int[] arr,int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>();//堆

        int index = 0;
        //0...K
        for(;index <= Math.min(arr.length-1,k);index ++){
            heap.add(arr[index]);
        }
        int i = 0;
        for (;index < arr.length;i++,index++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }

    }
}
