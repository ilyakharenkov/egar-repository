<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>
            Обслуживание ${alignment.name}
        </h2>
    </div>
    <div class="card">
        <div class="card-body">
            <p class="card-title">Имя инструмента: ${alignment.name}</p>
            <p class="card-title">Диаметр: ${alignment.diameter}</p>
            <p class="card-text">Длинна: ${alignment.length}</p>
            <p class="card-text">Рабочая длинна: ${alignment.workLength}</p>
            <p class="card-text">Угол: ${alignment.angle}</p>
            <form action="/alignment/renovation/add/${alignment.id}" method="post">
                <div class="form-group">
                    <label for="instrumentDayRenovation">Дней на обслуживание</label>
                    <input type="number" required class="form-control" id="instrumentDayRenovation" name="countDay">

                    <label for="instrumentPriceDiagnostics">Цена диагностики</label>
                    <input type="number" required class="form-control" id="instrumentPriceDiagnostics"
                           name="priceDiagnostics">

                    <label for="instrumentResultPrice">Итоговая цена</label>
                    <input type="number" required class="form-control" id="instrumentResultPrice" name="resultPrice">

                    <label for="instrumentDescriptionResult">Описание обслуживания</label>
                    <input type="text" required class="form-control" id="instrumentDescriptionResult"
                           name="descriptionResult">
                </div>
                <div>
                    <button type="submit" class="btn btn-dark btn-add-instrument">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</@b.template>
