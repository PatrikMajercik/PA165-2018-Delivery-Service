<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
         trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Deliveries">

    <jsp:attribute name="body">

	<div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
        		<my:a href="/delivery/new" class="btn btn-primary">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    New delivery
                        </my:a>
                </div>
                <br/>
                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Order date</th>
                            <th>Delivery date</th>
                            <th>Price</th>
                            <th>Customer</th>
                            <th>Courier</th>
                            <th>Articles</th>
                            <th>State</th>
                            <th>Ordered</th>
                            <th>Delivered</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${deliveries}" var="delivery">
                                    <tr>
                                        <td><c:out value="${delivery.id}"/></td>
                                        <td><c:out value="${delivery.ordered}"/></td>
                                        <td><c:out value="${delivery.delivered}"/></td>
                                        <td><c:out value="${delivery.price}"/></td>
                                        <td><c:out value="${delivery.customer.name}"/></td>
                                        <td><c:out value="${delivery.courier.name}"/></td>
                                        <td><c:out value="${delivery.articles}"/></td>
                                        <td><c:out value="${delivery.deliveryState}"/></td>
                                        <td><c:out value="${delivery.ordered.toString()}"/></td>
                                        <td><c:out value="${delivery.delivered.toString()}"/></td>
                                        <td>
                                         <a href="${pageContext.request.contextPath}/delivery/edit/${delivery.id}/" class="btn btn-primary">
                                            Edit
                                         </a>
                                        </td>
                                        <td>
                                            <form method="post"
                                                  action="${pageContext.request.contextPath}/delivery/delete/${delivery.id}">
                                                <button type="submit"
                                                        class="btn btn-sm btn-danger glyphicon glyphicon-trash"
                                                        onclick="return confirm('Are you sure you want to continue ?')"></button>
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