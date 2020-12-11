package com.arithmetic;

import java.util.*;

/**
  二叉树
 */
public class BinaryTree {
    public BinaryTree(){

    }

    public static class Node{
        public int value;
        Node left;
        Node right;

        public Node(int v){
            value = v;
        }
    }

    /**
     * 先序遍历, 头->左->右
     * 【思路】 【递归实现】
     *  其中每一次达到节点3次，假如有7个几点分别为1~7；数走的次序为
     *  124442555213666377731  -> 每次每个节点走了3次，其中打印每次的第一个节点为线序，打印第二次走的节点为中序，打印第三次走的节点为后序
     */
    public static void pre(Node head){
        if(head == null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序遍历, 左->头->右
     */
    public static void in(Node head){
        if(head == null){
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    /**
     * 后续遍历, 左->右->头
     */
    public static void pos(Node head){
        if(head == null){
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    /**
     * 先序、中序、后续遍历二叉树
     * 【思路】【非递归实现】
     *  设计一个栈，把节点压入栈中，每一次压入子树，按特定顺序，先序的话，弹出左，在压入左的右子树、左子树，弹出左子树，继续寻找左子树的子树。。。
     *  先序遍历的结果 倒过来为后续
     */
    public static void traverse(){

    }

    /**
     * 后续遍历二叉树
     * @param h
     */
    public static void pos2(Node h){
        if(h != null){
            Stack<Node> stack = new Stack<>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()){
                c = stack.peek();
                if(c.left != null && h != c.left && h != c.right){
                    stack.push(c.left);
                }
                else if(c.right != null && h != c.right){
                    stack.push(c.right);
                }
                else {
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
    }

    /**
     * 实现二叉树的按层遍历
     * 【思路】每层遍历
     *  一层一层加进队列，在依次弹出来
     */
    public static void layerTraverse(Node head){
        if(head == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(node.value);
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
    }

    /**
     * 找出最大层节点数
     * 【思路】 hashmap记录节点和层数
     *  用map记录其子节点的层级，然后queue用来做层级遍历
     */
    public static int layerTraverseEvolve(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node,Integer> map = new HashMap<>();
        map.put(head,1);

        int curLevel = 1; //当前正在统计的哪一层宽度
        int curLevelNodes = 0; //当前层多少
        int max = 0; //更新的所有曾宽度最大值
        while (!queue.isEmpty()){
            Node node = queue.poll();
            int nodeLevel = map.get(node);
            if(node.left != null){
                map.put(node.left,nodeLevel + 1);
                queue.add(node.left);
            }
            if(node.right != null){
                map.put(node.right,nodeLevel+1);
                queue.add(node.right);
            }

            if(curLevel == nodeLevel){
                curLevelNodes++;
            }
            else{
                max = Math.max(max,curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max,curLevelNodes);
        return max;
    }

    /**
     * 二叉树的序列化、反序列化
     * 【思路】 因为树存在空节点，所以存在子树，但子树只有左子树或者右子树，补全空缺的子节点 补null
     * 先序、后续、中序 ->序列化
     */
    public static void serializeBinaryTree(Node head){
        //先序
        //todo...
        Queue<String> rqs = new LinkedList<>();
        pres(head,rqs); //返回的rqs为先序遍历结果

        //中序
        //todo...
        Queue<String> inQue = new LinkedList<>();

        //后序
        //todo...
    }

    /**
     *
     * @param head
     * @param rqs
     */
    public static void pres(Node head,Queue<String> rqs){
        if(head == null){
            rqs.add(null);
        }
        else{
            rqs.add(String.valueOf(head.value));
            pres(head.left,rqs);
            pres(head.right,rqs);
        }
    }

    /**
     * 先序序列 build 整棵树，反序列化
     */
    public static Node unserializeBinaryTree(Queue<String> preQue){
        String val = preQue.poll();
        if(val == null){
            return null;
        }

        Node head = new Node(Integer.valueOf(val));
        head.left = unserializeBinaryTree(preQue);
        head.right = unserializeBinaryTree(preQue);
        return head;
    }

    /**
     * 按层序列化
     *
     */
    public static Queue<String> layerSerialize(Node head){
        Queue<String> ans = new LinkedList<>();
        if(head == null){
            ans.add(null);
        }
        else{
            //todo...
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(head);
            while (!queue.isEmpty()){
                if(head.left != null){
                    queue.add(head.left);
                    ans.add(String.valueOf(head.left.value));
                }
                else{
                    ans.add(null);
                }
                if(head.right != null){
                    queue.add(head.right);
                    ans.add(String.valueOf(head.right.value));
                }
                else{
                    ans.add(null);
                }
            }
        }

        return ans;
    }

    /**
     * 按层反序列化
     */
    public static Node unserializeLayer(Queue<String> queue){
        //todo...

        return null;
    }

    /**
     * 如何设计一个打印整棵树的打印函数
     * 【思路】
     */
    public static void printTreeFunc(){

    }

}
