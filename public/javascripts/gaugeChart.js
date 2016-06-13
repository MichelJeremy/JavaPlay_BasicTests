function drawGaugeChart(param1) {    
    var chart = c3.generate({
        bindto: '#humidity-gauge',
        data: {
            columns: [
                ['data', param1]
            ],
            type: 'gauge'
        },
        gauge: {
    //        label: {
    //            format: function(value, ratio) {
    //                return value;
    //            },
    //            show: false // to turn off the min/max labels.
    //        },
        min: 0,
        max: 100,
        units: '%',
    //    width: 39 // for adjusting arc thickness
        }, 
    //    color: {
    //        pattern: ['#000000', '#000000', '#000000', '#000000'], // the three color levels for the percentage values.
    //        threshold: {
    //            unit: 'value', // percentage is default
    //            max: 200, // 100 is default
    //            values: [30, 60, 90, 100]
    //        }
    //    },
        size: {
            height: 180
        }
    });
}