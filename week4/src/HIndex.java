import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class HIndex {
    public static void main(String[] args) throws IOException {
        // Sử dụng Fast I/O để tối ưu tốc độ đọc dữ liệu đầu vào
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Đọc dòng đầu tiên: N là số bài báo đã công bố[cite: 12]
        int n = Integer.parseInt(br.readLine().trim());

        // Đọc dòng thứ hai: Lượt trích dẫn của từng bài, ngăn cách bởi dấu cách[cite: 12]
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Khởi tạo mảng đếm tần số kích thước N + 1
        int[] count = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int citations = Integer.parseInt(st.nextToken());
            // Lọc chặn: Mọi số trích dẫn >= N đều được gộp chung vào mốc N
            if (citations >= n) {
                count[n]++;
            } else {
                count[citations]++;
            }
        }

        // Tìm h-index tối ưu từ N ngược về 0
        int totalPapers = 0;
        for (int h = n; h >= 0; h--) {
            totalPapers += count[h];
            // Nếu tổng số bài báo (có lượt trích dẫn >= h) đạt mức >= h
            if (totalPapers >= h) {
                System.out.println(h); // In ra chỉ số h-index[cite: 12]
                return;
            }
        }
    }
}