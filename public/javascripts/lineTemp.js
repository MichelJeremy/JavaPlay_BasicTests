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
/* sources: http://jsfiddle.net/n7joxbn6/4/
            http://bl.ocks.org/DStruths/9c042e3a6b66048b5bd4
            */
function plotGraphLineTempRaw1Day(mongoData) {
    // size configuration of the chart
    var margin = {top: 10, right: 30, bottom: 100, left: 20},
        margin2 = {top: 430, right: 30, bottom: 40, left: 20},
        width = 900 - margin.left - margin.right,
        height = 500 - margin.top - margin.bottom,
        height2 = 500 - margin2.top - margin2.bottom;

    var color = "#4682b4"

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

    // date format
    var parseDate = d3.time.format("%d-%m-%Y %H:%M");

    // x and y-position encoding
    var x = d3.time.scale().range([0, width]),
        x2 = d3.time.scale().range([0, width]), //because x scale is the same for both
        y = d3.scale.linear().range([height, 0]),
        y2 = d3.scale.linear().range([height2, 0]); // y scale differs because height of the brush area is not the same


    var format = d3.time.format("%d-%m %H:%M");

    var xAxis = d3.svg.axis().scale(x).orient("bottom").tickFormat(format);
    var xAxis2 = d3.svg.axis().scale(x2).orient("bottom");
    var yAxis = d3.svg.axis().scale(y).orient("left");

    var brush = d3.svg.brush()//for slider bar at the bottom
        .x(x2) 
        .on("brush", brushed);


    var line = d3.svg.line()
        .interpolate("monotone")
        .x(function(d) { return x(d.timestamp);})
        .y(function(d) { return y(d.value);});


    var line2 = d3.svg.line()
        .interpolate("monotone")
        .x(function(d) { return x2(d.timestamp);})
        .y(function(d) { return y2(d.value);});

    var area2 = d3.svg.area()
        .interpolate("monotone")
        .x(function(d) { return x2(d.timestamp);})
        .y0(height2)
        .y1(function(d) { return y2(d.value);});

    var svg = d3.select("#graphToday")
        .append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
        .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    svg.append("defs")
        .append("clipPath") 
            .attr("id", "clip")
        .append("rect")
            .attr("width", width)
            .attr("height", height); 

    var focus = svg.append("g")
        .attr("class", "focus")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    var context = svg.append("g")
        .attr("class", "context")
        .attr("transform", "translate(" + margin2.left + "," + margin2.top + ")");


    newMongoData.forEach(function(d) {
        d.timestamp = new Date(d.timestamp);
        d.value = d.value;
    });

    x.domain(d3.extent(newMongoData.map(function(d) { return d.timestamp; })));
    y.domain([d3.min(newMongoData.map(function(d) { return d.value; })), d3.max(newMongoData.map(function(d) { return d.value; }))]);
    x2.domain(x.domain());
    y2.domain(y.domain());





/*    ====================================================================
      =     FOCUS PART (= the big area displaying the graph)             =   
      ====================================================================*/


    focus.append("path")
        .datum(newMongoData)
        .attr("class", "line")
        .attr("stroke", color)
        .attr("d", line);


    focus.append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis)
        .append("text")
            .attr("y", -6)
            .attr("dy", ".25em")
            .attr("x", 660)
            .attr("dx", ".25em")
            .text("Temperature evolution over time");

    focus.append("g")
            .attr("class", "y axis")
            .call(yAxis)
        .append("text")
            .attr("transform", "rotate(-90)")
            .attr("y", 6)
            .attr("dy", ".71em")
            .style("text-anchor", "end")
            .text("Temperature(°C)");


/*    ====================================================================
      =     CONTEXT PART (= brushing area at the bottom of the graph)    =   
      ====================================================================*/

    context.append("path")
        .datum(newMongoData)
        .attr("class", "line")
        .attr("stroke", color)
        .attr("d", line2);

    context.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + height2 + ")")
        .call(xAxis2);

    context.append("g")
        .attr("class", "x brush")
        .call(brush)
        .selectAll("rect")
            .attr("height", height2) // Make brush rects same height 


    //here make the line zoom on the brushed part
    function brushed() {
        x.domain(brush.empty() ? x2.domain() : brush.extent()); // If brush is empty then reset the x domain to default, if not then make it the brush extent 
        focus.select(".line").attr("d", line);
        focus.select(".x.axis").call(xAxis);
    };

// how to add other data serie:
/*    svg.append("svg:path")
        .attr("d", line(NAME_OF_THE_ARRAY_CONTAINING_DATA_WITH_FORMATED_DATES(important!))
        .attr("stroke", "red")
        .attr("fill", "none");*/
}
