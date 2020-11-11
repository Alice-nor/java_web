<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <style> 	
    <%@include file="../theme.css"%>
  </style>
</head>

<body>

  <%@include file="../parts/navbar.jsp"%>
  <div class="text-center py-3">
    <div class="container">
      <div class="row">
        <div class="col-md-12 p-3">
          <h1 class="mb-0 text-left">最新消息管理</h1>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <ul class="breadcrumb">
            <li class="breadcrumb-item"> <a href="#">首頁</a> </li>
            <li class="breadcrumb-item"> <a href="list.html">最新消息管理</a> </li>
            <li class="breadcrumb-item active">新增消息</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="pb-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
        <%Date date = new Date();%>
          <a href="list" class="btn btn-sm btn-primary my-3">回列表</a>

            <form action="insert" method="post" class="justify-content-center">
            <h2 class="my-2">新增消息</h2>
            <input type="hidden" name="id" value="" />
            
            <div class="form-group"> <label>發佈日期</label> <input type="text" class="form-control" id="published_date" name="published_at" value="<c:out value="${news.published_at}"/>" autocomplete="off"> </div>
            <div class="form-group"> <label>標題</label> <input type="text" class="form-control" name="title" placeholder="輸入標題" value="<c:out value="${news.title}"/>"> </div>
            <div class="form-group"> <label>內容</label> <textarea name="content" id="" cols="30" rows="10" class="form-control"><c:out value="${news.content}"></c:out></textarea></div>
            <div class="col-md-12 d-flex justify-content-end">
          
             <input type="hidden" class="form-control" name="created_at" value="<%=date.toString()%>">	 
             <input type="hidden" class="form-control" name="updated_at" value="<%=date.toString()%>">
              <button type="submit" class="btn btn-primary d-inline-flex">確定送出</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div class="py-3">
    <div class="container">
      <div class="row">
        <div class="col-md-12 text-center">
          <p class="mb-0">© 2014-2018 MacroViz. All rights reserved</p>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="https://cdn.ckeditor.com/4.15.0/standard/ckeditor.js"></script>
  <script>
    CKEDITOR.replace( 'content' );
    $(function() {
      $( "#published_date" ).datepicker();
    });
    </script>
</body>

</html>