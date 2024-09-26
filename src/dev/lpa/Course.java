package dev.lpa;

public final class Course {

  private final String courseCode;
  private final String title;
  private final int lectureCount;

  public Course(String courseCode, String title, int lectureCount) {
    this.courseCode = courseCode;
    this.title = title;
    this.lectureCount = lectureCount;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public String getTitle() {
    return title;
  }

  public int getLectureCount() {
    return lectureCount;
  }
}
