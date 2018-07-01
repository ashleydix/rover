<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <link href="../../../resources/css/bootstrap.min.css" rel="stylesheet" media="screen" th:href="@{/resources/css/bootstrap.min.css}"/>
    <script src="../../../resources/js/bootstrap.min.js" th:src="@{/resources/js/bootstrap.min.js}"></script>
</head>
<body>
<!-- Reusable alert -->
<div th:fragment="alert (type, message)" class="alert alert-dismissable" th:classappend="'alert-' + ${type}">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
    <span th:text="${message}">Test</span>
</div>
<!-- filter modal -->
<div th:fragment="modal (id, title)" class="modal fade" th:id="${id}" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalLabel" th:text="${title}">My modal</h4>
            </div>
            <div class="modal-body" th:id="${id + '-body'}">
                <div class="btn-group-vertical mr-2 selectRatings" role="group" aria-label="First group">
                    <button type="button" data-id="5" class="btn btn-secondary active" data-dismiss="modal">5 star sitters</button>
                    <button type="button" data-id="4" class="btn btn-secondary active" data-dismiss="modal">4+ star sitters </button>
                    <button type="button" data-id="3" class="btn btn-secondary active" data-dismiss="modal">3+ star sitters</button>
                    <button type="button" data-id="2" class="btn btn-secondary active" data-dismiss="modal">2+ star sitters</button>
                    <button type="button" data-id="1" class="btn btn-secondary active" data-dismiss="modal">1+ star sitters</button>
                    <button type="button" data-id="0" class="btn btn-secondary active" data-dismiss="modal">See all sitters</button>
                </div>

            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div> -->
        </div>
    </div>
</div>


<!-- Head -->
<head th:fragment="head">
    <title>Rover - Sitters</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="../../../resources/css/bootstrap.min.css" rel="stylesheet" media="screen" th:href="@{/resources/css/bootstrap.min.css}"/>
    <link href="../../../resources/css/core.css" rel="stylesheet" media="screen" th:href="@{/resources/css/core.css}" />
    <script src="../../../resources/js/jquery.min.js" th:src="@{/resources/js/jquery.min.js}"></script>
    <script src="../../../resources/js/bootstrap.min.js" th:src="@{/resources/js/bootstrap.min.js}"></script>
    <script src="../../../resources/js/util.js" th:src="@{/resources/js/util.js}"></script>
    <link href="https://fonts.googleapis.com/css?family=Abril+Fatface|Montserrat" rel="stylesheet">
</head>

<!-- Carousel -->
<div th:fragment="carousel"  id="carousel" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="item active">
            <div class="background-carousel" style="background-image:url('/resources/images/Moby1.JPG');"></div>
            <img class="d-block w-100 active" src="/resources/images/Moby1.JPG" alt="First slide">
        </div>
        <div class="item">
            <div class="background-carousel" style="background-image:url('/resources/images/Moby2.jpg');"></div>
            <img class="d-block w-100" src="/resources/images/Moby2.jpg" alt="Second slide">
        </div>
        <div class="item">
            <div class="background-carousel" style="background-image:url('/resources/images/Minnie.JPG');"></div>
            <img class="d-block w-100" src="/resources/images/Minnie.JPG" alt="Third slide">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

</body>
</html>
