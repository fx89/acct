export class GoogleChartsStyle {
    public static createOptions() {
        let ret = {
            hAxis: {
                textStyle:{color: '#999', fontSize: 9},
                slantedText:true,
                slantedTextAngle:90
            },
            vAxis:{
                textStyle:{color: '#999', fontSize: 9},
                titleTextStyle : {color : "#999"},
                viewWindowMode:'explicit',
                viewWindow: {
                    max:3000,
                    min:500
                }
            },

            colors: ['#574', '#754', '#884'],
            backgroundColor: "#282828",

            legend: {position: 'top', textStyle: {color: '#AAA'}},

            tooltip: { isHtml: true }
        };

        return ret;
    }
}
