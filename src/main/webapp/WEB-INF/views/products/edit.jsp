<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actPrd" value="${ForwardConst.ACT_PRD.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>商品情報 編集ページ</h2>
        <form method="POST" action="<c:url value='?action=${actPrd}&command=${commUpd}' />">
            <c:import url="_form.jsp" />
        </form>

        <p>
            <a href="#" onclick="confirmDestroy();">この商品を廃盤にする</a>
        </p>
        <form method="POST"
            action="<c:url value='?action=${action}&command=${commDel}' />">
            <input type="hidden" name="${AttributeConst.PRD_ID.getValue()}" value="${product.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        </form>
        <script>
        function confirmDestroy(){
               if (confirm("本当に廃盤にしますか？")){
                   document.forms[1].submit();
               }
        }
        </script>

        <p>
            <a href="<c:url value='?action=Product&command=index' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>