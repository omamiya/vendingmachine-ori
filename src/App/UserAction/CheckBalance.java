package App.UserAction;

import App.Machine;
import App.MachineProvider;

/**
 * Created by orymamia on 30/01/2018.
 */
public class CheckBalance implements UserAction {

    String description = UserActionTypes.CHECK_BALANCE.getValue() + ". Check Balance";
    MachineProvider machineProvider;

    public  CheckBalance(MachineProvider machineProvider){
        this.machineProvider = machineProvider;
    }
    @Override
    public void invoke() {
        System.out.println("Your Total Balance is: " + machineProvider.getMachine().getCustomerBalance().getTotalBalance());
        System.out.println("------------------------");

    }

    @Override
    public void printDescription() {
        System.out.println(description);
    }
}
