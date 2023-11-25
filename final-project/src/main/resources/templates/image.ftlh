<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        Image
    </title>
</head>
<body>
<div>
    <#list listImage as img>
        <img src="/images/${img.id}" height="60px" alt=""/><br><br>
    </#list>
</div>
<div>
    <h3>Add images</h3>
    <form action="/image/add" method="post" enctype="multipart/form-data">
        <label>
            Image: <input type="file" name="listFile" multiple="multiple"/><br><br>
        </label>
        <input type="submit" value="Add image">
    </form>
</div>
</body>
</html>