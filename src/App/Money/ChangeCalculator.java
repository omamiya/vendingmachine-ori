package App.Money;

import App.Product.IProduct;


public class ChangeCalculator {

    public IBalance calculateChange(IProduct product, IBalance payment, IBalance machineBalance) throws NoChangeException {
        Integer totalChange = payment.getTotalBalance() - product.getProductPrice();
        IBalance change = new Balance();
        for (UsdCoinType coinType : UsdCoinType.values()){
            Integer amountOfCoins = totalChange / coinType.getValue();
            if (amountOfCoins > 0 && machineBalance.getAmountOfCoinType(coinType) >= amountOfCoins){
                change.updateCoinTypeAmount(coinType, amountOfCoins);
                totalChange = totalChange % coinType.getValue();
            }
        }
        if (totalChange != 0) {
            throw new NoChangeException();
        }
        return change;
    }

    public boolean isPaymentMissing(Integer price, Integer payment){
        return payment >= price;
    }

}
