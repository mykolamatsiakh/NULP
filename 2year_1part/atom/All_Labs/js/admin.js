// var i = 0;
// var now = new Date();
//
// function isOnline() {
//   return window.navigator.onLine;
// }
//
// function addLocal() {
//   var date ;
//   var d = new Date();
//   var curr_date = d.getDate();
//   var curr_month = d.getMonth() + 1;
//   var curr_year = d.getFullYear();
//   var curr_hour = d.getHours();
//   var curr_min = d.getMinutes();
//   var curr_sec = d.getSeconds();
//   date = ( curr_hour+":"+curr_min+":"+curr_sec + "  " + curr_year + "-" + curr_month + "-" + curr_date);
//   i++;
//   var list = [];
//   list.push({
//     "name": $('#name').val(),
//     "comments": $('#comments').val(),
//     "time": date
//   });
//   localStorage.setItem(i, JSON.stringify(list));
//   $('#name').val('');
//   $('#comments').val('');
// }
//
// function AddNews() {
//   if (($('#name').val() === "") || ($('#comments').val() === "")) {
//     alert('Заповніть всі дані');
//     return false;
//   }
//
//   if (isOnline()) {
//     addLocal();
//     $('#name').val('');
//     $('#comments').val('');
//     alert('Новину успішно додано');
//   } else {
//     addLocal();
//   }
// }

//////////////////////////////////////////////////////////////////
var i = 0;
var my_date ;
  var d = new Date();
  var curr_date = d.getDate();
  var curr_month = d.getMonth() + 1;
  var curr_year = d.getFullYear();
  var curr_hour = d.getHours();
  var curr_min = d.getMinutes();
  var curr_sec = d.getSeconds();
  my_date = ( curr_hour+":"+curr_min+":"+curr_sec + "  " + curr_year + "-" + curr_month + "-" + curr_date);

function isOnline() {
    return window.navigator.onLine;
}

function addNews() {
    if ($('#news-name').val() === "" || $('#news-text').val() === "" || $('#news-img').val() === "") {
        alert('Заповніть всі поля');
        return false;
    }
    if (isOnline()) {
        alert('Новина успішно надіслана.');
            var name = document.getElementById('news-name').value;
            var text = document.getElementById('news-text').value;
            imgData = target.src;
            i++;
            var list = [];
            list.push({"name": (name), "text": (text)});
            localStorage.setItem('n' + i, JSON.stringify(list));
            localStorage.setItem('i' + i, (imgData));
        }
        else {
            var transaction = db.transaction(["news"], "readwrite");
            var store = transaction.objectStore("news");
            var news1 = {
                name: document.getElementById('news-name').value,
                text: document.getElementById('news-text').value,
                img: target.src
            };
            store.add(news1);
        }
        document.getElementById('news-form').reset();
        document.getElementById('news-img-form').reset();
        target.src = 'img/add_image.png';
    }


function showImage(src, target) {
    var fr = new FileReader();
    // when image is loaded, set the src of the image where you want to display it
    fr.onload = function (e) {
        target.src = this.result;
    };
    src.addEventListener("change", function () {
        // fill fr with image data
        fr.readAsDataURL(src.files[0]);
    });
}

var src = document.getElementById("news-img");
var target = document.getElementById("target");
showImage(src, target);
