package App.Money;

/**
 * Created by orymamia on 30/12/2017.
 */
public enum UsdCoinType {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25),
    DOLLAR(100);

    private int value;

    UsdCoinType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
