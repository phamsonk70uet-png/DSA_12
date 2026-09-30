import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class QueueUsingTwoStacks {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());

        Stack<Integer> stackIn = new Stack<>();
        Stack<Integer> stackOut = new Stack<>();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                stackIn.push(Integer.parseInt(st.nextToken()));
            } else {
                if (stackOut.isEmpty()) {
                    while (!stackIn.isEmpty()) stackOut.push(stackIn.pop());
                }
                if (type == 2) stackOut.pop();
                else if (type == 3) output.append(stackOut.peek()).append("\n");
            }
        }
        System.out.print(output);
    }
}
