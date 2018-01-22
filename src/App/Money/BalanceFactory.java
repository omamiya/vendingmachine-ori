package App.Money;

import java.util.HashMap;

/**
 * Created by orymamia on 30/12/2017.
 */
public class BalanceFactory {
    public IBalance createCustomerBalance(){ return new Balance(); }
    public IBalance createMachineBalance(HashMap<UsdCoinType, Integer> balance){ return new Balance(balance); }
}