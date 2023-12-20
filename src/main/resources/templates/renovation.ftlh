<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>Инструмент на обслуживании</h2>
    </div>
    <div class="card-list">
        <#list renovationList as renovation>
            <div>
                <#if renovation.alignment?has_content>
                    <div class="card">
                        <div class="card-body">
                            <p class="card-title">Имя инструмента: ${renovation.alignment.name}</p>
                            <p class="card-title">Диаметр: ${renovation.alignment.diameter}</p>
                            <p class="card-title">Длинна: ${renovation.alignment.length}</p>
                            <p class="card-title">Рабочая длинна: ${renovation.alignment.workLength}</p>
                            <p class="card-title">Дней обслуживания: ${renovation.countDay}</p>
                            <p class="card-title">Начало обслуживания: ${renovation.startRenovation}</p>
                            <p class="card-title">Конец обслуживания: ${renovation.endRenovation}</p>
                        </div>
                    </div>
                </#if>
            </div>
            <div>
                <#if renovation.countersink?has_content>
                    <div class="card">
                        <div class="card-body">
                            <p class="card-title">Имя инструмента: ${renovation.countersink.name}</p>
                            <p class="card-title">Диаметр: ${renovation.countersink.diameter}</p>
                            <p class="card-title">Длинна: ${renovation.countersink.length}</p>
                            <p class="card-title">Рабочая длинна: ${renovation.countersink.workLength}</p>
                            <p class="card-title">Дней обслуживания: ${renovation.countDay}</p>
                            <p class="card-title">Начало обслуживания: ${renovation.startRenovation}</p>
                            <p class="card-title">Конец обслуживания: ${renovation.endRenovation}</p>
                        </div>
                    </div>
                </#if>
            </div>
        </#list>
    </div>
</@b.template>