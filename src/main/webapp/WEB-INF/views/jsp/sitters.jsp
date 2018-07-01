<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="fragments/components :: head"></head>
<body>
<div th:replace="fragments/layout :: header"></div>
<div th:replace="fragments/components :: carousel"></div>
<div class="container">
    <div class="text-center">
        <h1>Meet Our Sitters</h1>
        <button type="button" id="filter" class="btn btn-primary">Filter</button>
    </div>
    <div id="sitterInfos">
        <div th:each="sitter,index : ${sitters}" th:id="'sitterInfo' + ${index.index}" th:attr="data-rating=${sitter.ratingsScore}" class="sitterInfo">
            <a th:href="'/review/'+${sitter.id}">
                <img th:src="${sitter.image}" class="sitterImage img-circle"/>
                <h3 th:text="${sitter.name}" class="sitterName"></h3>
            </a>

            <div th:with="percentScore=${(sitter.ratingsScore/5) * 100}" class="sitterRank">
                <h5 th:inline="text">
                    Sitter score of [[${#numbers.formatDecimal(sitter.ratingsScore, 1, 1)}]]
                </h5>
                <!--<h5 th:inline="text">
                    sitter Rank of [[${#numbers.formatDecimal(sitter.sitterRank, 1, 1)}]]
                </h5>-->
                <div class="sitterPaws">
                    <div th:style="'width:'+ ${percentScore} +'%'" class="sitterPawsInner"></div>
                </div>
            </div>
        </div>
    </div>
    <div th:replace="fragments/layout :: footer">&copy; 2016 The Static Templates</div>
</div>

<script th:inline="javascript" type="text/javascript">
    $(document).ready(function () {
        $('#filter').on('click', function () {
            $("#filter-modal").modal();
            $("[data-id]").mouseover(function(){
                var element = $(this);
                var maxVal = element.data('id');

                $("[data-id]").filter(function() {
                    return parseInt($(this).data('id')) >= maxVal;
                }).addClass("passive");

            }).mouseout(function(){
                $("[data-id]").removeClass("passive");
            });

            $("[data-id]").click(function(){
                var element = $(this);
                var maxVal = element.data('id');
                $("[data-id]").removeClass("active");
                element.addClass("active");

                $("[data-id]").filter(function() {
                    return parseInt($(this).data('id')) >= maxVal;
                }).addClass("active");

                $("[data-rating]").filter(function() {
                    $(this).removeClass("hidden");
                    return parseInt($(this).data('rating')) < maxVal;
                }).addClass("hidden");

            })


        })
    });
</script>
<!-- Signup modal -->
<div th:replace="fragments/components :: modal(id='filter-modal', title='Filter')"></div>
</body>
</html>