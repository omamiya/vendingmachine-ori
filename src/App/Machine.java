package App;

import App.Money.*;
import App.Product.IProduct;

import java.util.HashMap;

/**
 * Created by orymamia on 30/12/2017.
 */
public class Machine implements IMachine{
    Balance customerBalance;
    Balance machineBalance;


    public Machine(){
        BalanceFactory bf = new BalanceFactory();
        this.customerBalance = bf.createCustomerBalance();
        this.machineBalance = createDefaultBalance();

    }

    private Balance createDefaultBalance(){
        CoinFactory cf = new CoinFactory();
        ICoin penny = cf.createCoin(CoinType.PENNY);
        ICoin nickel = cf.createCoin(CoinType.NICKEL);
        ICoin dime = cf.createCoin(CoinType.DIME);
        ICoin dollar = cf.createCoin(CoinType.DOLLAR);
        BalanceFactory bf = new BalanceFactory();
        HashMap<ICoin, Integer> defaultBalance = new HashMap<>();
        defaultBalance.put(penny, 10);
        defaultBalance.put(nickel, 10);
        defaultBalance.put(dime, 10);
        defaultBalance.put(dollar, 10);

        return bf.createMachineBalance(defaultBalance);
    }

    @Override
    public Balance getMachineBalance() { return this.machineBalance; }

    public Balance getCustomerBalance(){ return this.customerBalance; }

    @Override
    public Status insertCoin(ICoin coin) {
        return null;
    }

    @Override
    public Status selectProduct(Balance customerBalance, Balance machineBalance, IProduct product) {
        return null;
    }
}
