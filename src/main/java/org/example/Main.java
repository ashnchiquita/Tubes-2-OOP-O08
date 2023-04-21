package org.example;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press ⌥⏎ with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        System.out.println("Lombok Usage Example");
        Job furnitureCarpenter = new Job("carpenter");
        furnitureCarpenter.setPosition("furniture");
        furnitureCarpenter.setSalary(2500d);
        System.out.println(furnitureCarpenter);

        // Press ⌃R or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press ⌃D to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing ⌘F8.
            System.out.println("i = " + i);
        }
    }
}