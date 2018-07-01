<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="fragments/components :: head"></head>
<body>
<div th:replace="fragments/layout :: header"></div>
<!-- <div th:replace="fragments/components :: carousel"></div> -->
<div class="container">
    <div id="sitterImageHolder">
        <img th:src="${sitter.image}" class="img-circle sitterImage center-block">

    </div>
    <div class="text-center">
        <h1 th:text="${sitter.name}"></h1>
    </div>
    <div th:with="percentScore=${(sitter.ratingsScore/5) * 100}" class="sitterRank center-block">
        <div class="sitterPaws">
            <div th:style="'width:'+ ${percentScore} +'%'" class="sitterPawsInner"></div>
        </div>
        <div class="center-block">
                <h3 th:inline="text" class="sitterRankLabel text-center mb50">Sitter Score of [[${#numbers.formatDecimal(sitter.ratingsScore, 1, 1)}]]</h3>
        </div>
    </div>

    <div th:each="review,index : ${reviews}" th:id="'reviewBlock' + ${index.index}" th:with="owner=${review.owner}" class="reviewBlock mb20 p20">
        <div th:id="'reviewImageHolder'+${index.index}">
            <img th:src="${owner.image}" class="img-circle width180 reviewImage">
        </div>
        <div class="ownerInfo">
            <H3 th:text="${owner.name}" class="inline-block fLeft clearfix"></H3>
            <div th:with="score=${(review.rating/5.0) * 100.0}" class="sitterPaws">
                <div th:style="'width:'+ ${score} +'%'" class="sitterPawsInner"></div>
            </div>
        </div>
        <div class="reviewDate">
            <span th:text="${#temporals.format(review.reviewDate, 'MM/dd/yyyy')}"></span>
        </div>
        <p th:text="${review.text}"></p>
    </div>

</div>
</body>
</html>