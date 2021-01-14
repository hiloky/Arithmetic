package com.arithmeticDesign.LinkedList;

/**
 * 实现两个方法 删除单链表和双链表倒数第k个节点
 * 时间复杂度O(n)
 * 空间复杂度O(1)
 */
public class DeleteLinked {
    public static class Node{
        public Node next;
        public int value;
        public Node last;

        public Node(int _value){
            value = _value;
        }
    }

    public static void main(String[] args){

    }

    /**
     * 删除单链表倒数第k个节点
     * @param head 头节点
     * @param k 删除节点位置
     */
    public static Node deleteSingleLinked(Node head,int k){
        Node n = head;
        Node a = head;
        int kk = k;
        while (kk > 0 && n.next != null){
            n = n.next;
            kk--;
        }

        int i = 0;
        Node x = n;
        while (x.next != null){
            i++;
            x = x.next;
        }

        if(i > k){
            //得到倒数第k+1 节点
            while (i > k+1){
                i--;
                n = n.next;
            }
            deleteNode(n.next,n,n.next.next);
        }
        else {
            //得到倒数第k+1个节点
            while (k > i+1){
                --k;
                a = a.next;
            }
            deleteNode(a.next,a,a.next.next);
        }

        return head;
    }

    /**
     * 删除节点d
     * @param d 待删除的节点
     * @param l 待删除节点上一个节点
     * @param n 待删除节点下一个节点
     */
    public static void deleteNode(Node d,Node l,Node n){
        d.next = null;
        l.next = n;
    }

    /**
     * 双链表删除倒数第k节点
     * @param k
     */
    public static Node deleteDoubleLinked(Node head,int k){
        if(head == null || head.next == null){
            return head;
        }
        Node n = head;
        int kk = k;
        while (kk > 0 && n.next != null){
            kk--;
            n = n.next;
        }
        if(k != 0){
            return head;
        }

        int i = 0;
        Node x = n;
        while (x.next != null){
            x = x.next;
            i++;
        }
        //根据k节点在链表的位置决定从哪个节点位置出发找到k
        //降低时间复杂度 常数项
        //已知n节点 为第k个位置，head头节点、x尾节点
        if(i > k && i < 2*k){
            while (i>k){
                i--;
                n = n.next;
            }
            deleteNode(n,n.last,n.next);
        }
        else if(i > 2*k){
            while (k > 1){
                k--;
                x = x.last;
            }
            deleteNode(x,x.last,x.next);
        }
        else if (i < k && ((i << 1) > k)){
            while (k > i+1){
                k--;
                n = n.last;
            }
            deleteNode(n,n.last,n.next);
        }
        else{
            Node res = head;
            while (i >= 0){
                i--;
                res = res.next;
            }
            deleteNode(res,res.last,res.next);
        }

        return head;
    }
}
