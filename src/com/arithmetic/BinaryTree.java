package com.arithmetic;

import java.sql.Struct;
import java.util.*;

/**
  二叉树
  1.递归遍历
  2.hash操作
  3.递归遍历
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
     *  时间复杂度：O(N)
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
    public static void printTreeFunc(Node head,int height,String to,int len){
        if(head == null){
            return;
        }
        printTreeFunc(head.left,height+1,"^",len);


        printTreeFunc(head.right,height+1,"v",len );
    }

    /**
     * 中序遍历
     * 随机给一个节点，找后继节点
     * 【思路】 给定的节点是其头节点左子树，则其头节点是后继节点，否则一直找
     *  具体来说，给定节点，这个节点是其整棵树的 左子树的最右节点
     */
    public static void getInNode(Node k){


    }

    public static class Info{
        public boolean isAllBST;
        public int maxSubBSTSize;
        public int max;
        public int min;

        public Info(boolean is,int size,int mi,int ma){
            isAllBST = is;
            maxSubBSTSize = size;
            max = ma;
            min = mi;
        }
    }
    /**
     * 二叉树的递归套路
     * 假设以X节点为头，假设可以向X左树和X右树要任何信息
     * 在上一步的假设下，讨论以X为头节点的树，得到答案的可能性(最重要)
     * 列出所有可能性后，确定到底需要向左数和右树要什么样的信息
     * 把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S
     * 递归函数返回S，每一棵子树都这么要求
     * 写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息
     *
     */

    /**
     * 返回一棵树的最大搜索二叉树的节点信息， 整颗子树满足  左<头<右
     * @param X
     * @return
     */
    public static Info getNodeNum(Node X){
        if(X == null){
            return null;
        }
        Info leftInfo = getNodeNum(X.left);
        Info rightInfo = getNodeNum(X.right);

        int min = X.value;
        int max = X.value;
        if(leftInfo != null){
            min = Math.min(min,leftInfo.min);
            max = Math.max(max,leftInfo.max);
        }
        if(rightInfo != null){
            min = Math.min(min,rightInfo.min);
            max = Math.max(max,rightInfo.max);
        }

        int maxSubBSTSize = 0;
        if(leftInfo != null){
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if(rightInfo != null){
            maxSubBSTSize = Math.max(maxSubBSTSize,rightInfo.maxSubBSTSize);
        }

        boolean isAllBST = false;

        //左树整体需要时搜索二叉树 、右树整体为搜索二叉树、左树最大值小于x、右树zui
        if((leftInfo == null?true:leftInfo.isAllBST) &&
            (rightInfo == null?true:rightInfo.isAllBST) &&
            (leftInfo ==null? true:leftInfo.max < X.value)  &&
            (rightInfo == null?true:rightInfo.min > X.value)
        )
        {
            maxSubBSTSize = (leftInfo == null?0: leftInfo.maxSubBSTSize )+ (rightInfo == null ?0:rightInfo.maxSubBSTSize) +1;
            isAllBST = true;
        }

        return new Info(isAllBST,maxSubBSTSize,min,max);
    }

    /**
     * 证明一棵树为完全二叉树
     * 【思路】
     * 1.按层遍历 ，队列 + 判断到有节点为不全节点，即却子树或者无子树，则剩下遍历的节点都应该为叶节点（无子树）
     * 2.二叉树的递归套路:
     *   2.1)左树满的，右树满的  且 左树h == 右树h
     *   2.2）最后一层涨的时候没有越过左树 --》 左树高度=右树高度+1 && 左树是完全二叉树、右树是满二叉树
     *   2.3）左树是满二叉树、右树是满二叉树 && 左树高度 = 右树高度+1
     *   2.4）左树是满二叉树、右树是完全二叉树 && 左树高度 = 右树高度
     *   以上满足一点即为完全二叉树
     */
    public static InfoWanquan questionWait1(Node X){

        //todo...



        return new InfoWanquan(false,false,0);
    }

    public static class InfoWanquan{
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public InfoWanquan(boolean full,boolean CBT,int h){
            isCBT = CBT;
            isFull = full;
            height = h;
        }
    }

    /**
     * 深度优先算法例子
     * 将一个数组内元素以不同的组合方式组成字符串
     */
    public static void process(String[] strs,
                               HashSet<Integer> use,
                               String path,
                               ArrayList<String> all){
        if(use.size() == strs.length){
            all.add(path);
        }
        else{
            for (int i = 0;i< strs.length;i++){
                if(!use.contains(i)){
                    use.add(i);
                    process(strs,use,path+strs[i],all);
                    use.remove(i);
                }
            }
        }
    }

    /*************************************** 贪心算法 ************************************************/
    /**
     * 1 最自然智慧的算法
     * 2 用一种局部最功利的标准，总是做出在当前看来是最好的选择
     * 3 难点在于证明局部最公里的标准可以得到全局最优解
     * 4 对于贪心算法的学习主要以增加阅历和经验为主
     */


}
