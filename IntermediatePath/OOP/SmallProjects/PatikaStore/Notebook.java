package PatikaStore;

public class Notebook extends ElectronicDevice {
    public static int nextId = 1;
    public Notebook(int id, double discountRate, int stock, String name, Brand brand, double price, int storage, double screenSize, int ram) {
        super(id, discountRate, stock, name, brand, price, storage, screenSize, ram);
    }

    @Override
    public void printInfo() {
        System.out.format("| %d  | %-33s | %-9.1f | %-9s | %-8d | %-10.1f | %-5d   |\n", this.getId(), this.getName(), this.getPrice(), this.getBrand().getName(), this.getStorage(), this.getScreenSize(), this.getRam());
    }
}
