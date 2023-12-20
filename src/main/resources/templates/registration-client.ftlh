<#import "blocks/base.ftlh" as b>
<@b.template client, role>
    <div>
        <form action="/registration/add" method="post">
            <div class="form-group">
                <label for="registrationLogin">Логин</label>
                <input type="text" required class="form-control" id="registrationLogin" name="username">

                <label for="registrationPassword">Пароль</label>
                <input type="text" required class="form-control" id="registrationPassword" name="password">

                <label for="registrationFirstName">Имя</label>
                <input type="text" required class="form-control" id="registrationFirstName" name="firstName">

                <label for="registrationSurname">Фамилия</label>
                <input type="text" required class="form-control" id="registrationSurname" name="surname">
            </div>
            <button type="submit" class="btn btn-dark btn-add-instrument">Зарегестрироваться</button>
        </form>
    </div>
</@b.template>