package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hw("world");
    hw("user");
    hw("Iana");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);
    System.out.println("Расстояние между точками с координатами:" + " х1:" + p1.x + " у1:" + p1.y + " и " + "х2:" + p2.x + " у2:" + p2.y + " = " + p1.distance(p2));
  }

  public static void hw(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }
}