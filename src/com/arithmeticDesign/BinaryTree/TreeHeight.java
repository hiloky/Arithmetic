package com.arithmeticDesign.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求树的最小深度
 * 递归
 * morris
 */
public class TreeHeight {

    public static void main(String[] args){
        PrintTree.Node n = PrintTree.createTree(10,100);
        PrintTree p = new PrintTree();
        p.level(n);
        System.out.println();
        System.out.print(getTreeMinHeight(n));
    }

    /**
     * 递归
     * K -> 左树高度右树高度  min（左，右） + 1
     * @param head
     */
    public static int getTreeMinHeight(PrintTree.Node head){
        if(head == null){
            return 0;
        }

        int lHeight = 0;
        int rHeight = 0;
        if(head.left != null){
            lHeight = getTreeMinHeight(head.left);
        }
        if(head.right != null){
            rHeight = getTreeMinHeight(head.right);
        }

        int minH = Math.min(lHeight,rHeight) + 1;

        return minH;
    }

    /**
     * morris遍历求树高度
     * @param head
     * @return
     */
    public static int getMorrisMinTree(PrintTree.Node head){
        if(head == null){
            return 0;
        }

        PrintTree.Node cur = head;
        PrintTree.Node mostRight = null;
        int minH = 1;
        int tempH = 1;
        while (cur != null){
            mostRight = cur.left;

            if(mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                    tempH++;
                }

                if(mostRight != cur){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                else{
                    tempH = 1;
                    mostRight.right = null;
                }
            }

            cur = cur.right;
            minH = Math.min(tempH+minH,tempH);
        }


        return 1;
    }

}
