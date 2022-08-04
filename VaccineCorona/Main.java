import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int city = scanner.nextInt();
        int cities_number = 0;
        PriorityQueue<Road> roads_max = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road road, Road t1) {
                return Integer.compare(t1.price, road.price);
            }
        });
        PriorityQueue<Road> roads_min = new PriorityQueue<>(new Comparator<Road>() {
            @Override
            public int compare(Road road, Road t1) {
                return Integer.compare(road.price, t1.price);
            }
        });
        int number1, number2, number3, min_price, max_price;
        Road road;
        int[] max_past = new int[city + 1];
        int[] min_past = new int[city + 1];
        for (int i = 0; i < city; i++) {
            number1 = scanner.nextInt();
            number2 = scanner.nextInt();
            number3 = scanner.nextInt();
            max_past[number1] = number1;
            max_past[number2] = number2;
            min_past[number1] = number1;
            min_past[number2] = number2;
            road = new Road(number1, number2, number3);
            roads_min.add(road);
            roads_max.add(road);
            cities_number = Integer.max(Integer.max(number1, number2), cities_number);
        }
        int unused_min = cities_number - 2, unused_max = cities_number - 2;
        road = roads_max.poll();
        max_price = road.price;
        max_past[road.city1] = road.city2;
        road = roads_min.poll();
        min_price = road.price;
        min_past[road.city1] = road.city2;
        while (unused_max != 0 || unused_min != 0) {
            if (unused_max != 0) {
                road = roads_max.poll();
                number1 = road.city1;
                number2 = road.city2;
                while (max_past[number1] != number1)
                    number1 = max_past[number1];
                while (max_past[number2] != number2)
                    number2 = max_past[number2];
                if (number1 != number2) {
                    max_price += road.price;
                    max_past[number1] = number2;
                    unused_max--;
                }
            }
            if (unused_min != 0) {
                road = roads_min.poll();
                number1 = road.city1;
                number2 = road.city2;
                while (min_past[number1] != number1)
                    number1 = min_past[number1];
                while (min_past[number2] != number2)
                    number2 = min_past[number2];
                if (number1 != number2) {
                    min_price += road.price;
                    min_past[number1] = number2;
                    unused_min--;
                }
            }
        }
        System.out.println(max_price);
        System.out.println(min_price);
    }
}

class Road {
    int city1;
    int city2;
    int price;

    public Road(int city1, int city2, int price) {
        this.price = price;
        this.city2 = city2;
        this.city1 = city1;
    }
}
