import java.util.Arrays;
import java.util.Random;

public class MergeSortSurvey {
    public static void insertionSort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)              a[k] = aux[j++];
            else if (j > hi)          a[k] = aux[i++];
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else                      a[k] = aux[i++];
        }
    }

    private static void mergeSort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void mergeSort(int[] a) {
        int[] aux = new int[a.length];
        mergeSort(a, aux, 0, a.length - 1);
    }

    public static double measureInsertion(int[] base) {
        int[] copy = Arrays.copyOf(base, base.length);
        long start = System.nanoTime();
        insertionSort(copy);
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    public static double measureMerge(int[] base) {
        int[] copy = Arrays.copyOf(base, base.length);
        long start = System.nanoTime();
        mergeSort(copy);
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 50000, 100000};
        Random rng = new Random(12345);

        System.out.printf("%-8s | %-12s | %-15s | %-15s%n", "KichThuoc", "LoaiDuLieu", "Insertion(ms)", "MergeSort(ms)");
        System.out.println("-------------------------------------------------------------------");

        for (int n : sizes) {
            int[] rnd = new int[n];
            int[] asc = new int[n];
            int[] desc = new int[n];
            int[] eq = new int[n];

            for (int i = 0; i < n; i++) {
                rnd[i] = rng.nextInt(1_000_000);
                asc[i] = i;
                desc[i] = n - i;
                eq[i] = 7;
            }

            System.out.printf("%-8d | %-12s | %15.3f | %15.3f%n", n, "Ngau nhien", measureInsertion(rnd), measureMerge(rnd));
            System.out.printf("%-8d | %-12s | %15.3f | %15.3f%n", n, "Tang dan", measureInsertion(asc), measureMerge(asc));
            System.out.printf("%-8d | %-12s | %15.3f | %15.3f%n", n, "Giam dan", measureInsertion(desc), measureMerge(desc));
            System.out.printf("%-8d | %-12s | %15.3f | %15.3f%n", n, "Bang nhau", measureInsertion(eq), measureMerge(eq));
            System.out.println("-------------------------------------------------------------------");
        }
    }
}