package App.Product;

/**
 * Created by orymamia on 19/12/2017.
 */
public class Product implements IProduct {
    String name ;
    double price ;


    public Product(String name, double price) {
        this.name = name ;
        this.price = price ;
    }


    @Override
    public String getProductName() {
        return this.name;
    }

    @Override
    public double getProductPrice() {
        return this.price;
    }
}
