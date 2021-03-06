<!DOCTYPE html>
<html lang="en">
<head>
  <style type="text/css">
        <#include "css/styles.css">
    </style>
  <title>AuctiOne - Registration</title>
  <meta charset="UTF-8">
  <link rel="icon" href="img/Bag.png">
</head>
<body>
<nav>
  <ul class="nav-menu">
    <li class="nav-current">Registration</li>
    <li><a href="auctions">Deals<img src="img/gavel-24px.svg" alt="" title="Deals"></a></li>
  </ul>
</nav>
<main>
  <script charset="UTF-8" type="text/javascript">
    <#include "js/registrationValidation.js">
  </script>
  <form class="input-form" onsubmit="return validateRegistration()">
    <ui>
      <li class="form-row">
        <label for="login">Login</label>
        <input type="text" placeholder="Latin letters, numbers and symbols _ - . are allowed" id="login" required
               minlength="6" maxlength="45"
               pattern="(^[a-zA-Z0-9]+[a-zA-Z0-9\._-]+$)">
      </li>
      <li class="form-row">
        <label for="firstName">First name</label>
        <input type="text" placeholder="Enter Your first name" id="firstName" required minlength="1" maxlength="45"
               pattern="(^[a-zA-Z]+[- ]*[a-zA-Z]+$)">
      </li>
      <li class="form-row">
        <label for="lastName">Last name</label>
        <input type="text" placeholder="Enter Your last name" id="lastName" required minlength="1" maxlength="45"
               pattern="(^[a-zA-Z]+[- ]*[a-zA-Z]+$)">
      </li>
      <li class="form-row">
        <label for="email">E-mail</label>
        <input type="email" placeholder="Enter Your E-Mail" id="email" required minlength="3" maxlength="45"
               pattern="(^[a-zA-Z0-9]+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$)">
      </li>
      <li class="form-row">
        <label for="password">Password</label>
        <input type="password" placeholder="Latin letters, numbers and symbols ! # $ * + = - _ ^ . , are allowed"
               id="password" required minlength="8" maxlength="45"
               pattern="(^[a-zA-Z0-9!#$*+=_^.,-]+$)">
      </li>
      <li class="form-row">
        <label for="repeatPass">Repeat password</label>
        <input type="password" placeholder="Repeat password" id="repeatPass" required minlength="8" maxlength="45"
               pattern="(^[a-zA-Z0-9!#$*+=_^.,-]+$)">
      </li>
      <li>
        <input class="form-button" type="submit" value="Create new account">
      </li>
    </ui>
  </form>
  <hr>
  <p>Already have an account?</p>
  <a href="welcome">Login page</a>
</main>
<!-- Modal -->
<div id="validation-modal" class="modal">
  <div class="modal-content">
    <p><b>Registration failed!</b><br><span id="reason"></span></p>
    <button class="form-button" id="modal-close">Cancel</button>
  </div>
</div>
</body>
</html>