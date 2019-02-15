// prevent dbclick selection
document.addEventListener('mousedown', function (event) {
  if (event.detail > 1) {
	event.preventDefault();
  }
}, false);

// csrf token
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
 
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token);
});	