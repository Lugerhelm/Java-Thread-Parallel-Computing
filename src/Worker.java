import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {
    private final BlockingQueue<String> failideQueue;
    private final BlockingQueue<FailistTulemus> tulemusteQueue;

    public  Worker(BlockingQueue<String> failideQueue, BlockingQueue<FailistTulemus> tulemusteQueue) {
        this.failideQueue = failideQueue;
        this.tulemusteQueue = tulemusteQueue;

    }

    @Override
    public void run() {
        try {
            while (true) {
                String failiNimi = failideQueue.poll();
                if (failiNimi == null) break; // Kui rohkem faile pole, lõpetame

                BigInteger maxNumber = new BigInteger("-1");
                BigInteger summa = BigInteger.ZERO;

                try (Scanner scanner = new Scanner(new File(failiNimi), "UTF-8")) {
                    while (scanner.hasNext()) {
                        BigInteger num = new BigInteger(scanner.next());
                        summa = summa.add(num);
                        if (num.compareTo(maxNumber) > 0) {
                            maxNumber = num;
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.err.println("Faili ei leitud: " + failiNimi);
                    continue;
                }

                tulemusteQueue.put(new FailistTulemus(failiNimi, maxNumber, summa));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

