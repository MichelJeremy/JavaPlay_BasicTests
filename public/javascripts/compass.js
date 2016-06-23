var needle, svg, g, width, height;

function drawCompass(compassNeedleless, needle) {

	width = 200;
	height = 200;

	svg = d3.select("#compass")
		.append("svg")
		    .attr("width", width)
		    .attr("height", height);

	g = svg.append("g");

	g.append("svg:image")
	    .attr("xlink:href", compassNeedleless)
	    .attr("width", 200) // should not be useful since width and height are set in the original image
	    .attr("height", 200) // but make sure the scale is respected in case the original's image is edited+
	    .attr("id", "compass");

	needle = g.append("svg:image")
		.attr("xlink:href", needle)
		.attr("width", 200) // should not be useful since width and height are set in the original image
		.attr("height", 200) // but make sure the scale is respected in case the original's image is edited
		.attr("id", "needle");

}

function rotateNeedle(valueList, index) {
	var angle = valueList[index];
		
    d3.select("#needle")
    	.attr("transform", "rotate(180, 102, 98)");
    	
}
