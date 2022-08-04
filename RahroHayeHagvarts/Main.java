import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int classes = scanner.nextInt(), left1 = classes - 1, left2 = classes - 1,
                number1, number2, type, save1, save2, used = 0, ways = scanner.nextInt();
        int[] labels1 = new int[classes + 1], labels2 = new int[classes + 1];
        boolean isUsed;
        Node node;
        ArrayList<Node> nodes1 = new ArrayList<>(), nodes2 = new ArrayList<>();
        for (int i = ways; i > 0; i--) {
            number1 = scanner.nextInt();
            number2 = scanner.nextInt();
            type = scanner.nextInt();
            switch (type) {
                case 1: {
                    node = new Node(number1, number2);
                    nodes1.add(node);
                }
                break;
                case 2: {
                    node = new Node(number1, number2);
                    nodes2.add(node);
                }
                break;
                case 3: {
                    isUsed = true;
                    save1 = number1;
                    save2 = number2;
                    while (labels1[number1] != 0)
                        number1 = labels1[number1];
                    while (labels1[number2] != 0)
                        number2 = labels1[number2];
                    if (number1 != number2) {
                        left1--;
                        labels1[number2] = number1;
                    } else {
                        isUsed = false;
                    }
                    number1 = save1;
                    number2 = save2;
                    while (labels2[number1] != 0)
                        number1 = labels2[number1];
                    while (labels2[number2] != 0)
                        number2 = labels2[number2];
                    if (number1 != number2) {
                        left2--;
                        labels2[number2] = number1;
                    } else {
                        isUsed = false;
                    }
                    if (isUsed) {
                        used++;
                    }
                }
                break;
            }
        }
        if (left1 == 0 && left2 == 0) {
            System.out.println(ways - used);
        } else {
            if (left1 != 0) {
                for (int i = 0; i < nodes1.size(); i++) {
                    node = nodes1.get(i);
                    number1 = node.number1;
                    number2 = node.number2;
                    while (labels1[number1] != 0)
                        number1 = labels1[number1];
                    while (labels1[number2] != 0)
                        number2 = labels1[number2];
                    if (number1 != number2) {
                        left1--;
                        labels1[number2] = number1;
                        used++;
                    }
                    if (left1 == 0)
                        break;
                }
            }
            if (left2 != 0) {
                for (int i = 0; i < nodes2.size(); i++) {
                    node = nodes2.get(i);
                    number1 = node.number1;
                    number2 = node.number2;
                    while (labels2[number1] != 0)
                        number1 = labels2[number1];
                    while (labels2[number2] != 0)
                        number2 = labels2[number2];
                    if (number1 != number2) {
                        left2--;
                        labels2[number2] = number1;
                        used++;
                    }
                    if (left2 == 0)
                        break;
                }
            }
            if (left1 == 0 && left2 == 0)
                System.out.println(ways - used);
            else
                System.out.println(-1);
        }
    }
}

class Node {
    int number1;
    int number2;

    public Node(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }
}