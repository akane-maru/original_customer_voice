<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="actVoi" value="${ForwardConst.ACT_VOI.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>お客様の声への返信 詳細ページ</h2>


<h3>お客様の声 詳細</h3>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${voice.customer.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${voice.voiceDate}" pattern="yyyy-MM-dd" var="voiceDay" type="date" />
                    <td><fmt:formatDate value='${voiceDay}' pattern='yyyy-MM-dd' /></td>
                </tr>

                <tr>
                    <th>タイトル</th>
                    <td><pre><c:out value="${voice.title}" /></pre></td>
                </tr>

                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${voice.content}" /></pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${voice.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>

            </tbody>
        </table>


<br /><br />

<h3>声への返信 詳細</h3>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${reply.customer.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${reply.replyDate}" pattern="yyyy-MM-dd" var="replyDay" type="date" />
                    <td><fmt:formatDate value='${replyDay}' pattern='yyyy-MM-dd' /></td>
                </tr>

                <tr>
                    <th>タイトル</th>
                    <td><pre><c:out value="${reply.title}" /></pre></td>
                </tr>

                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${reply.content}" /></pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${reply.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>

            </tbody>
        </table>


<br /><br />

<h3>お客様の貴重なお声を投稿していただき、誠にありがとうございました。</h3>

        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>