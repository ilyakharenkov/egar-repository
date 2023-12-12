<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>
            Подробная информация ${alignment.name}
        </h2>
    </div>
    <div class="card">
        <div>
            <#list listImage as img>
                <img src="/images/${img.id}" height="100px" class="card-img-top" alt="..."/><br><br>
            </#list>
        </div>
        <div class="card-body">
            <h5 class="card-title">${alignment.name}</h5>
            <p class="card-text">Диаметр: ${alignment.diameter}</p>
            <p class="card-text">Длинна: ${alignment.length}</p>
            <p class="card-text">Рабочая длинна: ${alignment.workLength}</p>
            <p class="card-text">Угол: ${alignment.angle}</p>
        </div>
    </div>
</@b.template>