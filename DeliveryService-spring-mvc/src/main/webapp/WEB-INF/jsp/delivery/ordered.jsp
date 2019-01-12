<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
         trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Order delivery">
	<jsp:attribute name="body">
            <h2>Select courier</h2>
		<form:form method="post"
                   action="${pageContext.request.contextPath}/delivery/ordered/"
                   modelAttribute="person" cssClass="form-horizontal">


                    <div class="form-group">
                        <form:label path="id" cssClass="col-sm-2 control-label">Courier</form:label>
                        <div class="col-sm-10">
                            <form:select path="id" cssClass="form-control">
                                <c:forEach items="${persons}" var="c">
                                    <form:option value="${c.id}">${c.name}</form:option>
                                </c:forEach>
                                </form:select>
                        </div>
                    </div>
                        <div class="col-xs-12 col-md-6">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
                        </div>
                </form:form>
</jsp:attribute>
</my:pagetemplate>