package App.Money;

/**
 * Created by orymamia on 30/12/2017.
 */
public class Coin implements ICoin{
    CoinType type;

    Coin(CoinType type) {
        this.type = type;

    }

    @Override
    public CoinType getType(){ return this.type; }

    @Override
    public Integer getValue() {
        return this.type.getValue();
    }
}


