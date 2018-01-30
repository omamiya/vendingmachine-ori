package App.AdminAction;

/**
 * Created by orymamia on 30/01/2018.
 */
public enum AdminActionTypes {
    ADD_PRODUCT(1),
    ADD_COIN(2),
    PRINT_BALANCE(3),
    PRINT_INVENTORY(4);

    private int value;

    AdminActionTypes(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
