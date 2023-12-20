<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <h2>Инструменты</h2>
    </div>
    <div class="card-list">
        <div class="card">
            <img src="/storage/alignment/alignment.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Центровка</h5>
                <a href="/alignment" class="btn btn-primary">Перейти в раздел</a>
            </div>
        </div>
        <div class="card">
            <img src="/storage/countersink/countersink.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">Зенковка</h5>
                <a href="/countersink" class="btn btn-primary">Перейти в раздел</a>
            </div>
        </div>
    </div>
</@b.template>