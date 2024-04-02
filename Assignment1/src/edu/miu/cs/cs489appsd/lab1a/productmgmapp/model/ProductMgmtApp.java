package edu.miu.cs.cs489appsd.lab1a.productmgmapp.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductMgmtApp {

    public static void main(String[] args) {

        Product product1 = new Product(3128874119L, "Banana", "2023-01-24", 124, 0.55);
        Product product2 = new Product(2927458265L, "Apple", "2022-12-09", 18,  1.09);
        Product product3 = new Product(9189927460L, "Carrot", "2023-03-31", 89, 2.99);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        printProducts(products);
    }

    public static void printProducts(List<Product> products) {

        List<Product> sortedProducts = products
                .stream()
                .sorted(Comparator.comparing(Product::getName))
                .toList();

        printInJsonFormat(sortedProducts);
        System.out.println("---------------------------------------");
        printInXMLFormat(sortedProducts);
        System.out.println("---------------------------------------");
        printInCSVFormat(sortedProducts);
    }

    public static void printInJsonFormat(List<Product> products) {

        StringBuilder formatted = new StringBuilder();
        formatted.append("[");
        for (var product: products) {
            formatted.append("\n").append("\t").append(product);
        }
        formatted.append("\n").append("]");
        System.out.println("Printed in JSON format" + "\n" + formatted);
    }

    public  static void printInXMLFormat(List<Product> products) {

        System.out.println("Printed in XML Format:");
        System.out.println("<Products>");
        for (Product product : products) {
            System.out.println("\t<Product>");
            System.out.println("\t\t<ProductId>" + product.getId() + "</ProductId>");
            System.out.println("\t\t<Name>" + product.getName() + "</Name>");
            System.out.println("\t\t<DateSupplied>" + product.getDateSupplied() + "</DateSupplied>");
            System.out.println("\t\t<QuantityInStock>" + product.getQuantity() + "</QuantityInStock>");
            System.out.println("\t\t<UnitPrice>" + product.getUnitPrice() + "</UnitPrice>");
            System.out.println("\t</Product>");
        }
        System.out.println("</Products>");
    }

    public static  void printInCSVFormat(List<Product> products) {

        System.out.println("Printed in CSV Format:");
        System.out.println("ProductId,Name,DateSupplied,QuantityInStock,UnitPrice");
        for (Product product : products) {
            System.out.println(product.getId() + "," + product.getName() + "," +
                    product.getDateSupplied() + "," + product.getQuantity() + "," + product.getUnitPrice());
        }

    }
}
