package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hw("world");
    hw("user");
    hw("Iana");

    double l = 5;
    System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

    double a = 4;
    double b = 6;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(l, a));
  }

  public static void hw(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area (double l) {
    return l * l;
  }

  public static double area (double a, double b) {
    return a * b;
  }
}