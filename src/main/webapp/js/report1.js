window.onload = function() {

    var td = document.querySelectorAll("#name");
    var td1 = document.querySelectorAll("#sum");

    var names = Array.from(td).map((e) => {
        return e.innerHTML;
    });

    var values = Array.from(td1).map((e) => {
        return Number(e.innerHTML);
    });

    var result = names.map(function(name, id) {
        return {
            y: values[id] || 0,
            label: name
        };
    });

    var chart = new CanvasJS.Chart("chartContainer", {
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
                dataPoints: result
            }
    ]
    });
    chart.render();
}