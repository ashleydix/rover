<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <link href="../../../resources/css/bootstrap.min.css" rel="stylesheet" media="screen" th:href="@{/resources/css/bootstrap.min.css}"/>
</head>
<body>

<!-- Header-->
<nav class="navbar navbar-inverse navbar-fixed-top" th:fragment="header">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">
                <img class="logo-img" src="/resources/images/logo.png" />
            </a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav pull-right">
                <li th:classappend="${module == 'home' ? 'active' : ''}">
                    <a href="#" th:href="@{/}">Home</a>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<!-- Footer -->
<div th:fragment="footer" th:align="center">
    &copy;&nbsp;<span th:text="${#temporals.format(#temporals.createNow(), 'yyyy')}">2017</span>
    Demo project <span th:text="${@environment.getProperty('app.version')}"></span>
</div>

</body>
</html>