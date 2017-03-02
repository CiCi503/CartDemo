<%--
  Created by IntelliJ IDEA.
  User: CiCi
  Date: 2017/2/27
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="DAO.ItemsDao" %>
<%@ page import="Entity.Items" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>商品详细列表</title>
</head>
<body>
<center>
    <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>

                <!-- 商品循环开始 -->
                <%
                    ItemsDao itemsDao = new ItemsDao();
                    ArrayList<Items> list = itemsDao.getAllItems();
                    if ( list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            Items item = list.get(i);
                %>
                <div>
                    <dl>
                        <dt>
                            <a href="detail.jsp?id=<%=item.getId()%>"><img src="images/<%=item.getPicture()%>"
                                                                            width="120" height="90" border="1"/></a>
                        </dt>
                        <dd class="dd_name"><%=item.getName() %>
                        </dd>
                        <dd class="dd_city">产地:<%=item.getCity() %>&nbsp;&nbsp;价格:￥ <%=item.getPrice() %>
                        </dd>
                    </dl>
                </div>
                <!-- 商品循环结束 -->

                <%
                        }
                    }
                %>
            </td>
        </tr>
    </table>
</center>

<%--<%--%>
    <%--ItemsDao itemsDao = new ItemsDao();--%>
    <%--ArrayList<Items> a = itemsDao.getAllItems();--%>
    <%--for (int i=0; i<a.size(); i++) {--%>
        <%--Items item = a.get(i);--%>
        <%--System.out.print(item.getId() + " " + item.getName() + " " + item.getCity() + " " + item.getPrice());--%>
        <%--System.out.println();--%>
    <%--}--%>
<%--%>--%>
</body>
</html>
