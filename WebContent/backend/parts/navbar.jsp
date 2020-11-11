<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020/10/23
  Time: 下午 04:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container"> <a class="navbar-brand" href="#">
        <i class="fa d-inline fa-lg fa-stop-circle"></i>
        <b>Cake House後台管理系統</b>
    </a> <button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse" data-target="#navbar10">
        <span class="navbar-toggler-icon"></span>
    </button>
        <div class="collapse navbar-collapse" id="navbar10">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/list">使用者管理</a> </li>
            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/news-list">最新消息管理</a> </li>
            <li class="nav-item"> <a class="nav-link" href="<%=request.getContextPath()%>/product-list">產品管理</a> </li>
          </ul>
        </div>
    </div>
</nav>