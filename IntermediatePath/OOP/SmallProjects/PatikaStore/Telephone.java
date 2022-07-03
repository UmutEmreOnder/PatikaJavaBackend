package PatikaStore;

public class Telephone extends ElectronicDevice {
    public static int nextId = 1;
    private String color;
    private int batteryCapacity;
    private int camMp;

    public Telephone(int id, double discountRate, int stock, String name, Brand brand, double price, int storage, double screenSize, int ram, String color, int batteryCapacity, int camMp) {
        super(id, discountRate, stock, name, brand, price, storage, screenSize, ram);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.camMp = camMp;
    }

    @Override
    public void printInfo() {
        System.out.format("| %d  | %-29s | %-9.1f | %-9s | %-9d | %-9.1f | %-9d | %-9d | %-9d | %-9s |\n", this.getId(), this.getName(), this.getPrice(), this.getBrand().getName(), this.getStorage(), this.getScreenSize(), this.getCamMp(), this.getBatteryCapacity(), this.getRam(), this.getColor());
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public int getCamMp() {
        return camMp;
    }

    public void setCamMp(int camMp) {
        this.camMp = camMp;
    }
}
