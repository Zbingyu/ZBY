<%@page pageEncoding="utf-8"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>

<form action="${pageContext.request.contextPath}/showStudent" method="post" id="f">
    学号:<input type="text"  name="xh" id="xh"  value="${param.xh }"/> <br>
    姓名:<input type="text"  name="xm" id="xm" value="${param.xm }"/> <br>
    出生:<input type=text    name="minDate" id="minDate" value="${param.minDate }"/>
        - <input  type=text   name="maxDate" id="maxDate"  value="${param.maxDate }"/>
          <br>
    状态:<select id="status"  name="status">
                <option value="-1" >不限</option>
                <option value="1" >启用</option>
                <option value="0" >禁用</option>
          </select>
          <input type="hidden"  name="current" value="1" id="current">
    <button>搜索</button>
     
</form>

<hr>


<button onclick="batchEnable(1)">批量启用</button>
<button onclick="batchEnable(0)">批量禁用</button>
<table border="1"  align="center"  width="80%">
  <tr>
    <th>
       <input type="checkbox"  id="chkAll" />
    </th>
    <th>学号</th>
    <th>姓名</th>
    <th>出生</th>
    <th>状态</th>
    <th>操作</th>
  </tr>
  
  <c:forEach items="${result.records}" var="s">
	  <tr align="center">
	    <td>
	       <input type="checkbox"  class="chk" value="${s.xh }">
	    </td>
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
	         <button onclick="update('${s.xh}')">编辑</button>
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
     //批量启用
     function batchEnable(status)
     {
           //得到选中id
           var ids = "-1";
           $(".chk:checked").each(function(){
               ids = ids+","+this.value;
           }); 
          
          var url = '${pageContext.request.contextPath}/changeStatus2';
          var data = {ids:ids,status:status};
          var callback = function(data)
          {
               //修改界面
              $(".chk:checked").each(function(i,o){
                    
                      if (status==1)
                      {
                           var tr = $(o).parent().parent();
                           tr.children("td").eq(4).html("已启用");
                            tr.children("td").eq(5).children("button:first").html("禁用");
                       }
                       else 
                     {
                           var tr = $(o).parent().parent();
                           tr.children("td").eq(4).html("已禁用");
                           tr.children("td").eq(5).children("button:first").html("启用");
                       }
                    
              });
                
              
          }
          $.post(url,data,callback);
     }
     
     
     function update(xh)
     {
         var url = '${pageContext.request.contextPath}/toUpdateView/'+xh;
         location  = url;
     }
</script>





