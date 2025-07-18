<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Attendance</title>
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
    <div class="bg-white rounded-lg shadow-lg p-6">
        <div class="flex justify-between items-center mb-4">
            <h2 class="text-2xl font-semibold">Attendance List</h2>
            <a href="<%= request.getContextPath() %>/attendance/add" class="bg-blue-600 text-white p-2 rounded-md hover:bg-blue-700">Add New Attendance</a>
        </div>
        <table class="w-full border-collapse">
            <thead>
            <tr class="bg-gray-200">
                <th class="border p-2 text-left">ID</th>
                <th class="border p-2 text-left">Student ID</th>
                <th class="border p-2 text-left">Course ID</th>
                <th class="border p-2 text-left">Date</th>
                <th class="border p-2 text-left">Status</th>
                <th class="border p-2 text-left">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="attendance" items="${attendanceList}">
                <tr class="hover:bg-gray-50">
                    <td class="border p-2">${attendance.attendanceId}</td>
                    <td class="border p-2">${attendance.studentId}</td>
                    <td class="border p-2">${attendance.courseId}</td>
                    <td class="border p-2">${attendance.attendanceDate}</td>
                    <td class="border p-2">${attendance.status}</td>
                    <td class="border p-2">
                        <a href="<%= request.getContextPath() %>/attendance/edit?id=${attendance.attendanceId}" class="text-blue-600 hover:text-blue-800 mr-2">Edit</a>
                        <a href="<%= request.getContextPath() %>/attendance/delete?id=${attendance.attendanceId}" class="text-red-600 hover:text-red-800" onclick="return confirm('Are you sure you want to delete this attendance record?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty attendanceList}">
                <tr>
                    <td colspan="6" class="text-center p-4 text-gray-500">No attendance records found.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</main>
<footer class="bg-gray-200 text-gray-600 text-center p-4 mt-6">
    <p>&copy; 2025 Student Management System. All rights reserved.</p>
</footer>
</body>
</html>
