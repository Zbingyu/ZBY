<%@page pageEncoding="utf-8"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>

<form>
     学号:<input type="text"  readonly="readonly"  name="xh" id="xh"  value="${stu.xh }"/>  <br>
     玉照:<img src="${pageContext.request.contextPath }/images/"/>
     
</form>