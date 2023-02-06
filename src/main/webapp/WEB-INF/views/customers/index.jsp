<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actCus" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>アカウント 情報</h2>
        <table id="customer_list">
            <tbody>
                <tr>
                    <th>アカウント番号</th>
                    <th>氏名</th>
                    <th>年齢</th>
                    <th>性別</th>
                    <th>登録状況</th>
                </tr>
                <c:forEach var="customer" items="${customers}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${customer.code}" /></td>
                        <td><c:out value="${customer.name}" /></td>
                        <td><c:out value="${customer.age}" /></td>
                        <td><c:out value="${customer.gender}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${customer.deleteFlag == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='?action=${actCus}&command=${commShow}&id=${customer.id}' />">登録中</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${customers_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((customers_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actCus}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <!--  <p><a href="<c:url value='?action=${actCus}&command=${commNew}' />">新規アカウントの登録</a></p> -->

    </c:param>
</c:import>
