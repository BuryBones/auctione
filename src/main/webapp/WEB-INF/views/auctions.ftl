<!DOCTYPE html>
<html>
	<head>
		<link href="styles.css" rel="stylesheet">
		<title>AuctiOne - Deals</title>
		<meta charset="UTF-8">
        <link rel="icon" href="Bag.png">
    </head>
    <body>
        <nav>
            <ul class="nav-menu">
                <li class="nav-current">Auctions</li>
                <li><a href="welcome">Login page</a></li>
                <li><a href="items">Items<img src="icons\shopping_cart-24px.svg" alt="" title="Items"></a></li>
                <li><a href="admin">Admin Page<img src="icons\account_balance-24px.svg" alt="" title="Admin Page"></a></li>
                <li class="logout"><span class="nav-username">Test Username</span><a href="?action=logout">Logout<img src="icons\logout-24px.svg" alt="" title="Logout"></a></li>
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
                        <script src="tableSort.js"></script>
                        <th onclick="sortTable(0)">Seller</th>
                        <th onclick="sortTable(1)">Item</th>
                        <th>Info</th>
                        <th onclick="sortTable(3)">Start Date</th>
                        <th onclick="sortTable(4)">Start Price</th>
                        <th onclick="sortTable(5)">Last Bid</th>
                        <th onclick="sortTable(6)">Stop Date</th>
                        <th>Time Left</th>
                    </tr>
                    <tr>
                        <td>Dave Davidson</td>
                        <td>Plazma-TV</td>
                        <td>long long long long long long long long long long long long long long long description</td>
                        <td>15.02.2021 13:00</td>
                        <td>1000</td>
                        <td>1100</td>
                        <td>28.02.2021 13:00</td>
                        <td>test</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>Bob</td>
                        <td>GTX 270</td>
                        <td>No description</td>
                        <td>14.02.2021 13:00</td>
                        <td>100</td>
                        <td>140</td>
                        <td>28.02.2021 13:59</td>
                        <td>test</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>14.02.2021 14:00</td>
                        <td>205</td>
                        <td>490</td>
                        <td>01.03.2021 15:49</td>
                        <td>2 days 3 hours 15 minutes 33 seconds</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>16.02.2021 01:00</td>
                        <td>20</td>
                        <td>37</td>
                        <td>26.02.2021 01:15</td>
                        <td>test</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>17.02.2021 10:00</td>
                        <td>12000</td>
                        <td>12100</td>
                        <td>27.02.2021 15:55</td>
                        <td>CLOSED</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>14.02.2021 12:00</td>
                        <td>500</td>
                        <td>505</td>
                        <td>24.02.2021 22:59</td>
                        <td>test</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>11.02.2021 11:44</td>
                        <td>870</td>
                        <td>880</td>
                        <td>25.02.2021 10:00</td>
                        <td>test</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>12.02.2021 21:10</td>
                        <td>315</td>
                        <td>340</td>
                        <td>28.02.2021 22:23</td>
                        <td>test</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>11.02.2021 07:00</td>
                        <td>49</td>
                        <td>59</td>
                        <td>03.03.2021 21:15</td>
                        <td>test</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>18.02.2021 02:05</td>
                        <td>980</td>
                        <td>990</td>
                        <td>02.03.2021 17:40</td>
                        <td>test</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>19.02.2021 09:00</td>
                        <td>420</td>
                        <td>421</td>
                        <td>01.03.2021 07:00</td>
                        <td>test</td>
                        <td class="button-cell"><button class="table-button">TAKE MY MONEY!</button></td>
                    </tr>
                    <tr>
                        <td>some seller</td>
                        <td>some item</td>
                        <td>No description</td>
                        <td>18.02.2021 23:23</td>
                        <td>120</td>
                        <td>200</td>
                        <td>08.03.2021 11:00</td>
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
        <script src="newBid.js"></script>
    </body>
</html>