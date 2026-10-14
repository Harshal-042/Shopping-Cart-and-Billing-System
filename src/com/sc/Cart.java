package com.sc;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        for (Product p : items.keySet()) {
            if (p.getId() == product.getId()) {
                items.put(p, items.get(p) + quantity);
                return;
            }
        }
        items.put(product, quantity);
    }

    public boolean removeProduct(int productId) {
        Product toRemove = null;
        for (Product p : items.keySet()) {
            if (p.getId() == productId) {
                toRemove = p;
                break;
            }
        }
        if (toRemove != null) {
            items.remove(toRemove);
            return true;
        }
        return false;
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n----------------- Cart Items -----------------");
        System.out.printf("%-5s %-15s %-10s %-8s %-10s\n", "ID", "Name", "Price", "Qty", "Subtotal");
        System.out.println("----------------------------------------------");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            double subtotal = p.getPrice() * qty;
            System.out.printf("%-5d %-15s Rs.%-9.2f %-8d Rs.%-9.2f\n", p.getId(), p.getName(), p.getPrice(), qty, subtotal);
        }
        System.out.println("----------------------------------------------");
        System.out.printf("Total Amount: Rs.%.2f\n", calculateTotal());
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }
}
