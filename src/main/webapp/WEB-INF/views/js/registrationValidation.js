function validateRegistration() {
    return checkLogin() && checkFirstName() && checkLastName() && checkEmail() && checkPassword() && checkPasswordsMatch();
}

function checkLogin() {
    var login = document.getElementById("login");
    var pattern = "(^[a-zA-Z0-9]+[a-zA-Z0-9\._-]+$)";

    if (login.value.length < 6 || login.value.length > 45 || !login.value.match(pattern)) {
        showModal("Invalid login!");
        login.value = "";
        resetPasswords();
        return false;
    }
    return true;
}

function checkFirstName() {
    var firstName = document.getElementById("firstName");
    var pattern = "(^[a-zA-Z]+[- ]*[a-zA-Z]+$)";

    if (firstName.value.length < 1 || firstName.value.length > 45 || !firstName.value.match(pattern)) {
        showModal("Invalid first name!");
        firstName.value = "";
        resetPasswords();
        return false;
    }
    return true;    
}

function checkLastName() {
    var lastName = document.getElementById("lastName");
    var pattern = "(^[a-zA-Z]+[- ]*[a-zA-Z]+$)";

    if (lastName.value.length < 1 || lastName.value.length > 45 || !lastName.value.match(pattern)) {
        showModal("Invalid last name!");
        lastName.value = "";
        resetPasswords();
        return false;
    }
    return true;  
}

function checkEmail() {
    var email = document.getElementById("email");
    var pattern = "(^[a-zA-Z0-9]+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$)";

    if (email.value.length < 3 || email.value.length > 45 || !email.value.match(pattern)) {
        showModal("Invalid email!");
        email.value = "";
        resetPasswords();
        return false;
    }
    return true;
}

function checkPassword() {
    var password = document.getElementById("password");
    var pattern = "(^[a-zA-Z0-9!#$*+_^.,-]+$)";

    if (password.value.length < 3 || password.value.length > 45 || !password.value.match(pattern)) {
        showModal("Invalid password format!");
        resetPasswords();
        return false;
    }
    return true;
}

function checkPasswordsMatch() {
    var password = document.getElementById("password");
    var repeatPassword = document.getElementById("repeatPass");

    if (password.value === repeatPassword.value) {
        return true;
    } else {
        showModal("Passwords do not match!");
        resetPasswords();
        return false;
    }
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

function resetPasswords() {
    var password = document.getElementById("password");
    var repeatPassword = document.getElementById("repeatPass");
    password.value = "";
    repeatPassword.value = "";
}