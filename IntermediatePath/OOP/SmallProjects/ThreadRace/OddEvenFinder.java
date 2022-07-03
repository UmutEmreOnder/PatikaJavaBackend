package ThreadRace;

public class OddEvenFinder implements Runnable {
    private final int id;
    private final int size;
    public OddEvenFinder(int id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public void run() {
        int start = id * this.size;
        int end = start + this.size;

        while (start < end) {
            if (Starter.numbers.get(start) % 2 == 0) {
                synchronized (Starter.evens) {
                    Starter.evens.add(start);
                }
            }
            else {
                synchronized (Starter.odds) {
                    Starter.odds.add(start);
                }
            }

            start++;
        }
    }
}
