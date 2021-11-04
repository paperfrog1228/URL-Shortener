function urlDataSend() {
    var data={
        address : $("#address").val(),
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
