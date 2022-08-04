import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            int roomNumber, commandLines;
            String[] input = reader.readLine().split(" ");
            roomNumber = Integer.parseInt(input[0]);
            commandLines = Integer.parseInt(input[1]);
            int[] label = new int[roomNumber + 1];
            int[] size = new int[roomNumber + 1];
            int order, first, second;
            outer:
            for (int i = 0; i < commandLines; i++) {
                input = reader.readLine().split(" ");
                order = Integer.parseInt(input[0]);
                first = Integer.parseInt(input[1]);
                if (order == 2) {
                    while (size[first] == -1) {
                        first = label[first];
                    }
                    writer.write(Integer.toString(size[first] + 1) + "\n");
                    writer.flush();
                } else {
                    second = Integer.parseInt(input[2]);
                    while (size[second] == -1) {
                        if (first == second)
                            continue outer;
                        second = label[second];
                    }
                    while (size[first] == -1) {
                        if (first == second)
                            continue outer;
                        int temp = first;
                        first = label[first];
                        label[temp] = second;
                    }
                    if (second == first)
                        continue outer;
                    size[second] += size[first] + 1;
                    size[first] = -1;
                    label[first] = second;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}