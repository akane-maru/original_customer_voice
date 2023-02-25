<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>


<c:set var="actVoi" value="${ForwardConst.ACT_VOI.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>お客様の声 詳細ページ</h2>

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
        <h3>お客様の貴重なお声を投稿していただき、誠にありがとうございます。</h3>
        <h3>カスタマーサポートからこの声への返信が登録されるまで、今しばらくお待ちください。</h3>
        <h3>返信が登録されると【<a href="<c:url value='?action=${actRep}&command=${commIdx}' />">声への返信　一覧</a>&nbsp;】に表示されますので、お手数ですがそちらからご確認ください。</h3>

<!--  <br /><br />
        <h3>お客様の声への返信 内容</h3>


        <table>
            <tbody>-->


                    <!--<fmt:parseDate value="${reply.replyDate}" pattern="yyyy-MM-dd" var="replyDay" type="date" />
                    <tr class="row${status.count % 2}">-->

<!--
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
-->

        <br /><br />
        <p>
            <a href="<c:url value='?action=${actVoi}&command=${commIdx}' />">一覧に戻る</a>
        </p>




<c:if test="${sessionScope.login_customer.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
        <br /><br />
        <h3>【返信が未登録の場合】お客様の声への返信内容を記入・登録してください。</h3>
        <br />
        <c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
        <form method="POST" action="<c:url value='?action=${actRep}&command=${commCrt}' />">
        <fmt:parseDate value="${reply.replyDate}" pattern="yyyy-MM-dd" var="replyDay" type="date" />
        <label for="${AttributeConst.REP_DATE.getValue()}">日付</label><br />
        <input type="date" name="${AttributeConst.REP_DATE.getValue()}" id="${AttributeConst.REP_DATE.getValue()}" value="<fmt:formatDate value='${replyDay}' pattern='yyyy-MM-dd' />" />
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



            <label for="${AttributeConst.REP_TITLE.getValue()}">タイトル</label><br />
            <input type="text" name="${AttributeConst.REP_TITLE.getValue()}" id="${AttributeConst.REP_TITLE.getValue()}" value="${reply.title}" />
            <br /><br />
            <label for="${AttributeConst.REP_CONTENT.getValue()}">お客様の声への返信内容</label><br />
            <textarea  name="${AttributeConst.REP_CONTENT.getValue()}" id="${AttributeConst.REP_CONTENT.getValue()}" rows="10" cols="50">${reply.content}</textarea>
                <br /><br />
                <input type="hidden" name="${AttributeConst.VOI_ID.getValue()}" value="${voice.id}" />
                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />

                <button type="submit">投稿</button>
        </form>
</c:if>


    </c:param>
</c:import>