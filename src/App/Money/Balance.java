package App.Money;

import App.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by orymamia on 30/12/2017.
 */
public class Balance implements IBalance{

Map<Coin, Integer> balance;

    Balance(Map<Coin, Integer> balance){
        this.balance = balance;
    }

    Balance(){
        this.balance = new HashMap<>();
        this.balance.put(new Coin(UsdCoinType.PENNY), 0);
        this.balance.put(new Coin(UsdCoinType.NICKEL), 0);
        this.balance.put(new Coin(UsdCoinType.DIME), 0);
        this.balance.put(new Coin(UsdCoinType.QUARTER), 0);
        this.balance.put(new Coin(UsdCoinType.DOLLAR), 0);
    }

    @Override
    public Integer getTotalBalance() {
        Integer total = 0;
        for (Map.Entry<Coin, Integer> entry : this.balance.entrySet()) {
            Coin coin = entry.getKey();
            Integer amount = entry.getValue();
            total += (coin.getValue() * amount);
        }
        return total;
    }

    @Override
    public Status addCoin(Coin coin) {
        updateBalance(coin, 1);
        return new Status("Ok", true);
    }

    @Override
    public Status reduceCoin(Coin coin) {
        updateBalance(coin, -1);
        return new Status("Ok", true);    }

    @Override
    public Integer getAmountOfCoinType(Balance balance, UsdCoinType usdCoinType) {
        return null;
    }

    private void updateBalance(Coin coin, Integer dif){
        Integer amount = this.balance.containsKey(coin) ? this.balance.get(coin) : 0;
        this.balance.put(coin, amount + dif);
    }
}
