<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        Rental
    </title>
</head>
<body>
<div>
    Name alignment: ${alignmentRental.name}<br>
    Diameter alignment: ${alignmentRental.diameter}<br>
    Length alignment: ${alignmentRental.length}<br>
    Work length alignment: ${alignmentRental.workLength}<br>
    Angle alignment: ${alignmentRental.angle}<br>
    Day od rent: ${alignmentRental.priceDto.rentOfDay}<br><br>
    <form action="/alignment/rental/add/${alignmentRental.id}" method="post">
        <label>
            Enter day rental: <input type="number" name="dayRent">
        </label>
        <input type="submit" value="Rent">
    </form>
</div>
</body>
</html>