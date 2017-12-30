package App.Product;

/**
 * Created by orymamia on 19/12/2017.
 */
public class Product implements IProduct {
    String name ;
    Integer price ;


    Product(String name, Integer price) {
        this.name = name ;
        this.price = price ;
    }


    @Override
    public String getProductName() {
        return this.name;
    }

    @Override
    public Integer getProductPrice() {
        return this.price;
    }
}
