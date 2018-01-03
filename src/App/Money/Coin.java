package App.Money;

/**
 * Created by orymamia on 30/12/2017.
 */
public class Coin {
    private UsdCoinType type;

    public Coin(UsdCoinType type) {
        this.type = type;

    }

    public UsdCoinType getType(){ return this.type; }

    public Integer getValue() {
        return this.type.getValue();
    }
}


