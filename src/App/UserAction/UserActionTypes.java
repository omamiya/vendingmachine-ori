package App.UserAction;

/**
 * Created by orymamia on 30/01/2018.
 */
public enum UserActionTypes {
    BUY_PRODUCT(1),
    CHECK_BALANCE(2),
    WITHDRAW_MONEY(3);

    private int value;

    UserActionTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
