package App.Money;

import App.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by orymamia on 30/12/2017.
 */
public class Balance implements IBalance{

Map<UsdCoinType, Integer> balance;

    Balance(Map<UsdCoinType, Integer> balance){
        this.balance = balance;
    }

    Balance(){
        this.balance = new HashMap<>();

        this.balance.put(UsdCoinType.PENNY, 0);
        this.balance.put(UsdCoinType.NICKEL, 0);
        this.balance.put(UsdCoinType.DIME, 0);
        this.balance.put(UsdCoinType.QUARTER, 0);
        this.balance.put(UsdCoinType.DOLLAR, 0);
    }

    @Override
    public Integer getTotalBalance() {
        Integer total = 0;
        for (Map.Entry<UsdCoinType, Integer> entry : this.balance.entrySet()) {
            UsdCoinType uct = entry.getKey();
            Integer amount = entry.getValue();
            total += (uct.getValue() * amount);
        }
        return total;
    }

    @Override
    public Status addCoin(UsdCoinType usdCoinType) {
        updateBalance(usdCoinType, 1);
        return new Status("Ok", true);
    }

    @Override
    public Status reduceCoin(UsdCoinType usdCoinType) {
        boolean status = false;
        String message;
        if (this.balance.get(usdCoinType) <= 0){
            message = "error";
            status = false;
        }
        else {
            updateBalance(usdCoinType, -1);
            message = "ok";
            status = true;
        }
        return new Status(message, status);
    }

    @Override
    public Integer getAmountOfCoinType(UsdCoinType usdCoinType) {
        return this.balance.get(usdCoinType);
    }

    @Override
    public void addBalance(IBalance balanceToAdd) {
        Integer amountForCoin;
        for (UsdCoinType coinType : UsdCoinType.values()) {
            amountForCoin = balanceToAdd.getAmountOfCoinType(coinType);
            updateBalance(coinType, amountForCoin);
        }
    }
    private void updateBalance(UsdCoinType usdCoinType, Integer dif){
        Integer amount = this.balance.containsKey(usdCoinType) ? this.balance.get(usdCoinType) : 0;
        this.balance.put(usdCoinType, amount + dif);
    }
}
