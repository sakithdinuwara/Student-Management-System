<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:choose><c:when test="${attendance != null}">Edit Attendance</c:when><c:otherwise>Add Attendance</c:otherwise></c:choose></title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans">
<header class="bg-blue-600 text-white p-4">
    <h1 class="text-3xl font-bold text-center">Student Management System</h1>
</header>
<nav class="flex justify-center space-x-6 p-4 bg-gray-200 shadow-md">
    <a href="<%= request.getContextPath() %>/students" class="text-blue-600 hover:text-blue-800 font-medium">Students</a>
    <a href="<%= request.getContextPath() %>/courses" class="text-blue-600 hover:text-blue-800 font-medium">Courses</a>
    <a href="<%= request.getContextPath() %>/enrollments" class="text-blue-600 hover:text-blue-800 font-medium">Enrollments</a>
    <a href="<%= request.getContextPath() %>/grades" class="text-blue-600 hover:text-blue-800 font-medium">Grades</a>
    <a href="<%= request.getContextPath() %>/attendance" class="text-blue-600 hover:text-blue-800 font-medium">Attendance</a>
</nav>
<main class="container mx-auto p-6">
    <div class="bg-white rounded-lg shadow-lg p-6 max-w-md mx-auto">
        <h2 class="text-2xl font-semibold mb-4 text-center">
            <c:choose>
                <c:when test="${attendance != null}">Edit Attendance</c:when>
                <c:otherwise>Add New Attendance</c:otherwise>
            </c:choose>
        </h2>
        <form action="<%= request.getContextPath() %>/attendance/${attendance != null ? 'update' : 'add'}" method="post">
            <c:if test="${attendance != null}">
                <input type="hidden" name="id" value="${attendance.attendanceId}">
            </c:if>
            <div class="mb-4">
                <label for="studentId" class="block text-gray-700 font-medium mb-1">Select Student:</label>
                <select id="studentId" name="studentId" required
                        class="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <option value="">-- Select Student --</option>
                    <c:forEach var="student" items="${students}">
                        <option value="${student.studentId}" <c:if test="${attendance != null && attendance.studentId == student.studentId}">selected</c:if>>${student.name} (ID: ${student.studentId})</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-4">
                <label for="courseId" class="block text-gray-700 font-medium mb-1">Select Course:</label>
                <select id="courseId" name="courseId" required
                        class="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <option value="">-- Select Course --</option>
                    <c:forEach var="course" items="${courses}">
                        <option value="${course.courseId}" <c:if test="${attendance != null && attendance.courseId == course.courseId}">selected</c:if>>${course.courseName} (${course.courseCode})</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-4">
                <label for="attendanceDate" class="block text-gray-700 font-medium mb-1">Attendance Date:</label>
                <input type="date" id="attendanceDate" name="attendanceDate" value="${attendance != null ? attendance.attendanceDate : ''}" required
                       class="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>
            <div class="mb-4">
                <label for="status" class="block text-gray-700 font-medium mb-1">Status:</label>
                <select id="status" name="status" required
                        class="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <option value="">-- Select Status --</option>
                    <option value="Present" <c:if test="${attendance != null && attendance.status == 'Present'}">selected</c:if>>Present</option>
                    <option value="Absent" <c:if test="${attendance != null && attendance.status == 'Absent'}">selected</c:if>>Absent</option>
                </select>
            </div>
            <button type="submit" class="w-full bg-blue-600 text-white p-2 rounded-md hover:bg-blue-700">
                <c:choose>
                    <c:when test="${attendance != null}">Update Attendance</c:when>
                    <c:otherwise>Add Attendance</c:otherwise>
                </c:choose>
            </button>
        </form>
    </div>
</main>
<footer class="bg-gray-200 text-gray-600 text-center p-4 mt-6">
    <p>&copy; 2025 Student Management System. All rights reserved.</p>
</footer>
</body>
</html>
