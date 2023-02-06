<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<fmt:parseDate value="${voice.voiceDate}" pattern="yyyy-MM-dd" var="voiceDay" type="date" />
<label for="${AttributeConst.VOI_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.VOI_DATE.getValue()}" id="${AttributeConst.VOI_DATE.getValue()}" value="<fmt:formatDate value='${voiceDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label>氏名</label><br />
<c:out value="${sessionScope.login_customer.name}" />
<br /><br />

<label>年齢</label><br />
<c:out value="${sessionScope.login_customer.age}" />
<br /><br />

<label>性別</label><br />
<c:out value="${sessionScope.login_customer.gender}" />
<br /><br />

<label for="${AttributeConst.VOI_TITLE.getValue()}">タイトル</label><br />
<input type="text" name="${AttributeConst.VOI_TITLE.getValue()}" id="${AttributeConst.VOI_TITLE.getValue()}" value="${voice.title}" />
<br /><br />

<label for="${AttributeConst.VOI_CONTENT.getValue()}">内容</label><br />
<textarea  name="${AttributeConst.VOI_CONTENT.getValue()}" id="${AttributeConst.VOI_CONTENT.getValue()}" rows="10" cols="50">${voice.content}</textarea>
<br /><br />
<input type="hidden" name="${AttributeConst.VOI_ID.getValue()}" value="${voice.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>