package dev.lpa;

import java.time.LocalDate;
import java.time.Period;

public class CourseEngagement {

  private final Course course;
  private final LocalDate enrollmentDate;
  private String engagementType;
  private int lastLecture;
  private LocalDate lastActivityDate;



  public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
    this.course = course;
    this.enrollmentDate = this.lastActivityDate = enrollmentDate;
    this.engagementType = engagementType;
  }

  public String getCourseCode() {

    return course.getCourseCode();
  }

  public String getEngagementType() {
    return engagementType;
  }

  public int getLastLecture() {
    return lastLecture;
  }

  public int getEnrollmentYear() {

    return enrollmentDate.getYear();
  }

  public int getLastActivityYear() {

    return lastActivityDate.getYear();
  }

  public String getLastActivityMonth() {

    return "%tb".formatted(lastActivityDate);
  }

  public int getMonthsSinceActive() {

    return (int) Period.between(lastActivityDate, LocalDate.now()).toTotalMonths();
  }

  public double getPercentComplete() {

    return (double) lastLecture * 100.0 / course.getLectureCount();
  }

  public void watchLecture(int lectureNumber, LocalDate currentDate) {

    lastActivityDate = currentDate;
    lastLecture = Math.max(lectureNumber, lastLecture);
    engagementType = "Lecture " + lastLecture;
  }

  @Override
  public String toString() {
    return "%s: %s %d %s [%d]".formatted(course.courseCode(), getLastActivityMonth(),
      getLastActivityYear(), engagementType, getMonthsSinceActive());
  }
}
