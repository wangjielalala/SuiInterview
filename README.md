# SuiInterview
Interview for SuiShou

1、给定一个 list，插入新元素的同时保持它是从小到大有序的，请考虑程序效率。
示例:
依次插入 6、4、3 后，list 的顺序是 3、4、6 依次再插入 2、8 后，list 的顺序是 2、3、4、6、8

**第一题对应的源码是 Solution.class** 

`
 
    public class Solution {
     private static List<Integer> dataList = new LinkedList<>();
 
     static {
         dataList.add(5);
         dataList.add(10);
         dataList.add(15);
         dataList.add(25);
         dataList.add(35);
         dataList.add(45);
     }
 
     public static void add(Integer ...datas) {
         //TODO 把 data 添加到 dataList,并保持它从小到大有序
         for (int data : datas) {
             int a = search(data);
             if (a == -1) {
                 dataList.add(data);
             } else if (a == 0) {
                 dataList.add(0, data);
             } else {
                 dataList.add(a, data);
             }
             System.out.println(dataList);
         }
     }
 
     private static void add1(Integer ...datas) {
         for (int data : datas) {
             if(data<=dataList.get(0)){
                 dataList.add(0,data);
                 System.out.println(dataList);
                 continue;
             }
             Integer low = dataList.stream().filter(e->data >= e).max(Comparator.comparing(e->{
                 return e;
             })).get();
             dataList.add(dataList.indexOf(low)+1,data);
             System.out.println(dataList);
         }
     }
 
     public static void main(String[] args) throws InterruptedException {
 
         long start = System.currentTimeMillis();
         //二分排序
         add(46,43,23,2,5);
         //流处理
         add1(46,43,23,2,5);
         long end = System.currentTimeMillis();
         System.out.println(String.format("timeSpend:%s ms",end-start));
 
     }
 
     public static int search(int x)
     {
         if (x > dataList.get(dataList.size()-1))
             return -1;
         if (x <= dataList.get(0))
             return 0;
 
         return BinarySearch(0, dataList.size(), x);
     }
 
     public static int BinarySearch(int left, int right, int x)
     {
         int middle;
         while (right - left > 1)
         {
             middle = left + (right - left) / 2;
 
             if (dataList.get(middle) < x)
                 left = middle;
             else
                 right = middle;
         }
         return right;
     }
 }`

2:有 N 个程序猿围成一圈顺序循环报数，从第一个猿开始报数(从 1 到 4 报数，猿都是 顺序排列成一圈的)，凡报到 4 的猿退出圈子，问最后留下的是原来第几号的那位

**第二题对应的源码是 PostNumber.class** 
`
 
     
     public class PostNumber {
          private static Integer number=10000;
          private static LinkedList<Integer> numberList = null;
     
     static {
         //初始化，给程序员编号
         numberList = new LinkedList();
         for (int i=1;i<=number;i++){
             numberList.add(i);
         }
     }
 
 
     public static void main(String[] args) {
         //游戏排序
         sortout();
         //打印
         printout();
     }
 
     private static void printout() {
         numberList.stream().sorted().forEach(System.out::println);
     }
 
     private static void sortout(){
         //用数组的数量>3作为循环的条件
         while (numberList.size()>3){
             //每次循环后排除元素为0的情况
             numberList = numberList.stream().filter(e->e!=0).collect(Collectors.toCollection(LinkedList::new));
             //数组大小小于3直接退出
             if(numberList.size()<=3){
                 break;
             }
             //数组循环，剔除编号为4的程序员
             for (int i =1 ;i<=numberList.size();i++){
                 //判断如果轮到最后一轮，则将最后的数组成员写到数组头部
                 if (numberList.size()-i<numberList.size()%4){
                     List<Integer> list = numberList.subList(i-1,numberList.size());
                     int size = list.size();
                     for (int j = 1;j<=size;j++){
                         int a = numberList.remove(numberList.size() - 1);
                         numberList.addFirst(a);
                     }
                     break;
                 }
                 //将编号为4的程序编号设置为0
                 if(i%4==0){
                     numberList.set(i-1,0);
                 }
             }
         }
     }
 }`