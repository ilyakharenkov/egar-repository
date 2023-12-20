<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>
            Список центровок
        </h2>
    </div>
    <div>
        <#if client.username??>
            <#if role>
                <a class="btn btn-dark mt-3 mb-3 btn-add-instrument"
                   data-toggle="collapse" href="#collapseExample" role="button"
                   aria-expanded="true" aria-controls="collapseExample">
                    Добавить новый инструмент
                </a>
                <div class="collapse mb-3" id="collapseExample">
                    <form action="/alignment/add" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="instrumentName">Имя инструмента</label>
                            <input type="text" required class="form-control" id="instrumentName" name="name">

                            <label for="instrumentDiameter">Диаметр инструмента</label>
                            <input type="number" required class="form-control" id="instrumentDiameter" name="diameter">

                            <label for="instrumentLength">Длинна инструмента</label>
                            <input type="number" required class="form-control" id="instrumentLength" name="length">

                            <label for="instrumentWorkLength">Рабочая длинна инструмента</label>
                            <input type="number" required class="form-control" id="instrumentWorkLength"
                                   name="workLength">

                            <label for="instrumentAngle">Угол инструмента</label>
                            <input type="number" required class="form-control" id="instrumentAngle" name="angle">
                        </div>
                        <div class="form-group">
                            <label for="instrumentPurchase">Цена покупки</label>
                            <input type="number" required class="form-control" id="instrumentPurchase" name="purchase">

                            <label for="instrumentExpenses">Затраты на реализацию</label>
                            <input type="number" required class="form-control" id="instrumentExpenses" name="expenses">

                            <label for="instrumentMarkup">Наценка %</label>
                            <input type="number" required class="form-control" id="instrumentMarkup" name="markup">

                            <label for="instrumentPriceRentOfDay">Цена за один день аренды</label>
                            <input type="number" required class="form-control" id="instrumentPriceRentOfDay" name="priceRentOfDay">
                        </div>
                        <div class="form-group">
                            <label for="instrumentImage">Добавить фото инструмента</label>
                            <input type="file" id="instrumentImage" multiple="multiple" name="listFile">
                        </div>
                        <button type="submit" class="btn btn-dark btn-add-instrument">Добавить</button>
                    </form>
                </div>
            </#if>
        </#if>
    </div>

    <div class="card-list">
        <#list alignments as alignment>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${alignment.name}</h5>
                    <p class="card-text">Диаметр: ${alignment.diameter}</p>
                    <p class="card-text">Цена: ${alignment.priceDto.sell}</p>
                    <p class="card-text">Цена одного дня аренды: ${alignment.priceDto.priceRentOfDay}</p>
                    <p class="card-text">Статус инструмента:
                        <#if alignment.checkStatus>
                            Инструмент исправен
                        <#else>
                            Инструмент на обслуживании или в аренде
                        </#if>
                    </p>
                    <a href="/alignment/${alignment.id}" class="btn btn-primary btn-list">Подробнее</a>
                    <#if client.username??>
                        <#if alignment.checkStatus>
                            <a href="/alignment/rent/${alignment.id}" class="btn btn-primary btn-list">Аренда</a>
                        </#if>
                        <#if role>
                            <a href="/alignment/renovation/${alignment.id}" class="btn btn-primary btn-list">Обслуживание</a>
                            <form action="/alignment/delete/${alignment.id}" method="post">
                                <input type="submit" class="btn btn-primary btn-list" value="Удалить"><br><br>
                            </form>
                        </#if>
                    </#if>
                </div>
            </div>
        </#list>
    </div>
</@b.template>