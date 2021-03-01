  <table class="custom-table" id="deals-table">
    <tr>
      <script type="text/javascript">
            <#include "js/libs/moment.js">
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
