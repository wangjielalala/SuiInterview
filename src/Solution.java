import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
}
