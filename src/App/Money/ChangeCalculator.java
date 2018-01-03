package App.Money;

import App.Product.IProduct;

import java.util.Collection;

public class ChangeCalculator {

    public Collection<Coin> calculateChange(IProduct product, IBalance payment, IBalance machineBalance) {
        return null;
    }

    public boolean isPaymentMissing(Integer price, Integer payment){
        return payment >= price;
    }

    public boolean isEnoughChangeInMachine(Integer price, Integer payment, Integer machineTotalBalance){
        return payment - price > machineTotalBalance;
    }

}
