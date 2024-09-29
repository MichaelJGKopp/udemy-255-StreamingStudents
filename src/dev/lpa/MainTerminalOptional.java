package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class MainTerminalOptional {

  public static void main(String[] args) {

    Course pymc= new Course("PYMC", "Python Masterclass");
    Course jmc= new Course("JMC", "Java Masterclass");

    List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
      .limit(1000)
      .toList();

    int minAge = 18;
    students.stream()
      .filter(s -> s.getAge() <= minAge)
      .findAny()
      .ifPresentOrElse(s -> System.out.printf("Student %d from %s us %d%n",
        s.getStudentId(), s.getCountryCode(), s.getAge()),
        () -> System.out.println("Didn't find anyone under " + minAge));

    students.stream()
      .filter(s -> s.getAge() <= minAge)
      .findFirst()
      .ifPresentOrElse(s -> System.out.printf("Student %d from %s us %d%n",
          s.getStudentId(), s.getCountryCode(), s.getAge()),
        () -> System.out.println("Didn't find anyone under " + minAge));

    students.stream()
      .filter(s -> s.getAge() <= minAge)
      .min(Comparator.comparing(Student::getAge))
      // always needs a comparator
      .ifPresentOrElse(s -> System.out.printf("Student %d from %s us %d%n",
          s.getStudentId(), s.getCountryCode(), s.getAge()),
        () -> System.out.println("Didn't find anyone under " + minAge));

    students.stream()
      .filter(s -> s.getAge() <= minAge)
      .max(Comparator.comparing(Student::getAge))
      // always needs a comparator
      .ifPresentOrElse(s -> System.out.printf("Student %d from %s us %d%n",
          s.getStudentId(), s.getCountryCode(), s.getAge()),
        () -> System.out.println("Didn't find anyone under " + minAge));

    students.stream()
      .filter(s -> s.getAge() <= minAge)
      .mapToInt(Student::getAge)
      .average()
      .ifPresentOrElse(s -> System.out.printf("Student average age under %d is %.2f%n",
         minAge, s),
        () -> System.out.println("Didn't find anyone under " + minAge + "."));

    students.stream()
      .filter(s -> s.getAge() <= minAge)
      .map(Student::getCountryCode)
      .distinct()
      .sorted()
      .reduce((a, b) -> String.join(", ", a, b))
      .ifPresentOrElse( s -> System.out.print(s + " "), () -> System.out.println("None found."));
    System.out.println();

    students.stream()
      .map(Student::getCountryCode)
      .distinct()
      .sorted()
      .filter(l -> l.contains("AU"))
      .findAny()
      .ifPresentOrElse(System.out::println, () -> System.out.println("Missing AU"));
  }
}
