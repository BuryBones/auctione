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
    <li class="logout"><span class="nav-username">Test Username</span><a href="?action=logout">Logout<img src="img/logout-24px.svg" alt="" title="Logout"></a></li>
  </ul>
</nav>
<main>
  <p>Here is the list of your items. <a href="new-item">Create new item</a></p>
  <br>
  <div class="table-container">
    <table class="custom-table">
      <tr>
        <th>Item</th>
        <th>Info</th>
      </tr>
      <tr>
        <td>Какая-нибудь машина</td>
        <td>Не бита не крашена в гонках не участвовала</td>
        <td class="button-cell"><button class="table-button">Sell</button></td>
      </tr>
      <tr>
        <td>some item with long long long long name</td>
        <td>test</td>
        <td class="button-cell"><button class="table-button">Sell</button></td>
      </tr>
      <tr>
        <td>test</td>
        <td>long long long long long long long long description test</td>
        <td class="button-cell"><button class="table-button">Sell</button></td>
      </tr>
      <tr>
        <td>test</td>
        <td>test</td>
        <td class="button-cell"><button class="table-button">Sell</button></td>
      </tr>
      <tr>
        <td>test</td>
        <td>test</td>
        <td class="button-cell"><button class="table-button">Sell</button></td>
      </tr>
      <tr>
        <td>test</td>
        <td>test</td>
        <td class="button-cell"><button class="table-button">Sell</button></td>
      </tr>
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
    <form class="modal-input-form">
      <label for="init-price">Start price</label>
      <br>
      <input type="number" placeholder="Enter initial price" id="init-price" min="1" required>
      <br>
      <br>
      <label for="interval">Close time</label>
      <br>
      <input type="date" id="interval" required>
      <input type="time" id="interval" required>
      <br>
      <span class="modal-buttons">
                        <input class="form-button" type="submit" value="Start auction">
                        <button class="form-button" id="modal-close">Cancel</button>
                    </span>
    </form>
  </div>
</div>
<script type="text/javascript">
            <#include "js/itemSell.js">
        </script>
</body>
</html>