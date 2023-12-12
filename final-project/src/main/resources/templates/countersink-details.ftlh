<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>
            Подробная информация ${countersink.name}
        </h2>
    </div>
    <div class="card">
        <div>
            <#list listImage as img>
                <img src="/images/${img.id}" height="100px" class="card-img-top" alt="..."/><br><br>
            </#list>
        </div>
        <div class="card-body">
            <h5 class="card-title">${countersink.name}</h5>
            <p class="card-text">Диаметр: ${countersink.diameter}</p>
            <p class="card-text">Длинна: ${countersink.length}</p>
            <p class="card-text">Рабочая длинна: ${countersink.workLength}</p>
            <p class="card-text">Угол: ${countersink.angle}</p>
        </div>
    </div>
</@b.template>