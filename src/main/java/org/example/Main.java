package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        System.out.println("Lombok Usage Example");
        Job furnitureCarpenter = new Job("carpenter");
        furnitureCarpenter.setPosition("furniture");
        furnitureCarpenter.setSalary(2500d);
        System.out.println(furnitureCarpenter);

        for (int i = 1; i <= 5; i++) {

            System.out.println("i = " + i);
        }
    }
}