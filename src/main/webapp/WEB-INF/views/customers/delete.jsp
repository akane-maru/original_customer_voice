<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>


<c:set var="action" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>アカウント情報　削除ページ</h2>


        <h3>${customer.name} さんのアカウント情報</h3>

        <table>
            <tbody>
                <tr>
                    <th>アカウント番号</th>
                    <td><c:out value="${customer.code}" /></td>
                </tr>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${customer.name}" /></td>
                </tr>

                <tr>
                    <th>年齢</th>
                    <td><c:out value="${customer.age}" /></td>
                </tr>

                <tr>
                    <th>性別</th>
                    <td><c:out value="${customer.gender}" /></td>
                </tr>
            </tbody>
        </table>

        <p>
            <a href="#" onclick="confirmDestroy();">このアカウント情報を削除する</a>
        </p>
        <form method="POST"
            action="<c:url value='?action=${action}&command=${commDel}' />">
            <input type="hidden" name="${AttributeConst.CUS_ID.getValue()}" value="${customer.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        </form>
        <script>
            function confirmDestroy() {
                if (confirm("本当に削除してよろしいですか？")) {
                    document.forms[1].submit();
                }
            }
        </script>

        <p>
            <a href="<c:url value='?action=${actTop}&command=${commIdx}' />">トップページへ戻る</a>
        </p>
    </c:param>
</c:import>