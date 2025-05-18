<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Login - Student Management System</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <style>
    body {
      font-family: 'Inter', sans-serif;
      background-image: url('https://images.unsplash.com/photo-1503676260728-1c00da094a0b?auto=format&fit=crop&w=1950&q=80');
      background-size: cover;
      background-position: center;
    }
    .backdrop {
      backdrop-filter: blur(6px);
      background-color: rgba(255, 255, 255, 0.8);
    }
  </style>
</head>
<body class="min-h-screen flex items-center justify-center px-4">
<div class="backdrop p-8 rounded-xl shadow-2xl w-full max-w-md">
  <div class="text-center mb-6">
    <img src="https://cdn-icons-png.flaticon.com/512/3135/3135755.png" alt="Student Icon" class="w-16 h-16 mx-auto mb-2">
    <h2 class="text-3xl font-extrabold text-gray-800">Student Portal Login</h2>
    <p class="text-sm text-gray-600">Welcome back! Please login to your account.</p>
  </div>
  <form action="${pageContext.request.contextPath}/login" method="post" class="space-y-5">
    <div>
      <label for="username" class="block mb-1 font-medium text-gray-700">Username</label>
      <input type="text" id="username" name="username" required autofocus
             class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
    </div>
    <div>
      <label for="password" class="block mb-1 font-medium text-gray-700">Password</label>
      <input type="password" id="password" name="password" required
             class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
    </div>
    <c:if test="${not empty errorMessage}">
      <div class="text-red-600 font-medium">${errorMessage}</div>
    </c:if>
    <button type="submit"
            class="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition duration-200 font-semibold">
      Login
    </button>
  </form>
  <p class="text-center text-sm text-gray-500 mt-6">
    Don't have an account? <a href="#" class="text-blue-600 hover:underline">Contact Admin</a>
  </p>
</div>
</body>
</html>