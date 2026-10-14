package com.sc;

import java.util.Map;

public class Bill {
    private Customer customer;
    private Cart cart;

    public Bill(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
    }

    public void generateBill() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty! Cannot generate bill.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("             CUSTOMER BILL              ");
        System.out.println("========================================");
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("----------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-8s %-10s\n", "ID", "Name", "Price", "Qty", "Subtotal");
        System.out.println("----------------------------------------");

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = product.getPrice() * quantity;
            System.out.printf("%-5d %-15s Rs.%-9.2f %-8d Rs.%-9.2f\n",
                    product.getId(), product.getName(), product.getPrice(), quantity, subtotal);
        }

        System.out.println("----------------------------------------");
        System.out.printf("Final Total: Rs.%.2f\n", cart.calculateTotal());
        System.out.println("========================================");
        System.out.println("Thank you for shopping with us!");

        cart.clearCart();
    }
}
