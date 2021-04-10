package com.arithmeticDesign.Stack.Imp;

import com.arithmeticDesign.Stack.IMinNumStack;

import java.util.Stack;

/*
设计一个有getmin功能的栈
 */
public class MinNumStack implements IMinNumStack {

    //存数据
    private Stack<Integer> s1 = new Stack<>();
    //存最小值
    private Stack<Integer> s2 = new Stack<>();

    public MinNumStack(){

    }

    public void push(int newNum){
        if(s2.isEmpty())
            s2.push(newNum);
        else if(getMin() > newNum)
            s2.push(newNum);

        s1.push(newNum);
    }

    public int pop(){
        if (s1.isEmpty())
            throw new RuntimeException("栈无数据");

        int v = s1.pop();
        if (v == getMin())
            s2.pop();

        return v;
    }

    public int getMin(){
        if(s2.isEmpty())
            throw new RuntimeException("栈不存在最小值");
        return s2.peek();
    }

}
