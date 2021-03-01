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
  <td class="row-data"><span class="stopDate">${deal.stopDate}</span></td>
  <td class="row-data"><span class="countdown"></span></td>
  <#if deal.status>
    <td class="button-cell"><button class="table-button"  onclick="newBid()">TAKE MY MONEY!</button></td>
  </#if>
</tr>
</#foreach>