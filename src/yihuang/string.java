package yihuang;

public class string {

    //节点类，存储单个字符以及下一字符节点的位置
    static class Node {
        char data;
        int next;

        public Node(char data, int next) {//构造方法传参
            this.data=data;
            this.next=next;
        }
    }

    static Node[] Space = new Node[1000000];
    static int startP = 0;//指向空余空间的指针
    static int endP = 0;//指向存入串的末尾的指针

    //加入字符串
    private static Node strassign(char[] str) {
        if(str.length==0)
            return null;

        Node head = null;
        startP = endP;
        for (int i = 0; i < str.length; i++) {//循环遍历初始化节点，存入数据
            Space[endP+i] = new Node(str[i],startP+i+1);
        }
        endP=endP+str.length+1;
        head = Space[startP];
        return head;//成功添加字符串后返回头节点
    }

    //比较字符串是否相等
    private static boolean equal(Node head1, Node head2) {
        if (head1==null||head2==null)
            return false;
        Node cmpNode1 = head1;
        Node cmpNode2 = head2;
        while (cmpNode1 != null && cmpNode2 != null) {
            if (cmpNode1.data == cmpNode2.data) {//比较节点中存储的字符，若相同则继续，不同则返回false
                continue;
            }
            if (cmpNode1.data != cmpNode2.data) {
                return false;
            }
        }
        return true;
    }

    //拼接字符串
    private static Node joint(Node str1, Node str2) {
        if(str1==null||str2==null)
            return null;

        Node newnode = str1;
        while (Space[newnode.next] != null) {//循环遍历得到str1串的最后一位
            newnode = Space[newnode.next];
        }
        newnode.next = str2.next-1;//将其拼接
        str2 = str1;
        return str1; //返回新的头指针
    }

    //串的长度
    private static int length(Node head) {
        int length = 0;
        Node newnode = head;
        while (newnode != null) {//从头结点开始循环，直到下一节点为空
            length++;
            newnode = Space[newnode.next];
        }
        return length;
    }

    //返回第s到第e个字符长度的子串
    private static Node substring(Node head, int s, int len) {
        if(head == null||s<=0||len<=0)
            return null;

        Node newnode = head;
        for (int i = 0; i < s-1; i++) {
            newnode = Space[newnode.next];
        }
        char[] newstr = new char[len];
        for (int i = 0; i < len; i++) {
            newstr[i] = newnode.data;
            newnode = Space[newnode.next];
        }
        Node newhead = strassign(newstr);
        return newhead;
    }

    //删除特定子串
    private static void remove(Node head, int index) {
        if (head == null)
            return;

        Node newnode = head;
        for (int i = 0; i < index-2; i++) {
            newnode = Space[newnode.next];
        }
        Node temp = Space[newnode.next];
        newnode.next = temp.next;
        temp = null;
    }

    //输出
    private static void output(Node head) {
        StringBuffer s = new StringBuffer();
        Node newnode = head;
        while (newnode != null) {//循环遍历输出
            s.append(newnode.data);
            newnode = Space[newnode.next];
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        // write your code here
        String stest = "DataStructure";
        String stest1 = "String";
        char[] ctest = stest.toCharArray();
        char[] ctest1 = stest1.toCharArray();

        Node ntest = strassign(ctest);
        System.out.println("第一个串是：");
        output(ntest);
        Node ntest1 = strassign(ctest1);
        System.out.println("第二个串是：");
        output(ntest1);
        System.out.println("这两个串是否一样：");
        System.out.println(equal(ntest,ntest1));
        System.out.println("第一个串与第二个串拼接后：");
        ntest=joint(ntest,ntest1);
        output(ntest);
        System.out.println("拼接后字符串的第10个长度为5的子串是：");
        Node sonnode = substring(ntest,10,5);
        output(sonnode);
        System.out.println("这个子串的长度是");
        System.out.println(length(sonnode));
        System.out.println("删除这个子串的第三个元素：");
        remove(sonnode,3);
        output(sonnode);

    }
}
