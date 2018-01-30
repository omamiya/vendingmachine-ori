package App.AdminAction;

import App.MachineProvider;
import App.Product.IProduct;
import App.Product.ProductFactory;

import java.util.Scanner;

/**
 * Created by orymamia on 30/01/2018.
 */
public class AddProduct implements AdminAction {

    private String description = AdminActionTypes.ADD_PRODUCT.getValue() + ". Reload Product";
    private Scanner reader;
    private MachineProvider machineProvider;
    private ProductFactory productFactory;


    public AddProduct(MachineProvider machineProvider, ProductFactory productFactory, Scanner reader){
        this.machineProvider = machineProvider;
        this.productFactory = productFactory;
        this.reader = reader;
    }

    @Override
    public void invoke() {
        String name;
        Integer price;
        Integer amount;
        System.out.println("please enter product name: ");
        name = this.reader.nextLine();
        System.out.println("please enter product price: ");
        price = Integer.parseInt(this.reader.nextLine());
        System.out.println("Please enter amount: ");
        amount = Integer.parseInt(this.reader.nextLine());
        System.out.println(machineProvider.getMachineAdmin().addProduct(createProduct(name, price), amount).message);
        System.out.println("------------------------");
    }

    @Override
    public void printDescription() {
        System.out.println(description);
    }

    private IProduct createProduct(String name, Integer price){
        return this.productFactory.createProduct(name, price);
    }
}
