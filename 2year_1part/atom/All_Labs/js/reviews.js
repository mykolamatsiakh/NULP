// $(document).ready(function() {
//
//   $('#reg_form').bootstrapValidator({
//
//
//       feedbackIcons: {
//         valid: 'glyphicon glyphicon-ok',
//
//         validating: 'glyphicon glyphicon-refresh'
//       },
//       fields: {
//         first_name: {
//           validators: {
//             stringLength: {
//               min: 2
//             },
//             notEmpty: {
//               message: 'Будь ласка введіть своє ім\'я'
//             }
//           }
//         },
//
//         comment: {
//           validators: {
//             stringLength: {
//               min: 10,
//               max: 260,
//               message: 'Please enter at least 10 characters and no more than 200'
//             },
//             notEmpty: {
//               message: 'Будь ласка запонвіть це поле!'
//             }
//           }
//         },
//       }
//     })
//
//
//     .on('success.form.bv', function(j) {
//       $('#success_message').slideDown({
//         opacity: "show"
//       }, "slow")
//        Do something ...
//               $("li").append(li);
//          var popUpDiv = $("#content");
//          var li = document.createElement('li');
//          var rowDiv = document.createElement('div');
//          rowDiv.className = "row";
//          li.appendChild(rowDiv);
//
//          var colDiv = document.createElement('div');
//          colDiv.className = "col-sm";
//          rowDiv.appendChild(colDiv);
//
//
//          var nextDiv = document.createElement('div');
//          nextDiv.className ='testimonial testimonial-default-filled';
//          nextDiv.id='tb-testimonial';
//          nextDiv.appendChild(colDiv);
//
//          var commentDiv = document.createElement('div');
//          commentDiv.className ='testimonial-section';
//         //  commentDiv.appendChild('commentDiv');
//          commentDiv.appendChild(popUpDiv);
//
//
//
//
//       $('#reg_form').data('bootstrapValidator').resetForm();
//
//
//       e.preventDefault();
//
//
//       var $form = $(e.target);
//
//
//       var bv = $form.data('bootstrapValidator');
//
//
//       $.post($form.attr('action'), $form.serialize(), function(result) {
//         console.log(result);
//       }, 'json');
//     });
// });
//
/*
 * Date Format 1.2.3
 * (c) 2007-2009 Steven Levithan <stevenlevithan.com>
 * MIT license
 *
 * Includes enhancements by Scott Trenda <scott.trenda.net>
 * and Kris Kowal <cixar.com/~kris.kowal/>
 *
 * Accepts a date, a mask, or a date and a mask.
 * Returns a formatted version of the given date.
 * The date defaults to the current date/time.
 * The mask defaults to dateFormat.masks.default.
 */



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
