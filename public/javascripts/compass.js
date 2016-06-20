function drawCompass() {
   
	// size parameters
	var width = 200;
	var height = 200;
	var margin = {left: 20, top: 20, right: 20, bottom: 20};

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
		.append("g").attr("class", "wrapper")
			.attr("transform", "translate(" + (width / 2 + margin.left) + "," + (height / 2 + margin.top) + ")");

	// svg paths into arcs
	var arc = d3.svg.arc()
		.innerRadius(width*0.9/2) 
		.outerRadius(width*0.9/2 + 30);

	//define start and end angles
	var pie = d3.layout.pie()
		.value(function(d) {return 30;}) // 
		.padAngle(.01)
		.sort(null);

//Draw the arcs themselves
	svg.selectAll(".dirArc")
		.data(pie(dirData))
	   	.enter()
	   	.append("path")
			.attr("class", "dirArc")
			.attr("id", function(d,i) { return "dirArc_"+i; })
			.attr("d", arc);

	//Append the month names within the arcs
	svg.selectAll(".dirText")
		.data(dirData)
	   	.enter()
	   	.append("text")
			.attr("class", "dirText")
			.attr("x", -4) //Move the text from the start angle of the arc
			.attr("dy", 45) //Move the text down
	   	.append("textPath")
			.attr("xlink:href",function(d,i){return "#dirArc_"+i;}) //link text to corresponding arc (decided by "i" index)
			.text(function(d){return d.dir;});	


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
   		    