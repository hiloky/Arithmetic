package com.arithmetic;

import java.util.Arrays;

public class Arithmetic {

    public static void main(String[] args){
        System.out.println("this my hzk arithmetic question");

        SortArithmetic sort = new SortArithmetic();
        int[] res = sort.insertSort();
        toStringMethod(res);
    }

    private static void toStringMethod(int[] arr)
    {
        // 自定义一个字符缓冲区，
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        //遍历int数组，并将int数组中的元素转换成字符串储存到字符缓冲区中去
        for(int i=0;i<arr.length;i++)
        {
            if(i!=arr.length-1)
                sb.append(arr[i]+" ,");
            else
                sb.append(arr[i]+" ]");
        }
        System.out.println(sb);
    }

}
