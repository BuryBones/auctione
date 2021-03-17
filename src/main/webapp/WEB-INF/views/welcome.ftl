<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "common-macro.ftl" as common>
<@common.header title="${title}">
<@common.navigation
  pageDisplayName="${(pageDisplayName)!}"
  pageName="${(pageName)!}"
  currentUserName="${(currentUser)!}"/>
  <main>
    <p>To participate in online-auctions you need to log in.</p>
    <script charset="UTF-8" type="text/javascript">
      <#include "js/welcomeValidation.js">
    </script>
    <form class="input-form" id="login-form" action="j_spring_security_check" onsubmit="return validateWelcome();" method="post">
      <#if response??>
        <p class="error-response">${response}</p>
      </#if>
      <ui>
        <li class="form-row">
          <label for="login">Login</label>
          <input type="text" placeholder="Enter login" id="login" name="login" required
                 minlength="6" maxlength="45"
                 pattern="(^[a-zA-Z0-9]+[a-zA-Z0-9\._-]+$)">
        </li>
        <li class="form-row">
          <label for="password">Password</label>
          <input type="password" placeholder="Enter password" id="password" name="password" required
                 minlength="8" maxlength="45"
                 pattern="(^[a-zA-Z0-9!#$*+=_^.,-]+$)">
        </li>
        <li>
          <input class="form-button" type="submit" value="Log in">
        </li>
      </ui>
    </form>
    <p>You may see the auctions list without registration, however.</p>
    <hr>
    <p>Do not have an account?</p>
    <a href="registration">Create account</a>
  </main>
  <!-- Modal -->
  <div id="validation-modal" class="modal">
    <div class="modal-content">
      <p><b>Login failed!</b><br><span id="reason"></span></p>
      <button class="form-button" id="modal-close">Cancel</button>
    </div>
  </div>
</@common.header>