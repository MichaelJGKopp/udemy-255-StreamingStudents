package dev.lpa;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {

  public static void main(String[] args) {

    Course pymc= new Course("PYMC", "Python Masterclass");
    Course jmc= new Course("JMC", "Java Masterclass");

    List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
      .limit(1000)
      .collect(Collectors.toList());  // mutable

    Optional<Student> o1 = getStudent(null, "first");
    System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
    System.out.println(o1);
  }
  private static Optional<Student> getStudent(List<Student> list, String type) {

    if (list == null || list.isEmpty()) {
      return Optional.empty();  // never return null when returning type Optional
    } else if (type.equals("first")) {
      return Optional.of(list.get(0));
    } else if (type.equals("last")) {
      return Optional.of(list.get(list.size() - 1));
    }
    return Optional.of(list.get(new Random().nextInt(list.size())));
  }
}
