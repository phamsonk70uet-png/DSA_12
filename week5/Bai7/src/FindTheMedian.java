import java.util.Scanner;

public class FindTheMedian {
    private static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = a[lo];
        while (true) {
            while (a[++i] < v) {
                if (i == hi) break;
            }
            while (v < a[--j]) {
                if (j == lo) break;
            }
            if (i >= j) break;
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        int temp = a[lo];
        a[lo] = a[j];
        a[j] = temp;
        return j;
    }

    // Đệ quy tìm phần tử ở chỉ số k (với trung vị: k = a.length / 2)
    public static int selectRecursive(int[] a, int lo, int hi, int k) {
        if (lo == hi) return a[lo];
        int j = partition(a, lo, hi);
        if (j == k) {
            return a[k];
        } else if (j > k) {
            return selectRecursive(a, lo, j - 1, k);
        } else {
            return selectRecursive(a, j + 1, hi, k);
        }
    }

    public static int findMedian(int[] a) {
        int k = a.length / 2;
        return selectRecursive(a, 0, a.length - 1, k);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println(findMedian(a));
        sc.close();
    }
}