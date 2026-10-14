public static void partitionInPlace(int[] ar) {
    int lo = 0, hi = ar.length - 1;
    int i = lo, j = hi + 1;
    int pivot = ar[lo];
    while (true) {
        while (ar[++i] < pivot) {
            if (i == hi) break;
        }
        while (pivot < ar[--j]) {
            if (j == lo) break;
        }
        if (i >= j) break;
        int temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
    }
    int temp = ar[lo];
    ar[lo] = ar[j];
    ar[j] = temp;

    printArray(ar);
}

void main() {
}