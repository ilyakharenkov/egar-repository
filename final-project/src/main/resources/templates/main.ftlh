<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        Main page
    </title>
</head>
<header>
    <p>
        <a href="/login">Sign In</a>  |
        <a href="/logout">Sign Out</a>  |
        <a href="/registration">Registration</a> |
        <label>
            <#if client.username??>
                ${client.client.firstName}
            </#if>
        </label>
    </p>
</header>
<body>
<p><a href="/alignment">Alignment</a></p><br>
</body>
</html>