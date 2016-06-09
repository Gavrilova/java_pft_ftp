package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("World");
    hello("user");
    hello("Irina");

    Square s = new Square(9);
    System.out.println("Area of the square with size " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Area of the rectangle with sizes " + r.a + " and " + r.b + " = " + r.area());

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}
