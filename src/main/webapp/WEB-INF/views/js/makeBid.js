function bidDialog() {
    var modal = document.getElementById("bid-modal");
    var closeBtn = document.getElementById("modal-close");
    var rowId = event.target.parentNode.parentNode.id;
    var data = document.getElementById(rowId).querySelectorAll(".row-data");

    document.getElementById("dealId").value = data[0].innerHTML;
    document.getElementById("lot-name").textContent = "\"" + data[2].innerHTML + "\"";
    var lastBid = parseFloat(data[6].innerHTML);
    if (lastBid == 0) {
        lastBid = parseFloat(data[5].innerHTML);
    }
    document.getElementById("offer").value = lastBid + 5;
    document.getElementById("offer").min = lastBid + 5;

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