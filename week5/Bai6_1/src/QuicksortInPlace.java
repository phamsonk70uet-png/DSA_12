import java.util.Scanner;

public class QuicksortInPlace {
    public static void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + (i == A.length - 1 ? "" : " "));
        }
        System.out.println();
    }

    public static int partition(int[] A, int low, int high) {
        int pivot = A[high];
        int i = low;
        for (int j = low; j <= high - 1; j++) {
            if (A[j] <= pivot) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i = i + 1;
            }
        }
        int temp = A[i];
        A[i] = A[high];
        A[high] = temp;
        return i;
    }

    public static void quickSort(int[] A, int low, int high) {
        if (low < high) {
            int p = partition(A, low, high);
            printArray(A);
            quickSort(A, low, p - 1);
            quickSort(A, p + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        quickSort(A, 0, n - 1);
        sc.close();
    }
}