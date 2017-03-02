<%@ page import="Entity.Items" %>
<%@ page import="DAO.ItemsDao" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: CiCi
  Date: 2017/2/27
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情页</title>
    <script>
        function selflog_show(id)
        {
            console.log("11111");
            var num =  document.getElementById("number").value;
//            var frm = document.form;
//            frm.action = "/Servlet/DepServlet?id="+id  + "&num=" +num + "&action=add";
            window.location.href="/Servlet/CartServlet?id="+id+"&num="+num+"&action=add";
//            J.dialog.get({id: 'haoyue_creat',title: '购物成功',width: 600,height:400, link: '/Servlet/CartServlet?id='+id+'&num='+num+'&action=add', cover:true});
//            J.dialog.get( link: '/Servlet/CartServlet?id='+id+'&num='+num+'&action=add', cover:true});
        }

        function add()
        {
            var num = parseInt(document.getElementById("number").value);
            if(num<100)
            {
                document.getElementById("number").value = ++num;
            }
        }
        function sub()
        {
            var num = parseInt(document.getElementById("number").value);
            if(num>1)
            {
                document.getElementById("number").value = --num;
            }
        }

    </script>

    <style type="text/css">
        hr{

            /*border-color: FF7F00;*/
        }

        div{
            float:left;
            margin-left: 30px;
            margin-right:30px;
            margin-top: 5px;
            margin-bottom: 5px;

        }
        div dd{
            margin:0px;
            font-size:10pt;
        }
        div dd.dd_name
        {
            color:blue;
        }
        div dd.dd_city
        {
            color:#000;
        }
        div #cart
        {
            margin:0px auto;
            text-align:right;
        }
        span{
            padding:0 2px;border:1px #c0c0c0 solid;cursor:pointer;
        }
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<h1>商品详情</h1>
<hr>
<center>
    <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <!-- 商品详情 -->
            <%
                ItemsDao itemDao = new ItemsDao();
                Items item = itemDao.getItemByID(Integer.parseInt(request.getParameter("id")));//获取前一个页面传过来的参数
                if(item!=null)
                {
            %>
            <td width="70%" valign="top">
                <table>
                    <tr>
                        <td rowspan="4"><img src="images/<%=item.getPicture()%>" width="200" height="160"/></td>
                    </tr>
                    <tr>
                        <td><B><%=item.getName() %></B></td>
                    </tr>
                    <tr>
                        <td>产地：<%=item.getCity()%></td>
                    </tr>
                    <tr>
                        <td>价格：<%=item.getPrice() %>￥</td>
                    </tr>
                    <tr>
                        <td>购买数量：<span id="sub" onclick="sub();">-</span><input type="text" id="number" name="number" value="1" size="2"/><span id="add" onclick="add();">+</span></td>
                    </tr>
                    <tr>
                    <div id="cart">
                        <img src="images/buy_now.png"><a href="javascript:selflog_show(<%=item.getId()%>)"><img src="images/in_cart.png"></a><a href="Servlet/CartServlet?action=show"><img src="images/view_cart.jpg"/></a>
                    </div>
                    </tr>
                </table>
            </td>
            <%
                }
            %>
            <%
                String list ="";
                //从客户端获得Cookies集合
                Cookie[] cookies = request.getCookies();
                //遍历这个Cookies集合
                if(cookies!=null&&cookies.length>0)
                {
                    for(Cookie c:cookies)
                    {
                        if(c.getName().equals("ListViewCookie"))
                        {
                            list = c.getValue();
                        }
                    }
                }

                list+=request.getParameter("id")+"A";
                //如果浏览记录超过1000条，清零.
                String[] arr = list.split("A");
                if(arr!=null&&arr.length>0)
                {
                    if(arr.length>=1000)
                    {
                        list="";
                    }
                }
                Cookie cookie = new Cookie("ListViewCookie",list);
                response.addCookie(cookie);

            %>

            <!-- 浏览过的商品 -->
            <td width="30%" bgcolor="#EEE" align="center">
                <br>
                <b>您浏览过的商品</b><br>
                <!-- 循环开始 -->
                <%
                    ArrayList<Items> itemlist = itemDao.getViewList(list);
                    if(itemlist!=null&&itemlist.size()>0 )
                    {
//                        System.out.println("itemlist.size="+itemlist.size());
                        for(Items i:itemlist)
                        {

                %>
                <div>
                    <dl>
                        <dt>
                            <a href="detail.jsp?id=<%=i.getId()%>"><img src="images/<%=i.getPicture() %>" width="120" height="90" border="1"/></a>
                        </dt>
                        <dd class="dd_name"><%=i.getName() %></dd>
                        <dd class="dd_city">产地:<%=i.getCity() %>&nbsp;&nbsp;价格:<%=i.getPrice() %> ￥ </dd>
                    </dl>
                </div>
                <%
                        }
                    }
                %>
                <!-- 循环结束 -->
                <!-- 循环结束 -->
            </td>
        </tr>
    </table>
</center>
</body>
</html>
