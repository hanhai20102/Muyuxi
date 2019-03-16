$("input[name='pic']:radio").click(function(){
    var value = $('input:radio[name="pic"]:checked').val()  //获取选中的radio的值
    if(value=='file'){
        $("#urlUpload").hide();
        $("#fileUpload").show();

    };
    if(value=='url'){
        $("#fileUpload").hide();
        $("#urlUpload").show();

    };
});

/*图片上传功能*/
$("#btn-upload").click(function () {
    var fileName = $("#fileUp").val();  //C:\fakepath\考拉.png
    if(fileName == ""){
        toastr.warning("请选择需要上传的文件");
        return false;
    }
    var imgarr=fileName.split('\\'); //分割
    var imgName=imgarr[imgarr.length-1];  //图片名称
    var suffix = imgName.lastIndexOf('.'); //获取 . 出现的位置
    var ext = imgName.substring(suffix, imgName.length).toUpperCase(); //获取文件后缀
    var file = $('#fileUp').get(0).files[0]; //获取上传的文件
    var fileSize = file.size;           //获取上传的文件大小
    var maxSize = 1048576;              //最大1MB
    if(ext !='.PNG' && ext !='.GIF' && ext !='.JPG' && ext !='.JPEG' && ext !='.BMP'){
        toastr.error('文件类型错误,请上传图片类型');
        return false;
    }else if(parseInt(fileSize) >= parseInt(maxSize)){
        toastr.error('上传的文件不能超过1MB');
        return false;
    }else{
        $.ajaxFileUpload({
            url: "/KoalaShoppingCenter/seller/uploadImg",
            type: 'POST',
            dataType: 'json',
            fileElementId: 'fileUp',
            success:function (result) {
                if(result.code == 0){
                    var url = result.res.imgUrl;
                    $("#imageForUrl").attr("value",url);
                    $("#imgShow").attr("src",url);
                    toastr.success("上传成功");
                }else {
                    toastr.warning(result.message);
                }
            }
        });
        return false;
    }
});

//校验input数据
function validate_input(ele,minLen,maxLen,name) {
    var actualLen = $(ele).val().length;
    if(actualLen>maxLen||actualLen<minLen){
        toastr.warning("输入的"+name+"长度需要在"+minLen+"-"+maxLen+"之间");
        return false;
    }
    return true;
}

//用户输入图片的url地址显示在右侧的img中
$("#imageForUrl").change(function () {
    var value = $("#imageForUrl").val();
    $("#imgShow").attr("src",value);
});