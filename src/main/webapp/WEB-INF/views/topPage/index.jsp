<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actEmp" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="actPrd" value="${ForwardConst.ACT_PRD.getValue()}" />

<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>商品情報管理システム</h2>
        <h3>商品情報 一覧</h3>
        <table id="product_list">
            <tbody>
                <tr>
                    <th class="product_name">商品名</th>
                    <th class="product_price">価格</th>
                    <th class="product_size">サイズ</th>
                    <th class="product_material">素材</th>
                    <th class="product_content">商品説明</th>
                    <th class="product_quantity">在庫数</th>
                    <th class="product_action">操作</th>
                </tr>
                <c:forEach var="report" items="${products}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="product_name"><c:out value="${product.name}" /></td>
                        <td class="product_price"><c:out value="${product.price}" /></td>
                        <td class="product_size"><c:out value="${product.size}" /></td>
                        <td class="product_material"><c:out value="${product.material}" /></td>
                        <td class="product_content"><c:out value="${product.content}" /></td>
                        <td class="product_quantity"><c:out value="${product.quantity}" /></td>
                        <td class="product_date"><fmt:formatDate value='${productDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="product_action"><a href="<c:url value='?action=${actPrd}&command=${commShow}&id=${product.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${products_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((products_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actTop}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actPrd}&command=${commNew}' />">新規商品の登録</a></p>
    </c:param>
</c:import>