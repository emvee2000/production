<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
    <h1>Production of ${MainItemName}</h1>
    <ul class="overall-list">
        <c:forEach var="bpo" items="${bpoList}">
            <li class="overall-item">
                <details>
                    <summary id="${bpo.productName}">${bpo.productName}</summary>
                    <div class="input-fields">
                        <div>
                            <label class="label" for="${bpo.productName}-ME">ME</label>
                            <label class="label-number">0</label>
                            <input id="${bpo.productName}-ME" class="input slider ME" type=range min="0" max="5"
                                   value="0">
                        </div>
                        <div>
                            <label class="label" for="${bpo.productName}-TE">TE</label>
                            <label class="label-number">0</label>
                            <input id="${bpo.productName}-TE" class="input slider TE" type=range min="0" max="5"
                                   value="0">
                        </div>
                        <div>
                            <label class="label" for="${bpo.productName}-runs">Runs</label>
                            <input class="input number runs" type="number" id="${bpo.productName}-runs"
                                   name="${bpo.productName}-runs"
                                   value="1" min="1">
                        </div>
                    </div>
                    <table class="BPO-table">
                        <tr class="BPO-table-header">
                            <th>Name</th>
                            <th>Amount/run</th>
                            <th>Amount</th>
                            <th>Price/run</th>
                            <th>Price</th>
                            <th>Done</th>
                        </tr>
                        <c:forEach var="map" items="${bpo.materials}">
                            <tr class="BPO-table-data">
                                <td><a href="#${map.key.name}">${map.key.name}</a></td>
                                <td>${map.value}</td>
                                <td class="multiply-${bpo.productName}-runs">${map.value}</td>
                                <td>${map.key.basePrice}</td>
                                <td class="multiply-${bpo.productName}-runs">${map.key.basePrice}</td>
                                <td><label>
                                    <input type="checkbox">
                                </label></td>
                            </tr>
                        </c:forEach>
                    </table>
                </details>
            </li>
        </c:forEach>
    </ul>
</main>
<footer>
    footer
</footer>
</body>
</html>
