<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actUser" value="${ForwardConst.ACT_USER.getValue()}" />
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
        <h2>発注商品 一覧</h2>
        <h3>(在庫10点未満)</h3>
        <table id="product_list">
            <tbody>
                <tr>
                    <th class="product_name">商品名</th>
                    <th class="product_price">価格(¥)</th>
                    <th class="product_size">サイズ(cm)</th>
                    <th class="product_material">素材</th>
                    <th class="product_quantity">在庫数</th>
                    <th class="product_obsolete_flag">活動状況</th>
                    <th class="product_action">詳細</th>
                </tr>
                <c:forEach var="product" items="${products}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="product_name"><c:out value="${product.name}" /></td>
                        <td class="product_price"><c:out value="${product.price}" /></td>
                        <td class="product_size"><c:out value="${product.width}" />
                            × <c:out value="${product.depth}" /> × <c:out
                                value="${product.height}" /></td>
                        <td class="product_material"><c:out
                                value="${product.material}" /></td>
                        <td class="product_quantity"><c:out
                                value="${product.quantity}" /></td>
                        <td class="product_obsolete_flag"><c:if
                                test="${product.obsoleteFlag== AttributeConst.PRD_OBS_TRUE.getIntegerValue()}">
                                <c:out value="廃盤" />
                            </c:if> <c:if
                                test="${product.obsoleteFlag== AttributeConst.PRD_OBS_FALSE.getIntegerValue()}">
                                <c:out value="活動中" />
                            </c:if></td>
                        <td class="product_action"><a
                            href="<c:url value='?action=${actPrd}&command=${commShow}&id=${product.id}' />">表示</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${products_count} 件）<br />
            <c:forEach var="i" begin="1"
                end="${((products_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a
                            href="<c:url value='?action=${actTop}&command=${commIdx}&page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p>
            <a href="<c:url value='?action=${actPrd}&command=${commNew}' />">新規商品の登録</a>
        </p>
    </c:param>
</c:import>