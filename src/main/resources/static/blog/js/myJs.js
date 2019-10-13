$(function () {
    getContentType();
    getRecommandationList();
    getReadRankList();
    getFrindUrl();
});



// 获取友链
function getFrindUrl() {
    var friendUrlList;
    $.ajax({
        url:"../friendUrl",
        dataType:'json',
        async: false,
        success:function(json){
            friendUrlList = json.data;
        }
    });
    for (var i in friendUrlList){
        var a = "<a href='"+friendUrlList[i].url+"'>"+friendUrlList[i].name+"</a>";
        $("#keyWord").append(a);

    }
}

// 获取站长推荐
function getRecommandationList() {
    var recommandationList;
    $.ajax({
        url:"/blog/recommendationList",
        dataType:'json',
        async: false,
        success:function(json){
            recommandationList = json.data;
        }
    });
    for(var i in recommandationList){
        $("#recommandationList").append("<li><a href='/blog/info?contentId="+recommandationList[i].content_id+"'>"+recommandationList[i].title+"</a></li>")
    }
}
// 获取阅读排行榜
function getReadRankList() {
    var readRankList;
    $.ajax({
        url:"/blog/readRankList",
        dataType:'json',
        async: false,
        success:function(json){
            readRankList = json.data;
        }
    });
    for(var i in readRankList){
        $("#readRankList").append("<li><a href='/blog/info?contentId="+readRankList[i].content_id+"'>"+readRankList[i].title+"</a></li>")
    }
}

//  获取文章标签
function getContentType() {
    $.ajax({
        url:'/blog/contentType',
        dataType:'json',
        async: false,
        success:function(json){
            var b = json.data;
            for (var i in b) {
                var str = b[i].name +"("+b[i].num+")";
                $("#contentType").append("<li><a href='/blog/list?contentType="+b[i].name+"'>"+str+"</a></li>");
            }
        }
    });
}


// 更改标题
function changeTitle() {
    function c() {
        document.title = document[a] ? '震惊！某码农居然居然做出....': d
    }
    var a = document.title;
    var b = document.title;
    var d = document.title;
    typeof document.hidden !== 'undefined' ? (a = 'hidden', b = 'visibilitychange')
        : typeof document.mozHidden !== 'undefined' ? (a = 'mozHidden', b = 'mozvisibilitychange')
        : typeof document.webkitHidden !== 'undefined' && (a = 'webkitHidden', b = 'webkitvisibilitychange')
    typeof document.addEventListener === 'undefined' && typeof document[a] === 'undefined' || document.addEventListener(b, c, !1)
}