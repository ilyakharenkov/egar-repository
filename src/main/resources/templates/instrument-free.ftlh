<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>
            Свободный инструмент
        </h2>
    </div>
    <div>
        <a href="/rent" class="btn btn-primary btn-list">Показать аренду за все время</a>
        <a href="/rent-rent" class="btn btn-primary btn-list">Показать весь арендованный инструмент</a>
        <a href="/rent-free" class="btn btn-primary btn-list">Показать инструмент который был в аренде</a>
        <a href="/instrument-free" class="btn btn-primary btn-list">Показать весь свободный иснтрумент</a>
        <a href="/rent/sort-start" class="btn btn-primary btn-list">Сортировать по началу аренды</a>
        <a href="/rent/sort-end" class="btn btn-primary btn-list">Сортировать по окончанию аренды</a>
    </div>
    <div class="card-list">
        <#list alignmentList as alignment>
            <div>
                <#if alignment?has_content>
                    <div class="card">
                        <div class="card-body">
                            <p class="card-title">Имя инструмента: ${alignment.name}</p>
                            <p class="card-title">Диаметр: ${alignment.diameter}</p>
                            <p class="card-title">Длинна: ${alignment.length}</p>
                            <p class="card-title">Рабочая длинна: ${alignment.workLength}</p>
                            <p class="card-title">Цена одного дня аренды: ${alignment.priceDto.priceRentOfDay}</p>
                            <p class="card-text">Цена инструмента: ${alignment.priceDto.sell}</p>
                        </div>
                    </div>
                </#if>
            </div>
        </#list>
        <#list countersinkList as countersink>
            <div>
                <#if countersink?has_content>
                    <div class="card">
                        <div class="card-body">
                            <p class="card-title">Имя инструмента: ${countersink.name}</p>
                            <p class="card-title">Диаметр: ${countersink.diameter}</p>
                            <p class="card-title">Длинна: ${countersink.length}</p>
                            <p class="card-title">Рабочая длинна: ${countersink.workLength}</p>
                            <p class="card-title">Цена одного дня аренды: ${countersink.priceDto.priceRentOfDay}</p>
                            <p class="card-text">Цена инструмента: ${countersink.priceDto.sell}</p>
                        </div>
                    </div>
                </#if>
            </div>
        </#list>
    </div>
</@b.template>



