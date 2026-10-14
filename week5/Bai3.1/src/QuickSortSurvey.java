import java.util.Arrays;
import java.util.Random;

public class QuickSortSurvey {
    private static final Random RNG = new Random(42);

    public static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + RNG.nextInt(n - i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

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

    // Khử đệ quy nhánh lớn hơn để tránh tràn bộ nhớ StackOverflowError khi không shuffle trên mảng đã sắp xếp
    private static void quickSort(int[] a, int lo, int hi) {
        while (lo < hi) {
            int j = partition(a, lo, hi);
            if (j - lo < hi - j) {
                quickSort(a, lo, j - 1);
                lo = j + 1;
            } else {
                quickSort(a, j + 1, hi);
                hi = j - 1;
            }
        }
    }

    public static void quickSortWithShuffle(int[] a) {
        shuffle(a);
        quickSort(a, 0, a.length - 1);
    }

    public static void quickSortNoShuffle(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 50000, 100000};
        Random dataRng = new Random(12345);

        System.out.printf("%-8s | %-12s | %-12s | %-12s | %-14s | %-14s%n",
                "N", "LoaiDuLieu", "Insertion", "MergeSort", "QS(Shuffle)", "QS(NoShuffle)");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int n : sizes) {
            int[] rnd = new int[n];
            int[] asc = new int[n];
            int[] desc = new int[n];
            int[] eq = new int[n];

            for (int i = 0; i < n; i++) {
                rnd[i] = dataRng.nextInt(1_000_000);
                asc[i] = i;
                desc[i] = n - i;
                eq[i] = 7;
            }

            Object[][] datasets = {
                    {"Ngau nhien", rnd},
                    {"Tang dan", asc},
                    {"Giam dan", desc},
                    {"Bang nhau", eq}
            };

            for (Object[] ds : datasets) {
                String name = (String) ds[0];
                int[] base = (int[]) ds[1];

                double tIns = MergeSortSurvey.measureInsertion(base);
                double tMrg = MergeSortSurvey.measureMerge(base);

                int[] c1 = Arrays.copyOf(base, base.length);
                long s1 = System.nanoTime();
                quickSortWithShuffle(c1);
                double tQsShuf = (System.nanoTime() - s1) / 1_000_000.0;

                int[] c2 = Arrays.copyOf(base, base.length);
                long s2 = System.nanoTime();
                quickSortNoShuffle(c2);
                double tQsNoShuf = (System.nanoTime() - s2) / 1_000_000.0;

                System.out.printf("%-8d | %-12s | %12.3f | %12.3f | %14.3f | %14.3f%n",
                        n, name, tIns, tMrg, tQsShuf, tQsNoShuf);
            }
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }
}