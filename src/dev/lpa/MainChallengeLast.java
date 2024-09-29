package dev.lpa;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallengeLast {

  public static void main(String[] args) {

    Course pymc = new Course("PYMC", "Python Masterclass", 50);
    Course jmc = new Course("JMC", "Java Masterclass", 100);
    Course jgames = new Course("CGIJ", "Creating Games in Java", 100);

    var students = Stream
      .generate(() -> Student.getRandomStudent(pymc, jmc, jgames))
      .filter(s -> s.getYearEnrolled() - LocalDate.now().getYear() < 4)
      .limit(10_000)
      .toList();

    students.stream()
      .limit(5)
      .forEach(System.out::println);

    var byCount = students.stream()
        .collect(Collectors.groupingBy((Student s) -> (long) s.getEngagementMap().values().size()));

    byCount.forEach((k, v) -> System.out.println("Lectures: " + k + " Student Count: " + v.size()));
  }
}
