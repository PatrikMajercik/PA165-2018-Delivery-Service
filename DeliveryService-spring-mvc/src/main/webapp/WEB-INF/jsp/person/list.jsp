<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="People">

    <jsp:attribute name="body">

	<div class="row">
            <div class="col-xs-12">
        	<div class="box">
                    <div class="box-header">
        		<my:a href="/person/new" class="btn btn-primary">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    New person
                        </my:a>
                    </div>
                    <br/>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Phone number</th>
                                    <th>E-mail</th> 
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${persons}" var="person">
                                    <tr>
                                        <td><c:out value="${person.id}"/></td>
                                        <td><c:out value="${person.name}"/></td>
                                        <td><c:out value="${person.phoneNumber}"/></td>
                                        <td><c:out value="${person.email}"/></td>
                                        <td><c:out value="${person.address}"/></td>
                                        <td><a href="${pageContext.request.contextPath}/person/edit/${person.id}" class="btn btn-sm btn-primary glyphicon glyphicon-pencil"></a></td>
                                        <td>
                                            <form method="post" action="${pageContext.request.contextPath}/person/delete/${person.id}">
                                                <button type="submit" class="btn btn-sm btn-danger glyphicon glyphicon-trash" onclick="return confirm('Are you sure you want to continue ?')"></button>
                                            </form>
                                        </td>
                                    </tr>
                                    
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
        	<!-- /.box-body -->
                </div>
        	<!-- /.box -->
            </div>
        </div>

    </jsp:attribute>


</my:pagetemplate>