<#import "common-macro.ftl" as common>
<@common.header title="${title}">
<@common.navigation
  pageDisplayName="${(pageDisplayName)!}"
  pageName="${(pageName)!}"
  currentUserName="${(currentUser)!}"/>
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