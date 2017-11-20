var i = 0;
var now = new Date();

function isOnline() {
  return window.navigator.onLine;
}

function addLocal() {
  var date ;
  var d = new Date();
  var curr_date = d.getDate();
  var curr_month = d.getMonth() + 1;
  var curr_year = d.getFullYear();
  var curr_hour = d.getHours();
  var curr_min = d.getMinutes();
  var curr_sec = d.getSeconds();
  date = ( curr_hour+":"+curr_min+":"+curr_sec + "  " + curr_year + "-" + curr_month + "-" + curr_date);
  i++;
  var list = [];
  list.push({
    "name": $('#name').val(),
    "comments": $('#comments').val(),
    "time": date
  });
  localStorage.setItem(i, JSON.stringify(list));
  $('#name').val('');
  $('#comments').val('');
}

function AddNews() {
  if (($('#name').val() === "") || ($('#comments').val() === "")) {
    alert('Заповніть всі дані');
    return false;
  }

  if (isOnline()) {
    addLocal();
    $('#name').val('');
    $('#comments').val('');
    alert('Новину успішно додано');
  } else {
    addLocal();
  }
}
