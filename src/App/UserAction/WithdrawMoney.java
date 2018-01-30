package App.UserAction;

import App.MachineProvider;

public class WithdrawMoney implements UserAction {
    String description = UserActionTypes.WITHDRAW_MONEY.getValue() + ". Withdraw Money";
    MachineProvider machineProvider;

    public  WithdrawMoney(MachineProvider machineProvider){
        this.machineProvider = machineProvider;
    }

    @Override
    public void invoke() {
        machineProvider.getMachine().emptyCustomerBalance();
        System.out.println("Thank You");
        System.out.println("------------------------");
    }

    @Override
    public void printDescription() {
        System.out.println(description);
    }
}
