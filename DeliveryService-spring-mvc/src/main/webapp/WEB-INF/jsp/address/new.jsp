<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="false" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="New address">
	<jsp:attribute name="body">
            <h2>New address</h2>
		<form:form method="post"
			action="${pageContext.request.contextPath}/address/create"
			modelAttribute="addressCreate" cssClass="form-horizontal">
                    
                    <div class="form-group row">
                        <div class="col-xs-12 col-md-6">
                            <label for="street" class="col-form-label">Street:</label>
                            <form:input path="street" type="text" class="form-control" id="street" name="street" required="required" placeholder="Street"/>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label for="streetNumber" class="col-form-label">House number:</label>
                            <form:input path="streetNumber" type="text" class="form-control" id="streetNumber" name="streetNumber" required="required" placeholder="House number"/>
                        </div> 
                        <div class="col-xs-12 col-md-6">
                            <label for="city" class="col-form-label">City:</label>
                            <form:input path="city" type="text" class="form-control" id="city" name="city" required="required" placeholder="City"/>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label for="postalCode" class="col-form-label">Postal Code:</label>
                            <form:input path="postalCode" type="text" class="form-control" id="postalCode" name="postalCode" required="required" placeholder="Postal code"/>
                        </div>
                    </div>
                    
                    <div class="col-xs-12 col-md-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <a class="btn btn-lg btn-default btn-block" href="${pageContext.request.contextPath}/address/list">Cancel</a>
                    </div>
                </form:form>

	</jsp:attribute>
</my:pagetemplate>