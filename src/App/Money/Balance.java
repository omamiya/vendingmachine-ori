package App.Money;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by orymamia on 30/12/2017.
 */
public class Balance implements IBalance{

Map<ICoin, Integer> balance;

    Balance(Map<ICoin, Integer> balance){
        this.balance = balance;
    }

    Balance(){
        CoinFactory cf = new CoinFactory();
        this.balance = new HashMap<>();
        this.balance.put(cf.createCoin(CoinType.PENNY), 0);
        this.balance.put(cf.createCoin(CoinType.NICKEL), 0);
        this.balance.put(cf.createCoin(CoinType.DIME), 0);
        this.balance.put(cf.createCoin(CoinType.QUARTER), 0);
        this.balance.put(cf.createCoin(CoinType.DOLLAR), 0);
    }

    @Override
    public Integer getTotalBalance() {
        Integer total = 0;
        for (Map.Entry<ICoin, Integer> entry : this.balance.entrySet()) {
            ICoin coin = entry.getKey();
            Integer amount = entry.getValue();
            total += (coin.getValue() * amount);
        }
        return total;
    }
}
