<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "common-macro.ftl" as common>
<@common.header title="Admin Page">
<@common.navigation
  pageDisplayName="Admin Page"
  pageName="admin"
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
          <td class="row-data">${user.login}</td>
          <td class="row-data">${user.lastName} ${user.firstName}</td>
          <td class="row-data"><#list user.roles as role>${role} </#list></td>
        </tr>
      </#list>
      </table>
    </div>
  </main>
</@common.header>