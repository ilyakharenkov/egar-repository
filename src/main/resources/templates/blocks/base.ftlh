<#macro template client, role>
    <!DOCTYPE html>
    <html lang="ru">
    <head>

        <!-- Required meta tags -->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/style.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
              integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
              crossorigin="anonymous">

        <title>ИНСТРУМЕНТ</title>
    </head>
    <body>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a href="/">
                <span class="navbar-brand h2">ИНСТРУМЕНТ</span>
            </a>
            <p>
                <#if role>
                    <a href="/profit" class="navbar-brand">Выручка</a>
                    <a href="/rent" class="navbar-brand">Аренда</a>
                    <a href="/renovation" class="navbar-brand">Обслуживание</a>
                    <a href="/archive" class="navbar-brand">Архив в формате JSON</a>
                </#if>
                <label>
                    <#if client.username??>
                        <a href="/profile-rent" class="navbar-brand">Список вашей аренды</a>
                    </#if>
                </label>
                <a href="/login" class="navbar-brand">Войти</a>
                <a href="/logout" class="navbar-brand">Выйти</a>
                <a href="/registration" class="navbar-brand">Регистрация</a>
                <label>
                    <#if client.username??>
                        <span class="navbar-brand">Имя пользователя: ${client.client.firstName}</span>
                    </#if>
                </label>
            </p>
        </div>
    </nav>

    <div class="container mt-4">
        <#nested/>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>