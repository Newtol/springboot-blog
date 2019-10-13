$(function () {
    maveMumu();
});



function getSoup() {
    var soup;
    $.ajax({
        url:"../api/soup",
        dataType:'json',
        async: false,
        success:function(json){
            soup = json.data.content;
        }
    });
    if(document.getElementById('message').style.display ==="none"){
        document.getElementById('message').style.display ="";
        $("#message").html("<p style='width: 100%'>"+soup+"</p>");
    }
    setTimeout(function(){document.getElementById("message").style.display="none";},4000);
}

// 小人物图标移动
function maveMumu() {
    var flag = false;
    var cur = {
        x:0,
        y:0
    }
    var nx,ny,dx,dy,x,y ;
    function down(){
        flag = true;
        var touch ;
        if(event.touches){
            touch = event.touches[0];
        }else {
            touch = event;
        }
        cur.x = touch.clientX;
        cur.y = touch.clientY;
        dx = div2.offsetLeft;
        dy = div2.offsetTop;
    }
    function move(){
        if(flag){
            var touch ;
            if(event.touches){
                touch = event.touches[0];
            }else {
                touch = event;
            }
            nx = touch.clientX - cur.x;
            ny = touch.clientY - cur.y;
            x = dx+nx;
            y = dy+ny;
            div2.style.left = x+"px";
            div2.style.top = y +"px";
            //阻止页面的滑动默认事件
            document.addEventListener("touchmove",function(){
                event.preventDefault();
            },false);
        }
    }
    //鼠标释放时候的函数
    function end(){
        flag = false;
    }
    var div2 = document.getElementById("spig");
    div2.addEventListener("mousedown",function(){
        down();
    },false);
    div2.addEventListener("touchstart",function(){
        down();
    },false);
    div2.addEventListener("mousemove",function(){
        move();
    },false);
    div2.addEventListener("touchmove",function(){
        move();
    },false);
    document.body.addEventListener("mouseup",function(){
        end();
    },false);
    div2.addEventListener("touchend",function(){
        end();
    },false);

}

//    获取天气信息
function getWeather() {
    var x = window.sessionStorage.getItem("isFrist");
    if(x != "1"){
        var weather;
        $.ajax({
            url:"../api/weather",
            dataType:'json',
            async: false,
            success:function(json){
                weather = json.data;
            }
        });
        if(weather == null){
            weather = "努力奋斗吧，少年！";
        }
        $("#message").html("<p style='width: 100%'>小新提醒您："+weather+"</p>");
        setTimeout(function(){document.getElementById("message").style.display="none";},4000);
        window.sessionStorage.setItem("isFrist","1");
    }
    return;
}