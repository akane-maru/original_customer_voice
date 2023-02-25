<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />

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
        <h2>今までに投稿されたお客様の声への返信</h2>
        <h3>【声への返信　一覧】</h3>
        <table id="reply_list">
            <tbody>
                <tr>
                    <th class="reply_name">氏名</th>
                    <th class="reply_date">日付</th>
                    <th class="reply_age">年齢</th>
                    <th class="reply_gender">性別</th>
                    <th class="reply_title">タイトル</th>
                    <th class="reply_action">操作</th>
                </tr>
                <c:forEach var="reply" items="${reply}" varStatus="status">
                    <fmt:parseDate value="${reply.replyDate}" pattern="yyyy-MM-dd" var="replyDay" type="date" />
                    <tr class="row${status.count % 2}">
                        <td class="reply_name"><c:out value="${reply.customer.name}" /></td>
                        <td class="reply_date"><fmt:formatDate value='${replyDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="reply_age"><c:out value="${reply.customer.age}" /></td>
                        <td class="reply_gender"><c:out value="${reply.customer.gender}" /></td>
                        <td class="reply_title">${reply.title}</td>
                        <td class="reply_action"><a href="<c:url value='?action=${actRep}&command=${commShow}&id=${reply.id}' />">内容を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${replys_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((replys_count - 1) / maxRow) + 1}" step="1">
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
    </c:param>
</c:import>
