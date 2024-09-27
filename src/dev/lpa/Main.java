package dev.lpa;

import java.util.List;
import java.util.function.Predicate;
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

        long studentsSize = 100;

        List<Student> students = students(20, pymc, jmc).toList();

        for (var gender : List.of("M", "F", "U")) {
            var studentsGender = students.stream()
              .filter(s -> s.getGender().equalsIgnoreCase(gender));
            System.out.println("Gender: " + gender + " Count: #" + studentsGender.count());
        }

        List<Predicate<Student>> predicates = List.of(
          (s) -> s.getAge() < 30,
          (Student s) -> s.getAge() >= 30 && s.getAge() <= 60
        );

        long total = 0;
        for (int i = 0; i < predicates.size(); i++) {
            var myStudents = students.stream().filter(predicates.get(i));
            long cnt = myStudents.count();
            total += cnt;
            System.out.printf("# of students (%s) = %d%n", i == 0 ? " < 30" : ">= 30 & < 60", cnt);
        }
        System.out.printf("# of students (%s) = %d%n", "> 60", studentsSize - total);

        var ageSummary = students.stream()
          .mapToInt(Student::getAge)
          .summaryStatistics();
        System.out.println("Student age summary: " + ageSummary);

        System.out.println("Students countries: ");
        students.stream()
          .map(Student::getCountryCode)
          .distinct()
          .forEach(c -> System.out.print(c + " "));
        System.out.println();

        var anyActive7 = students.stream()
          .anyMatch(s -> s.getYearsSinceEnrolled() >= 7);
        System.out.println("Any Students enrolled >= 7 years: " + anyActive7);

        System.out.println("Student data of 5: ");
        students.stream()
          .limit(5)
          .forEach(System.out::println);
    }

    public static Stream<Student> students(int studentsSize, Course... courses) {
        return Stream.generate(() -> Student.getRandomStudent(courses))
          .limit(studentsSize);
    }
}
