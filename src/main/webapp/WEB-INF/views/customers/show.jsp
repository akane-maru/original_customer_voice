<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commCon" value="${ForwardConst.CMD_DESTROY_CONFORM.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>${customer.name} さんのアカウント情報 詳細ページ</h2>

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


                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${customer.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${customer.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <p>
            <a href="<c:url value='?action=${actCus}&command=${commEdit}&id=${customer.id}' />">このアカウント情報を編集する</a>
        </p>

        <p>
            <a href="<c:url value='?action=${actCus}&command=${commCon}&id=${customer.id}' />">アカウント情報を削除する</a>
        </p>

        <p>
            <a href="<c:url value='?action=${actTop}&command=${commIdx}' />">トップページへ戻る</a>
        </p>

    </c:param>
</c:import>