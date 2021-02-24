var modal = document.getElementById("bid-modal");
var buttons = document.getElementsByClassName("table-button");
var closeBtn = document.getElementById("modal-close");
// var item = document.getElementsByName();

var f = function() {
    modal.style.display = "block";
} 

for (var i = 0; i < buttons.length; i++) {
    buttons[i].onclick = f;
}

closeBtn.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}