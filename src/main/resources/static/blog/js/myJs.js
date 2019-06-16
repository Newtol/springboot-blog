$(function () {
    getContentType();
    getRecommandationList();
    getReadRankList();
})

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