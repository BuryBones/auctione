<!DOCTYPE html>
<html lang="en">
<head>
  <style type="text/css">
    <#include "css/styles.css">
  </style>
  <title>AuctiOne - My Profile</title>
  <meta charset="UTF-8">
  <link rel="icon" href="img/Bag.png">
</head>
<body>
<nav>
  <ul class="nav-menu">
    <li class="nav-current">My Items</li>
    <li><a href="welcome">Login page</a></li>
    <li><a href="auctions">Deals<img src="img/gavel-24px.svg" alt="" title="Deals"></a></li>
    <li><a href="admin">Admin Page<img src="img/account_balance-24px.svg" alt="" title="Admin Page"></a></li>
    <li class="logout"><span class="nav-username">Test Username</span><a href="welcome?action=logout">Logout<img src="img/logout-24px.svg" alt="" title="Logout"></a></li>
  </ul>
</nav>
<main>
  <p>Here is the list of your items. <a href="new-item">Create new item</a></p>
  <br>
  <div class="table-container">
    <table class="custom-table">
      <tr>
        <th>Item#</th>
        <th>Item</th>
        <th>Info</th>
      </tr>
    <#foreach item in items>
      <tr id="${item?index}">
        <td class="row-data">${item.id}</td>
        <td class="row-data">${item.name}</td>
        <td class="row-data">
          <#if item.descript??>
          ${item.descript}
          <#else>
          No Description
        </#if>
        </td>
        <td class="button-cell"><button class="table-button" onclick="sellDialog();">Sell</button></td>
      </tr>
    </#foreach>
    </table>
  </div>
  <div class="center">
    <div class="pagination">
      <a href="#">First</a>
      <a href="#">&laquo;</a>
      <a class="active" href="#">1</a>
      <a href="#">2</a>
      <a href="#">3</a>
      <a href="#">&raquo;</a>
      <a href="#">Last</a>
    </div>
  </div>
</main>
<!-- Modal -->
<div id="sell-modal" class="modal">
  <div class="modal-content">
    <p>Create new auction for <span id="item-name"></span>.</p>
    <br>
    <p id="modal-message"></p>
    <form class="modal-input-form" onsubmit="return validateSellRequest() && sendSellRequest();">
      <input type="hidden" id="itemId" value="-1">
      <label for="init-price">Start price</label>
      <br>
      <input type="number" placeholder="Enter initial price" id="init-price" min="1" required>
      <br>
      <br>
      <label for="until">Close time</label>
      <br>
      <span id="until">
        <input type="date" id="until-date" required>
        <input type="time" id="until-time" required>
      </span>
      <br>
      <span class="modal-buttons">
        <input class="form-button" type="submit" value="Start auction">
        <button class="form-button" id="modal-close">Cancel</button>
      </span>
    </form>
  </div>
</div>
  <script type="text/javascript">
    <#include "js/sellItem.js">
    <#include "js/sendSellItemRequest.js">
    <#include "js/libs/moment.js">
  </script>
</body>
</html>