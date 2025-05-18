package model;

public class Grade {
    private int gradeId;
    private int studentId;
    private int courseId;
    private String grade;

    // Default Constructor
    public Grade() {
    }

    // Parameterized Constructor
    public Grade(int gradeId, int studentId, int courseId, String grade) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    // Getters and Setters
    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", grade='" + grade + '\'' +
                '}';
    }
}
