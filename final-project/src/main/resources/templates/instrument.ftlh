<!DOCTYPE html>
<html lang="ru">
<head>
    <title>
        Instruments
    </title>
</head>
<body>
<h1>Hello World Instruments</h1>
<#list instruments.cylinderCutters as cylinder>
    <ul>
        <li><b>Имя интсрумента: </b> ${cylinder.name} </li>
        <li><b>Диаметр интсрумента: </b> ${cylinder.diameter} </li>
        <li><b>Длинна интсрумента: </b> ${cylinder.length} </li>
    </ul>
</#list>
<#list instruments.sphericalCutters as sphere>
    <ul>
        <li><b>Имя интсрумента </b> ${sphere.name} </li>
        <li><b>Диаметр интсрумента </b> ${sphere.diameter} </li>
        <li><b>Длинна интсрумента </b> ${sphere.length} </li>
    </ul>
</#list>
<#list instruments.toroidalCutters as toroidal>
    <ul>
        <li><b>Имя интсрумента </b> ${toroidal.name} </li>
        <li><b>Диаметр интсрумента </b> ${toroidal.diameter} </li>
        <li><b>Длинна интсрумента </b> ${toroidal.length} </li>
    </ul>
</#list>
</body>
</html>