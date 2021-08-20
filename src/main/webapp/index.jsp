<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="page" value="Home"/>
</jsp:include>

<main>
    <h1>Production of </h1>
    <ul class="overall-list">
        <jsp:include page="BPO-list-item.jsp">
            <jsp:param name="item" value="tom"/>
        </jsp:include>
    </ul>
</main>
<footer>
    footer
</footer>
</body>
</html>
