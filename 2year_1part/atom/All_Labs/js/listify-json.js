$.ajax({
    url: 'http://localhost:8000/notes',
    type: "get",
    dataType: "json",

    success: function(data) {
        drawTable(data);
    }
});

function drawTable(data) {
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i]);
    }
}

function drawRow(rowData) {
    var row = $("<article>")
    $("#personDataTable").append(row);
    Image = "<div class=\"img\"><img src=\"img/iot.png\" alt=\"альтернативный текст\" width=\"469.56px\" height=\"150px\"></div>"
    Addition = "<a class=\"read-more\" href=\"#\">read more</a>"
    row.append($(Image));
    row.append($("<h4>" + rowData.title + "</h4>"));
    row.append($("<span>" + rowData.text + "</span>"));
    row.append($(Addition));
}
