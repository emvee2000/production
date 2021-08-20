<li class="overall-item">
    <details>
        <summary>${param.item}</summary>
        <div class="input-fields">
            <div>
                <label class="label" for="${param.item}-ME">ME</label>
                <label class="label-number">0</label>
                <input id="${param.item}-ME" class="input slider ME" type=range min="0" max="5" value="0">
            </div>
            <div>
                <label class="label" for="${param.item}-TE">TE</label>
                <label class="label-number">0</label>
                <input id="${param.item}-TE" class="input slider TE" type=range min="0" max="5" value="0">
            </div>
            <div>
                <label class="label" for="${param.item}-runs">Runs</label>
                <input class="input number runs" type="number" id="${param.item}-runs" name="${param.item}-runs"
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
            <jsp:include page="BPO-list-item-table-item.jsp">
                <jsp:param name="item" value="RAM"/>
                <jsp:param name="product" value="${param.item}"/>
            </jsp:include>
            <jsp:include page="BPO-list-item-table-item.jsp">
                <jsp:param name="item" value="Food"/>
                <jsp:param name="product" value="${param.item}"/>
            </jsp:include>
        </table>
    </details>
</li>