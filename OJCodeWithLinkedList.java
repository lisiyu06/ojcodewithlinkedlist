/**
 * Author: lisiyu
 * Created: 2019/10/6
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

public class OJCodeWithLinkedList {

    // 1. 删除链表中等于给定值 **val** 的所有节点------力扣203
    public ListNode removeEiements(ListNode head, int val) {
        // 1.链表为空的情况
        if (head == null) {
            return null;
        }
        // 2.先处理后面的结点
        ListNode prev = head;
        ListNode node = head.next;
        while (node != null) {
            if (node.val == val) {
                prev.next = node.next;
                node = prev.next;
            } else {
                prev = node;
                node = node.next;
            }
        }
        // 3.最后处理删除头结点的情况
        if (head.val == val) {
            head = head.next;
        }
        return head;
    }


    // 2. 反转一个单链表------力扣206
    public ListNode reverseList(ListNode head) {
        //1.空链表的情况
        if (head == null) {
            return null;
        }
        //2.链表只有一个结点的情况
        if (head.next == null) {
            return head;
        }
        //3.多个结点的情况
        ListNode prev = null;
        ListNode cur = head;
        ListNode newHead = null;
//        ListNode next = cur.next;
        while (cur != null) {
            ListNode next = cur.next;
            if (next == null) {
                //cur已经指向最后一个结点
                newHead = cur;
            }
            //反转
            cur.next = prev;
            //更新prev和cur
            prev = cur;
            cur = next;
        }
        return newHead;
    }


    // 3. 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
    //    如果有两个中间结点，则返回第二个中间结点。-------牛客
    public ListNode middleNode(ListNode head) {
        //1.得到链表的长度
        int steps = size(head) / 2;
        //2.根据长度的一半移动cur的位置
        ListNode cur = head;
        for (int i = 0; i < steps; i++) {
            cur = cur.next;
        }
        return cur;
    }
    public int size(ListNode head) {
        int size = 0;
        for (ListNode cur = head;
                cur != null;
                cur = cur.next) {
            size++;
        }
        return size;
    }


    // 4. 输入一个链表，输出该链表中倒数第k个结点。------牛客
    public ListNode FindKthToTail(ListNode head,int k) {
        int len = size(head);
        if(head == null || k < 0 ||k > len) {
            return null;
        }
        int offset = len - k;
        ListNode cur = head;
        for(int i = 0; i < offset; i++) {
            cur = cur.next;
        }
        return cur;
    }


    // 5. 将两个有序链表合并为一个新的有序链表并返回。
    //    新链表是通过拼接给定的两个链表的所有节点组成的------力扣21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode newHead = null;
        ListNode newTail = null;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                //把cur1指向的结点插入到新链表的尾部
                if (newHead == null) {
                    //新链表为空
                    newHead = cur1;
                    newTail = cur1;
                    cur1 = cur1.next;
                } else {
                    //新链表不为空
                    newTail.next = cur1;
                    //更新尾部指向
                    newTail = newTail.next;
                    cur1 = cur1.next;
                }
            } else {
                //把cur2指向的结点插入到新链表的尾部
                if (newHead == null) {
                    newHead = cur2;
                    newTail = cur2;
                    cur2 = cur2;
                } else {
                    newTail.next = cur2;
                    newTail = newTail.next;
                    cur2 = cur2.next;
                }
            }
        }
        //判断哪个链表到达尾部，哪个链表海域剩余
        if (cur1.next == null) {
            //cur2还有剩余
            newTail.next = cur2;
        } else  {
            //cur1还有剩余
            newTail.next = cur1;
        }
        return newHead;
    }


    // 6. 以给定值x为基准将链表分割成两部分，
    //    所有小于x的结点排在大于或等于x的结点之前------牛客
    public ListNode partition(ListNode pHead, int x) {
        if (pHead == null) {
            return null;
        }
        if (pHead.next == null) {
            return pHead;
        }
        ListNode smallHead = new ListNode(-1);
        ListNode smallTail = smallHead;
        ListNode bigHead = new ListNode(-1);
        ListNode bigTail = bigHead;
        for (ListNode cur = pHead;
                cur != null;
                cur = cur.next) {
            if (cur.val < x) {
                //插入到smallTail之后
                smallTail.next = new ListNode(cur.val);
                smallTail = smallTail.next;
            } else {
                //插入到 bigTail之后
                bigTail.next = new ListNode(cur.val);
                bigTail = bigTail.next;
            }
        }
        smallTail.next = bigHead.next;
        return smallHead.next;
    }


    // 7. 删除有序链表中重复的结点，重复的结点不保留，返回链表头指针-----牛客
    public ListNode deleteDuplication(ListNode pHead) {
        //先创建新的链表用来放置不重复的结点
        ListNode newHead = new ListNode(-1);
        ListNode newTail = newHead;

        ListNode cur = pHead;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
            } else {
                newTail.next = new ListNode(cur.val);
                newTail = newTail.next;
                cur = cur.next;
            }
        }
        return newHead.next;
    }


    // 8. 判断链表是否为回文结构
    public boolean chkPalindrome(ListNode A) {
        if (A == null || A.next == null) {
            return true;
        }
        //找中间节点
        int steps = size(A) / 2;
        ListNode B = A;
        for (int i = 0; i < steps; i++) {
            B = B.next;
        }

        ListNode prev = null;
        ListNode cur = B;
        while(cur != null) {
            ListNode next = cur.next;
            if (next == null) {
                // 使用 B 指向新链表的头部
                B = cur;
            }
            //反转
            cur.next = prev;
            //更新结点
            prev = cur;
            cur = next;
        }
        //判断A,B两个链表是否相同
        while (B != null) {
            if (A.val != B.val) {
                return false;
            }
            A = A.next;
            B = B.next;
        }
        return true;
    }


    // 9. 编写一个程序，找到两个单链表相交的起始节点。------力扣160
    // (不带环的链表)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //1.分别求两个链表的长度
        int lenA = size(headA);
        int lenB = size(headB);
        //2.让长的链表先走长的差值步
        if (lenA > lenB) {
            int offset = lenA - lenB;
            for (int i = 0; i < offset; i++) {
                headA = headA.next;
            }
        } else {
            int offset = lenB - lenA;
            for (int i = 0; i < offset; i++) {
                headB = headB.next;
            }
        }
        //3.分别让两个链表的结点同时移动，比较结点是否是相同节点
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }


    // 10. 给定一个链表，判断链表中是否有环。 ------力扣
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    // 11. 给定一个链表，返回链表开始入环的第一个节点。 
    //     如果链表无环，则返回 null
    public ListNode detectCycle(ListNode head) {
        // 从链表头部出发, 到入口点的距离,
        // 从快慢指针的交汇处出发, 到入口点的距离
        // 两个相等
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        //链表不带环
        if (fast == null || fast.next == null) {
            return  null;
        }
        //循环结束之后, fast 和 slow 就已经重合了
        ListNode cur1 = fast;
        ListNode cur2 = head;
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }


}
