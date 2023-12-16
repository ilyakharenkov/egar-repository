<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <p class="card-text">Исключение NullPointerException: ${exception}</p>
    </div>
</@b.template>