$(function(){
    $.get("https://image.baidu.com/search/index?tn=resultjson_com&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=index&fr=&hs=0&xthttps=111111&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=六道木属&oq=s&rsp=-1","",function(data){
        imgList = JSON.parse(data);
        for(i = 0; i < 3; ++i){
            $(".left").append("<img src"+imgList.data.middleURL
                +">")
        }
    });

})