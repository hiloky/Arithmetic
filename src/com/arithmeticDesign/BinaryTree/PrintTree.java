package com.arithmeticDesign.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 打印二叉树
 * 递归与非递归方式
 * morris遍历
 */
public class PrintTree {
    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }

    public static void main(String[] args){
        Node t = createTree(4,100);
        PrintTree p = new PrintTree();
        p.level(t);

        System.out.println();

        //p.in(t);
        p.pre(t);System.out.println();
        p.nonRecPre2(t);System.out.println();
    }

    /* 递归 */
    /**
     * 先序遍历打印
     * top -> left -> right
     */
    public void pre(Node head){
        if(head == null){
            return;
        }

        System.out.print(head.value + " ");
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序遍历
     * left -> top -> right
     * @param head
     */
    public void in(Node head){
        if(head == null){
            return;
        }

        in(head.left);
        System.out.print(head.value + " ");
        in(head.right);
    }

    /**
     * 后序遍历
     * left -> right -> top
     * @param head
     */
    public void pos(Node head){
        if(head == null){
            return;
        }

        pos(head.left);
        pos(head.right);
        System.out.print(head.value + " ");
    }

    /* 非递归遍历 */

    /**
     * 按层遍历（建立额外的空间栈）
     * @param head
     */
    public void level(Node head){
        if(head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            System.out.print(head.value + " "); //打印弹出栈内最底元素
            if(head.left != null){
                queue.add(head.left);
            }
            if(head.right != null){
                queue.add(head.right);
            }
        }
    }

    /**
     * 非递归-先序遍历
     * top -> left -> right
     * @param h
     */
    public void nonRecPre(Node h){
        if(h == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(h);
        Node k = null;
        while (!stack.isEmpty()){
            k = stack.peek();

            if(h == k){
                System.out.print(k.value + " ");
            }

            if(k.left != null && k.left != h && k.right != h){
                stack.add(k.left);
                h = k.left;
            }
            else if(k.right != null && k.right != h){
                stack.add(k.right);
                h = k.right;
            }
            else{
                h = stack.pop();
            }
        }
    }

    /**
     * 先序遍历--非递归（改进版本）
     * 降低遍历节点次数，上面方法每个节点遍历3次，时间复杂度 O(3N) ,降低常数项
     */
    public void nonRecPre2(Node h){
        if(h == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(h);
        while (!stack.isEmpty()){
            h = stack.pop();
            System.out.print(h.value + " ");
            if(h.right != null){
                stack.add(h.right);
            }
            if(h.left != null){
                stack.add(h.left);
            }
        }
    }


    /**
     * 非递归-中序遍历
     * left -> top -> right
     * @param h
     */
    public void nonRecIn(Node h){
        if(h == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || h != null){
            if(h != null){
                stack.add(h);
                h = h.left;
            }
            else {
                h = stack.pop();
                System.out.print(h.value + " ");
                h = h.right;
            }
        }
    }

    /**
     * 非递归-后序遍历
     * left -> right -> top
     * @param head
     */
    public void nonRecPos(Node head){
        if(head == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(head);
        Node k = null;
        while (!stack.isEmpty()){
            k = stack.peek();

            if(k.left != null && head != k.right && head != k.left){
                stack.add(k.left);
            }
            else if(k.right != null && head != k.right){
                stack.add(k.right);
            }
            else{
                head = k;
                System.out.print(stack.pop().value + " ");
            }
        }
    }

    /**
     * morris 遍历
     * 空间复杂度 O(1)
     * 从头节点开始
     * 1 当无left节点，cur = cur.right
     * 2 存在left节点，找到left的最右节点 mostRight
     *  ① mostRight的右指针为null，mostRight.right = cur ,cur = cur.left;
     *  ② mostRight的右指针不为null，mostRight.right = null,cur = cur.right;
     * 停止条件 cur = null
     * 适用于递归左右两树信息可串行获取
     * @param h
     */
    public void morris(Node h){
        if(h == null){
            return;
        }

        Node cur = h;
        while(cur != null){
            if(cur.left != null){
                Node mostRight = cur.left;
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                else{
                    mostRight.right = null;
                }

            }

            cur = cur.right;
        }
    }

    /**
     * 对数器,生成随机二叉树
     * @param h 树的高度
     * @param size 最大数
     * @return
     */
    public static Node createTree(int h,int size){
        Queue<Node> queue = new LinkedList<>();
        //第一层初始化
        Node head = new Node((int)(Math.random()*size));
        queue.add(head);
        queue.add(null);
        int i = 1;
        while (!queue.isEmpty() && i < h){
            Node k = queue.poll();
            if(k == null ){
                i++;
                continue;
            }

            double lNum = Math.random();
            double rNum = Math.random();

            //0.5概率存在子树
            if(Math.round(lNum) == 1){
                Node l = new Node((int)(Math.random()*size));
                k.left = l;
                queue.add(l);
            }
            else{
                k.left = new Node(-1);
            }
            if(Math.round(lNum) == 1){
                Node r = new Node((int)(Math.random()*size));
                k.right = r;
                queue.add(r);
            }
            else {
                k.right = new Node(-2);
            }
            //下一个元素为null时,添加层级标识
            if(queue.peek() == null){
                queue.add(null);
            }
        }

        return head;

    }
}
