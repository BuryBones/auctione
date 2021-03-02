function validateNewItem() {
    return checkItemName() && checkDescription();
}

function checkItemName() {
    var name = document.getElementById("name");

    if (name.value.length < 6 || name.value.length > 45) {
        showModal("Invalid item name length!");
        return false;
    }
    return true;
}

function checkDescription() {
    var description = document.getElementById("description");

    if (description.value.length > 300) {
        showModal("Description is too long! Max long is 300 symbols.");
        return false;
    }
    return true;
}

function showModal(message) {
    var modal = document.getElementById("validation-modal");
    var closeBtn = document.getElementById("modal-close");
    var reason = document.getElementById("reason");

    reason.textContent = message;

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