<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>


<!-- 搜索区 -->
<form id="f">
<div id="searchDiv">
	学号:<input type="text" name="xh" id="xh"  /> <br>
	姓名:<input type="text" name="xm" id="xm" /> <br>
	出生:<input type=text name="minDate" id="minDate"
		value="" /> - <input type=text name="maxDate"
		id="maxDate" value="" /> <br> 状态:<select
		id="status" name="status">
		<option value="-1">不限</option>
		<option value="1">启用</option>
		<option value="0">禁用</option>
	</select> <input type="hidden" name="current" value="1" id="current">
	<button>搜索</button>
</div>
</form>

<!-- 数据区 -->
<div id="dataDiv"></div>


<script>
     function go(page)
     {
         $("#current").val(page);
         var data = $("#f").serialize(); //  xh=1&xm=yyy&
         var url = "${pageContext.request.contextPath}/showStudent2?"+data;
           $("#dataDiv").load(url);
         
     }
     
     go(1);
   </script>




