package controller;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import model.Course;
import model.Enrollment;
import model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EnrollmentController", urlPatterns = {"/enrollments/*"})
public class EnrollmentController extends HttpServlet {
    private EnrollmentDAO enrollmentDAO;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        enrollmentDAO = new EnrollmentDAO();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action == null || action.equals("/")) {
                listEnrollments(request, response);
            } else if (action.equals("/add")) {
                showAddForm(request, response);
            } else if (action.equals("/delete")) {
                deleteEnrollment(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action.equals("/add")) {
                addEnrollment(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }

    private void listEnrollments(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
        request.setAttribute("enrollments", enrollments);
        request.getRequestDispatcher("/jsp/viewEnrollments.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/jsp/enrollStudent.jsp").forward(request, response);
    }

    private void addEnrollment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        Enrollment enrollment = new Enrollment(0, studentId, courseId, new Date());
        enrollmentDAO.addEnrollment(enrollment);
        response.sendRedirect(request.getContextPath() + "/enrollments");
    }

    private void deleteEnrollment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int enrollmentId = Integer.parseInt(request.getParameter("id"));
        enrollmentDAO.deleteEnrollment(enrollmentId);
        response.sendRedirect(request.getContextPath() + "/enrollments");
    }
}
