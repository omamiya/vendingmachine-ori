package App.Money;

import App.Product.IProduct;

import java.util.Collection;

public class ChangeCalculator {

    public IBalance calculateChange(IProduct product, IBalance payment, IBalance machineBalance) {

        //calculate the coins should be return

        return new Balance();
    }

    public boolean isPaymentMissing(Integer price, Integer payment){
        return payment >= price;
    }

    public boolean isEnoughChangeInMachine(Integer price, Integer payment, Integer machineTotalBalance){
        return payment - price > machineTotalBalance;
    }

}
