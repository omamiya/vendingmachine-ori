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
        this.initBalance();
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
    public Integer getAmountOfCoinType(UsdCoinType usdCoinType) {
        return this.balance.get(usdCoinType);
    }

    @Override
    public void addBalance(IBalance balanceToAdd) {
        updateBalance(balanceToAdd, 1);
    }

    public void reduceBalance(IBalance balanceToReduce) {
        updateBalance(balanceToReduce, -1);
    }

    @Override
    public void updateCoinTypeAmount(UsdCoinType usdCoinType, Integer dif){
        Integer amount = this.balance.containsKey(usdCoinType) ? this.balance.get(usdCoinType) : 0;
        this.balance.put(usdCoinType, amount + dif);
    }

    @Override
    public void emptyBalance() {
        this.initBalance();
    }

    private void initBalance(){
        this.balance.put(UsdCoinType.PENNY, 0);
        this.balance.put(UsdCoinType.NICKEL, 0);
        this.balance.put(UsdCoinType.DIME, 0);
        this.balance.put(UsdCoinType.QUARTER, 0);
        this.balance.put(UsdCoinType.DOLLAR, 0);
    }

    private void updateBalance(IBalance balanceToUpdate, Integer action){
        Integer amountForCoin;
        for (UsdCoinType coinType : UsdCoinType.values()) {
            amountForCoin = balanceToUpdate.getAmountOfCoinType(coinType) * action;
            updateCoinTypeAmount(coinType, amountForCoin);
        }
    }
}
