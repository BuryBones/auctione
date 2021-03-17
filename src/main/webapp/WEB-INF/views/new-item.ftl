<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "common-macro.ftl" as common>
<@common.header title="${title}">
<@common.navigation
  pageDisplayName="${(pageDisplayName)!}"
  pageName="${(pageName)!}"
  currentUserName="${(currentUser)!}"/>
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
</@common.header>