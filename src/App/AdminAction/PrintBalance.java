package App.AdminAction;

import App.Money.IBalance;
import App.Money.UsdCoinType;

/**
 * Created by orymamia on 30/01/2018.
 */
public class PrintBalance implements AdminAction {
    private String description = AdminActionTypes.PRINT_BALANCE.getValue() + ". Check Machine Balance";
    private IBalance balance;


    public PrintBalance(IBalance balance){
        this.balance = balance;
    }

    @Override
    public void invoke() {
        System.out.println("Machine Balance");
        for (UsdCoinType coinType : UsdCoinType.values()) {
            System.out.println(coinType.name() + " - " + balance.getAmountOfCoinType(coinType));
        }
        System.out.println("------------------------");
    }

    @Override
    public void printDescription() {
        System.out.println(description);
    }
}
