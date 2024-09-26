package dev.lpa;

import java.time.LocalDate;
import java.util.*;

public class Student {

  private static long lastStudentId = 1;
  private final static Random random = new Random();

  private final long studentId;
  private final String countryCode;
  private final int yearEnrolled;
  private final int ageEnrolled;
  private final String gender;
  private final boolean programmingExperience;
  private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

  public Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender,
                 boolean programmingExperience, Course... courses) {
    this.studentId = lastStudentId++;
    this.countryCode = countryCode;
    this.yearEnrolled = yearEnrolled;
    this.ageEnrolled = ageEnrolled;
    this.gender = gender;
    this.programmingExperience = programmingExperience;

    for (Course course : courses) {
      addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
    }
  }

  public long getStudentId() {
    return studentId;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public int getYearEnrolled() {
    return yearEnrolled;
  }

  public int getAgeEnrolled() {
    return ageEnrolled;
  }

  public String getGender() {
    return gender;
  }

  public boolean hasProgrammingExperience() {
    return programmingExperience;
  }

  public Map<String, CourseEngagement> getEngagementMap() {
    return Map.copyOf(engagementMap);
  }

  public int getYearsSinceEnrolled() {

    return LocalDate.now().getYear() - yearEnrolled;
  }

  public int getAge() {

    return ageEnrolled + getYearEnrolled();
  }

  public void addCourse(Course course) {

    addCourse(course, LocalDate.now());
  }

  public void addCourse(Course course, LocalDate enrollDate) {

    engagementMap.put(course.getCourseCode(), new CourseEngagement(course, enrollDate,
      "Enrollment"));
  }

  public int getMonthsSinceActive(String courseCode) {

    CourseEngagement info = engagementMap.get(courseCode);
    return info == null ? 0 : info.getMonthsSinceActive();
  }

  public int getMonthsSinceActive() {

    List<Integer> monthsSinceActive = new ArrayList<>();
    engagementMap.forEach((k, v) -> monthsSinceActive.add(v.getMonthsSinceActive()));
    var x = monthsSinceActive.stream();

    return engagementMap
      .values()
      .stream()
      .map(CourseEngagement::getMonthsSinceActive)
      .max(Integer::compare)
      .orElse(0);
  }

  public double getPercentComplete(String courseCode) {

    CourseEngagement info = engagementMap.get(courseCode);
    return courseCode == null ? 0 : info.getPercentComplete();
  }

  public void watchLecture(String courseCode, int lectureNumber, int month, int year) {

    var activity = engagementMap.get(courseCode);
    if (activity != null) {
      activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
    }
  }

  @Override
  public String toString() {
    return "Student{" +
      "studentId=" + studentId +
      ", countryCode='" + countryCode + '\'' +
      ", yearEnrolled=" + yearEnrolled +
      ", ageEnrolled=" + ageEnrolled +
      ", gender='" + gender + '\'' +
      ", programmingExperience=" + programmingExperience +
      ", engagementMap=" + engagementMap +
      '}';
  }
}
