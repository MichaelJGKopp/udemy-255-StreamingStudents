package dev.lpa;

import java.time.LocalDate;
import java.util.*;

public class Student {

  private long studentId;
  private String countryCode;
  private int yearEnrolled;
  private int ageEnrolled;
  private String gender;
  private boolean programmingExperience;
  private Map<String, CourseEngagement> engagementMap;

  public Student(long studentId, String countryCode, int yearEnrolled, int ageEnrolled,
                 String gender, boolean programmingExperience) {
    this.studentId = studentId;
    this.countryCode = countryCode;
    this.yearEnrolled = yearEnrolled;
    this.ageEnrolled = ageEnrolled;
    this.gender = gender;
    this.programmingExperience = programmingExperience;
    this.engagementMap = new TreeMap<>();
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

  public boolean isProgrammingExperience() {
    return programmingExperience;
  }

  public Map<String, CourseEngagement> getEngagementMap() {
    return new TreeMap<>(engagementMap);
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

    engagementMap.put(course.getCourseCode(), new CourseEngagement(course, enrollDate));
  }

  public int getMonthsSinceActive(String courseCode) {

    return engagementMap.get(courseCode).getMonthsSinceActive();
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

    return engagementMap.get(courseCode).getPercentComplete();
  }

}
