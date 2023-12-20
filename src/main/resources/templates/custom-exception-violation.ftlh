<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <#list exception as e>
            <p class="card-text">Ошибка в заполнении формы: ${e.getMessage()}</p>
        </#list>
    </div>
</@b.template>