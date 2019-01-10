<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="false" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="New article">
	<jsp:attribute name="body">
            <h2>New article</h2>
		<form:form method="post"
			action="${pageContext.request.contextPath}/article/create"
			modelAttribute="articleCreate" cssClass="form-horizontal">
                    
                    <div class="form-group row">
                        <div class="col-xs-12 col-md-6">
                            <label for="name" class="col-form-label">Name:</label>
                            <form:input path="name" type="text" class="form-control" id="name" name="name" required="required" placeholder="Name"/>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label for="weight" class="col-form-label">Weight:</label>
                            <form:input path="weight"  class="form-control" id="weight" name="weight" required="required" placeholder="Weight"/>
                        </div>                        
                    </div>
                    
                    <div class="col-xs-12 col-md-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <a class="btn btn-lg btn-default btn-block" href="${pageContext.request.contextPath}/article/list">Cancel</a>
                    </div>
                </form:form>

	</jsp:attribute>
</my:pagetemplate>