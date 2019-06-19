$(function () {
    // 复制增加版权信息
    document.oncopy = addLink;

// 禁止右键菜单
    document.oncontextmenu=stop;

// 静止使用F12审查元素
    document.onkeydown = banF12;
});


function addLink() {
    var selection = window.getSelection();
    var pagelink =  '<br>---------------------<br>'
        + '<br>'
        + '作者：Newtol<br> 源地址：' + document.location.href
        + '<br>来源：Newtol个人博客<br> 版权声明：本文为博主原创文章，著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处，附上博文链接！';
    var copytext = selection + pagelink;
    var newdiv = document.createElement('div');
    newdiv.style.position = 'absolute';
    newdiv.style.left = '-99999px';
    document.body.appendChild(newdiv);
    newdiv.innerHTML = copytext;
    selection.selectAllChildren(newdiv);
    window.setTimeout(function () {
        document.body.removeChild(newdiv);
    }, 100);
}

function stop(){
    alert("右键菜单已被禁用，复制请用快捷键“Ctrl + C”")
    return false;
}

function banF12() {
    if(window.event && window.event.keyCode == 123) {
        alert("F12被禁用");
        event.keyCode=0;
        event.returnValue=false;
    }
    if(window.event && window.event.keyCode == 13) {
        window.event.keyCode = 505;
    }
    if(window.event && window.event.keyCode == 8) {
        alert(str+"\n请使用Del键进行字符的删除操作！");
        window.event.returnValue=false;
    }
}