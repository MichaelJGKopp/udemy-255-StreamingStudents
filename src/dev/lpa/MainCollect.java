package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {

  public static void main(String[] args) {

    Course pymc= new Course("PYMC", "Python Masterclass");
    Course jmc= new Course("JMC", "Java Masterclass");

    List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
      .limit(1000)
      .toList();

    Set<Student> australianStudents = students.stream()
      .filter(s -> s.getCountryCode().equals("AU"))
      .collect(Collectors.toSet());
    System.out.println("# of Australian Students = " + australianStudents.size());
//    australianStudents.forEach(System.out::println);

    Set<Student> underThirty = students.stream()
      .filter(s -> s.getAge() < 30)
      .collect(Collectors.toSet());
    System.out.println("# of Under Thirty Students = " + underThirty.size());
//    underThirty.forEach(System.out::println);

    Set<Student> youngAussies1 = new TreeSet<>(Comparator.comparing(Student::getStudentId));
    youngAussies1.addAll(australianStudents);
    youngAussies1.retainAll(underThirty); // intersect two sets
    youngAussies1.forEach((s) -> System.out.print(s.getStudentId() + " "));
    System.out.println();
  }
}
