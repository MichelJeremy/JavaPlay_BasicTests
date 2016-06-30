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

function displayAirData(dataList) {
	
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
	document.getElementById("airDataValueCur").appendChild(document.createTextNode(current));
	document.getElementById("airDataValueMin").appendChild(document.createTextNode(min));
	document.getElementById("airDataValueMax").appendChild(document.createTextNode(max));
	document.getElementById("airDataTimeCur").appendChild(document.createTextNode(currentDateF));
	document.getElementById("airDataTimeMin").appendChild(document.createTextNode(minDateF));
	document.getElementById("airDataTimeMax").appendChild(document.createTextNode(maxDateF));
	document.getElementById("airDataValueAvg").appendChild(document.createTextNode(averageF));

}

function displayTempData(dataList) {
	
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
	document.getElementById("tempDataValueCur").appendChild(document.createTextNode(current));
	document.getElementById("tempDataValueMin").appendChild(document.createTextNode(min));
	document.getElementById("tempDataValueMax").appendChild(document.createTextNode(max));
	document.getElementById("tempDataTimeCur").appendChild(document.createTextNode(currentDateF));
	document.getElementById("tempDataTimeMin").appendChild(document.createTextNode(minDateF));
	document.getElementById("tempDataTimeMax").appendChild(document.createTextNode(maxDateF));
	document.getElementById("tempDataValueAvg").appendChild(document.createTextNode(averageF));

}

function displayHumiData(dataList) {
	
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
	document.getElementById("humiDataValueCur").appendChild(document.createTextNode(current));
	document.getElementById("humiDataValueMin").appendChild(document.createTextNode(min));
	document.getElementById("humiDataValueMax").appendChild(document.createTextNode(max));
	document.getElementById("humiDataTimeCur").appendChild(document.createTextNode(currentDateF));
	document.getElementById("humiDataTimeMin").appendChild(document.createTextNode(minDateF));
	document.getElementById("humiDataTimeMax").appendChild(document.createTextNode(maxDateF));
	document.getElementById("humiDataValueAvg").appendChild(document.createTextNode(averageF));

}

function displayWindData(dataList) {
	
	//INIT
	var minWS = dataList[0];
	var minTimeWS = dataList[1];
	var maxWS = dataList[2];
	var maxTimeWS = dataList[3];
	var averageWS = dataList[4];
	var currentWS = dataList[5];
	var currentTimeWS = dataList[6];
	var minWD = dataList[7];
	var minTimeWD = dataList[8];
	var maxWD = dataList[9];
	var maxTimeWD = dataList[10];
	var averageWD = dataList[11];
	var currentWD = dataList[12];
	var currentTimeWD = dataList[13];

												/********************
												*	  WIND SPEED	*
												*********************/
	//MIN
	var minDateWS = new Date(minTimeWS); //epoch timestamp to javascript date
	var hours = (minDateWS.getHours() < 10 ? "0"+minDateWS.getHours() : minDateWS.getHours()); //append a 0 if hours is lt 10
	var minutes = (minDateWS.getMinutes() < 10 ? "0"+minDateWS.getMinutes() : minDateWS.getMinutes());//append a 0 if minutes is lt 10
	var minDateWSF =  hours + "H" + minutes;

	//MAX
	var maxDateWS = new Date(maxTimeWS);//epoch timestamp to javascript date
	hours = (maxDateWS.getHours() < 10 ? "0"+maxDateWS.getHours() : maxDateWS.getHours());//append a 0 if minutes is lt 10
	minutes = (maxDateWS.getMinutes() < 10 ? "0"+maxDateWS.getMinutes() : maxDateWS.getMinutes());//append a 0 if minutes is lt 10
	var maxDateWSF =  hours + "H" + minutes;

	//AVERAGE
	var averageWSF = (Math.round(averageWS * 100)/100).toFixed(2);

	//CURRENT
	var currentDateWS = new Date(currentTimeWS);//epoch timestamp to javascript date
	hours = (currentDateWS.getHours() < 10 ? "0"+currentDateWS.getHours() : currentDateWS.getHours()); //append a 0 if hours is lt 10
	minutes = (currentDateWS.getMinutes() < 10 ? "0"+currentDateWS.getMinutes() : currentDateWS.getMinutes());//append a 0 if minutes is lt 10
	var currentDateWSF = hours + "H" + minutes;



												/********************
												*   WIND DIRECTION	*
												*********************/
	//MIN
	var minDateWD = new Date(minTimeWD); //epoch timestamp to javascript date
	var hours = (minDateWD.getHours() < 10 ? "0"+minDateWD.getHours() : minDateWD.getHours()); //append a 0 if hours is lt 10
	var minutes = (minDateWD.getMinutes() < 10 ? "0"+minDateWD.getMinutes() : minDateWD.getMinutes());//append a 0 if minutes is lt 10
	var minDateWDF =  hours + "H" + minutes;

	//MAX
	var maxDateWD = new Date(maxTimeWD);//epoch timestamp to javascript date
	hours = (maxDateWD.getHours() < 10 ? "0"+maxDateWD.getHours() : maxDateWD.getHours());//append a 0 if minutes is lt 10
	minutes = (maxDateWD.getMinutes() < 10 ? "0"+maxDateWD.getMinutes() : maxDateWD.getMinutes());//append a 0 if minutes is lt 10
	var maxDateWDF =  hours + "H" + minutes;

	//AVERAGE
	var averageWDF = (Math.round(averageWD * 100)/100).toFixed(2);

	//CURRENT
	var currentDateWD = new Date(currentTimeWD);//epoch timestamp to javascript date
	hours = (currentDateWD.getHours() < 10 ? "0"+currentDateWD.getHours() : currentDateWD.getHours()); //append a 0 if hours is lt 10
	minutes = (currentDateWD.getMinutes() < 10 ? "0"+currentDateWD.getMinutes() : currentDateWD.getMinutes());//append a 0 if minutes is lt 10
	var currentDateWDF = hours + "H" + minutes;


	//APPEND
	document.getElementById("windDataValueWSCur").appendChild(document.createTextNode(currentWS));
	document.getElementById("windDataValueWSMin").appendChild(document.createTextNode(minWS));
	document.getElementById("windDataValueWSMax").appendChild(document.createTextNode(maxWS));
	document.getElementById("windDataTimeWSCur").appendChild(document.createTextNode(currentDateWSF));
	document.getElementById("windDataTimeWSMin").appendChild(document.createTextNode(minDateWSF));
	document.getElementById("windDataTimeWSMax").appendChild(document.createTextNode(maxDateWSF));
	document.getElementById("windDataValueWSAvg").appendChild(document.createTextNode(averageWSF));

	document.getElementById("windDataValueWDCur").appendChild(document.createTextNode(currentWD));
	document.getElementById("windDataValueWDMin").appendChild(document.createTextNode(minWD));
	document.getElementById("windDataValueWDMax").appendChild(document.createTextNode(maxWD));
	document.getElementById("windDataTimeWDCur").appendChild(document.createTextNode(currentDateWDF));
	document.getElementById("windDataTimeWDMin").appendChild(document.createTextNode(minDateWDF));
	document.getElementById("windDataTimeWDMax").appendChild(document.createTextNode(maxDateWDF));
	document.getElementById("windDataValueWDAvg").appendChild(document.createTextNode(averageWDF));

}