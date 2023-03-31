<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.AttributeConst"%>
<%@ page import="constants.ForwardConst"%>

<c:set var="actPrd" value="${ForwardConst.ACT_PRD.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>商品 一覧</h2>

        <table>
            <tbody>
                <tr>
                    <th>商品名</th>
                    <td><c:out value="${product.name}" /></td>
                </tr>
                <tr>
                    <th>価格</th>
                    <td><c:out value="${product.price}" /></td>
                </tr>
                <tr>
                    <th>サイズ</th>
                    <td class="product_size"><c:out value="${product.width}" /> ×
                        <c:out value="${product.depth}" /> × <c:out
                            value="${product.height}" /></td>
                </tr>
                <tr>
                    <th>素材</th>
                    <td><c:out value="${product.material}" /></td>
                </tr>
                <tr>
                    <th>商品説明</th>
                    <td><c:out value="${product.content}" /></td>
                </tr>
                <tr>
                    <th>在庫数</th>
                    <td><c:out value="${product.quantity}" /></td>
                </tr>
                <tr>
                    <th>活動状況</th>
                    <td><c:choose>
                            <c:when
                                test="${product.obsoleteFlag == AttributeConst.PRD_OBS_TRUE.getIntegerValue()}">廃盤</c:when>
                            <c:otherwise>通常販売</c:otherwise>
                        </c:choose></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${product.createdAt}"
                        pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value='${createDay}'
                            pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${product.updatedAt}"
                        pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value='${updateDay}'
                            pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <c:if test="${sessionScope.login_user.id == product.user.id}">
            <p>
                <a
                    href="<c:url value='?action=${actPrd}&command=${commEdt}&id=${product.id}' />">この商品を編集する</a>
            </p>
        </c:if>

        <c:if test="${sessionScope.login_user.id != product.user.id}">
            <form method="POST"
                action="<c:url value='?action=${actPrd}&command=${commGod}' />">
                <p>
                    <input type="hidden" name="${AttributeConst.REP_ID.getValue()}"
                        value="${product.id}" /> <input type="hidden"
                        name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                    <button type="submit">いいね！</button>
                </p>
            </form>
        </c:if>

        <p>
            <a href="<c:url value='?action=${actPrd}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>