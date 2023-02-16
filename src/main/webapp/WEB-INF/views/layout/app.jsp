<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="actVoi" value="${ForwardConst.ACT_VOI.getValue()}" />
<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />

<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commOut" value="${ForwardConst.CMD_LOGOUT.getValue()}" />
<c:set var="commRep" value="${ForwardConst.CMD_REPLY.getValue()}" />

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
    <title><c:out value="お客様の声　投稿サイト" /></title>
    <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1><a href="<c:url value='/?action=${actTop}&command=${commIdx}' />">お客様の声　投稿サイト</a></h1>&nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_customer != null}">

                    <c:if test="${sessionScope.login_customer.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
                        <a href="<c:url value='?action=${actCus}&command=${commRep}' />">お客様の声返信履歴</a>&nbsp;
                    </c:if>

                    <c:if test="${sessionScope.login_customer.deleteFlag == AttributeConst.DEL_FLAG_FALSE.getIntegerValue()}">

                        <a href="<c:url value='?action=${actCus}&command=${commShow}&id=${sessionScope.login_customer.id}' />">アカウント管理</a>&nbsp;

                    </c:if>
                    <a href="<c:url value='?action=${actVoi}&command=${commIdx}' />">皆様の声 一覧</a>&nbsp;
                </c:if>
            </div>
            <c:if test="${sessionScope.login_customer != null}">
                <div id="customer_name">
                    <c:out value="${sessionScope.login_customer.name}" />
                    &nbsp;さん&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value='?action=${actAuth}&command=${commOut}' />">ログアウト</a>
                </div>
            </c:if>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by Akane Nishihara.</div>
    </div>
</body>
</html>