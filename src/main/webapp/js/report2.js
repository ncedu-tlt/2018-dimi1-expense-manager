window.onload = function() {

    //common Chart
    var sumReq = document.getElementById("sumRequired").value;
    var sumUnr = document.getElementById("sumUnrequired").value;
    var names = ["Required", "Unrequired"];
    var values = [sumReq, sumUnr];

    var result = names.map(function(name, id) {
        return {
            y: values[id] || 0,
            label: name,
            link: "?showModalpie" + names[id]
        };
    });
    var chart = new CanvasJS.Chart("chartCommon", {
        title: {
            text: ""
        },
        animationEnabled: true,
        axisY: {
            title: ""
        },
        legend: {
            verticalAlign: "bottom",
            horizontalAlign: "center"
        },
        theme: "theme2",
        data: [
            {
                type: "pie",
                showInLegend: true,
                legendText: "{label}",
                radius: "220%",
                dataPoints: result
            }
        ]
    });
    chart.render();
    chart.options.data[0].click = function(e){
        var dataPoint = e.dataPoint;
        if(!dataPoint.exploded)
            window.open(dataPoint.link,'_top');
    };

    //chart Required

    var nameReq = document.querySelectorAll("#nameReq");
    var sumReq = document.querySelectorAll("#sumReq");

    var namesReq = Array.from(nameReq).map((e) => {
        return e.innerHTML;
    });

    var valuesReq = Array.from(sumReq).map((e) => {
        return Number(e.innerHTML);
    });

    var resultReq = namesReq.map(function(name, id) {
        return {
            y: valuesReq[id] || 0,
            label: name
        };
    });

    var chartReq = new CanvasJS.Chart("chartRequired", {
        title: {
            text: ""
        },
        animationEnabled: true,
        legend: {
            verticalAlign: "bottom",
            horizontalAlign: "center"
        },
        theme: "light2",
        data: [
            {
                type: "pie",
                showInLegend: true,
                legendText: "{label}",
                radius: "220%",
                dataPoints: resultReq
            }
        ]
    });
    chartReq.render();

    //char Unrequired

    var nameUnr = document.querySelectorAll("#nameUnr");
    var sumUnr = document.querySelectorAll("#sumUnr");

    var namesUnr = Array.from(nameUnr).map((e) => {
        return e.innerHTML;
    });

    var valuesUnr = Array.from(sumUnr).map((e) => {
        return Number(e.innerHTML);
    });

    var resultUnr = namesUnr.map(function(name, id) {
        return {
            y: valuesUnr[id] || 0,
            label: name
        };
    });

    var chartUnr = new CanvasJS.Chart("chartUnrequired", {
        title: {
        text: ""
        },
        animationEnabled: true,
        legend: {
            verticalAlign: "bottom",
            horizontalAlign: "center"
        },
        theme: "theme2",
        data: [
                {
                    type: "pie",
                    showInLegend: true,
                    legendText: "{label}",
                    radius: "220%",
                    dataPoints: resultUnr
                }
        ]
    });
    chartUnr.render();
}