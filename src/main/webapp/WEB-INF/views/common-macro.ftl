<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
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
    <@security.authorize access="isAuthenticated()">
      <#if pageName != 'items' && pageName != 'welcome' && pageName != 'registration'>
        <li><a href="items">Items<img src="img/shopping_cart-24px.svg" alt="" title="Items"></a></li>
      </#if>
    </@security.authorize>
    <#if pageName != 'auctions'>
      <li><a href="auctions">Deals<img src="img/gavel-24px.svg" alt="" title="Deals"></a></li>
    </#if>
    <@security.authorize access="hasRole('ADMIN')">
      <#if pageName != 'admin' && pageName != 'welcome' && pageName != 'registration'>
        <li><a href="admin">Admin Page<img src="img/account_balance-24px.svg" alt="" title="Admin Page"></a></li>
      </#if>
    </@security.authorize>
    <#if pageName != 'welcome' && pageName != 'registration'>
      <li class="logout"><span class="nav-username">
        ${currentUserName}
      </span>
      <@security.authorize access="isAuthenticated()">
        <a href="j_spring_security_logout">Logout<img src="img/logout-24px.svg" alt="" title="Logout"></a></li>
      </@security.authorize>
      <@security.authorize access="!isAuthenticated()">
        <a href="welcome">Log in<img src="img/login-24px.svg" alt="" title="Log in"></a></li>
      </@security.authorize>
    </#if>
  </ul>
</nav>
</#macro>

<#macro dealtable dealsList>
  <#foreach deal in deals>
    <tr id="${deal?index}">
      <td class="row-data">${deal.id}</td>
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
      <td class="row-data"><span class="stopDate">${deal.stopDate?string("yyyy-MM-dd HH:mm:ss")}</span></td>
      <td class="row-data"><span class="countdown"></span></td>
      <#if deal.status>
        <@security.authorize access="isAuthenticated()">
          <td class="button-cell"><button class="table-button"  onclick="bidDialog()">MAKE A BID</button></td>
        </@security.authorize>
      </#if>
    </tr>
  </#foreach>
</#macro>