$(function() {
    venueStatistics();
});

function choiceType(type) {
    if(type.text=="场馆统计信息") {
        $('#no_pay').addClass("active");
        $('#no_execute').removeClass("active");
        $('#finished').removeClass("active");
        venueStatistics();
    }else if(type.text=="会员统计信息"){
        $('#no_pay').removeClass("active");
        $('#no_execute').addClass("active");
        $('#finished').removeClass("active");
        userStatistics();
    }else if(type.text=="财务信息"){
        $('#no_pay').removeClass("active");
        $('#no_execute').removeClass("active");
        $('#finished').addClass("active");
        ticketStatistics();
    }
}
function ticketStatistics() {
    $('.statistics_venue').hide();
    $('.statistics_user').hide();
    $('.statistics_finance').show();
    var onlinePrice = 0;
    var onSpotPrice = 0;
    $.ajax({
        type:"POST",
        url:"/getOnlinePrice",
        async:false,
        success:function (data) {
            onlinePrice = data;
        },
        error:function () {
            alert("Sth Wrong!");
        }
    });
    $.ajax({
        type:"POST",
        url:"/getOnSpotPrice",
        async:false,
        success:function (data) {
            onSpotPrice = data;
        },
        error:function () {
            alert("Sth Wrong!");
        }
    });
    var total_price = (parseFloat(onlinePrice)+parseFloat(onSpotPrice))*0.05;
    $('#total_finance').html(total_price+"元");
    var myChart = echarts.init(document.getElementById('finance_pie'));
    var option = {
        title: {
            text: '经理线上线下收入对比',
            x:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['线上','线下']
        },
        series: [
            {
                name:'收入（元）',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:parseFloat(onlinePrice)*0.05, name:'线上'},
                    {value:parseFloat(onSpotPrice)*0.05, name:'线下'}
                ]

            }
        ]
    };
    myChart.setOption(option);


    var venues = [];
    var venueName = [];
    $.ajax({
        type:"POST",
        url:"/getAllVenue",
        async:false,
        success:function (data) {
            var list= $.parseJSON(data);
            venues = list;
            for(var j=0;j<venues.length;j++){
                venueName.push(venues[j].venueName);
            }
        },
        error:function () {
            alert("Sth Wrong!");
        }
    });
    var myChart1 = echarts.init(document.getElementById('finance_bar'));
    var option1 = {
        title: {
            text: '从各场馆获得收入对比',
            x:'right'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:venueName
        },
        series: [
            {
                name:'收入（元）',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:function () {
                    var res = []
                    for(var i=0;i<venues.length;i++){
                        res.push({
                            value:(parseFloat(venues[i].finance)/0.95*0.05),
                            name:(venues[i].venueName)
                        })
                        console.log(venues[i]);
                    }

                    return res;
                }()

            }
        ]
    };
    myChart1.setOption(option1);
}

function userStatistics() {
    $('.statistics_venue').hide();
    $('.statistics_user').show();
    $('.statistics_finance').hide();
    var users = [];
    var numOfVip0 = 0;
    var numOfVip1 = 0;
    var numOfVip2 = 0;
    var consumption = [];
    var user_name = [];
    $.ajax({
        type:"POST",
        url:"/getAllUser",
        async:false,
        success:function (data) {
            var list= $.parseJSON(data);
            users = list;
            for(var j=0;j<users.length;j++){
                if(users[j].level==0){
                    numOfVip0 = numOfVip0+1;
                }
                if(users[j].level==1){
                    numOfVip1 = numOfVip1+1;
                }
                if(users[j].level==2){
                    numOfVip2 = numOfVip2+1;
                }
                consumption.push(users[j].consumption);
                user_name.push(users[j].userName);
            }
        },
        error:function () {
            alert("Sth Wrong!");
        }
    });
    var myChart = echarts.init(document.getElementById('user_pie'));
    var barChart = echarts.init(document.getElementById('user_bar'));
    var option = {
        title: {
            text: '各会员等级人数',
            x:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['VIP0','VIP1','VIP2']
        },
        series: [
            {
                name:'人数',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:numOfVip0, name:'VIP0'},
                    {value:numOfVip1, name:'VIP1'},
                    {value:numOfVip2, name:'VIP2'}
                ]

            }
        ]
    };
    var option1 = {
        title: {
            text: '各会员消费金额'
        },
        tooltip: {},
        legend: {
            data:['消费（元）']
        },
        xAxis: {
            data: user_name
        },
        yAxis: {},
        series: [{
            name: '消费（元）',
            type: 'bar',
            data: consumption
        }]
    };
    myChart.setOption(option);
    barChart.setOption(option1);
}

function venueStatistics() {
    $('.statistics_venue').show();
    $('.statistics_user').hide();
    $('.statistics_finance').hide();
    var venues = [];
    var venueName = [];
    var finance = [];
    $.ajax({
        type:"POST",
        url:"/getAllVenue",
        async:false,
        success:function (data) {
            var list= $.parseJSON(data);
            venues = list;
            var performTotal = 0;
            for(var j=0;j<venues.length;j++){
                venueName.push(venues[j].venueName);
                finance.push(venues[j].finance);
                performTotal = performTotal + getPerformNum(venues[j].idCode);
            }
            $('#venueNum').html(venues.length);
            $('#performanceNum').html(performTotal);
        },
        error:function () {
            alert("Sth Wrong!");
        }
    });
    var myChart = echarts.init(document.getElementById('venue_pie'));
    var barChart = echarts.init(document.getElementById('venue_bar'));

// 指定图表的配置项和数据
    var option = {
        title: {
            text: '各场馆发布演出数饼状图',
            x:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:venueName
        },
        series: [
            {
                name:'发布演出总数',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:function () {
                    var res = []
                    for(var i=0;i<venues.length;i++){
                        res.push({
                            value:(getPerformNum(venues[i].idCode)),
                            name:(venues[i].venueName)
                        })
                        console.log(venues[i]);
                    }

                    return res;
                }()

            }
        ]
    };
    var option1 = {
        title: {
            text: '各场馆收入'
        },
        tooltip: {},
        legend: {
            data:['收入（元）']
        },
        xAxis: {
            data: venueName
        },
        yAxis: {},
        series: [{
            name: '收入（元）',
            type: 'bar',
            data: finance
        }]
    };

// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    barChart.setOption(option1);
}

//找到每个场馆发布演出的数目
function getPerformNum(venueID) {
    var performNum = 0;
    $.ajax({
        type:"POST",
        url:"/getPerformancesByVenue",
        async:false,
        data:{"venueID":venueID},
        success:function (data){
            var list = $.parseJSON(data);
            performNum = list.length;

        },
        error:function () {
            alert("Sth Wrong！");
        }
    });
    return performNum;
}