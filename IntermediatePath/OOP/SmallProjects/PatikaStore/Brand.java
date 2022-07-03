package PatikaStore;

import java.util.ArrayList;
import java.util.List;

public class Brand {
    private int id;
    private String name;
    private List<Product> productsList;

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
        this.productsList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productsList.add(product);
    }

    public void deleteProduct(Product p) {
        productsList.remove(p);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }
}
