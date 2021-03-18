<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "common-macro.ftl" as common>
<@common.header title="${title}">
<@common.navigation
  pageDisplayName="${(pageDisplayName)!}"
  pageName="${(pageName)!}"
  currentUserName="${(currentUser)!}"/>
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
          <td class="button-cell">
            <#if item.getOnSale()>
              <button class="table-button" disabled>On Sale</button>
            <#else>
              <button class="table-button" onclick="sellDialog();">Sell</button>
            </#if>
          </td>
        </tr>
      </#foreach>
      </table>
    </div>
  </main>
  <!-- Modal -->
  <div id="sell-modal" class="modal">
    <div class="modal-content">
      <p>Create new auction for <span id="item-name"></span>.</p>
      <br>
      <p id="modal-message"></p>
      <form class="modal-input-form" action="items/sell" onsubmit="return validateSellRequest();" method="post">
        <input type="hidden" id="itemId" name="itemId">
        <label for="init-price">Start price</label>
        <br>
        <input type="number" step="0.01" name="initPrice" placeholder="Enter initial price" id="init-price" min="1" required>
        <br>
        <br>
        <label for="until">Close time</label>
        <br>
        <span id="until">
          <input type="date" name="stopDate" id="until-date" required>
          <input type="time" name="stopTime" id="until-time" required>
        </span>
        <br>
        <span>
          The minimal time gap between opening and closing of an auction is 1 hour.
        </span>
        <br>
        <span class="modal-buttons">
          <input class="form-button" type="submit" value="Start auction">
          <button class="form-button" id="modal-close">Cancel</button>
        </span>
      </form>
    </div>
  </div>
  <script charset="UTF-8" type="text/javascript">
    <#include "js/sellItem.js">
    <#include "js/libs/moment.js">
  </script>
</@common.header>