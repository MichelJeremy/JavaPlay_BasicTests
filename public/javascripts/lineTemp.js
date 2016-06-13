// param0: number of sensors
// param1: data to push through eval

function plotGraphLineTemp(param0, param1) {
    //First, we dynamically create as much chartData var as there are sensors
    var nbOfSensors = param0;
    for (i = 1; i < nbOfSensors+1; i++) {
        eval("var chartData" + i + " = []");
    }
    // Now that we got every values needed, we can push the data in the correct array
    // We need to replace the '&quot' by real quotes before, though:
    var chartDatasToEval = param1;
    eval(chartDatasToEval);

    // configuration of the chart
    var margin = {top:20 , right: 80, bottom: 80, left: 50},
        width = 960 - margin.left - margin.right,
        height = 500 - margin.top - margin.bottom;

    // date format
    var parseDate = d3.time.format("%Y-%d-%m %H:%M").parse;

    // Get the min/max of each axis & the scales
    var maxY = Math.max((d3.max(chartData1, function(d) { return d.tonsOfBananas;})), (d3.max(chartData2, function(d) { return d.tonsOfBananas;})));
    var minY = Math.min((d3.min(chartData1, function(d) { return d.tonsOfBananas;})), (d3.min(chartData2, function(d) { return d.tonsOfBananas;})));

    var maxX = d3.max(chartData1, function(d) { return parseDate(d.date);});
    var minX = d3.min(chartData1, function(d) { return parseDate(d.date);});

    // x-position encoding
    var x = d3.time.scale()
        .range([0, width])
        .domain([minX,maxX]);

    // y-position encoding
    var y = d3.scale.linear()
        .range([height, 0])
        .domain([minY, maxY]);


    // init x axis
    var format = d3.time.format("%d-%m %H:00");
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
        .interpolate("basis")
        .x(function(d) { return x(d.date);})
        .y(function(d) { return y(d.tonsOfBananas);});


    // parse the String dates to Date dates for every chartDatas
    chartData1.forEach(function(d) {
        d.date = parseDate(d.date);
    })

    chartData2.forEach(function(d) {
        d.date = parseDate(d.date);
    })

    // define svg canvas
    var svg = d3.select("#graph")
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
            .text("Banana domination over time");

    // append y axis
    svg.append("g")
            .attr("class", "y axis")
            .call(yAxis)
        .append("text")
            .attr("transform", "rotate(-90)")
            .attr("y", 6)
            .attr("dy", ".71em")
            .style("text-anchor", "end")
            .text("Bananas (tons)");

    svg.append("svg:path")
        .attr("d", line(chartData1))
        .attr("stroke", "blue")
        .attr("fill", "none");

// other data serie, can be used with the first without conflict
/*    svg.append("svg:path")
        .attr("d", line(chartData2))
        .attr("stroke", "red")
        .attr("fill", "none");*/
}
