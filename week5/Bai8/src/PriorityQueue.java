import java.util.NoSuchElementException;

public class PriorityQueue {
    private static final int K = 10000;
    private final int[] data;
    private int M; // Số phần tử hiện tại, lưu từ chỉ số 0 tới M - 1

    public PriorityQueue() {
        this.data = new int[K];
        this.M = 0;
    }

    public PriorityQueue(int capacity) {
        this.data = new int[capacity];
        this.M = 0;
    }

    // Thêm một số vào hàng đợi
    public void insert(int x) {
        if (M >= data.length) {
            throw new IllegalStateException("PriorityQueue is full");
        }
        data[M++] = x;
    }

    // Xóa số nhỏ nhất ra khỏi hàng đợi và trả về số đó
    public int deleteMin() {
        if (M == 0) {
            throw new NoSuchElementException("PriorityQueue is empty");
        }
        int minIdx = 0;
        for (int i = 1; i < M; i++) {
            if (data[i] < data[minIdx]) {
                minIdx = i;
            }
        }
        int minVal = data[minIdx];
        data[minIdx] = data[M - 1];
        M--;
        return minVal;
    }
}