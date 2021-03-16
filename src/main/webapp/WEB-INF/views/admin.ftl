<#import "common-macro.ftl" as common>
<@common.header title="${title}">
  <nav>
    <ul class="nav-menu">
      <li class="nav-current">Admin Page</li>
      <li><a href="items">Items<img src="img/shopping_cart-24px.svg" alt="" title="Items"></a></li>
      <li><a href="auctions">Deals<img src="img/gavel-24px.svg" alt="" title="Deals"></a></li>
      <li class="logout"><span class="nav-username">Test Username</span><a href="j_spring_security_logout">Logout<img src="img/logout-24px.svg" alt="" title="Logout"></a></li>
    </ul>
  </nav>
  <main>
    <p>List of users and their roles.</p>
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
          <td><#list user.roles as role>${role} </#list></td>
        </tr>
      </#list>
      </table>
    </div>
  </main>
</@common.header>