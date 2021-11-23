import java.util.Arrays;

/**
 * 堆排序算法
 * @author dongyafei
 * @date 2021/11/23
 */
public class HeapSort {

    /**
     * 堆排序
     * @param a 实现了Comparable接口的对象的待排序数组
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        // 堆生成过程
        for (int i = n / 2; i > 0; i--) {
            sink(a, i, n);
        }
        // 交换排序过程
        int j = n;
        while(j > 1){
            swap(a, 1, j--);        // 将当前最大值交换至尾部并使尾部前移
            sink(a, 1, j);          // 对剩余值下沉排序，排序后最大值位于1处
        }
    }

    /**
     * 局部下沉排序
     * @param a 原始待排序数组
     * @param k 待排序子树的根结点
     * @param n 待排序范围最大值
     */
    public static void sink(Comparable[] a, int k, int n) {
        // 在排序范围内不断下沉
        while (2 * k <= n) {
            // 找到 k 节点的值较大的子节点
            int j = 2 * k;
            if (j < n && less(a, j, j + 1)) {
                j++;
            }
            // 已堆有序则退出
            if (!less(a, k, j)) {
                break;
            }
            // 交换使得局部堆有序并继续下沉
            swap(a, k, j);
            k = j;
        }
    }

    /**
     * 辅助比较函数，处理堆排序中数组下标1-开头的问题
     */
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    /**
     * 辅助交换函数，处理堆排序中数组下标1-开头的问题
     */
    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    /**
     * 主函数，测试排序效果
     */
    public static void main(String[] args) {
        String[] item = "H e a p S o r t E x a m p l e".split(" ");
        System.out.println("====== Before HeapSort ======");
        System.out.println(Arrays.toString(item));
        HeapSort.sort(item);
        System.out.println("====== After HeapSort ======");
        System.out.println(Arrays.toString(item));
    }
}
