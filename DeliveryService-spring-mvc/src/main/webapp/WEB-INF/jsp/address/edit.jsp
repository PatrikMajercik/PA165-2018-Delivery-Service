<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="false" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Edit address">
	<jsp:attribute name="body">
            <h2>Edit address</h2>
		<form:form method="post"
			action="${pageContext.request.contextPath}/address/save/${address.id}"
			modelAttribute="address" cssClass="form-horizontal">
                    
                    <div class="form-group row">
                        <div class="col-xs-12">
                            ID <c:out value="${address.id}"/>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label class="col-form-label">Street:</label>
                            <form:input path="street" type="text" class="form-control" required="required" value="${address.street}"/>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label for="streetNumber" class="col-form-label">House number:</label>
                            <form:input path="streetNumber" type="text" class="form-control" id="streetNumber" name="streetNumber" required="required" value="${address.streetNumber}"/>
                        </div> 
                        <div class="col-xs-12 col-md-6">
                            <label for="city" class="col-form-label">City:</label>
                            <form:input path="city" type="text" class="form-control" id="city" name="city" required="required" value="${address.city}"/>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label for="postalCode" class="col-form-label">Postal code:</label>
                            <form:input path="postalCode" type="text" class="form-control" id="postalCode" name="postalCode" required="required" value="${address.postalCode}"/>
                        </div>
                    </div>
                    
                    <div class="col-xs-12 col-md-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Save changes</button>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <a class="btn btn-lg btn-default btn-block" href="${pageContext.request.contextPath}/address/list">Cancel</a>
                    </div>
                </form:form>

	</jsp:attribute>
</my:pagetemplate>
