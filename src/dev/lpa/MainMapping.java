package dev.lpa;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class MainMapping {

  public static void main(String[] args) {

    Course pymc = new Course("PYMC", "Python Masterclass", 50);
    Course jmc = new Course("JMC", "Java Masterclass", 100);
    Course jgames = new Course("CGIJ", "Creating Games in Java", 100);

    List<Student> students = IntStream
      .rangeClosed(1, 5000)
      .mapToObj(s -> Student.getRandomStudent(pymc, jmc))
      .toList();

    var mappedStudents = students.stream()
      .collect(groupingBy(Student::getCountryCode));

    mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));

    System.out.println("-----------------------------------------------");
    int minAge = 25;
    var youngerSet = students.stream()
      .collect(groupingBy(Student::getCountryCode,
        filtering(s -> s.getAge() <= minAge, toList()))); // modifiable List
    youngerSet.forEach((k, v) -> System.out.println(k + " " + v.size()));

    var experienced = students.stream()
      .collect(partitioningBy(Student::hasProgrammingExperience));
    System.out.println("Experienced Students = " + experienced.get(true).size());

    var expCount = students.stream()
      .collect(partitioningBy(Student::hasProgrammingExperience, counting()));
    System.out.println("Experienced Students = " + expCount.get(true));

    var experiencedAndActive = students.stream()
      .collect(partitioningBy(
        s -> s.hasProgrammingExperience()
            && s.getMonthsSinceActive() == 0,
        counting()));
    System.out.println("Experienced and Active Students = " + experiencedAndActive.get(true));
  }
}
