package controller;

import dao.CourseDAO;
import model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CourseController", urlPatterns = {"/courses/*"})
public class CourseController extends HttpServlet {
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action == null || action.equals("/")) {
                listCourses(request, response);
            } else if (action.equals("/add")) {
                showAddForm(request, response);
            } else if (action.equals("/edit")) {
                showEditForm(request, response);
            } else if (action.equals("/delete")) {
                deleteCourse(request, response);
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
                addCourse(request, response);
            } else if (action.equals("/update")) {
                updateCourse(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/jsp/viewCourses.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/addCourse.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = courseDAO.getCourseById(courseId);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/jsp/addCourse.jsp").forward(request, response);
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String courseName = request.getParameter("courseName");
        String courseCode = request.getParameter("courseCode");

        Course course = new Course(0, courseName, courseCode);
        courseDAO.addCourse(course);
        response.sendRedirect(request.getContextPath() + "/courses");
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        String courseName = request.getParameter("courseName");
        String courseCode = request.getParameter("courseCode");

        Course course = new Course(courseId, courseName, courseCode);
        courseDAO.updateCourse(course);
        response.sendRedirect(request.getContextPath() + "/courses");
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        courseDAO.deleteCourse(courseId);
        response.sendRedirect(request.getContextPath() + "/courses");
    }
}
