function displayRainData(dataList) {
	
	//INIT
	var min = dataList[0];
	var minTime = dataList[1];
	var max = dataList[2];
	var maxTime = dataList[3];
	var average = dataList[4];
	var current = dataList[5];
	var currentTime = dataList[6];

	//MIN
	var minDate = new Date(minTime); //epoch timestamp to javascript date
	var hours = (minDate.getHours() < 10 ? "0"+minDate.getHours() : minDate.getHours()); //append a 0 if hours is lt 10
	var minutes = (minDate.getMinutes() < 10 ? "0"+minDate.getMinutes() : minDate.getMinutes());//append a 0 if minutes is lt 10
	var minDateF =  hours + "H" + minutes;

	//MAX
	var maxDate = new Date(maxTime);//epoch timestamp to javascript date
	hours = (maxDate.getHours() < 10 ? "0"+maxDate.getHours() : maxDate.getHours());//append a 0 if minutes is lt 10
	minutes = (maxDate.getMinutes() < 10 ? "0"+maxDate.getMinutes() : maxDate.getMinutes());//append a 0 if minutes is lt 10
	var maxDateF =  hours + "H" + minutes;

	//AVERAGE
	var averageF = (Math.round(average * 100)/100).toFixed(2);

	//CURRENT
	var currentDate = new Date(currentTime);//epoch timestamp to javascript date
	hours = (currentDate.getHours() < 10 ? "0"+currentDate.getHours() : currentDate.getHours()); //append a 0 if hours is lt 10
	minutes = (currentDate.getMinutes() < 10 ? "0"+currentDate.getMinutes() : currentDate.getMinutes());//append a 0 if minutes is lt 10
	var currentDateF = hours + "H" + minutes;

	//APPEND
	document.getElementById("rainDataValueCur").appendChild(document.createTextNode(current));
	document.getElementById("rainDataValueMin").appendChild(document.createTextNode(min));
	document.getElementById("rainDataValueMax").appendChild(document.createTextNode(max));
	document.getElementById("rainDataTimeCur").appendChild(document.createTextNode(currentDateF));
	document.getElementById("rainDataTimeMin").appendChild(document.createTextNode(minDateF));
	document.getElementById("rainDataTimeMax").appendChild(document.createTextNode(maxDateF));
	document.getElementById("rainDataValueAvg").appendChild(document.createTextNode(averageF));

}