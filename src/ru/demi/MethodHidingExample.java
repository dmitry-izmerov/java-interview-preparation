package ru.demi;

/**
 * It is static compile time binding unlike overriding with dynamic binding.
 */
public class MethodHidingExample {

    public static void main(String[] args) {
        A b = new B();
        b.staticMethod(); // == A.staticMethod()
        ((B) b).staticMethod(); // == B.staticMethod()
    }

    private static class A {
        private static void staticMethod() {
            System.out.println("class A staticMethod");
        }
    }

    private static class B extends A {
        private static void staticMethod() {
            System.out.println("class B staticMethod");
        }
    }
}
