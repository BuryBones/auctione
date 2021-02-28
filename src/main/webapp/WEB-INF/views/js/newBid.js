function newBid() {
    var modal = document.getElementById("bid-modal");
    var closeBtn = document.getElementById("modal-close");
    var rowId = event.target.parentNode.parentNode.id;
    var data = document.getElementById(rowId).querySelectorAll(".row-data");

    document.getElementById("lot-name").textContent = "\"" + data[1].innerHTML + "\"";
    var lastBid = parseFloat(data[5].innerHTML);
    if (lastBid == 0) {
        lastBid = parseFloat(data[4].innerHTML);
    }
    document.getElementById("offer").value = lastBid + 1;
    document.getElementById("offer").min = lastBid + 1;

    modal.style.display = "block";

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    closeBtn.addEventListener("click",function(ev) {
            ev.preventDefault();
    });

    closeBtn.onclick = function() {
        modal.style.display = "none";
    }
}