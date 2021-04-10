package com.arithmeticDesign.Stack.Imp;

import com.arithmeticDesign.Stack.ITwoStackQueue;

import java.util.Stack;

/*
两个栈组成的队列
s1为空栈，s2为反序的栈
 */
public class TowStackQueue implements ITwoStackQueue {

    private Stack<Object> s1 = new Stack<>();
    private Stack<Object> s2 = new Stack<>();

    public Boolean add(Object o){

        synchronized (this.getClass()){
            if (!s1.isEmpty())
                throw new RuntimeException("栈内有数");
            dump(s2,s1);
            //此时s2为空，s1有数,且s1为数的倒叙
            s1.add(o);
            //将s1 倾倒入s2
            dump(s1,s2);
        }

        return true;
    }

    public Object poll(){
        synchronized (this.getClass()){
            if (s2.isEmpty())
                return null;
            return s2.pop();
        }
    }


    public Object peek(){
        synchronized (this.getClass()){
            if (s2.isEmpty())
                return null;
            return s2.peek();
        }
    }

    /**
     * 倾倒数据进入另一个栈
     * @param one
     * @param two
     */
    private void dump(Stack<Object> one,Stack<Object> two){
        if(one.isEmpty())
            return;
        if (!two.isEmpty())
            throw new RuntimeException("第二个栈要为空");
        while (!one.isEmpty()){
            two.add(one.pop());
        }
    }
}
