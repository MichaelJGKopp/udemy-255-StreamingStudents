package dev.lpa;

import java.time.LocalDate;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

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
      .limit(10)
//      .peek(System.out::println)
      .forEach(s -> System.out.print(s.getPercentComplete("PYMC") + " "));
    System.out.println();

    System.out.println("Courses average percentage completed");
    String[] courseCodes = {"PYMC", "JMC", "CGIJ"};
    for (var cc : courseCodes) {
      System.out.printf("%s %.2f%% %n", cc, students.stream()
          .mapToDouble(s -> s.getPercentComplete(cc))
          .filter(d -> d != 0.0)
          .average()
          .orElseGet(() -> 0.0)
      );
    }
    System.out.println();

    for (var cc : courseCodes) {
      var byClass = students.stream()
        .filter(s -> s.getEngagementMap().values().stream()
          .map(ce -> ce.getCourseCode().equals(cc))
          .findAny()
          .orElse(false)
        )
        .toList();
      System.out.println("Class: " + cc + " Student Count: " + byClass.size());
    }

    var byCount = students.stream()
      .collect(groupingBy((Student s) -> (long) s.getEngagementMap().values().size()));
    byCount.forEach((k, v) -> System.out.println("Lectures: " + k + " Student Count: " + v.size()));

      var byCount2 = students.stream()
        .collect(groupingBy((Student s) -> (long) s.getEngagementMap().values().size(),
          Collectors.averagingDouble(s -> {
            double sum = 0;
            int divisor = 0;
            for (var cc : courseCodes) {
              double percentage = s.getPercentComplete(cc);
              if (percentage != 0)
                divisor++;
              sum += percentage;
            }
            return sum / divisor;
          })));
      byCount2.forEach((k, v) -> System.out.printf(
        "Lecture Count: %d Student percent complete: %.2f%% %n", k, v));
    System.out.println();

      for (var cc : courseCodes) {
        var map = students.stream()
          .flatMap(s -> s.getEngagementMap().values()
            .stream()
            .filter(ce -> ce.getCourseCode().equals(cc))
            .map(ce -> ce.getLastActivityYear())
          )
          .collect(groupingBy(s -> s, TreeMap::new, counting()));
        System.out.println("Course Code: " + cc);
        map.forEach((k, v) -> System.out.printf("Year: %d Activity Count: %d %n", k, v));
        System.out.println();
      }
    }
}
