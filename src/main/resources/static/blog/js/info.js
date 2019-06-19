var contentId;
$(function () {
    contentId = getContentId();
    getContent(contentId);
    getPraise();
})
// 从url获取文章ID
function getContentId() {
    var contentId;
    // 获取contentId
    var url = location.search; //获取url中"?"符后的字串
    if (url.indexOf("?") != -1) {  //判断是否有参数
        var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
        strs = str.split("=");  //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
        contentId = (strs[1]);     //直接弹出第一个参数 （如果有多个参数 还要进行循环的）
    }
    return contentId;
}


// 获取文章并解析
function getContent(contentId) {
    var data;
    var errorMsg;
    $.ajax({
        url:"./content?contentId="+contentId,
        dataType:'json',
        async: false,
        success:function(json){
            data = json.data;
            errorMsg = json.errorMsg;
        }
    });
    if(data === null){
        $("#testEditorMdview").html('<textarea id="appendTest"></textarea>');
        $("#appendTest").val(errorMsg);
    }else {
        // 解析文章内容
        $("#testEditorMdview").html('<textarea id="appendTest"></textarea>');
        $("#appendTest").val(data.content);
        $(".news_title").html(data.title);
        $(".news_about").html(data.summary);
        $(".author").html("作者:"+data.writer);
        $(".timer").html("时间："+data.createTime);
        $(".view").html(data.readNum+"人已阅读");
        getNearContent(data.createTime);
        var stringkey = data.stringKey.split(",");
        for (var i in stringkey) {
            $(".tags").append("<a href=''>"+stringkey[i]+"</a>")
        }
    }
    //转换开始,第一个参数是上面的div的id
    editormd.markdownToHTML("testEditorMdview", {
        htmlDecode: "style,script,iframe", //可以过滤标签解码
        emoji: true,
        taskList:true,
        tex: true,               // 默认不解析
        flowChart:true,         // 默认不解析
        sequenceDiagram:true,  // 默认不解析
    });
}

// 获取点赞数
function getPraise() {
    var praiseNum=0;
    $.ajax({
        url:"/blog/praise?contentId="+contentId,
        dataType:'json',
        async: false,
        success:function(json){
            praiseNum = json.data;
        }
    });
    $(".diggit").html("赞："+praiseNum);
}


// 获取上一篇、下一篇文章
function getNearContent(createTime) {

    var nearContentList;
    $.ajax({
        url:"/blog/nearContent?createTime="+createTime,
        dataType:'json',
        async: false,
        success:function(json){
            nearContentList = json.data;
        }
    });
    var exp = nearContentList[0];
    if(exp != undefined){
        $("#nextContent").html("上一篇："+"<a href='/blog/info?contentId="+exp.content_id+"'>"+exp.title+"</a>")
    }
    var exp1 = nearContentList[1];
    if(exp1 != undefined){
        $("#previousContent").html("下一篇："+"<a href='/blog/info?contentId="+exp1.content_id+"'>"+exp1.title+"</a>")
    }
}


