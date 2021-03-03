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
      <li class="logout"><span class="nav-username">Test Username</span><a href="welcome?action=logout">Logout<img src="img/logout-24px.svg" alt="" title="Logout"></a></li>
    </ul>
  </nav>
  <main>
    <h2>Deals</h2>
    <div class="table-container">
      <div class="deals-radio">
        <form onchange="refresh()">
          <script type="text/javascript">
            <#include "js/refreshAuctions.js">
            <#include "js/setOrder.js">
          </script>
          <b>Show deals:</b><br>
          <label for="open">Open</label>
          <input type="radio" class="show-radio" id="open" name="showDeals" value="open" checked>
          <label for="closed">Closed</label>
          <input type="radio" class="show-radio" id="closed" name="showDeals" value="closed">
          <label for="all">All</label>
          <input type="radio" class="show-radio" id="all" name="showDeals" value="all">
          <input type="hidden" id="sortBy" name="sortBy" value="stopDate">
          <input type="hidden" id="sortMode" name="sortMode" value="asc">
        </form>
      </div>
      <table class="custom-table" id="deals-table">
        <thead>
          <tr>
            <th onclick="setSortValue('seller');refresh()">Seller</th>
            <th onclick="setSortValue('item');refresh()">Item</th>
            <th>Info</th>
            <th onclick="setSortValue('startDate');refresh()">Start Date</th>
            <th onclick="setSortValue('startPrice');refresh()">Start Price</th>
            <th onclick="setSortValue('lastBid');refresh()">Last Bid</th>
            <th onclick="setSortValue('stopDate');refresh()">Stop Date</th>
            <th>Time Left</th>
          </tr>
        </thead>
        <tbody id="table-body">
        <#foreach deal in deals>
          <tr id="${deal?index}">
            <script type="text/javascript"><#include "js/makeBid.js"></script>
            <td class="row-data">${deal.seller}</td>
            <td class="row-data">${deal.item}</td>
            <td class="row-data">${deal.info}</td>
            <td class="row-data">${deal.startDate?datetime?string("yyyy-MM-dd HH:mm:ss")}</td>
            <td class="row-data">${deal.startPrice?string["0.00"]}</td>
            <td class="row-data">
              <#if deal.lastBid??>
              ${deal.lastBid?string["0.00"]}
              <#else>
              0
            </#if>
            </td>
            <td class="row-data"><span class="stopDate">${deal.stopDate?datetime?string("yyyy-MM-dd HH:mm:ss")}</span></td>
            <td class="row-data"><span class="countdown"></span></td>
            <#if deal.status>
              <td class="button-cell"><button class="table-button"  onclick="bidDialog();">MAKE A BID</button></td>
            </#if>
          </tr>
        </#foreach>
        </tbody>
      <script type="text/javascript">
          <#include "js/libs/moment.js">
          <#include "js/countdown.js">
      </script>
      </table>
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