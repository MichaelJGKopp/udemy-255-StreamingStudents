package dev.lpa;

public record Course(String courseCode, String title, int lectureCount) {

  public Course {
    if (getLectureCount() <= 0) {
      lectureCount = 1;
    }
  }

  public Course(String courseCode, String title) {
    this(courseCode, title, 40);
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

  @Override
  public String toString() {
    return "%s %s".formatted(courseCode, title);
  }
}
