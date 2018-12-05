// prevent dbclick selection
document.addEventListener('mousedown', function (event) {
  if (event.detail > 1) {
	event.preventDefault();
  }
}, false);