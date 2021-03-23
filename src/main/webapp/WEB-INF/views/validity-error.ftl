<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "common-macro.ftl" as common>
<@common.header title="- Error">
<@common.navigation
pageDisplayName="Error Page"
pageName="validity-error"
currentUserName="${(currentUser)!}"/>
<main>
  <div>
    <h3>An error occurred while proceeding your request!</h3>
    <p>${message}</p>
  </div>
</main>
</@common.header>