import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<Node> helathy = new HashSet<>();
        ArrayList<Node> ruined = new ArrayList<>();
        int city = scanner.nextInt();
        int island = city, pre;
        int[] labels = new int[city + 1];
        int ways = scanner.nextInt(), number1, number2;
        Node node;
        for (int i = 0; i < ways; i++) {
            number1 = scanner.nextInt();
            number2 = scanner.nextInt();
            node = new Node(Integer.min(number1, number2), Integer.max(number1, number2));
            helathy.add(node);
        }
        ways = scanner.nextInt();
        int[] answers = new int[ways];
        for (int i = 0; i < ways; i++) {
            number1 = scanner.nextInt();
            number2 = scanner.nextInt();
            node = new Node(Integer.min(number1, number2), Integer.max(number1, number2));
            ruined.add(node);
            helathy.remove(node);
        }
        Node[] nodes = new Node[helathy.size()];
        helathy.toArray(nodes);
        for (int i = 0; i < nodes.length; i++) {
            node = nodes[i];
            number1 = node.city1;
            number2 = node.city2;
            while (labels[number1] != 0) {
                pre =number1;
                number1 = labels[number1];
                labels[pre] =number2;
            }
            while (labels[number2] != 0) {
                pre =number2;
                number2 = labels[number2];
                labels[pre] =number1;
            }
            if (number1 != number2) {
                labels[number2] = number1;
                island--;
            }
        }
        for (int i = ruined.size() - 1; i >= 0; i--) {
            answers[i] = island;
            node = ruined.get(i);
            number1 = node.city1;
            number2 = node.city2;
            while (labels[number1] != 0) {
                pre =number1;
                number1 = labels[number1];
                labels[pre] =number2;
            }
            while (labels[number2] != 0) {
                pre =number2;
                number2 = labels[number2];
                labels[pre] =number1;
            }
            if (number1 != number2) {
                labels[number2] = number1;
                island--;
            }
        }
        for (int i = 0; i < answers.length; i++) {
            System.out.println(answers[i]);
        }
    }
}

class Node {
    int city1;
    int city2;

    public Node(int city1, int city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return city1 == node.city1 &&
                city2 == node.city2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city1, city2);
    }
}