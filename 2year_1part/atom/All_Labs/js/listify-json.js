$.ajax({
    url: 'http://localhost:8080/api/bears',
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
    Image =             "<li class=\"list-group-item col-sm-4 thumbnail\">\n" +
                        "    <article class=\"element\">\n" +
                        "        <div  class=\"excerpt\">\n" +  "<img src='src/1111.png' width='100%' he>" +
                        "             <p>" + "<strong class=\"title\"></strong>" +rowData.title + "</p>\n" +
                        "            <p class=\"comments news\"></p>\n" + rowData.longdescription +
                        "            <p class=\"time\"></p>\n" +rowData.shortdescription +
                        "        </div>\n" +
                        "    </article>\n" +
                        "</li>";
    row.append($(Image));
}
