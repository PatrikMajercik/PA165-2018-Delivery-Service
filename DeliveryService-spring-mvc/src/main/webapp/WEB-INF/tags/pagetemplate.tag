<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
  <title><c:out value="${title}"/></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <jsp:invoke fragment="head"/>
  <style>

    /* Remove the navbar's default margin-bottom and rounded borders */
    .navbar {
      border-radius: 0;
    }

    /* Add a gray background color, some padding to the footer and position */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
      position: absolute;
      bottom: 0px;
      left: 0px;
      width:100%;
    }

    /* because of footer */
    body {
      min-height: 100vh;
    }

    /* table text align */
    table {
      text-align: left;
    }
  </style>
</head>
<body>

    
<!--

    NAVBAR

-->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
            <li><my:a href="/" class="navbar-brand"><i class="fas fa-home"></i> Delivery Service</my:a></li>
            <li><my:a href="/delivery/list" class="navbar-brand"><i class="fas fa-truck"></i> Deliveries</my:a></li>
            <li><my:a href="/person/list" class="navbar-brand"><i class="fas fa-user"></i> People</my:a></li>
            <li><my:a href="/article/list" class="navbar-brand"><i class="fas fa-gift"></i> Articles</my:a></li>
            <li><my:a href="/address/list" class="navbar-brand"><i class="fas fa-map-marked"></i> Addresses</my:a></li>

      </ul>
      <ul class="nav navbar-nav navbar-right">

          <li>
            <a href="${pageContext.request.contextPath}/auth/login">Login</a>
          </li>

      </ul>
    </div>
  </div>
</nav>
  

<!-- 
    ALERTS
-->
<c:if test="${not empty alert_danger}">
    <div class="alert alert-danger fade in" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <c:out value="${alert_danger}"/>
    </div>
</c:if>
<c:if test="${not empty alert_info}">
    <div class="alert alert-info fade in" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <c:out value="${alert_info}"/>
    </div>
</c:if>
<c:if test="${not empty alert_success}">
    <div class="alert alert-success fade in" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <c:out value="${alert_success}"/>
    </div>
</c:if>
<c:if test="${not empty alert_warning}">
    <div class="alert alert-warning fade in" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <c:out value="${alert_warning}"/>
    </div>
</c:if>


<div class="container bg-3 text-center">
  <jsp:invoke fragment="body"/>   
</div>


</body>
</html>
