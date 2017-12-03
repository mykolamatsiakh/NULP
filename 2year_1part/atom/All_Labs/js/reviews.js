// function send() {
//     if ($('#name').val() === "" || $('#content').val() === "") {
//         alert('Заповніть всі поля');
//         return false;
//     } else {
//         var date ;
//         var d = new Date();
//         var curr_date = d.getDate();
//         var curr_month = d.getMonth() + 1;
//         var curr_year = d.getFullYear();
//         var curr_hour = d.getHours();
//         var curr_min = d.getMinutes();
//         var curr_sec = d.getSeconds();
//         date = (curr_year + "-" + curr_month + "-" + curr_date+ "  " + curr_hour+":"+curr_min+":"+curr_sec );
//         var author = document.getElementById('name').value;
//         var text = document.getElementById('content').value;
//         var parentElem = document.getElementById('list');
//         var out = document.createElement('div');
//         out.id = 'list';
//         out.innerHTML =
//             "<div class='row'>"+
//               "<div class='col-sm'>"+
//                 "<div id='tb-testimonial' class='testimonial testimonial-default-filled'>"+
//                   "<div class='testimonial-section'>"+text+
//                   "</div><div class='testimonial-desc'>"+
//                     "<img src='https://placeholdit.imgix.net/~text?txtsize=9&txt=100%C3%97100&w=100&h=100'  />"+
//                     "<div class='testimonial-writer'>"+
//                       "<div class='testimonial-writer-name'>"+ author +"</div>"+"<p class='testimonial-writer-name'>  "+ "<span id='date'>"+date+"</span>"+"</p>"+
//                     "</div>"+
//                   "</div>"+
//                 "</div>"+
//               "</div>";
//
//
//         parentElem.appendChild(out);
//         document.getElementById('form').reset();
//     }
// }
/////////////////////////////////////////////
window.addEventListener('load', function () {
    function updateOnlineStatus(event) {
        if (isOnline()) {
            readOfflineReview();
        }
    }

    window.addEventListener('online', updateOnlineStatus);
    window.addEventListener('offline', updateOnlineStatus);
});

var i = 0;
var my_date ;
var d = new Date();
var curr_date = d.getDate();
var curr_month = d.getMonth() + 1;
var curr_year = d.getFullYear();
var curr_hour = d.getHours();
var curr_min = d.getMinutes();
var curr_sec = d.getSeconds();
my_date = (curr_year + "-" + curr_month + "-" + curr_date+ "  " + curr_hour+":"+curr_min+":"+curr_sec );

function isOnline() {
    return window.navigator.onLine;
}

function addReview() {
    if ($('#name').val() === "" || $('#text').val() === "") {
        alert('Заповніть всі поля');
        return false;
    }
    if (isOnline()) {
        var date = my_date;
        var author = document.getElementById('name').value;
        var text = document.getElementById('text').value;
        var parentElem = document.getElementById('reviews-list');
        var out = document.createElement('div');
        out.id = 'review';
        out.innerHTML =
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
    } else {
        if (useLocalStorage) {
            var date = my_date;
            var author = document.getElementById('name').value;
            var text = document.getElementById('text').value;
            i++;
            var list = [];
            list.push({"name": author, "text": text, "date": date});
            localStorage.setItem('r' + i, JSON.stringify(list));
        } else {
            var transaction = db.transaction(["reviews"], "readwrite");
            var store = transaction.objectStore("reviews");
            var review = {
                message: document.getElementById('text').value,
                author: document.getElementById('name').value,
                time: my_date
            };
            store.add(review);
            console.log("sosoososos")
        }
        document.getElementById('form').reset();
    }
}

function readOfflineReview() {
    if (useLocalStorage) {
        len = localStorage.length + 1;
        for (var k = 1; k < len; k++) {
            review = JSON.parse(localStorage.getItem('r' + k));
            var parentElem = document.getElementById('reviews-list');
            var out = document.createElement('div');
            out.id = 'review';
            out.innerHTML =
                "<div class='col-sm'>"+
                  "<div id='tb-testimonial' class='testimonial testimonial-default-filled'>"+
                    "<div class='testimonial-section'>"+review[0].text +
                    "</div><div class='testimonial-desc'>"+
                      "<img src='https://placeholdit.imgix.net/~text?txtsize=9&txt=100%C3%97100&w=100&h=100'  />"+
                      "<div class='testimonial-writer'>"+
                        "<div class='testimonial-writer-name'>"+ review[0].name  +"</div>"+"<p class='testimonial-writer-name'>  "+
                        "<span id='date'>"+review[0].date +"</span>"+"</p>"+
                      "</div>"+
                    "</div>"+
                  "</div>"+
                "</div>";
            parentElem.appendChild(out);
            localStorage.removeItem(k);
        }
    } else {
        var transaction = db.transaction(["reviews"], "readonly");
        var store = transaction.objectStore("reviews");

        store.openCursor().onsuccess = function (e) {
            var cursor = e.target.result;
            if (cursor) {
                cursor.continue();
                var parentElem = document.getElementById('reviews-list');
                var out = document.createElement('div');
                out.id = 'review';
                out.innerHTML =
                    "<div class='col-sm'>"+
                      "<div id='tb-testimonial' class='testimonial testimonial-default-filled'>"+
                        "<div class='testimonial-section'>"+cursor.value.message +
                        "</div><div class='testimonial-desc'>"+
                          "<img src='https://placeholdit.imgix.net/~text?txtsize=9&txt=100%C3%97100&w=100&h=100'  />"+
                          "<div class='testimonial-writer'>"+
                            "<div class='testimonial-writer-name'>"+ cursor.value.author  +"</div>"+"<p class='testimonial-writer-name'>  "+
                            "<span id='date'>"+cursor.value.time +"</span>"+"</p>"+
                          "</div>"+
                        "</div>"+
                      "</div>"+
                    "</div>";
                parentElem.appendChild(out);
            }
        }
    }
}
