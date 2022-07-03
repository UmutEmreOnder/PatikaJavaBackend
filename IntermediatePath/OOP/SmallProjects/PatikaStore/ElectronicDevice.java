package PatikaStore;

public class ElectronicDevice extends Product {
    private int storage;
    private double screenSize;
    private int ram;

    public ElectronicDevice(int id, double discountRate, int stock, String name, Brand brand, double price, int storage, double screenSize, int ram) {
        super(id, discountRate, stock, name, brand, price);
        this.storage = storage;
        this.screenSize = screenSize;
        this.ram = ram;
    }


    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
