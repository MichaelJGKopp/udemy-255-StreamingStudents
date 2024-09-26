package dev.lpa;

import java.time.LocalDate;
import java.time.Period;

public class CourseEngagement {

  private Course course;
  private LocalDate enrollmentDate;
  private String engagementType;
  private int lastLecture;
  private LocalDate lastActivityDate;

  public String getCourseCode() {

    return course.getCourseCode();
  }

  public Course getCourse() {
    return course;
  }

  public LocalDate getEnrollmentDate() {

    return enrollmentDate;
  }

  public String getEngagementType() {
    return engagementType;
  }

  public int getLastLecture() {
    return lastLecture;
  }

  public LocalDate getLastActivityDate() {
    return lastActivityDate;
  }

  public int getEnrollmentYear() {

    return enrollmentDate.getYear();
  }

  public int getLastActivityYear() {

    return lastActivityDate.getYear();
  }

  public String getLastActivityMonth() {

    return lastActivityDate.getMonth().toString();
  }

  public int getMonthsSinceActive() {

    return Period.between(enrollmentDate, LocalDate.now()).getMonths();
  }

  public double getPercentComplete() {

    return (double) lastLecture/course.getLectureCount() * 100;
  }

  public void watchLecture(int lecture, LocalDate date) {

    lastActivityDate = date;
    lastLecture += lecture;
  }
}
