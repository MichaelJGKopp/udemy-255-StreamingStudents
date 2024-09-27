package dev.lpa;

import java.util.List;
import java.util.Set;
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
    System.out.println("# of Australian students = " + australianStudents.size());
//    australianStudents.forEach(System.out::println);
  }
}
