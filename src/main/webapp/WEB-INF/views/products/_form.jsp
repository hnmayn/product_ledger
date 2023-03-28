<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.AttributeConst"%>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>

<label for="${AttributeConst.PRD_NAME.getValue()}">商品名</label>
<br />
<input type="text" name="${AttributeConst.PRD_NAME.getValue()}"
    id="${AttributeConst.PRD_NAME.getValue()}" value="${product.name}"
    size="40" />
<br />
<br />

<label for="${AttributeConst.PRD_PRC.getValue()}">価格 (¥)</label>
<br />
<input type="number" name="${AttributeConst.PRD_PRC.getValue()}"
    id="${AttributeConst.PRD_PRC.getValue()}" value="${product.price}"
    size="8" min="0" max="9999999" />
<br />
<br />

<label>サイズ (幅×奥行×高さ)</label>
<br />
<input type="number" name="${AttributeConst.PRD_WIDTH.getValue()}"
    id="${AttributeConst.PRD_WIDTH.getValue()}" value="${product.width}"
    min="0" max="300" size="8" />
×
<input type="number" name="${AttributeConst.PRD_DEPTH.getValue()}"
    id="${AttributeConst.PRD_DEPTH.getValue()}" value="${product.depth}"
    min="0" max="300" size="8" />
×
<input type="number" name="${AttributeConst.PRD_HEIGHT.getValue()}"
    id="${AttributeConst.PRD_HEIGHT.getValue()}" value="${product.height}"
    min="0" max="300" size="8" />
cm
<br />
<br />

<label for="${AttributeConst.PRD_MTRL.getValue()}">素材</label>
<br />
<input type="text" name="${AttributeConst.PRD_MTRL.getValue()}"
    id="${AttributeConst.PRD_MTRL.getValue()}" value="${product.material}" />
<br />
<br />

<label for="${AttributeConst.PRD_CONTENT.getValue()}">商品説明</label>
<br />
<textarea name="${AttributeConst.PRD_CONTENT.getValue()}"
    id="${AttributeConst.PRD_CONTENT.getValue()}" rows="10" cols="60">${product.content}</textarea>
<br />
<br />

<label for="${AttributeConst.PRD_QUANTITY.getValue()}">在庫数</label>
<br />
<input type="number" name="${AttributeConst.PRD_QUANTITY.getValue()}"
    id="${AttributeConst.PRD_QUANTITY.getValue()}"
    value="${product.quantity}" size="8">
<br />
<br />
<input type="hidden" name="${AttributeConst.PRD_ID.getValue()}"
    value="${product.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}"
    value="${_token}" />
<button type="submit">登録</button>