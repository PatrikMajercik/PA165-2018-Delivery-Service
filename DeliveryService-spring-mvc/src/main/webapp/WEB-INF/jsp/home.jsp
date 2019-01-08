<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:pagetemplate>
    <jsp:attribute name="body">

    <h1>Delivery Service</h1>
            <c:choose>
                <c:when test="${empty loggedUser}">
                    <p class="lead"></p>
                    <p align="right">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/login" role="button">
                            Sign in
                        </a>
                    </p>

                </c:when>
            </c:choose>

    </jsp:attribute>
</own:pagetemplate>