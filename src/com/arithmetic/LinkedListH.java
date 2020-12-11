package com.arithmetic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 链表
 */
public class LinkedListH {
    public static class Node{
        public int value;
        public Node next;
        public Node rand;

        public Node(int v){
            value = v;
        }
    }

    public LinkedListH(){

    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 思路：创建两个指针，一个穿两步的快指针，一个走一步的慢指针
     */
    public static Node getLinkMid(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }

        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 给定一个单链表head，判断链表是否为回文结构
     * 会问结构  abcba ,中间数对称结构
     * 思路：将链数据存入栈中，栈是先进后出，所以再依次抛出对比
     */
    public static boolean checkNodeIsHuiWen(Node head){
        Stack<Node> stack = new Stack<Node>();
        Node node = head;
        while (node !=null){
            stack.push(node);
            node = node.next;
        }

        while (head != null){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }

        return true;
    }

    /**
     * 给定一个单链表head，判断链表是否为回文结构
     * 优化思路：只需比较一半即可
     * @param head
     * @return
     */
    public static boolean checkNodeIsHuiWen2(Node head){
        Stack<Node> stack = new Stack<Node>();
        Node node = head;
        //todo...
        return false;
    }

    /**
     * 给定一个单链表head，判断链表是否为回文结构
     * 优化思路：将后半段指针反过来指，然后依次判断，最后直到null
     * 时间复杂度 ：O(1)
     * @param head
     * @return
     */
    public static boolean checkNodeIsHuiWen3(Node head){
        if(head == null || head.next == null){
            return true;
        }

        Node slow = head.next;
        Node fast = head.next;
        while (slow.next != null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //得到中点slow
        Node t = null;
        Node slow1 = slow.next;
        while (slow != null){
            t = slow1.next;
            slow1.next = slow;
            slow = slow1;
            slow1 = t;
        }

        return false;
    }

    /**
     * 克隆链表 ，链表中有额外的指针 map哈希表克隆
     *  链表是：非闭环，但每个节点有额外的指针 rand，也就是双指针
     */
    public static Node copyListWithRand(Node head){
        HashMap<Node,Node> map = new HashMap<Node,Node>();
        Node node = head;
        while (node != null){
            map.put(node,new Node(node.value));
            node = node.next;
        }
        node = head;
        while (node != null){
            map.get(node).next = map.get(node.next);
            map.get(node).rand = map.get(node.rand);
            node = node.next;
        }

        return map.get(head);
    }

    /**
     * 不用哈希表克隆链表
     * 1->1'->2->2'->3->3' 按这个思路，再设置好 1‘ 、2’、3‘ 的rand，
     * 再分离 成 1->2->3 和 1'->2'->3'，此时copy的链表的rand已经设置好的，可直接返回
     */
    public static Node copyListWithRand2(Node head){

        return head;
    }

    /**
     * 约瑟夫环
     * 给定两个可能有环可能无环的单链表，头节点head1和head2.
     * 请实现一个函数，如果两个链表相交，返回相交的第一个节点。如果不相交，返回null
     * 【要求】 如果两个链表长度之和为N，时间复杂度请达到O(N),额外空间复杂度达到O(1)
     *
     * 【思路】用hashset ，将第一个链表放入hashset，之后遍历第二条链表（简单）
     */
    public static Node bunana1(Node head1,Node head2){
        HashSet<Node> set = new HashSet<Node>();
        Node node = head1;
        boolean res = true;
        while (node != null && res){
            res = set.add(node);
            node = node.next;
        }
        Node node2 = head2;
        while (node2 != null){
            boolean res2 = set.add(node2);
            if(!res2){
                return node2;
            }
            node2 = node2.next;
        }

        //无相交
        return null;
    }

    /**
     * 对应上面
     * 【思路二】 分情况，1两链表无环，2，两链表有环
     * 因为自从相交节点之后，之后的部分必然为公共部分，所以只能存在都五环或者都有环
     *
     */
    public static Node bunana2(Node head1,Node head2){
        //无环
        if(checkIsLoop2(head1) == null && checkIsLoop2(head2) == null){
            if(head1 == null || head2 == null){
                return null;
            }

            Node node1 = head1;
            Node node2 = head2;
            int n = 0;
            while (node1.next != null){
                n++;
                node1 = node1.next;
            }
            while (node2.next != null){
                n--;
                node2 = node2.next;
            }
            //不相交
            if(node1 != node2){
                return null;
            }
            //赋值头节点给走完的node1与node2
            node1 = n>0?head1:head2;
            node2 = node1 == head1?head2:head1;

            n = Math.abs(n);
            while (n != 0){
                n--;
                node1 = node1.next;
            }
            while (node1 != node2){
                node1 = node1.next;
                node2 = node2.next;
            }
            return node1;
        }

        //有环
        //分两种：1 入环节点一致，也就是链表2 不是在环内入环，在环外入环，执行流程和上面代码一致
        if(checkIsLoop2(head1) != null && checkIsLoop2(head2) != null){
            Node loop1 = checkIsLoop2(head1);
            Node loop2 = checkIsLoop2(head2);
            if(loop1 == loop2){
                //入环节点一致，代码同上无环链表相交
                //todo...
            }
            else{
                //入环节点找到后，一直前进，直到找到loop2
                //停止条件,循环完一圈，也就是loop1 != loop1
                Node node1 = loop1.next;
                while (node1 != loop1){
                    if(node1 == loop2){
                        return loop2;
                    }
                    node1 = node1.next;
                }
                //否则不相交
            }
        }

        //其他情况，两链表不相交
        return null;
    }

    /**
     * 判断一个链表是否有环，无则返回null ， 有则返回第一个入环节点
     * 思路一：将节点存入hashset中，时间复杂度O(N),空间额外申请了O(N)
     * 思路二：设置一个快指针、一个慢指针，分别在 head.next.next 和 head.next上，快指针走两格，慢指针走一格，快、慢指针会在环中的某个点相遇，然后重新将快指针指向head节点
     *        慢指针位置不变，此时快、慢指针同时一格一格前进，再次相遇的点为环的入口点；可证明此猜想
     *        空间复杂度O(1)
     * @param head
     * @return
     */
    public static Node checkIsLoop(Node head){
        HashSet<Node> set = new HashSet<Node>();
        Node node = head;
        while (node != null){
            boolean res = set.add(node);
            if(!res){
                return node;
            }
            node = node.next;
        }

        return null;
    }

    /**
     * 对应上面思路二
     * @param head
     * @return
     */
    public static Node checkIsLoop2(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node n1 = head.next;
        Node n2 = head.next.next;
        while (n1 != n2){
            if(n2.next == null || n2.next.next == null){
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head;
        while (n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }

        return n1;
    }

    /**
     * 告诉某个链表要删除的节点，如何删除这个节点
     * 【思路】 因为jvm会释放掉不被引用的内存地址，所以将 node.next 地址内存copy到当前节点，然后当前节点指向 node.next.next ，node.next->next = null,可实现
     *          缺点：1.有些内存空间复杂，难以拷贝，2.若是删除服务器之类的，替代法不可用，很可能下一节点的服务器是正在使用的，当前服务器是报废的
     *               3.这种做法无法删除最后一个节点 ，所以此方法不可取
     *  综上所述：最为安全、可靠的删除节点的办法必须是给到头节点，否则无法安全、可靠的删除节点
     */
    public boolean deleteNode(Node node){

        return true;
    }
}
