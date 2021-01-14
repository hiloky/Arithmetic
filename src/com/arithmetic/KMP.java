package com.arithmetic;

/**
 * KMP算法
 * 1 获取PMT 获取字符串前后缀，取最大值
 * 2 指针回溯 index位置PMT[j]
 * 3 求解PMT值，字符串模式匹配，
 */
public class KMP {


    public KMP(){

    }

    /**
     *
     * @param t
     * @param r
     * @return
     */
    public int getKMP(char[] t,char[] r){
        int i = 0;
        int j = 0;
        int[] next = new int[r.length];
        getNext(r,next);

        while (i < t.length && j < r.length){
            if(j == -1 || t[i] == r[j]){
                j++;
                i++;
            }
            else
                j = next[j];
        }

        if (j == r.length)
            return i-j;
        else
            return -1;
    }

    public void getNext(char[] r,int[] next){
        next[0] = -1;
        int i = 0,j = -1;
        while (i < r.length){
            if(j == -1 || r[j] == r[i]){
                ++i;
                ++j;
                next[i] = j;
            }
            else
                j = next[j];
        }
    }
}
