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
        <form>
          <b>Show deals:</b><br>
          <label for="open">Open</label>
          <input type="radio" id="open" name="show-deals" value="open" checked>
          <label for="closed">Closed</label>
          <input type="radio" id="closed" name="show-deals" value="closed">
          <label for="all">All</label>
          <input type="radio" id="all" name="show-deals" value="all">
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
        <tr>
          <td>Dave Davidson</td>
          <td>Plazma-TV</td>
          <td>long long long long long long long long long long long long long long long description</td>
          <td><span class="t-date">2021-02-15</span><br><span class="t-time">13:00</span></td>
          <td>1000</td>
          <td>1100</td>
          <td><span class="t-date">2021-02-28</span><br><span class="t-time">13:00</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>Bob</td>
          <td>GTX 270</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-14</span><br><span class="t-time">13:00</span></td>
          <td>100</td>
          <td>140</td>
          <td><span class="t-date">2021-02-28</span><br><span class="t-time">13:59</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2020-02-14</span><br><span class="t-time">14:00</span></td>
          <td>205</td>
          <td>490</td>
          <td><span class="t-date">2020-03-01</span><br><span class="t-time">15:49</span></td>
          <td>2 days 3 hours 15 minutes 33 seconds</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-16</span><br><span class="t-time">01:00</span></td>
          <td>20</td>
          <td>37</td>
          <td><span class="t-date">2022-02-26</span><br><span class="t-time">01:15</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-17</span><br><span class="t-time">10:00</span></td>
          <td>12000</td>
          <td>12100</td>
          <td><span class="t-date">2021-02-27</span><br><span class="t-time">15:55</span></td>
          <td>CLOSED</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-14</span><br><span class="t-time">12:00</span></td>
          <td>500</td>
          <td>505</td>
          <td><span class="t-date">2021-02-24</span><br><span class="t-time">22:59</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-11</span><br><span class="t-time">11:44</span></td>
          <td>870</td>
          <td>880</td>
          <td><span class="t-date">2021-02-25</span><br><span class="t-time">10:00</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-12</span><br><span class="t-time">21:10</span></td>
          <td>315</td>
          <td>340</td>
          <td><span class="t-date">2021-02-28</span><br><span class="t-time">22:23</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-11</span><br><span class="t-time">07:00</span></td>
          <td>49</td>
          <td>59</td>
          <td><span class="t-date">2021-03-03</span><br><span class="t-time">21:15</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-18</span><br><span class="t-time">02:05</span></td>
          <td>980</td>
          <td>990</td>
          <td><span class="t-date">2021-03-02</span><br><span class="t-time">17:40</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-19</span><br><span class="t-time">09:00</span></td>
          <td>420</td>
          <td>421</td>
          <td><span class="t-date">2021-03-01</span><br><span class="t-time">07:00</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
        </tr>
        <tr>
          <td>some seller</td>
          <td>some item</td>
          <td>No description</td>
          <td><span class="t-date">2021-02-18</span><br><span class="t-time">23:23</span></td>
          <td>120</td>
          <td>200</td>
          <td><span class="t-date">2021-03-08</span><br><span class="t-time">11:00</span></td>
          <td>test</td>
          <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
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
  <script type="text/javascript">
              <#include "js/newBid.js">
          </script>
  </body>
</html>