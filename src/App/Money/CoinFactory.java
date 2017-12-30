package App.Money;

/**
 * Created by orymamia on 30/12/2017.
 */
public class CoinFactory {
    public ICoin createCoin(CoinType type) {
        return new Coin(type);
    }

}
