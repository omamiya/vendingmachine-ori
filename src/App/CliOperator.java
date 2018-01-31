package App;

import App.AdminAction.*;
import App.Product.ProductFactory;
import App.UserAction.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CliOperator {
    private ProductFactory productFactory = new ProductFactory();
    private MachineProvider machineProvider;
    private Scanner reader = new Scanner(System.in);

    CliOperator(MachineProvider machineProvider){
        this.machineProvider = machineProvider;
    }

    void runMachine() {
        String user;
        do{
            user = login();
            switch (user) {
                case "admin":
                    adminFlow();
                    break;
                case "user":
                    userFlow();
                    break;
                case "exit":
                    break;
            }
        } while(user != "exit");

        this.reader.close();
    }

    String login(){
        String[] options = {"admin", "user", "exit"};
        String selection;
        System.out.println("Please login:" + "\n" + "1. Admin" + "\n" + "2. User" + "\n" + "3. Exit");
        selection = options[Integer.parseInt(this.reader.nextLine()) - 1];
        return selection;

    }

    void userFlow(){
        Integer selection;
        Map<Integer, UserAction> userActions = new HashMap<>();
        userActions.put(UserActionTypes.BUY_PRODUCT.getValue(), new BuyProduct(this.machineProvider, this.reader));
        userActions.put(UserActionTypes.CHECK_BALANCE.getValue(), new CheckBalance(this.machineProvider));
        userActions.put(UserActionTypes.WITHDRAW_MONEY.getValue(), new WithdrawMoney(this.machineProvider));
        userActions.put(UserActionTypes.LOGOUT.getValue(), new UserLogout());

        do {
            for (UserAction action : userActions.values()){
                action.printDescription();
            }
            selection = Integer.parseInt(this.reader.nextLine());
            userActions.get(selection).invoke();
        } while(selection != 4);
    }

    void adminFlow(){
        Integer selection;
        Map<Integer, AdminAction> adminActions = new HashMap<>();

        adminActions.put(AdminActionTypes.ADD_PRODUCT.getValue(), new AddProduct(this.machineProvider, this.productFactory, this.reader));
        adminActions.put(AdminActionTypes.ADD_COIN.getValue(), new AddCoin(this.machineProvider, this.reader));
        adminActions.put(AdminActionTypes.PRINT_BALANCE.getValue(), new PrintBalance(this.machineProvider.getMachineAdmin().getMachineBalance()));
        adminActions.put(AdminActionTypes.PRINT_INVENTORY.getValue(), new PrintInventory(this.machineProvider));
        adminActions.put(AdminActionTypes.LOGOUT.getValue(), new AdminLogout());

        do {
            for (AdminAction action : adminActions.values()){
                action.printDescription();
            }
            selection = Integer.parseInt(this.reader.nextLine());
            adminActions.get(selection).invoke();
        } while(selection != 5);
    }
}
