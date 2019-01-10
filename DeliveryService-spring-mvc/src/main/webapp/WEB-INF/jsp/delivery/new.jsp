<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
         trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New delivery">
	<jsp:attribute name="body">
            <h2>New delivery</h2>
		<form:form method="post"
                   action="${pageContext.request.contextPath}/delivery/create"
                   modelAttribute="deliveryCreate" cssClass="form-horizontal">

                    <div class="form-group">
                        <div class="form-group ${name_error?'has-error':''}">
                        <form:label path="customerId" cssClass="col-sm-2 control-label">Customer</form:label>
                        <div class="col-sm-10">
                            <form:select path="customerId" cssClass="form-control">
                                <c:forEach items="${persons}" var="c">
                                    <form:option value="${c.id}">${c.name}</form:option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="customerId" cssClass="error"/>
                            <p class="help-block"><form:errors path="customerId" cssClass="error"/></p>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-group ${name_error?'has-error':''}">
                        <form:label path="articleID" cssClass="col-sm-2 control-label">Articles</form:label>
                        <div class="col-sm-10">
                            <form:select path="articleID" cssClass="form-control">
                                <c:forEach items="${articles}" var="c">
                                    <form:option value="${c.id}">${c.name}</form:option>
                                </c:forEach>
                            </form:select>
                            <form:errors path="articleID" cssClass="error"/>
                            <p class="help-block"><form:errors path="customerId" cssClass="error"/></p>
                        </div>
                    </div>
                        <div class="form-group ${name_error?'has-error':''}">
                        <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
                        <div class="col-sm-10">
                            <form:input path="price" type="number" cssClass="form-control" required="required"/>
                            <form:errors path="price" cssClass="help-block"/>
                        </div>
                            <form:errors path="price" cssClass="error"/>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<form:label path="deliveryState" cssClass="col-sm-2 control-label">Delivery state</form:label>--%>
                        <%--<div class="col-sm-10">--%>
                            <%--<form:select path="deliveryState" cssClass="form-control">--%>
                                <%--<c:forEach items="${states}" var="c">--%>
                                    <%--<form:option value="${c}">${c}</form:option>--%>
                                <%--</c:forEach>--%>
                            <%--</form:select>--%>
                            <%--<form:errors path="deliveryState" cssClass="error"/>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="col-xs-12 col-md-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <a class="btn btn-lg btn-default btn-block"
                           href="${pageContext.request.contextPath}/delivery/list">Cancel</a>
                    </div>
                </form:form>

</jsp:attribute>
</my:pagetemplate>