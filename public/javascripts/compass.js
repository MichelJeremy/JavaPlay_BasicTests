function drawCompass() {
   
	// size parameters
	var width = 200;
	var height = 200;
	var margin = {left: 55, top: 20, right: 20, bottom: 20};

   	// create array with the directions
   	var dirData = [
   		{dir: "N"},
   		{dir: "NE"},
   		{dir: "E"},
   		{dir: "SE"},
   		{dir: "S"},
   		{dir: "SW"},
   		{dir: "W"},
   		{dir: "NW"}
   	];

	//Create the SVG
	var svg = d3.select("#compass")
		.append("svg")
			.attr("width", (width + margin.left + margin.right))
			.attr("height", (height + margin.top + margin.bottom))
		.append("g")
			.attr("transform", "translate(" + (width/2 + margin.left) + "," + (width/2 + margin.top) + ")");

	// draw outer circle (will comtain graduation)
	svg.append("circle")
		.attr("cx", 0)
		.attr("xy", 0)
		.attr("r", 120)
		.attr("fill", "#FFFFFF")
		.attr("stroke", "#afafaf")
		.attr("stroke-width", 2);

	//draw inner circle
	svg.append("circle")
		.attr("cx", 0)
		.attr("xy", 0)
		.attr("r", 70)
		.attr("fill", "#FFFFFF")
		.attr("stroke", "#afafaf")
		.attr("stroke-width", 1);

/*	// svg paths into arcs
	var arc = d3.svg.arc()
		.innerRadius(width/2) 
		.outerRadius(width/2 + 20); // value does not matter, has to to equal to the return below

	//define start and end angles
	var pie = d3.layout.pie()
		.value(function(d) {return 20;}) // value does not matter, has to to equal to the outer radius adding
		.padAngle(.01)
		.sort(null);

	//Draw the arcs themselves
	svg.selectAll(".dirArc")
		.data(pie(dirData))
	   	.enter()
	   	.append("path")
			.attr("class", "dirArc")
			.attr("d", arc);


	//now, append the directions inside (hardcoded)
	svg.append("text")
		.attr("id", "dirText")
	    .attr("dy", "-7.0em")
	    .attr("dx", "-0.3em")
	    .text("N");

	svg.append("text")
		.attr("id", "dirText")
	    .attr("dy", "7.7em")
	    .attr("dx", "-0.3em")
	    .text("S");
*/
/*	svg.append("text")
		.attr("id", "dirText")
	    .attr("dy", "-7.5em")
	    .attr("dx", "-0.3em")
	    .text("N");

	svg.append("text")
		.attr("id", "dirText")
	    .attr("dy", "-7.5em")
	    .attr("dx", "-0.3em")
	    .text("N");

	svg.append("text")
		.attr("id", "dirText")
	    .attr("dy", "-7.5em")
	    .attr("dx", "-0.3em")
	    .text("N");

	svg.append("text")
		.attr("id", "dirText")
	    .attr("dy", "-7.5em")
	    .attr("dx", "-0.3em")
	    .text("N");

	svg.append("text")
		.attr("id", "dirText")
	    .attr("dy", "-7.5em")
	    .attr("dx", "-0.3em")
	    .text("N");

	svg.append("text")
		.attr("id", "dirText")
	    .attr("dy", "-7.5em")
	    .attr("dx", "-0.3em")
	    .text("N");

	svg.append("text")
		.attr("id", "dirText")
	    .attr("dy", "-7.5em")
	    .attr("dx", "-0.3em")
	    .text("N");	    	    	    	    	    	    	    	   
*/
/*
	//Append the dir names within the arcs
	svg.selectAll(".dirText")
		.data(dirData)
	   	.enter()
	   	.append("text")
			.attr("class", "dirText")
			.attr("x", -4) //Move the text from the start angle of the arc
			.attr("dy", 45) //Move the text down
	   	.append("textPath")
			.attr("xlink:href",function(d,i){return "#dirArc_"+i;}) //link text to corresponding arc (decided by "i" index)
			.text(function(d){return d.dir;});	*/


/*    Needle = function () {
        function Needle(len, radius1) {
            this.len = len;
            this.radius = radius1;
        }
        Needle.prototype.drawOn = function (el, perc) {
            el.append('circle').attr('class', 'needle-center').attr('cx', 0).attr('cy', 0).attr('r', this.radius);
            return el.append('path').attr('class', 'needle').attr('d', this.mkCmd(perc));
        };
        Needle.prototype.animateOn = function (el, perc) {
            var self;
            self = this;
            return el.transition().delay(500).ease('elastic').duration(3000).selectAll('.needle').tween('progress', function () {
                return function (percentOfPercent) {
                    var progress;
                    progress = percentOfPercent * perc;
                    return d3.select(this).attr('d', self.mkCmd(progress));
                };
            });
        };
        Needle.prototype.mkCmd = function (perc) {
            var centerX, centerY, leftX, leftY, rightX, rightY, thetaRad, topX, topY;
            thetaRad = percToRad(perc / 2);
            centerX = 0;
            centerY = 0;
            topX = centerX - this.len * Math.cos(thetaRad);
            topY = centerY - this.len * Math.sin(thetaRad);
            leftX = centerX - this.radius * Math.cos(thetaRad - Math.PI / 2);
            leftY = centerY - this.radius * Math.sin(thetaRad - Math.PI / 2);
            rightX = centerX - this.radius * Math.cos(thetaRad + Math.PI / 2);
            rightY = centerY - this.radius * Math.sin(thetaRad + Math.PI / 2);
            return 'M ' + leftX + ' ' + leftY + ' L ' + topX + ' ' + topY + ' L ' + rightX + ' ' + rightY;
        };
        return Needle;
    }();
    needle = new Needle(90, 15);
    needle.drawOn(chart, 0);
    needle.animateOn(chart, percent);*/
}
   		    