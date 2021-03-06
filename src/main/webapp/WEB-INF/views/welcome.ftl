<!DOCTYPE html>
<html lang="en">
<head>
  <style type="text/css">
          <#include "css/styles.css">
      </style>
  <title>AuctiOne - Welcome Page</title>
  <meta charset="UTF-8">
  <link rel="icon" href="img/Bag.png">
</head>
<body>
<nav>
  <ul class="nav-menu">
    <li class="nav-current">Welcome to AuctiOne!</li>
    <li><a href="items">Items<img src="img/shopping_cart-24px.svg" alt="" title="Items"></a></li>
    <li><a href="auctions">Deals<img src="img/gavel-24px.svg" alt="" title="Deals"></a></li>
  </ul>
</nav>
<main>
  <p>To participate in online-auctions you need to log in.</p>
  <script charset="UTF-8" type="text/javascript">
    <#include "js/welcomeValidation.js">
  </script>
  <form class="input-form" onsubmit="return validateWelcome()">
    <ui>
      <li class="form-row">
        <label for="login">Login</label>
        <input type="text" placeholder="Enter login" id="login" required
               minlength="6" maxlength="45"
               pattern="(^[a-zA-Z0-9]+[a-zA-Z0-9\._-]+$)">
      </li>
      <li class="form-row">
        <label for="password">Password</label>
        <input type="password" placeholder="Enter password" id="password" required
               minlength="8" maxlength="45"
               pattern="(^[a-zA-Z0-9!#$*+=_^.,-]+$)">
      </li>
      <li>
        <input class="form-button" type="submit" value="Log in">
      </li>
    </ui>
  </form>
  <p>You may see the auctions list without registration, however.</p>
  <hr>
  <p>Do not have an account?</p>
  <a href="registration">Create account</a>
</main>
<!-- Modal -->
<div id="validation-modal" class="modal">
  <div class="modal-content">
    <p><b>Login failed!</b><br><span id="reason"></span></p>
    <button class="form-button" id="modal-close">Cancel</button>
  </div>
</div>
</body>
</html>
