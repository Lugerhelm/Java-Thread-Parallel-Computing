import java.math.BigInteger;

public class FailistTulemus {
    private String failiNimi;
    private BigInteger maxNumber;
    private BigInteger summa;

    public  FailistTulemus(String failiNimi, BigInteger maxNumber, BigInteger summa ) {
        this.failiNimi = failiNimi;
        this.maxNumber = maxNumber;
        this.summa = summa;
    }

    public String getFailiNimi() {
        return failiNimi;
    }

    public BigInteger getMaxNumber() {
        return maxNumber;
    }

    public BigInteger getSumma() {
        return summa;
    }
}
