package dev.lpa;

import java.util.List;
import java.util.stream.IntStream;

public class MainMapping {

  public static void main(String[] args) {

    Course pymc = new Course("PYMC", "Python Masterclass", 50);
    Course jmc = new Course("JMC", "Java Masterclass", 100);
    Course jgames = new Course("CGIJ", "Creating Games in Java", 100);

    List<Student> students2 = IntStream
      .rangeClosed(1, 5000)
      .mapToObj(s -> Student.getRandomStudent(pymc, jmc))
      .toList();
  }
}
