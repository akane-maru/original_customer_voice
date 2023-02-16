<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="command" value="${ForwardConst.CMD_LOGIN.getValue()}" />


<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<label for="${AttributeConst.CUS_CODE.getValue()}">アカウント番号</label><br />
<input type="text" name="${AttributeConst.CUS_CODE.getValue()}"  id="${AttributeConst.CUS_CODE.getValue()}" value="${customer.code}" />
<br /><br />

<label for="${AttributeConst.CUS_NAME.getValue()}">氏名</label><br />
<input type="text" name="${AttributeConst.CUS_NAME.getValue()}"  id="${AttributeConst.CUS_NAME.getValue()}" value="${customer.name}" />
<br /><br />

<label for="${AttributeConst.CUS_AGE.getValue()}">年齢</label><br />
<input type="text" name="${AttributeConst.CUS_AGE.getValue()}"  id="${AttributeConst.CUS_AGE.getValue()}" value="${customer.age}" />
<br /><br />

<label for="${AttributeConst.CUS_GENDER.getValue()}">性別</label><br />
<input type="text" name="${AttributeConst.CUS_GENDER.getValue()}"  id="${AttributeConst.CUS_GENDER.getValue()}" value="${customer.gender}" />
<br /><br />



<label for="${AttributeConst.CUS_PASS.getValue()}">パスワード</label><br />
<input type="password" name="${AttributeConst.CUS_PASS.getValue()}" id="${AttributeConst.CUS_PASS.getValue()}" />
<br /><br />


<input type="hidden" name="${AttributeConst.CUS_ID.getValue()}" value="${customer.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">登録</button>
