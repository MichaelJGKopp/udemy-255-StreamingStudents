package dev.lpa;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainChallenge {

  public static void main(String[] args) {

    Course pymc = new Course("PYMC", "Python Masterclass", 50);
    Course jmc = new Course("JMC", "Java Masterclass", 100);
    Course cgij = new Course("CGIJ", "Creating Games in Java", 100);

//    List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc, jmc, cgij))
//      .limit(5000)
//      .toList();

    List<Student> students = Stream
      .iterate(1, s -> s <= 5000, s -> s + 1)
      .map(s -> Student.getRandomStudent(pymc, jmc, cgij))
      .toList();

    List<Student> students2 = IntStream
      .rangeClosed(1, 5000)
      .boxed()  // or mapToObj
      .map(s -> Student.getRandomStudent(pymc, jmc, cgij))
      .toList();


    var jmcSum = students.stream()
      .mapToDouble( s -> s.getPercentComplete("JMC"))
      .reduce(0, Double::sum); // or .sum()
    var jmcCount = students.stream()  // same as students.size() here
      .mapToDouble( s -> s.getPercentComplete("JMC"))
      .reduce(0, (d, e) -> d + 1);
    var jmcAvg = jmcSum/jmcCount;
    System.out.println("jmcAvg: " + jmcAvg + " jmcCount: " + jmcCount);

    var jmcPerc = jmcAvg * 1.25;

    Set<Student> studentPerc = students.stream()
      .filter(s -> s.getPercentComplete("JMC") >= jmcPerc)
      .collect(() -> new TreeSet<>(
        Comparator.comparing(s -> s.getPercentComplete("JMC"))),  // also used to determine uniqueness of Student
        Set::add,
        Set::addAll);

    System.out.println("Before");
    List<Student> studentPercList = studentPerc.stream()
      .sorted(Comparator.comparing(Student::getYearsSinceEnrolled)
        .thenComparing(Student::getStudentId))
      .limit(10)
      .peek(System.out::println)
      .toList();  // .collect(Collectors.toList()); // is modifiable, .toList() is not
//    .collect(Collectors.toSet()); // HashSet
    studentPercList.stream()
      .forEach(s -> s.addCourse(new Course("NC", "New Course")));
    System.out.println("After");
    studentPercList.forEach(System.out::println);
  }
}
