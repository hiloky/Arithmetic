package com.arithmeticDesign.LinkedList;

import com.arithmetic.SortArithmetic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定有序列表头head1 和 head2 ，打印其公共部分
 * 【思考】 有序链表即表示无环，只需找到相交点，并打印相交之后的节点即可
 */
public class PrintCommonalityPart {
    public static void main(String[] args){
        Node n1 = createNode();
        Node n2 = createNode();

        Node k1 = n1;
        Node k2 = n2;
        while (n1 != null){
            System.out.print(n1.value + " ");
            n1 = n1.next;
        }
        System.out.println();
        while (n2 != null){
            System.out.print(n2.value + " ");
            n2 = n2.next;
        }
        System.out.println();

        print2(k1,k2);
    }

    public static class Node{
        public Node next;
        public int value;

        public Node(int _value){
            value = _value;
        }
    }

    /**
     * 方法二：已知为有序链表,从小到大
     * 额外空间复杂度O(1)
     * @param head1
     * @param head2
     */
    public static void print2(Node head1,Node head2){
        while (head1 != null && head2 != null){
            if(head1.value > head2.value){
                head2 = head2.next;
            }
            else if(head1.value < head2.value){
                head1 = head1.next;
            }
            else{
                System.out.print(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    /**
     * 生成随机node
     */
    public static Node createNode(){
        int size = 50;
        int maxNum = 200;
        Queue<Node> queue = new LinkedList<>();
        int[] arr = new int[size];
        for(int i = 0;i<size;i++){
            arr[i] = (int)(Math.random()*maxNum);
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        SortArithmetic sortArithmetic = new SortArithmetic();
        sortArithmetic.selectSort(arr);
        Node n = null;
        Node lastN = new Node(arr[size-1]);
        for (int j = size-2;j>=0;j--){
            n = new Node(arr[j]);
            n.next = lastN;
            lastN = n;
        }

        return lastN;
    }
}
