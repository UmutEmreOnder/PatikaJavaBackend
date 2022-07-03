package ThreadRace;

import java.util.ArrayList;
import java.util.List;

public class Starter {
    public static List<Integer> numbers = new ArrayList<>();
    public static List<Integer> evens = new ArrayList<>();
    public static List<Integer> odds = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 100000; i++) {
            numbers.add(i);
        }

        OddEvenFinder f1 = new OddEvenFinder(0, numbers.size() / 4);
        OddEvenFinder f2 = new OddEvenFinder(1, numbers.size() / 4);
        OddEvenFinder f3 = new OddEvenFinder(2, numbers.size() / 4);
        OddEvenFinder f4 = new OddEvenFinder(3, numbers.size() / 4);

        Thread thread1 = new Thread(f1);
        Thread thread2 = new Thread(f2);
        Thread thread3 = new Thread(f3);
        Thread thread4 = new Thread(f4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println(evens.size());
        System.out.println(odds.size());
    }
}
