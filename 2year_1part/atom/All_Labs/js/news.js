// window.addEventListener('load', function() {
//   var status = document.getElementById("status");
//   var log = document.getElementById("log");
//   var condition;
//
//   function updateOnlineStatus(event) {
//     condition = navigator.onLine ? "online" : "offline";
//
//     status.className = condition;
//     status.innerHTML = condition.toUpperCase();
//   }
//   window.addEventListener('online', updateOnlineStatus);
//   window.addEventListener('offline', updateOnlineStatus);
// });
// var status;
// setInterval(function() {
//   status.innerHTML = navigator.onLine ? 'online' : 'offline';
//   if (navigator.onLine) {
//     ReadOflineNews();
//   }
// }, 250);
//
// function ReadOflineNews() {
//   len = localStorage.length + 1;
//
//   for (var k = 1; k < len; k++) {
//
//     news = JSON.parse(localStorage.getItem(k));
//     if (news[0].name != null) {
//       $("#news_list").prepend("<li class=\"list-group-item col-sm-4 thumbnail\">\n" +
//         "    <article class=\"element\">\n" +
//         "        <div  class=\"excerpt\">\n" +
//         "             <p>" + "<strong class=\"title\"></strong>" + "</p>\n" +
//         "            <p class=\"comments news\"></p>\n" +
//         "            <p class=\"time\"></p>\n" +
//         "        </div>\n" +
//         "    </article>\n" +
//         "</li>");
//
//       $('#news_list li:first .title').append(news[0].name);
//       $('#news_list li:first .comments').append(news[0].comments);
//       $('#news_list li:first .time').append(news[0].time);
//
//       localStorage.removeItem(k);
//     }
//   }
// }

/////////////////////////////////////////////////////////
window.addEventListener('load', function () {
    function updateOnlineStatus(event) {
        if (isOnline()) {
            readOfflineNews();
        }
    }

    window.addEventListener('online', updateOnlineStatus);
    window.addEventListener('offline', updateOnlineStatus);
});

function isOnline() {
    return window.navigator.onLine;
}

function readOfflineNews() {
    if (useLocalStorage) {
        len = localStorage.length + 1;
        for (var k = 1; k < len; k++) {
            news = JSON.parse(localStorage.getItem('n' + k));
            var img = localStorage.getItem('i' + k);
            var parentElem = document.getElementById('news-list');
            var out = document.createElement('div');
            out.id = 'news';
            out.innerHTML =
            "<li class=\"list-group-item col-sm-4 thumbnail\">\n" +
                    "    <article class=\"element\">\n" +
                    "        <div  class=\"excerpt\">\n" +  "<img src='" + img + "' width='100%'>" +
                    "             <p>" + "<strong class=\"title\"></strong>" +news[0].name + "</p>\n" +
                    "            <p class=\"comments news\"></p>\n" + news[0].text +
                    "            <p class=\"time\"></p>\n" +
                    "        </div>\n" +
                    "    </article>\n" +
                    "</li>";
            parentElem.appendChild(out);
            localStorage.removeItem(k);
        }
    } else {
        var transaction = db.transaction(["news"], "readonly");
        var store = transaction.objectStore("news");
        store.openCursor().onsuccess = function (e) {
            var cursor = e.target.result;
            if (cursor) {
                cursor.continue();
                var parentElem = document.getElementById('news-list');
                var out = document.createElement('div');
                out.id = 'news';
                out.innerHTML =
                "<li class=\"list-group-item col-sm-4 thumbnail\">\n" +
                        "    <article class=\"element\">\n" +
                        "        <div  class=\"excerpt\">\n" +  "<img src='" +cursor.value.img + "' width='100%'>" +
                        "             <p>" + "<strong class=\"title\"></strong>" +cursor.value.name+ "</p>\n" +
                        "            <p class=\"comments news\"></p>\n" + cursor.value.text +
                        "            <p class=\"time\"></p>\n" +
                        "        </div>\n" +
                        "    </article>\n" +
                        "</li>";
                parentElem.appendChild(out);
            }
        }
    }
}
