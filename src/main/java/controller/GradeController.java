package controller;

import dao.CourseDAO;
import dao.GradeDAO;
import dao.StudentDAO;
import model.Course;
import model.Grade;
import model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GradeController", urlPatterns = {"/grades/*"})
public class GradeController extends HttpServlet {
    private GradeDAO gradeDAO;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        gradeDAO = new GradeDAO();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            if (action == null || action.equals("/")) {
                listGrades(request, response);
            } else if (action.equals("/add")) {
                showAddForm(request, response);
            } else if (action.equals("/edit")) {
                showEditForm(request, response);
            } else if (action.equals("/delete")) {
                deleteGrade(request, response);
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
                addGrade(request, response);
            } else if (action.equals("/update")) {
                updateGrade(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }

    private void listGrades(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Grade> grades = gradeDAO.getAllGrades();
        request.setAttribute("grades", grades);
        request.getRequestDispatcher("/jsp/viewGrades.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/jsp/addGrade.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int gradeId = Integer.parseInt(request.getParameter("id"));
        Grade grade = gradeDAO.getGradeById(gradeId);
        List<Student> students = studentDAO.getAllStudents();
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("grade", grade);
        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/jsp/addGrade.jsp").forward(request, response);
    }

    private void addGrade(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String gradeValue = request.getParameter("grade");

        Grade grade = new Grade(0, studentId, courseId, gradeValue);
        gradeDAO.addGrade(grade);
        response.sendRedirect(request.getContextPath() + "/grades");
    }

    private void updateGrade(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int gradeId = Integer.parseInt(request.getParameter("id"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String gradeValue = request.getParameter("grade");

        Grade grade = new Grade(gradeId, studentId, courseId, gradeValue);
        gradeDAO.updateGrade(grade);
        response.sendRedirect(request.getContextPath() + "/grades");
    }

    private void deleteGrade(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int gradeId = Integer.parseInt(request.getParameter("id"));
        gradeDAO.deleteGrade(gradeId);
        response.sendRedirect(request.getContextPath() + "/grades");
    }
}
