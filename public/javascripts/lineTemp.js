// plots graphs for last 7 days

function plotGraphLineTempRaw7Days(mongoData) {
    // configuration of the chart
    var margin = {top:20 , right: 80, bottom: 80, left: 50},
        width = 960 - margin.left - margin.right,
        height = 500 - margin.top - margin.bottom;

    // date format
    var parseDate = d3.time.format("%d-%m-%Y %H:%M");

    // Get the min/max of each axis & the scales
    var maxY = d3.max(mongoData, function(d) { return d.value;});
    var minY = d3.min(mongoData, function(d) { return d.value;});

    var maxX = d3.max(mongoData, function(d) { return new Date(d.timestamp);});
    var minX = d3.min(mongoData, function(d) { return new Date(d.timestamp);});

    // x-position encoding
    var x = d3.time.scale()
        .range([0, width])
        .domain([minX,maxX]);

    // y-position encoding
    var y = d3.scale.linear()
        .range([height, 0])
        .domain([minY, maxY]);

    // init x axis
    var format = d3.time.format("%d-%m");
    var xAxis = d3.svg.axis()
        .scale(x)
        .orient("bottom")
        .tickFormat(format);

    // init y axis
    var yAxis = d3.svg.axis()
        .scale(y)
        .orient("left");

    // display line shape
    var line = d3.svg.line()
        /*interpolate basis smoothes the peaks, and linear draws straight lines from one point to the other
        -> basis
        -> linear
        */
        .interpolate("linear")
        .x(function(d) { return x(d.timestamp);})
        .y(function(d) { return y(d.value);});

    // parse the String dates to Date dates for every chartDatas
    mongoData.forEach(function(d) {
        d.timestamp = new Date(d.timestamp);
    })

    // define svg canvas
    var svg = d3.select("#graph7Days")
        .append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
        .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    // append x axis
    svg.append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis)
        .append("text")
            .attr("y", -6)
            .attr("dy", ".25em")
            .attr("x", 660)
            .attr("dx", ".25em")
            .text("Temperature evolution over time");

    // append y axis
    svg.append("g")
            .attr("class", "y axis")
            .call(yAxis)
        .append("text")
            .attr("transform", "rotate(-90)")
            .attr("y", 6)
            .attr("dy", ".71em")
            .style("text-anchor", "end")
            .text("Temperature(°C)");

    svg.append("svg:path")  
        .attr("d", line(mongoData))
        .attr("stroke", "blue")
        .attr("fill", "none");

// how to add other data serie:
/*    svg.append("svg:path")
        .attr("d", line(NAME_OF_THE_ARRAY_CONTAINING_DATA_WITH_FORMATED_DATES(important!))
        .attr("stroke", "red")
        .attr("fill", "none");*/
}


// plots graphs for last day

function plotGraphLineTempRaw1Day(mongoData) {
    // configuration of the chart
    var margin = {top:20 , right: 80, bottom: 80, left: 50},
        width = 960 - margin.left - margin.right,
        height = 500 - margin.top - margin.bottom;

    var newMongoData = []; // will host the new array with only one the values of the actual day

    // we need to keep only the actual day's datas
    // first, get the latest timestamp and deduce the beginning of the day in timestamp value
    var date = new Date(mongoData[0]["timestamp"])
    date.setHours(0) //set the date to the beginning of the same day
    var newTime = date.getTime();

    // then, keep only the values greater than the timestamp obtained (which are in the same day)
    mongoData.forEach(function(d) {
        if(d.timestamp > newTime) { //still in the same day
            newMongoData.push({
                timestamp: d.timestamp,
                unit: d.unit,
                value: d.value
            });
        } //else do nothing because we don't need the other values
    });
    console.log(mongoData)
    console.log(newMongoData)

    // date format
    var parseDate = d3.time.format("%d-%m-%Y %H:%M");

    // Get the min/max of each axis & the scales
    var maxY = d3.max(newMongoData, function(d) { return d.value;});
    var minY = d3.min(newMongoData, function(d) { return d.value;});

    var maxX = d3.max(newMongoData, function(d) { return new Date(d.timestamp);});
    var minX = d3.min(newMongoData, function(d) { return new Date(d.timestamp);});

    // x-position encoding
    var x = d3.time.scale()
        .range([0, width])
        .domain([minX,maxX]);

    // y-position encoding
    var y = d3.scale.linear()
        .range([height, 0])
        .domain([minY, maxY]);

    // init x axis
    var format = d3.time.format("%d-%m %H:%M");
    var xAxis = d3.svg.axis()
        .scale(x)
        .orient("bottom")
        .tickFormat(format);

    // init y axis
    var yAxis = d3.svg.axis()
        .scale(y)
        .orient("left");

    // display line shape
    var line = d3.svg.line()
        /*interpolate basis smoothes the peaks, and linear draws straight lines from one point to the other
        -> basis
        -> linear
        */
        .interpolate("linear")
        .x(function(d) { return x(d.timestamp);})
        .y(function(d) { return y(d.value);});

    // parse the String dates to Date dates for every chartDatas
    newMongoData.forEach(function(d) {
        d.timestamp = new Date(d.timestamp);
    })

    // define svg canvas
    var svg = d3.select("#graphToday")
        .append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
        .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    // append x axis
    svg.append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis)
        .append("text")
            .attr("y", -6)
            .attr("dy", ".25em")
            .attr("x", 660)
            .attr("dx", ".25em")
            .text("Temperature evolution over time");

    // append y axis
    svg.append("g")
            .attr("class", "y axis")
            .call(yAxis)
        .append("text")
            .attr("transform", "rotate(-90)")
            .attr("y", 6)
            .attr("dy", ".71em")
            .style("text-anchor", "end")
            .text("Temperature(°C)");

    svg.append("svg:path")  
        .attr("d", line(newMongoData))
        .attr("stroke", "blue")
        .attr("fill", "none");

// how to add other data serie:
/*    svg.append("svg:path")
        .attr("d", line(NAME_OF_THE_ARRAY_CONTAINING_DATA_WITH_FORMATED_DATES(important!))
        .attr("stroke", "red")
        .attr("fill", "none");*/
}
