package PatikaStore;

import java.util.*;

public class Store {
    private static List<Brand> brandsList;

    static {
        brandsList = new ArrayList<>();
        String[] nameList = {("Samsung"), ("Lenovo"), ("Apple"), ("Huawei"), ("Casper"), ("Asus"), ("HP"), ("Xiaomi"), ("Monster")};
        for (int i = 0; i < nameList.length; i++) {
            brandsList.add(new Brand(i, nameList[i]));
        }
        brandsList.sort(Comparator.comparing(Brand::getName));

        for (Brand b : brandsList) {
            switch (b.getName()) {
                case "Huawei":
                    b.addProduct(new Notebook(Notebook.nextId++, 0.05, 100, "HUAWEI Matebook 14", b, 7000, 512, 14.0, 16));
                    break;
                case "Lenovo":
                    b.addProduct(new Notebook(Notebook.nextId++, 0.01, 100, "LENOVO V14 IGL", b, 3699, 1024, 14, 8));
                    break;
                case "Asus":
                    b.addProduct(new Notebook(Notebook.nextId++, 0.01, 100, "ASUS Tuf Gaming", b, 8199, 2048, 15.6, 32));
                    break;
                case "Samsung":
                    b.addProduct(new Telephone(Telephone.nextId++, 0, 100, "SAMSUNG GALAXY A51", b, 3199, 128, 6.5, 6, "Black", 4000, 32));
                    break;
                case "Apple":
                    b.addProduct(new Telephone(Telephone.nextId++, 0.01, 100, "iPhone 11 64 GB", b, 7379, 64, 6.1, 6, "Blue", 3046, 5));
                    break;
                case "Xiaomi":
                    b.addProduct(new Telephone(Telephone.nextId++, 0.3, 100, "Redmi Note 10 Pro 8GB", b, 4012, 128, 6.5, 12, "White", 4000, 35));
                    break;
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.println("\n\n");
        }
    }

    public static void printMenu() {
        System.out.print("PatikaStore Product Management Panel !\n" +
                "1 - Notebook Menu\n" +
                "2 - Phone Menu\n" +
                "3 - List Brands\n" +
                "0 - Exit\n" +
                "Your choice : ");

        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextInt()) {
            case 1:
                printNotebooksMenu();
                break;
            case 2:
                printPhonesMenu();
                break;
            case 3:
                printBrands();
                break;
            default:
                System.exit(0);
        }
    }

    private static void printBrands() {
        System.out.println("\tOur Brands \n ---------------");
        for (Brand b : brandsList) {
            System.out.println("- " + b.getName());
        }
    }

    private static void printPhonesMenu() {
        System.out.print("\n\nPatikaStore Phone Management Panel! \n" +
                "1 - Add Phone\n" +
                "2 - Delete Phone\n" +
                "3 - Find a Phone by ID\n" +
                "4 - Find all Phones of a Brand\n" +
                "5 - List Phones \n" +
                "Your choice : ");

        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextInt()) {
            case 1:
                addTelephone();
                break;
            case 2:
                deleteTelephone();
                break;
            case 3:
                System.out.print("Enter the ID : ");
                findTelephone(scanner.nextInt(), " ");
                break;
            case 4:
                System.out.print("Enter the name of the Brand : ");
                findTelephone(-1, scanner.next());
                break;
            default:
                printTelephones();
                break;
        }
    }

    private static void printTelephones() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| ID | Ürün Adı                      | Fiyat     | Marka     | Depolama  | Ekran     | Kamera    | Pil       | RAM       | Renk      | \n" +
                "--------------------------------------------------------------------------------------------------------------------------------------");
        for (Brand b : brandsList) {
            for (Product p : b.getProductsList()) {
                if (p instanceof Telephone) p.printInfo();
            }
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    private static void findTelephone(int id, String brandName) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------\n" +
                "| ID | Ürün Adı                      | Fiyat     | Marka     | Depolama  | Ekran     | Kamera    | Pil       | RAM       | Renk      | \n" +
                "--------------------------------------------------------------------------------------------------------------------------------------");

        if (id >= 0) {
            for (Brand b : brandsList) {
                for (Product p : b.getProductsList()) {
                    if (!(p instanceof Telephone)) continue;

                    if (p.getId() == id) {
                        p.printInfo();
                    }
                }
            }
        }
        else {
            for (Brand b : brandsList) {
                if (b.getName().equals(brandName)) {
                    for (Product p : b.getProductsList()) {
                        if (!(p instanceof Telephone)) continue;
                        p.printInfo();
                    }
                }
            }
        }


        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    private static void deleteTelephone() {
        System.out.print("\n\nEnter the id: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        for (Brand b : brandsList) {
            for (Product p : b.getProductsList()) {
                if (!(p instanceof Telephone)) continue;

                if (p.getId() == id) {
                    b.deleteProduct(p);
                    break;
                }
            }
        }
    }

    private static void addTelephone() {
        System.out.println("\n\ndiscountRate, stock, \nname\nbrand, price, storage, screenSize, ram, color, batteryCapacity, camMp");
        System.out.println("Enter the details about notebook in the same order : ");

        Scanner scanner = new Scanner(System.in);
        Brand brand = null;
        double discountRate = scanner.nextDouble();
        int stock = scanner.nextInt();
        scanner.nextLine();
        String name = scanner.nextLine();
        String brandName = scanner.next();

        for (Brand b : brandsList) {
            if (b.getName().equals(brandName)) brand = b;
        }

        double price = scanner.nextDouble();
        int storage = scanner.nextInt();
        double screenSize = scanner.nextDouble();
        int ram = scanner.nextInt();
        String color = scanner.next();
        int batteryCapacity = scanner.nextInt();
        int camMp = scanner.nextInt();

        assert brand != null;
        brand.addProduct(new Telephone(Telephone.nextId++, discountRate, stock, name, brand, price, storage, screenSize, ram, color, batteryCapacity, camMp));
    }

    private static void printNotebooksMenu() {
        System.out.print("\n\nPatikaStore Notebooks Management Panel! \n" +
                "1 - Add Notebook\n" +
                "2 - Delete Notebook\n" +
                "3 - Find a Notebook by ID\n" +
                "4 - Find all Notebooks of a Brand\n" +
                "5 - List Notebooks \n" +
                "Your choice : ");

        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextInt()) {
            case 1:
                addNotebook();
                break;
            case 2:
                deleteNotebook();
                break;
            case 3:
                System.out.print("Enter the ID : ");
                findNotebook(scanner.nextInt(), " ");
                break;
            case 4:
                System.out.print("Enter the name of the Brand : ");
                findNotebook(-1, scanner.next());
                break;
            default:
                printNotebooks();
                break;
        }
    }

    public static void addNotebook() {
        System.out.println("\n\ndiscountRate, stock, \nname\nbrand, price, storage, screenSize, ram");
        System.out.println("Enter the details about notebook in the same order : ");

        Scanner scanner = new Scanner(System.in);
        Brand brand = null;
        double discountRate = scanner.nextDouble();
        int stock = scanner.nextInt();
        scanner.nextLine();
        String name = scanner.nextLine();
        String brandName = scanner.next();

        for (Brand b : brandsList) {
            if (b.getName().equals(brandName)) brand = b;
        }
        double price = scanner.nextDouble();
        int storage = scanner.nextInt();
        double screenSize = scanner.nextDouble();
        int ram = scanner.nextInt();

        assert brand != null;
        brand.addProduct(new Notebook(Notebook.nextId++, discountRate, stock, name, brand, price, storage, screenSize, ram));
    }

    public static void deleteNotebook() {
        System.out.print("\n\nEnter the id: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        for (Brand b : brandsList) {
            for (Product p : b.getProductsList()) {
                if (!(p instanceof Notebook)) continue;

                if (p.getId() == id) {
                    b.deleteProduct(p);
                    break;
                }
            }
        }
    }

    public static void printNotebooks() {
        System.out.println("----------------------------------------------------------------------------------------------------\n" +
                "| ID | Product Name                      | Price     | Brand     | Storage  | Screen     | RAM     |\n" +
                "----------------------------------------------------------------------------------------------------");
        for (Brand b : brandsList) {
            for (Product p : b.getProductsList()) {
                if (p instanceof Notebook) p.printInfo();
            }
        }

        System.out.println("----------------------------------------------------------------------------------------------------\n");
    }

    public static void findNotebook(int id, String brandName) {
        System.out.println("----------------------------------------------------------------------------------------------------\n" +
                "| ID | Product Name                      | Price     | Brand     | Storage  | Screen     | RAM     |\n" +
                "----------------------------------------------------------------------------------------------------");

        if (id >= 0) {
            for (Brand b : brandsList) {
                for (Product p : b.getProductsList()) {
                    if (!(p instanceof Notebook)) continue;

                    if (p.getId() == id) {
                        p.printInfo();
                    }
                }
            }
        }
        else {
            for (Brand b : brandsList) {
                if (b.getName().equals(brandName)) {
                    for (Product p : b.getProductsList()) {
                        if (!(p instanceof Notebook)) continue;
                        p.printInfo();
                    }
                }
            }
        }


        System.out.println("----------------------------------------------------------------------------------------------------\n");
    }
}
