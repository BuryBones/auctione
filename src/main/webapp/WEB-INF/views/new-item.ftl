<!DOCTYPE html>
<html lang="en">
<head>
  <style type="text/css">
          <#include "css/styles.css">
      </style>
  <title>AuctiOne - New Item</title>
  <meta charset="UTF-8">
  <link rel="icon" href="img/Bag.png">
</head>
<body>
<nav>
  <ul class="nav-menu">
    <li class="nav-current">New Item</li>
    <li><a href="items">Items<img src="img/shopping_cart-24px.svg" alt="" title="Items"></a></li>
    <li><a href="auctions">Deals<img src="img/gavel-24px.svg" alt="" title="Deals"></a></li>
    <li><a href="admin">Admin Page<img src="img/account_balance-24px.svg" alt="" title="Admin Page"></a></li>
    <li class="logout"><span class="nav-username">Test Username</span><a href="welcome?action=logout">Logout<img src="img/logout-24px.svg" alt="" title="Logout"></a></li>
  </ul>
</nav>
<main>
  <p>Fill the form to create new item.</p>
  <script charset="UTF-8" type="text/javascript">
    <#include "js/newItemValidation.js">
    <#include "js/newItemRequest.js">
  </script>
  <form class="input-form" onsubmit="return validateNewItem() && createItem();">
    <ui>
      <li class="form-row">
        <label for="name">Item Name</label>
        <input type="text" placeholder="Enter item name" id="name" required minlength="6" maxlength="45">
      </li>
      <li class="form-row">
        <label for="description">Description</label>
        <input type="text" placeholder="Enter description" id="description" maxlength="300">
      </li>
      <li>
        <input class="form-button" type="submit" value="Create new item">
      </li>
    </ui>
  </form>
</main>
<!-- Modal -->
<div id="validation-modal" class="modal">
  <div class="modal-content">
    <p><b>Failed to create new item!</b><br><span id="reason"></span></p>
    <button class="form-button" id="modal-close">Cancel</button>
  </div>
</div>
</body>
</html>