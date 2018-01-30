package App;

import App.AdminAction.*;
import App.Money.IBalance;
import App.Money.UsdCoinType;
import App.Product.IProduct;
import App.Product.ProductFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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

    IProduct getProductSelectionFromUser(List<IProduct> products){
        System.out.println("Enter a number: ");
        Integer selection = Integer.parseInt(this.reader.nextLine());
        IProduct product = products.get(selection - 1);
        System.out.println("Your selection is: " + product.getProductName() + ". Please insert " + product.getProductPrice() + "cents");
        return product;
    }

    void collectMoney(IProduct product){
        IBalance customerBalance = machineProvider.getMachine().getCustomerBalance();
        String input;
        if(customerBalance.getTotalBalance() < product.getProductPrice()){
            System.out.println("Please choose one of the coins: \n");
            for(UsdCoinType coinType : UsdCoinType.values()){
                System.out.println(coinType.name() + " - " + coinType.getValue());
            }
            System.out.println("Total: " + customerBalance.getTotalBalance());
        }
        while(customerBalance.getTotalBalance() < product.getProductPrice()){
            input = this.reader.nextLine();
            customerBalance.updateCoinTypeAmount(UsdCoinType.valueOf(input.toUpperCase()), 1);
            System.out.println("Total: " + customerBalance.getTotalBalance());
        }
        System.out.println("Processing....");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void printProductsMenu(List<IProduct> products){
        int i = 1;
        System.out.println("Menu: ");
        System.out.println("Please select a product");
        for(IProduct product : products){
            System.out.println(i + ". " + product.getProductName() + " " + product.getProductPrice() + " cents");
            i++;
        }
    }

    Status buyProduct(){
        printProductsMenu(this.machineProvider.getMachine().getAllProducts());
        IProduct product = getProductSelectionFromUser(this.machineProvider.getMachine().getAllProducts());
        collectMoney(product);
        return this.machineProvider.getMachine().purchaseProduct(product);
    }

    void printUserMainMenu(){
        System.out.println("1. Buy a Product");
        System.out.println("2. Check Balance");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Logout");
    }

    void userFlow(){
        Status status;
        Integer selection;

        do {
            printUserMainMenu();
            selection = Integer.parseInt(this.reader.nextLine());

            switch (selection) {
                case 1:
                    status = buyProduct();
                    if (!status.status) {
                        System.out.println(status.message + "\n");
                    } else System.out.println("Enjoy!!! \n");
                    break;
                case 2:
                    System.out.println("Your Total Balance is: " + machineProvider.getMachine().getCustomerBalance().getTotalBalance());
                    break;
                case 3:
                    machineProvider.getMachine().emptyCustomerBalance();
                    break;
                case 4:
                    break;
            }
        } while(selection != 4);
    }

    void adminFlow(){
        Integer selection;
        Map<Integer, AdminAction> adminActions = new HashMap<>();

        adminActions.put(AdminActionTypes.ADD_PRODUCT.getValue(), new AddProduct(this.machineProvider, this.productFactory, this.reader));
        adminActions.put(AdminActionTypes.ADD_COIN.getValue(), new AddCoin(this.machineProvider, this.reader));
        adminActions.put(AdminActionTypes.PRINT_BALANCE.getValue(), new PrintBalance(this.machineProvider.getMachineAdmin().getMachineBalance()));
        adminActions.put(AdminActionTypes.PRINT_INVENTORY.getValue(), new PrintInventory(this.machineProvider));

        do {
            for (AdminAction action : adminActions.values()){
                action.printDescription();
            }
            selection = Integer.parseInt(this.reader.nextLine());
            if(selection != 5) adminActions.get(selection).invoke();
        } while(selection != 5);
    }
}
