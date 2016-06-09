package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("World");
    hello("user");
    hello("Irina");

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}

