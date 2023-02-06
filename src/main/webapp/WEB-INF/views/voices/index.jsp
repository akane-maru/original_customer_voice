<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="actVoi" value="${ForwardConst.ACT_VOI.getValue()}" />

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
        <h2>お客様の声　登録サイトへようこそ</h2>
        <h3>【自分の声　一覧】</h3>
        <table id="voice_list">
            <tbody>
                <tr>
                    <th class="voice_name">氏名</th>
                    <th class="voice_date">日付</th>
                    <th class="voice_title">タイトル</th>
                    <th class="voice_action">操作</th>
                </tr>
                <c:forEach var="voice" items="${voices}" varStatus="status">
                    <fmt:parseDate value="${voice.voiceDate}" pattern="yyyy-MM-dd" var="voiceDay" type="date" />
                    <tr class="row${status.count % 2}">
                        <td class="voice_name"><c:out value="${voice.customer.name}" /></td>
                        <td class="voice_date"><fmt:formatDate value='${voiceDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="voice_title">${voice.title}</td>
                        <td class="voice_action"><a href="<c:url value='?action=${actVoi}&command=${commShow}&id=${voice.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${voices_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((voices_count - 1) / maxRow) + 1}" step="1">
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
        <p><a href="<c:url value='?action=${actVoi}&command=${commNew}' />">新規お客様の声登録</a></p>
    </c:param>
</c:import>
