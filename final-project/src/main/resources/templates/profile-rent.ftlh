<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>Ваша аренда инструмента</h2>
    </div>
    <div class="card-list">
        <#list rentList as rent>
            <div>
                <#if rent.alignment?has_content>
                    <div class="card">
                        <div class="card-body">
                            <p class="card-title">Имя инструмента: ${rent.alignment.name}</p>
                            <p class="card-title">Диаметр: ${rent.alignment.diameter}</p>
                            <p class="card-title">Длинна: ${rent.alignment.length}</p>
                            <p class="card-title">Рабочая длинна: ${rent.alignment.workLength}</p>
                            <p class="card-title">Дней аренды: ${rent.dayRent}</p>
                            <p class="card-text">Начало аренды: ${rent.startRental}</p>
                            <p class="card-text">Конец аренды: ${rent.endRental}</p>
                        </div>
                    </div>
                </#if>
            </div>
            <div>
                <#if rent.countersink?has_content>
                    <div class="card">
                        <div class="card-body">
                            <p class="card-title">Имя инструмента: ${rent.countersink.name}</p>
                            <p class="card-title">Диаметр: ${rent.countersink.diameter}</p>
                            <p class="card-title">Длинна: ${rent.countersink.length}</p>
                            <p class="card-title">Рабочая длинна: ${rent.countersink.workLength}</p>
                            <p class="card-title">Дней аренды: ${rent.dayRent}</p>
                            <p class="card-text">Начало аренды: ${rent.startRental}</p>
                            <p class="card-text">Конец аренды: ${rent.endRental}</p>
                        </div>
                    </div>
                </#if>
            </div>
        </#list>
    </div>
</@b.template>