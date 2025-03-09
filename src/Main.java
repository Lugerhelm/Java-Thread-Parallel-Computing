import java.math.BigInteger;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int tuumadeArv = Runtime.getRuntime().availableProcessors();
        BlockingQueue<String> failideQueue = new LinkedBlockingQueue<>();
        BlockingQueue<FailistTulemus> tulemusteQueue = new LinkedBlockingQueue<>();

        for (String failiNimi : args){
            failideQueue.put(failiNimi);
        }

        ExecutorService executor = Executors.newFixedThreadPool(tuumadeArv);
        for (int i = 0; i < tuumadeArv; i++) {
            executor.execute(new Worker(failideQueue, tulemusteQueue));
        }
        executor.shutdown();
        BigInteger koguSumma = BigInteger.ZERO;
        BigInteger suurimNumber = new BigInteger("-1");
        String maxFail = "";
        String minSummaFail = "";
        BigInteger minSumma = null;

        while (!tulemusteQueue.isEmpty()) {
            FailistTulemus tulemus = tulemusteQueue.poll();
            if (tulemus == null) continue;
            koguSumma = koguSumma.add(tulemus.getSumma());
            if (tulemus.getMaxNumber().compareTo(suurimNumber) > 0) {
                suurimNumber = tulemus.getMaxNumber();
                maxFail = tulemus.getFailiNimi();
            }
            if (minSumma == null || tulemus.getMaxNumber().compareTo(minSumma) < 0) {
                minSumma = tulemus.getMaxNumber();
                minSummaFail = tulemus.getFailiNimi();
            }
        }
        System.out.println("Kõikide leitud arvude kogusumma: " + koguSumma);
        System.out.println("Kõige suurem üksik arv: " + suurimNumber + " (failis " + maxFail + ")");
        System.out.println("Kõige väiksema arvude summaga fail: " + minSummaFail);
    }
}
