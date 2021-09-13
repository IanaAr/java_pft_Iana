package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;


public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
    for (String l : langs) {
      System.out.print("Хочу выучить" + l);


    /*
      1. variation using list class
    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("PHP");

      2. variation using cicle
   for (int i = 0; i < langs.length; i++) {
   System.out.print("Хочу выучить" + langs[i]);
    }

      3. variation using massive + cicle + class
     String[] langs = {"Java", "C#", "Python", "PHP"};
    for (int i = 0; i < languages.size(); i++) {
      System.out.print("Хочу выучить" + languages.get(i));
    }
    */

    }
  }
}
