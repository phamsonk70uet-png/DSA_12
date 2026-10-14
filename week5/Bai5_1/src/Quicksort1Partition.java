import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quicksort1Partition {
    static void partition(int[] ar) {
        int pivot = ar[0];
        List<Integer> left = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int x : ar) {
            if (x < pivot) {
                left.add(x);
            } else if (x > pivot) {
                right.add(x);
            } else {
                equal.add(x);
            }
        }

        int idx = 0;
        for (int x : left)  ar[idx++] = x;
        for (int x : equal) ar[idx++] = x;
        for (int x : right) ar[idx++] = x;

        printArray(ar);
    }

    static void printArray(int[] ar) {
        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + (i == ar.length - 1 ? "" : " "));
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        if (!in.hasNextInt()) return;
        int n = in.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = in.nextInt();
        }
        partition(ar);
        in.close();
    }
}