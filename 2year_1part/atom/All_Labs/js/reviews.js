function send() {
    if ($('#name').val() === "" || $('#content').val() === "") {
        alert('Заповніть всі поля');
        return false;
    } else {
        var date ;
        var d = new Date();
        var curr_date = d.getDate();
        var curr_month = d.getMonth() + 1;
        var curr_year = d.getFullYear();
        var curr_hour = d.getHours();
        var curr_min = d.getMinutes();
        var curr_sec = d.getSeconds();
        date = (curr_year + "-" + curr_month + "-" + curr_date+ "  " + curr_hour+":"+curr_min+":"+curr_sec );
        var author = document.getElementById('name').value;
        var text = document.getElementById('content').value;
        var parentElem = document.getElementById('list');
        var out = document.createElement('div');
        out.id = 'list';
        out.innerHTML =
            "<div class='row'>"+
              "<div class='col-sm'>"+
                "<div id='tb-testimonial' class='testimonial testimonial-default-filled'>"+
                  "<div class='testimonial-section'>"+text+
                  "</div><div class='testimonial-desc'>"+
                    "<img src='https://placeholdit.imgix.net/~text?txtsize=9&txt=100%C3%97100&w=100&h=100'  />"+
                    "<div class='testimonial-writer'>"+
                      "<div class='testimonial-writer-name'>"+ author +"</div>"+"<p class='testimonial-writer-name'>  "+ "<span id='date'>"+date+"</span>"+"</p>"+
                    "</div>"+
                  "</div>"+
                "</div>"+
              "</div>";


        parentElem.appendChild(out);
        document.getElementById('form').reset();
    }
}
