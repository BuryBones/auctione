<#macro header title="">
<!DOCTYPE html>
<html lang="en">
<head>
  <style type="text/css">
      <#include "css/styles.css">
    </style>
  <title>AuctiOne${title}</title>
  <meta charset="UTF-8">
  <link rel="icon" href="img/Bag.png">
</head>
<body>
  <#nested>
</body>
</html>
</#macro>

<#macro navigation pageDisplayName="AuctiOne" pageName="" currentUserName="Guest">
<nav>
  <ul class="nav-menu">
    <li class="nav-current">${pageDisplayName}</li>
    <#if pageName != 'items'>
      <li><a href="items">Items<img src="img/shopping_cart-24px.svg" alt="" title="Items"></a></li>
    </#if>
    <#if pageName != 'auctions'>
      <li><a href="auctions">Deals<img src="img/gavel-24px.svg" alt="" title="Deals"></a></li>
    </#if>
    <#if pageName != 'admin'>
      <li><a href="admin">Admin Page<img src="img/account_balance-24px.svg" alt="" title="Admin Page"></a></li>
    </#if>
    <#if pageName != 'welcome' && pageName != 'registration'>
      <li class="logout"><span class="nav-username">
        ${currentUserName}
      </span><a href="j_spring_security_logout">Logout<img src="img/logout-24px.svg" alt="" title="Logout"></a></li>
    </#if>
  </ul>
</nav>
</#macro>