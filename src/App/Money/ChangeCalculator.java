package App.Money;

import App.Product.IProduct;

public class ChangeCalculator {

    public IBalance calculateChange(IProduct product, IBalance payment, IBalance machineBalance) {
        Integer totalChange = payment.getTotalBalance() - product.getProductPrice();
        IBalance change = new Balance();
        for (UsdCoinType coinType : UsdCoinType.values()){
            Integer amountOfCoins = totalChange / coinType.getValue();
            if (amountOfCoins > 0 && machineBalance.getAmountOfCoinType(coinType) >= amountOfCoins){
                change.updateBalance(coinType, amountOfCoins);
                totalChange = totalChange % coinType.getValue();
            }
        }
        if (totalChange != 0) return null;
        return change;

        // what if not enough change? in terms of specific coins (for example the total chnage is 1 penny (1) and the machine
        // balance has only nickel (5) )
    }

    public boolean isPaymentMissing(Integer price, Integer payment){
        return payment >= price;
    }

    public boolean isEnoughChangeInMachine(Integer price, Integer payment, Integer machineTotalBalance){
        return payment - price > machineTotalBalance;
    }

}
