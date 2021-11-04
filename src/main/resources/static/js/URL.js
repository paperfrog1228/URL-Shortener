function urlDataSend() {
    var url= $("#address").val();
    if(!url.includes("http"))
        url="http:\/\/"+url;
    var data={
        address :url,
    };
    var messageDTO=data;
    $.ajax({
        url: window.location.href.split('?')[0]+"/url",
        data: messageDTO,
        type:"POST",
    }).done(function (fragment) {
        $("#URLTable").replaceWith(fragment);
    });
}
