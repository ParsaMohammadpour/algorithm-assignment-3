import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Road> roads = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        long city = scanner.nextInt();
        long people = scanner.nextInt();
        long[] child = new long[(int)city + 1];
        long[] labels = new long[(int)city + 1];
        for (long i = 0; i < city - 1; ) {
            roads.add(new Road(scanner.nextInt(), scanner.nextInt(), scanner.nextLong()));
            i++;
            child[(int)i] = 1;
        }
        child[(int)city] = 1;
        roads.sort(new Comparator<Road>() {
            @Override
            public int compare(Road road, Road t1) {
                return Long.compare(road.price, t1.price);
            }
        });
        long[] prices = new long[(int)city - 1];
        prices[0] = roads.get(0).price;
        long[] ways = new long[(int)city - 1];
        long arrayIndex = 0, level1, level2;
        long number1, number2, temp;
        long price, totalWays = 0;
        for (Road road : roads
        ) {
            number1 = road.number1;
            number2 = road.number2;
            price = road.price;
            level1 =0;
            level2 =0;
            while (labels[(int)number2] != 0) {
                temp = number2;
                number2 = labels[(int)number2];
                labels[(int)temp] = number1;
                level2++;
            }
            while (labels[(int)number1] != 0) {
                temp = number1;
                number1 = labels[(int)number1];
                labels[(int)temp] = number2;
                level1++;
            }
            totalWays -= ((child[(int)number1] * (child[(int)number1] - 1)) / 2) + ((child[(int)number2] * (child[(int)number2] - 1)) / 2);
            temp = child[(int)number1] + child[(int)number2];
            totalWays += ((temp * (temp - 1)) / 2);
            if (prices[(int)arrayIndex] != price)
                arrayIndex++;
            prices[(int)arrayIndex] = price;
            ways[(int)arrayIndex] = totalWays;
            if (level1 > level2) {
                child[(int)number1] = temp;
                labels[(int) number2] = number1;
            } else {
                labels[(int) number1] = number2;
                child[(int) number2] = temp;
            }
        }
        long min, max;
        //System.out.println();
        for (long i = 0; i < people; i++) {
            //System.out.println(Arrays.toString(prices) + Arrays.toString(ways));
            min = scanner.nextLong();
            max = scanner.nextLong();
            number2 = findMax(prices, max, (int)arrayIndex);
            number1 = findMin(prices, min, (int)arrayIndex);
            temp = i + 1;
            //System.out.println("For Input Number : " + temp + "   ===    Number1 : " + number1 + " && " + "Number2 : " + number2);
            if (number2 < 0){
                System.out.println(0);
                continue;
            }else
                max = ways[(int) number2];
            if (number1 < 0)
                min =0;
            else
                min =ways[(int) number1];
            System.out.println(max - min);
        }
    }

    public static int findMax(long[] array, long number, int end) {
        if (number < array[0])
            return -1;
        int start = 0;
        int mid=0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (array[mid] == number)
                return mid;
            if (array[mid] > number)
                end = mid - 1;
            else
                start = mid + 1;
        }
        if (array[mid] > number)
            mid--;
        return mid;
    }

    public static int findMin(long[] array, long number, int end) {
        if (number <= array[0])
            return -1;
        int start = 0;
        int mid=0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (array[mid] == number)
                return mid - 1;
            if (array[mid] > number)
                end = mid - 1;
            else
                start = mid + 1;
        }
        if (array[mid] >= number)
            mid--;
        return mid;
    }
}

class Road {
    int number1;
    int number2;
    long price;

    public Road(int number1, int number2, long price) {
        this.number1 = Integer.min(number1, number2);
        this.number2 = Integer.max(number1, number2);
        this.price = price;
    }
}