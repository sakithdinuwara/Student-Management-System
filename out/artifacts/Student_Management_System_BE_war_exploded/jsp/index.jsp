<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Management System</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: {
                            50: '#f0f9ff',
                            100: '#e0f2fe',
                            200: '#bae6fd',
                            300: '#7dd3fc',
                            400: '#38bdf8',
                            500: '#0ea5e9',
                            600: '#0284c7',
                            700: '#0369a1',
                            800: '#075985',
                            900: '#0c4a6e',
                        }
                    },
                    fontFamily: {
                        sans: ['Inter', 'system-ui', 'sans-serif']
                    }
                }
            }
        }
    </script>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap');

        .nav-link {
            position: relative;
        }

        .nav-link::after {
            content: '';
            position: absolute;
            width: 0;
            height: 2px;
            bottom: -4px;
            left: 0;
            background-color: currentColor;
            transition: width 0.3s ease;
        }

        .nav-link:hover::after {
            width: 100%;
        }

        .card-hover {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card-hover:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
        }
    </style>
</head>
<body class="bg-gray-50 font-sans min-h-screen flex flex-col">
<header class="bg-gradient-to-r from-primary-600 to-primary-800 text-white py-6 px-4 shadow-lg">
    <div class="container mx-auto">
        <h1 class="text-3xl md:text-4xl font-bold text-center">
            <i class="fas fa-graduation-cap mr-2"></i>Student Management System
        </h1>
    </div>
</header>

<nav class="bg-white shadow-md py-3 sticky top-0 z-10">
    <div class="container mx-auto px-4">
        <div class="flex flex-wrap justify-center md:justify-center space-x-1 md:space-x-8">
            <a href="<%= request.getContextPath() %>/students" class="nav-link px-3 py-2 text-primary-600 hover:text-primary-800 font-medium flex items-center">
                <i class="fas fa-user-graduate mr-2"></i>Students
            </a>
            <a href="<%= request.getContextPath() %>/courses" class="nav-link px-3 py-2 text-primary-600 hover:text-primary-800 font-medium flex items-center">
                <i class="fas fa-book mr-2"></i>Courses
            </a>
            <a href="<%= request.getContextPath() %>/enrollments" class="nav-link px-3 py-2 text-primary-600 hover:text-primary-800 font-medium flex items-center">
                <i class="fas fa-clipboard-list mr-2"></i>Enrollments
            </a>
            <a href="<%= request.getContextPath() %>/grades" class="nav-link px-3 py-2 text-primary-600 hover:text-primary-800 font-medium flex items-center">
                <i class="fas fa-award mr-2"></i>Grades
            </a>
            <a href="<%= request.getContextPath() %>/attendance" class="nav-link px-3 py-2 text-primary-600 hover:text-primary-800 font-medium flex items-center">
                <i class="fas fa-calendar-check mr-2"></i>Attendance
            </a>
        </div>
    </div>
</nav>

<main class="container mx-auto p-6 flex-grow">
    <div class="bg-white rounded-xl shadow-lg p-8 text-center max-w-3xl mx-auto border border-gray-100">
        <h2 class="text-2xl md:text-3xl font-semibold mb-6 text-gray-800">Welcome to Student Management System</h2>
        <p class="text-gray-600 mb-8">Use the navigation links above to manage students, courses, and academic records.</p>

        <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mt-8">
            <div class="card-hover bg-primary-50 rounded-lg p-6 border border-primary-100">
                <div class="text-primary-600 text-3xl mb-3">
                    <i class="fas fa-user-graduate"></i>
                </div>
                <h3 class="font-semibold text-lg text-gray-800 mb-2">Manage Students</h3>
                <p class="text-gray-600 text-sm">Register, update, and track student information</p>
            </div>

            <div class="card-hover bg-primary-50 rounded-lg p-6 border border-primary-100">
                <div class="text-primary-600 text-3xl mb-3">
                    <i class="fas fa-book"></i>
                </div>
                <h3 class="font-semibold text-lg text-gray-800 mb-2">Course Management</h3>
                <p class="text-gray-600 text-sm">Add, edit, and schedule courses</p>
            </div>

            <div class="card-hover bg-primary-50 rounded-lg p-6 border border-primary-100">
                <div class="text-primary-600 text-3xl mb-3">
                    <i class="fas fa-chart-line"></i>
                </div>
                <h3 class="font-semibold text-lg text-gray-800 mb-2">Performance Tracking</h3>
                <p class="text-gray-600 text-sm">Monitor grades and attendance records</p>
            </div>
        </div>
    </div>
</main>

<footer class="bg-gray-800 text-gray-300 py-6 mt-8">
    <div class="container mx-auto px-4">
        <div class="flex flex-col md:flex-row justify-between items-center">
            <p class="mb-4 md:mb-0">&copy; 2025 Student Management System. All rights reserved.</p>
            <div class="flex space-x-4">
                <a href="#" class="text-gray-300 hover:text-white transition-colors">
                    <i class="fab fa-github"></i>
                </a>
                <a href="#" class="text-gray-300 hover:text-white transition-colors">
                    <i class="fab fa-twitter"></i>
                </a>
                <a href="#" class="text-gray-300 hover:text-white transition-colors">
                    <i class="fab fa-linkedin"></i>
                </a>
            </div>
        </div>
    </div>
</footer>
</body>
</html>