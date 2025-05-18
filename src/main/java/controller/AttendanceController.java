package controller;

import dao.AttendanceDAO;
import dao.CourseDAO;
import dao.StudentDAO;
import model.Attendance;
import model.Course;
import model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "AttendanceController", urlPatterns = {"/attendance/*"})
public class AttendanceController extends HttpServlet {
    private AttendanceDAO attendanceDAO;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    @Override
    public void init() throws ServletException {
        attendanceDAO = new AttendanceDAO();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getPathInfo();
        try {
            if (action == null || action.equals("/")) {
                listAttendance(request, response);
            } else if (action.equals("/add")) {
                showAddForm(request, response);
            } else if (action.equals("/edit")) {
                showEditForm(request, response);
            } else if (action.equals("/delete")) {
                deleteAttendance(request, response);
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
                addAttendance(request, response);
            } else if (action.equals("/update")) {
                updateAttendance(request, response);
            }
        } catch (SQLException | ParseException e) {
            throw new ServletException("Error processing request: " + e.getMessage(), e);
        }
    }

    private void listAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Attendance> attendanceList = attendanceDAO.getAllAttendance();
        request.setAttribute("attendanceList", attendanceList);
        request.getRequestDispatcher("/jsp/viewAttendance.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/jsp/addAttendance.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int attendanceId = Integer.parseInt(request.getParameter("id"));
        Attendance attendance = attendanceDAO.getAttendanceById(attendanceId);
        List<Student> students = studentDAO.getAllStudents();
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("attendance", attendance);
        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/jsp/addAttendance.jsp").forward(request, response);
    }

    private void addAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String attendanceDateStr = request.getParameter("attendanceDate");
        String status = request.getParameter("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date attendanceDate = sdf.parse(attendanceDateStr);

        Attendance attendance = new Attendance(0, studentId, courseId, attendanceDate, status);
        attendanceDAO.addAttendance(attendance);
        response.sendRedirect(request.getContextPath() + "/attendance");
    }

    private void updateAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int attendanceId = Integer.parseInt(request.getParameter("id"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String attendanceDateStr = request.getParameter("attendanceDate");
        String status = request.getParameter("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date attendanceDate = sdf.parse(attendanceDateStr);

        Attendance attendance = new Attendance(attendanceId, studentId, courseId, attendanceDate, status);
        attendanceDAO.updateAttendance(attendance);
        response.sendRedirect(request.getContextPath() + "/attendance");
    }

    private void deleteAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int attendanceId = Integer.parseInt(request.getParameter("id"));
        attendanceDAO.deleteAttendance(attendanceId);
        response.sendRedirect(request.getContextPath() + "/attendance");
    }
}
