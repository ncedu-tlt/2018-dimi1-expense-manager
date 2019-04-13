window.onload = function () {

    var sum = document.querySelectorAll("#sum");
    var date = document.querySelectorAll("#date");

    var masSum = Array.from(sum).map((e) => {
        return Number(e.innerHTML);
    });

    var masDate = Array.from(date).map((e) => {
        return new Date(e.innerHTML);
    });

    var resultUnr = masSum.map(function(name, id) {
        return {
            x: masDate[id],
            y: name
        };
    });

    var chart = new CanvasJS.Chart("chartContainer", {

	animationEnabled: true,
	theme: "light2",
	title:{
		text: "Planned expenses - Chart"
	},
	axisX:{
		valueFormatString: "DD MMM",
		crosshair: {
			enabled: true,
			snapToDataPoint: true
		}
	},
	axisY: {
		title: "Amount per day",
		crosshair: {
			enabled: true
		}
	},
	toolTip:{
		shared:true
	},
	legend:{
		cursor:"pointer",
		verticalAlign: "bottom",
		horizontalAlign: "left",
		dockInsidePlotArea: true,
		itemclick: toogleDataSeries
	},
	data: [{
            type: "line",
            showInLegend: true,
            name: "line expenses",
            markerType: "square",
            xValueFormatString: "DD MM, YYYY",
            color: "#F08080",
            dataPoints: resultUnr
	    }]
    });
    chart.render();

    function toogleDataSeries(e){
        if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        } else{
            e.dataSeries.visible = true;
        }
        chart.render();
    }

}

$(function () {
    $('#datetimepicker').datetimepicker({
        format: 'DD/MM/YYYY'
    });
});


