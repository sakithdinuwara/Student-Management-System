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
    }
  </style>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
  <h2 class="text-2xl font-bold mb-6 text-center">Login</h2>
  <form action="${pageContext.request.contextPath}/login" method="post" class="space-y-4">
    <div>
      <label for="username" class="block mb-1 font-medium text-gray-700">Username</label>
      <input type="text" id="username" name="username" required autofocus
             class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
    </div>
    <div>
      <label for="password" class="block mb-1 font-medium text-gray-700">Password</label>
      <input type="password" id="password" name="password" required
             class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />
    </div>
    <c:if test="${not empty errorMessage}">
      <div class="text-red-600 font-medium">${errorMessage}</div>
    </c:if>
    <button type="submit" class="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition">
      Login
    </button>
  </form>
</div>
</body>
</html>

