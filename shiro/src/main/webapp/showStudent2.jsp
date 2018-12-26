<%@page pageEncoding="utf-8"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>


<hr>



<table border="1"  align="center"  width="80%">
  <tr>
    <th>学号</th>
    <th>姓名</th>
    <th>出生</th>
    <th>状态</th>
    <th>操作</th>
  </tr>
  
  <c:forEach items="${result.records}" var="s">
	  <tr align="center">
	    <td>${s.xh }</td>
	    <td>${s.xm}</td>
	     <td>
	        <fmt:formatDate value="${s.cs}" pattern="yyyy-MM-dd HH:mm:ss"/>
	     </td>
	    <td>
	         ${s.status==1 ?"已启用":"已禁用" }
	    </td>
	     <td>
	         <button  id="${s.xh}"  class="btnGo" type="button">${s.status==1 ?"禁用":"启用" }</button>
	     </td>
	  </tr>
  </c:forEach>
      
      <tr align="center">
          <td colspan="5">
               当前${result.current }页/共${count}页
               <a href="javascript:go(1)">首页</a>
                <a href="javascript:go(${result.current-1})">上一页</a>
                       <c:set var="left" value="${result.current-5<1 ? 1 : result.current-5 }"></c:set>                
                       <c:set var="right" value="${left+9>count ? count : left+9 }"></c:set>
                       <c:forEach begin="${left}" end="${right }" var="page">
                             <a href="javascript:go(${page})">${page}</a>
                       </c:forEach>
                
               <a href="javascript:go(${result.current+1})">下一页</a>
               <a href="javascript:go(${count})">尾页</a>
          </td>
      </tr>
  
</table>

<script>
    //状态默认
     $("#status").val(${param.status})
     
     //分页,下一页，上一页...
     function go(page)
     {
         $("#current").val(page);
         $("#f")[0].submit();
     }
     

    //去改变
     $(".btnGo").click ( function(){
          var btn = $(this);
          var url = "${pageContext.request.contextPath}/changeStatus/"+this.id;
          var data = {};
          var callback = function(data)
          {
             if (data=="")
             {
                alert("编号不存在 ");
                return;
                 
             }
             if (data=="1")  //已启用
             {
                 btn.html("禁用");
                 btn.parent().prev().html("已启用");
             }
             else
             {
                 btn.html("启用");
                 btn.parent().prev().html("已禁用");
             }
          }
          $.post(url,data,callback);
     })
</script>





