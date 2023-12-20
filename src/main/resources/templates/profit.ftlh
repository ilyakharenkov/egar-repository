<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>Доход</h2>
    </div>
    <div>
        <h3>Сумма доходов за 7 дней: ${sumProfit}</h3>
    </div>
    <div class="card-list">
        <#list profitList as profit>
            <div class="card">
                <div class="card-body">
                    <p class="card-title">Имя клиента: ${profit.rent.client.firstName}</p>
                    <p class="card-title">Фамилия клиента: ${profit.rent.client.surname}</p>
                    <p class="card-title">Выгода: ${profit.income}</p>
                    <p class="card-title">Налог на прибыль: ${profit.tax}</p>
                    <p class="card-title">Дней в аренде: ${profit.dayRent}</p>
                    <#if profit.rent.alignment?has_content>
                        <p class="card-title">Имя инструмента: ${profit.rent.alignment.name}</p>
                        <a href="/alignment/${profit.rent.alignment.id}" class="btn btn-primary btn-list">Подробнее</a>
                    </#if>
                    <#if profit.rent.countersink?has_content>
                        <p class="card-title">Имя инструмента: ${profit.rent.countersink.name}</p>
                        <a href="/countersink/${profit.rent.countersink.id}" class="btn btn-primary btn-list">Подробнее</a>
                    </#if>
                </div>
            </div>
        </#list>
    </div>
</@b.template>