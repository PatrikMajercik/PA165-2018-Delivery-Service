<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="false" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="New person">
	<jsp:attribute name="body">
            <h2>New person</h2>
		<form:form method="post"
			action="${pageContext.request.contextPath}/person/create"
			modelAttribute="personCreate" cssClass="form-horizontal">
                    
                    <div class="form-group row">
                        <div class="col-xs-12 col-md-6">
                            <label for="name" class="col-form-label">Name:</label>
                            <form:input path="name" type="text" class="form-control" id="name" name="name" required="required" placeholder="name"/>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label for="phoneNumber" class="col-form-label">Phone number:</label>
                            <form:input path="phoneNumber" type="text" class="form-control" id="phoneNumber" name="phoneNumber" required="required" placeholder="Phone number"/>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label for="email" class="col-form-label">E-mail:</label>
                            <form:input path="email" type="email" class="form-control" id="email" name="email" required="required" placeholder="E-mail"/>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label for="addresses" class="col-form-label">Address:</label>
                            <form:select id="addresses" path="addressId" class="form-control">
                                <form:option value="" label="--- Select Address ---"/>
                                <c:forEach items="${addresses}" var="address">
                                    <form:option type="text" value="${address.id}">${address.street}, ${address.streetNumber},${address.postalCode}, ${address.city}</form:option>
                                </c:forEach>
                            </form:select>                            
                        </div>
                    </div>
                    
                    <div class="col-xs-12 col-md-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <a class="btn btn-lg btn-default btn-block" href="${pageContext.request.contextPath}/person/list">Cancel</a>
                    </div>
                </form:form>

	</jsp:attribute>
</my:pagetemplate>