<%@ page import="Entity.Items" %>
<%@ page import="DAO.ItemsDao" %><%--
  Created by IntelliJ IDEA.
  User: CiCi
  Date: 2017/3/2
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<body>
<%
    ItemsDao itemsDao = new ItemsDao();
    String id = request.getParameter("id");
    String name = itemsDao.getItemByID(Integer.parseInt(id)).getName();
%>
<h1>成功将商品<%=name%>加入购物车</h1>
</body>
</html>
