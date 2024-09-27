package dev.lpa;

import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");
//        Student tim = new Student("AU", 2019, 30, "M",
//                true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//        System.out.println(tim);

        int studentsSize = 20;

        List<Student> students = students(20, pymc, jmc).toList();

        var maleCount = students
          .stream()
//          .peek(System.out::println)
          .filter(s -> s.getGender().equalsIgnoreCase("M"))
          .count();

        var femaleCount = students.stream()
          .filter(s -> s.getGender().equalsIgnoreCase("F"))
          .count();

        var undecidedCount = students.stream()
          .filter(s -> s.getGender().equalsIgnoreCase("U"))
          .count();

        System.out.println("Males: " + maleCount + " Females: " + femaleCount + " Undecided: " +
          undecidedCount);


        var ageCount = students
          .stream()
          .filter(s -> s.getAge() < 30)
          .count();
        System.out.println("Student count younger than 30: " + ageCount);

        var ageCount2 = students
          .stream()
          .filter(s -> s.getAge() >= 30 && s.getAge() <= 60)
          .count();
        System.out.println("Student count between 30 and 60 years old: " + ageCount2);

        var ageCount3 = students
          .stream()
          .filter(s -> s.getAge() > 60)
          .count();
        System.out.println("Student count older than 60: " + ageCount3);

        var ageSummary = students
          .stream()
          .mapToInt(Student::getAge)
          .summaryStatistics();
        System.out.println("Student age summary: " + ageSummary);

        System.out.println("Students countries: ");
        students
          .stream()
          .map(Student::getCountryCode)
          .distinct()
          .forEach(c -> System.out.print(c + " "));
        System.out.println();

        var anyActive7 = students
          .stream()
          .anyMatch(s -> s.getYearsSinceEnrolled() >= 7);
        System.out.println("Any Students enrolled >= 7 years: " + anyActive7);

        System.out.println("Student data of 5: ");
        students
          .stream()
          .limit(5)
          .forEach(System.out::println);
    }

    public static Stream<Student> students(int studentsSize, Course... courses) {
        return Stream.generate(() -> Student.getRandomStudent(courses))
          .limit(studentsSize);
    }
}
