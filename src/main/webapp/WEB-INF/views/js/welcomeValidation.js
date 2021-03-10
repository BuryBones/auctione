function validateWelcome() {
    return checkLogin() && checkPassword();
}

function checkLogin() {
    var login = document.getElementById("login");
    var pattern = "(^[a-zA-Z0-9]+[a-zA-Z0-9\._-]+$)";

    if (login.value.length < 6 || login.value.length > 45 || !login.value.match(pattern)) {
        showModal("Invalid login!");
        login.value = "";
        resetPassword();
        return false;
    }
    return true;
}

function checkPassword() {
    var password = document.getElementById("password");
    var pattern = "(^[a-zA-Z0-9!#$*+=_^.,-]+$)";

    if (password.value.length < 3 || password.value.length > 45 || !password.value.match(pattern)) {
        showModal("Invalid password format!");
        resetPassword();
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

function resetPassword() {
    var password = document.getElementById("password");
    password.value = "";
}