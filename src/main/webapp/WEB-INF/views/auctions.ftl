<!DOCTYPE html>
<html lang="en">
  <head>
    <style type="text/css">
            <#include "css/styles.css">
        </style>
    <title>AuctiOne - Deals</title>
    <meta charset="UTF-8">
    <link rel="icon" href="img/Bag.png">
  </head>
  <body>
  <nav>
    <ul class="nav-menu">
      <li class="nav-current">Auctions</li>
      <li><a href="welcome">Login page</a></li>
      <li><a href="items">Items<img src="img/shopping_cart-24px.svg" alt="" title="Items"></a></li>
      <li><a href="admin">Admin Page<img src="img/account_balance-24px.svg" alt="" title="Admin Page"></a></li>
      <li class="logout"><span class="nav-username">Test Username</span><a href="?action=logout">Logout<img src="img/logout-24px.svg" alt="" title="Logout"></a></li>
    </ul>
  </nav>
  <main>
    <h2>Deals</h2>
    <div class="table-container">
      <div class="deals-radio">
        <form action="auctions?show-deals">
          <b>Show deals:</b><br>
          <label for="open">Open</label>
          <input type="radio" id="open" name="show-deals" value="open" checked>
          <label for="closed">Closed</label>
          <input type="radio" id="closed" name="show-deals" value="closed">
          <label for="all">All</label>
          <input type="radio" id="all" name="show-deals" value="all">
          <input type="submit" value="Submit">
        </form>
      </div>
      <table class="custom-table" id="deals-table">
        <tr>
          <script type="text/javascript">
            <#include "js/sortString.js">
            <#include "js/sortNumber.js">
            <#include "js/sortDate.js">
          </script>
          <th onclick="sortTableString(0)">Seller</th>
          <th onclick="sortTableString(1)">Item</th>
          <th>Info</th>
          <th onclick="sortTableDate(3)">Start Date</th>
          <th onclick="sortTableNumber(4)">Start Price</th>
          <th onclick="sortTableNumber(5)">Last Bid</th>
          <th onclick="sortTableDate(6)">Stop Date</th>
          <th>Time Left</th>
        </tr>
        <#foreach deal in deals>
        <tr id="${deal?index}">
          <script type="text/javascript"><#include "js/newBid.js"></script>
          <td class="row-data">${deal.seller}</td>
          <td class="row-data">${deal.item}</td>
          <td class="row-data">${deal.info}</td>
          <td class="row-data">${deal.startDate}</td>
          <td class="row-data">${deal.startPrice?string["0.00"]}</td>
          <td class="row-data">
            <#if deal.lastBid??>
              ${deal.lastBid?string["0.00"]}
            <#else>
              0
            </#if>
          </td>
          <td class="row-data">${deal.stopDate}</td>
          <td class="row-data">[countdown]</td>
          <td class="button-cell"><button class="table-button"  onclick="newBid()">TAKE MY MONEY!</button></td>
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
  <div id="bid-modal" class="modal">
    <div class="modal-content">
      <p>Make a bid for <span id="lot-name"></span>.</p>
      <form class="modal-input-form">
        <label for="offer">Give your price</label>
        <input type="number" placeholder="Enter your price" id="offer" min="1" required>
        <br>
        <span class="modal-buttons">
                          <input class="form-button" type="submit" value="Make a bid">
                          <button class="form-button" id="modal-close">Cancel</button>
                      </span>
      </form>
    </div>
  </div>
  </body>
</html>