package App;

import App.Inventory.IInventory;
import App.Money.*;
import App.Product.IProduct;

import java.util.HashMap;

/**
 * Created by orymamia on 30/12/2017.
 */
public class Machine implements IMachine{
    Balance customerBalance;
    Balance machineBalance;
    ChangeCalculator changeCalculator = new ChangeCalculator();


    public Machine(){
        BalanceFactory bf = new BalanceFactory();
        this.customerBalance = bf.createCustomerBalance();
        this.machineBalance = createDefaultBalance();


    }

    private Balance createDefaultBalance(){
        BalanceFactory bf = new BalanceFactory();
        HashMap<Coin, Integer> defaultBalance = new HashMap<>();

        defaultBalance.put(new Coin(UsdCoinType.PENNY), 10);
        defaultBalance.put(new Coin(UsdCoinType.NICKEL), 10);
        defaultBalance.put(new Coin(UsdCoinType.DIME), 10);
        defaultBalance.put(new Coin(UsdCoinType.QUARTER), 10);
        defaultBalance.put(new Coin(UsdCoinType.DOLLAR), 10);

        return bf.createMachineBalance(defaultBalance);
    }

    @Override
    public Balance getMachineBalance() { return this.machineBalance; }

    public Balance getCustomerBalance(){ return this.customerBalance; }

    @Override
    public Status insertCoin(Coin coin, IBalance customerBalance) {
        customerBalance.addCoin(coin);
        return new Status("ok", true);
    }

    @Override
    public Status selectProduct(IProduct product) {
        return null;
    }

    @Override
    public Status verifyPurchase(IProduct product, IBalance customerBalance, IBalance machineBalance, IInventory inventory) {
        String message = "Ok";
        boolean status = true;

        Integer productPrice = product.getProductPrice();
        Integer totalPayment = customerBalance.getTotalBalance();
        Integer totalMachineBalance = machineBalance.getTotalBalance();

        boolean enoughPayment = changeCalculator.isPaymentMissing(productPrice, customerBalance.getTotalBalance());
        boolean enoughChange = changeCalculator.isEnoughChangeInMachine(productPrice, totalPayment, totalMachineBalance);
        boolean productAvailable = inventory.isProductAvailable(product);

        if (!enoughPayment){
            message = "Payment is missing";
            status = false;
        }else if(!enoughChange) {
            message = "Not enough change";
            status = false;
        }else if(!productAvailable){
            message = "Product is not available";
            status = false;
        }

        return new Status(message, status);

    }
}
