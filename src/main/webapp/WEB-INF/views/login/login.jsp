<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_AUTH.getValue()}" />
<c:set var="command" value="${ForwardConst.CMD_LOGIN.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${loginError}">
            <div id="flush_error">
                アカウント番号かパスワードが間違っています。
            </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>ログイン</h2>
        <form method="POST" action="<c:url value='/?action=${action}&command=${command}' />">
            <label for="${AttributeConst.CUS_CODE.getValue()}">アカウント番号</label><br />
            <input type="text" name="${AttributeConst.CUS_CODE.getValue()}" id="${AttributeConst.CUS_CODE.getValue()}" value="${code}" />
            <br /><br />

            <label for="${AttributeConst.CUS_PASS.getValue()}">パスワード</label><br />
            <input type="password" name="${AttributeConst.CUS_PASS.getValue()}" id="${AttributeConst.CUS_PASS.getValue()}" />
            <br /><br />

            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <button type="submit">ログイン</button>
        </form>

        <p>
            <a href="<c:url value='/?action=${actCus}&command=${commNew}' />">新規アカウントを作成する</a>
        </p>

    </c:param>
</c:import>