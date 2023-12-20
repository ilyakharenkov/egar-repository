<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>
            Аренда ${countersink.name}
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
            <p class="card-title">Диаметр: ${countersink.diameter}</p>
            <p class="card-text">Длинна: ${countersink.length}</p>
            <p class="card-text">Рабочая длинна: ${countersink.workLength}</p>
            <p class="card-text">Угол: ${countersink.angle}</p>
            <p class="card-text">Цена одного дня аренды: ${countersink.price.priceRentOfDay}</p>
            <form action="/countersink/rent/add/${countersink.id}" method="post">
                <div class="form-group">
                    <label for="instrumentDayRent">Введите колличество дней аренды</label>
                    <input type="number" required class="form-control" id="instrumentDayRent" name="dayRent">
                </div>
                <div>
                    <button type="submit" class="btn btn-dark btn-add-instrument">Арендовать</button>
                </div>
            </form>
        </div>
    </div>
</@b.template>