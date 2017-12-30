package App.Money;

import java.util.HashMap;

/**
 * Created by orymamia on 30/12/2017.
 */
public class BalanceFactory {
    public Balance createCustomerBalance(){ return new Balance(); }
    public Balance createMachineBalance(HashMap<ICoin, Integer> balance){ return new Balance(balance); }
}