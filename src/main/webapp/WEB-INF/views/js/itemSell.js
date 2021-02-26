function sellItem() {
    var modal = document.getElementById("sell-modal");
    var closeBtn = document.getElementById("modal-close");
    var rowId = event.target.parentNode.parentNode.id;
    var data = document.getElementById(rowId).querySelectorAll(".row-data");

    document.getElementById("item-name").textContent = "\"" + data[0].innerHTML + "\"";

    document.getElementById("until-date").min =  new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];

    modal.style.display = "block";

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    closeBtn.onclick = function() {
        modal.style.display = "none";
    }
}