package com.arithmetic;

/***
 * 查找算法
 */
public class SearchArithmetic {

    private static int[] arr = {1,2,4,5,8,23,54,123,234,1111,5464,34645};

    /***
     * 构造初始化 arr
     */
    public SearchArithmetic() {

    }
    /***
     * 二分查找
     * 时间复杂度 N = 2^k  ,k = log(2^N)
     * @param num
     * @return
     */
    public boolean dichotomySearch(int num){
        int r = arr.length - 1;
        int l = 0;
        int mid = 0;

        while(l < r){
            mid = l + ((r-l) >> 1);

            if(arr[mid] == num){
                return true;
            }
            else if(arr[mid] > num){
                //查找数再左边
                r = mid -1;
            }
            else {
                //在右边
                l = mid + 1;
            }
        }

        return arr[l] == num;
    }

    /***
     * 获取局部最小数位置(二分法)
     * 无序数组获取局部最小   arr[i-1]>arr[i]<arr[i+1]
     * 所以，若是 arr[0] 或arr[i-1] 都不为局部最小，则数据是先降后升，一定存在局部最小，找到数据的拐点 ，即 'f(x) = 0 的位置
     * @return
     */
    public int getMinPartIndex(){
        int l = 1;
        int r = arr.length - 2;
        int mid = 0;

        //查找第一个、最后一个数是否满足
        if(arr[0] < arr[1]){
            return 0;
        }
        if(arr[r+1] < arr[r]){
            return r+1;
        }

        while(l < r){
            mid = l + ((r-l) >> 1);
            //arr[k] 1、大于两个数  2、 介于两数中间 3、小于两数
            if(arr[mid] > arr[mid-1]){
                r = mid -1;
            }
            else if(arr[mid] > arr[mid+1]){
                l = mid +1;
            }
            else{
                return mid;
            }
        }

        return l;
    }

    /***
     * 一个数组中两组出现奇数次，其他数出现偶数次，如何找出这两组数？
     * 思路 ：异或获取 a^b ---> 找到最右侧位为 1 的位数，将这些为1的位数异或得到 a ----> 再将为0的数异或得到b
     */
    public TwoTuple<Integer,Integer> getSignTwoNum(int[] arrs){
        int eor = 0;
        int a = 0;
        int b = 0;
        for(int i=0;i<arrs.length;i++){
            eor = eor^arrs[i];
        }

        //此时eor = a^b;
        int eorR = eor & ((~eor)+1);
        for(int j=0;j<arrs.length;j++){
            //& eor同为为1的数
            if((arrs[j] & eorR) == eor){
                a = a^arrs[j];
            }
            /*//& eor同为0 的数
            if((arrs[j] & eorR) == 0){
                b = b^arrs[j];
            }*/
        }

        b = a^eor;
        return new TwoTuple<Integer, Integer>(a,b);
    }

    /***
     * 获取一个数中有多少1
     * 遍历次数最少思路 ： 取最右侧1 ，然后再抹去最右侧1；
     */
    public int bitCount(int num){
        int count = 0;
        while(num != 0){
            int rightOne = num & ((~num) +1);
            count++;
            num ^= rightOne;
        }
        return count;
    }

}

class TwoTuple<A,B>{
    public final A first;
    public final B second;

    public TwoTuple(A a,B b){
        first = a;
        second = b;
    }
}