import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
}
