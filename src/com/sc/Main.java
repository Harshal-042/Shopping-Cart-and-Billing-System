package com.sc;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Keyboard", 300.99));
        products.add(new Product(2, "Mouse", 100.50));
        products.add(new Product(3, "Headphones", 140.00));
        products.add(new Product(4, "USB Cable", 80.99));

        int choice = -1;

        while (choice != 0) {
            System.out.println("================================");
            System.out.println("SHOPPING CART AND BILLING SYSTEM");
            System.out.println("================================");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. Generate Bill");
            System.out.println("0. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                System.out.println();
                continue;
            }

            switch (choice) {
                case 1:
                    displayProducts(products);
                    break;
                case 2:
                    addProductToCart(scanner, cart, products);
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    removeProductFromCart(scanner, cart);
                    break;
                case 5:
                    generateCustomerBill(scanner, cart);
                    break;
                case 0:
                    System.out.println("Thank you for using the Shopping Cart and Billing System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please choose between 0 and 5.");
                    break;
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void displayProducts(ArrayList<Product> products) {
        System.out.println("\n--- Available Products ---");
        System.out.printf("%-5s %-15s %-10s\n", "ID", "Name", "Price");
        System.out.println("------------------------------");
        for (Product product : products) {
            System.out.printf("%-5d %-15s Rs.%-9.2f\n", product.getId(), product.getName(), product.getPrice());
        }
    }

    private static Product findProductById(ArrayList<Product> products, int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    private static void addProductToCart(Scanner scanner, Cart cart, ArrayList<Product> products) {
        try {
            System.out.print("Enter Product ID to add: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Product product = findProductById(products, id);

            if (product == null) {
                System.out.println("Product not found with ID: " + id);
                return;
            }

            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
                return;
            }

            cart.addProduct(product, quantity);
            System.out.println(quantity + " x " + product.getName() + " added to cart successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }
    }

    private static void removeProductFromCart(Scanner scanner, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        try {
            System.out.print("Enter Product ID to remove: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            boolean removed = cart.removeProduct(id);
            if (removed) {
                System.out.println("Product removed from cart successfully.");
            } else {
                System.out.println("Product with ID " + id + " is not in the cart.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        }
    }

    private static void generateCustomerBill(Scanner scanner, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty! Cannot generate bill.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Customer";
        }

        Customer customer = new Customer(name);
        Bill bill = new Bill(customer, cart);
        bill.generateBill();
    }
}
