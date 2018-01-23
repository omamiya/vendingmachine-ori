package App.Money;

/**
 * Created by orymamia on 30/12/2017.
 */
public enum UsdCoinType {
    DOLLAR(100),
    QUARTER(25),
    DIME(10),
    NICKEL(5),
    PENNY(1);

    private int value;

    UsdCoinType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
