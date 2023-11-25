<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        Alignment
    </title>
</head>
<body>
<div>
    <#list alignments as alignment>
        <div>
            <p>
                Name: ${alignment.name} <br> Diameter: ${alignment.diameter} <br> Price: ${alignment.priceDto.sell} <br>
                Rental 1 day: ${alignment.priceDto.rentOfDay} <br>
                Status:
                <#if alignment.checkStatus>
                    Ok
                <#else>
                    False
                </#if>
                <br>
                <a href="/alignment/${alignment.id}">Details</a>
            </p>
            <#if principal.username??>
                <#if alignment.checkStatus>
                    <form action="/alignment/rental/${alignment.id}" method="get">
                        <input type="submit" value="Rental"><br>
                    </form>
                    <#if role>
                        <form action="/alignment/delete/${alignment.id}" method="post">
                            <input type="submit" value="Delete">
                        </form>
                    </#if>
                </#if>
            </#if>
        </div>
    </#list>
</div>
<div>
    <br><br>
</div>
<#if principal.username??>
    <#if role>
        <div>
            <form action="/alignment/add" method="post" enctype="multipart/form-data">
                <label>
                    Name: <input type="text" name="name"/><br><br>
                </label>
                <label>
                    Diameter: <input type="number" name="diameter"/><br><br>
                </label>
                <label>
                    Length: <input type="number" name="length"/><br><br>
                </label>
                <label>
                    Work Length: <input type="number" name="workLength"/><br><br>
                </label>
                <label>
                    Angle: <input type="number" name="angle"/><br><br>
                </label>
                <label>
                    Purchase: <input type="number" name="purchase"/><br><br>
                </label>
                <label>
                    Expenses: <input type="number" name="expenses"/><br><br>
                </label>
                <label>
                    Add photo: <input type="file" name="listFile" multiple="multiple"/><br><br>
                </label>
                <input type="submit" value="Add Alignment"/>
            </form>
        </div>
    </#if>
</#if>
</body>
</html>