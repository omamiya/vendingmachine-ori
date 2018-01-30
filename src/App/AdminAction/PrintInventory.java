package App.AdminAction;

import App.MachineProvider;
import App.Product.IProduct;

import java.util.List;

/**
 * Created by orymamia on 30/01/2018.
 */
public class PrintInventory implements AdminAction {
    private String description = AdminActionTypes.PRINT_INVENTORY.getValue() + ". Check Machine Inventory";
    MachineProvider machineProvider;

    public PrintInventory(MachineProvider machineProvider){
        this.machineProvider = machineProvider;

    }
    @Override
    public void invoke() {
        List<IProduct> productList = this.machineProvider.getMachine().getAllProducts();
        System.out.println("Inventory: ");
        for (IProduct product : productList) {
            System.out.println(product.getProductName() + " - " + machineProvider.getMachineAdmin().getProductAmount(product));
        }
        System.out.println("--------------------");
    }

    @Override
    public void printDescription() {
        System.out.println(description);
    }
}
