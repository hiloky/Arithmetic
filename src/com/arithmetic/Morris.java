package com.arithmetic;

/**
 * morris算法（遍历二叉树）
 * 当前节点cur，一开始cur来到整棵树头
 * 1.cur无左树，cur = cur.right
 * 2.cur有左树，找到左树最右节点，mostright
 *   ① mostright的右指针指向null的，mostright.right = cur，cur = cur.left
 *   ② mostright的右指针指向cur ，mostright.right = null，cur = cur.right
 *  停止条件 cur = null
 *  这个算法的时间复杂度： O(N)
 */

/**
 * 【什么时候可以用morris】
 *  传统方法 递归dp可以基本上解决二叉树问题，但是所依赖的空间复杂度 O(N)
 *  morris空间复杂度为O(1)
 *  递归套路中 节点X需要左树、右树的信息才能推展开则只能用递归dp；原理为，获取左树信息后压入栈，再获取右树信息压入栈，再依次释放右树、左树信息
 *  若是节点X依次需要左树、右树信息，左右两树没有依赖关系，可以串行获取信息的话，可以改为morris遍历
 */
public class Morris {

    public static class Node{
        public Node left;
        public Node right;
        public int value;

        public Node(int v){
            value = v;
        }
    }

    /**
     * morris算法（遍历二叉树）
     * 当前节点cur，一开始cur来到整棵树头
     * 1.cur无左树，cur = cur.right
     * 2.cur有左树，找到左树最右节点，mostright
     *   ① mostright的右指针指向null的，mostright.right = cur，cur = cur.left
     *   ② mostright的右指针指向cur ，mostright.right = null，cur = cur.right
     *  停止条件 cur = null
     *  这个算法的时间复杂度： O(N)
     * @param head
     */
    public static void morris(Node head){
        if(head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;

            if(mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                else {
                    mostRight.right = null;
                }
            }
            // else内容 先序遍历
            else{
                System.out.print(cur.value + " ");
            }

            System.out.print(cur.value + " "); //中序遍历
            cur = cur.right;
        }
    }

    /**
     * morris 逆序打印
     * 【思路】 将整个树拆成以右边界 的单链表 ，反转链表，依次打印，再反转回来
     */
    public static void printEdge(Node head){
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    /**
     * 链表逆序
     */
    public static Node reverseEdge(Node head){
        Node cur = head.right;
        head.right = null;
        while (cur != null){
            Node next = cur.right;
            cur.right = head;
            cur = next;
        }

        return cur;
    }

}
