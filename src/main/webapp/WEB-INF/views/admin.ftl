<!DOCTYPE html>
<html lang="en">
  <head>
    <style type="text/css">
      <#include "css/styles.css">
    </style>
    <title>AuctiOne - Admin Page</title>
    <meta charset="UTF-8">
    <link rel="icon" href="img/Bag.png">
  </head>
  <body>
    <nav>
      <ul class="nav-menu">
        <li class="nav-current">Admin Page</li>
        <li><a href="items">Items<img src="img/shopping_cart-24px.svg" alt="" title="Items"></a></li>
        <li><a href="auctions">Deals<img src="img/gavel-24px.svg" alt="" title="Deals"></a></li>
        <li class="logout"><span class="nav-username">Test Username</span><a href="?action=logout">Logout<img src="img/logout-24px.svg" alt="" title="Logout"></a></li>
      </ul>
    </nav>
    <main>
      <p>Probably there we would have a list of users</p>
      <div class="table-container">
        <table class="custom-table">
          <tr>
            <th>Login</th>
            <th>Full Name</th>
            <th>Roles</th>
          </tr>
          <#list users as user>
          <tr>
            <td>${user.login}</td>
            <td>${user.lastName} ${user.firstName}</td>
            <td><#list user.userRoles as role>${role.roleName} </#list></td>
          </tr>
        </#list>
        </table>
      </div>
    </main>
  </body>
</html>