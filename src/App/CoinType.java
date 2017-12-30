package App;

/**
 * Created by orymamia on 30/12/2017.
 */
public enum CoinType {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25),
    DOLLAR(100);

    private int value;

    private CoinType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
