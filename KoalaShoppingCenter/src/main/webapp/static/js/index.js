var ulCommodityList = $("#showCommodity");
function sendRequest(requestURL,userType) {
    $.ajax({
        url:requestURL,
        type:"GET",
        success:function (data) {
            if(data.code == 0){
                var resultdata = data.res.result;
                generateForm(resultdata,userType);
            }else {
                var errorMsg = result.message;
                toastr.error(errorMsg);
            }
        }
    });
}
function generateForm(resultdata,userType) {
    var a=273862;
    for(var i=0; i < resultdata.length;i++)
    {
        //处理一下图片的相对路径
        var imgUri = resultdata[i].picture;
        if(imgUri.charAt(0) == '.'){
            imgUri = imgUri.substring(3);
        }
        var item = '<div class="col-md-3">'
            +'<a href="/KoalaShoppingCenter/view/commodityInfo.jsp?commodityID='+resultdata[i].commodityID+'" class="link good">'
            +'<div class="img">'
            +'<img class="good-pic" src="'+imgUri+'" alert="'+resultdata[i].title+'">'
            +'</div>'
            +'<div class="good-bottom">'
            +'<div class="good-title">'+resultdata[i].title+'</div>'
            +'<div class="price good-price">￥'+resultdata[i].currentPrice+'</div>'
            if(userType == 1&&resultdata[i].soldAmount>0){
                item +='<div class="good-sold"><span>已售出'+resultdata[i].soldAmount+'件</span></div>'
                      +'</div>'
                      +'<span class="had"><b>已售出</b></span>';
            } else if (userType == 1&&resultdata[i].soldAmount<=0) {
                item +='<div class="good-unsold"><button class="btn btn-danger" commodityID="'+resultdata[i].commodityID+'">删除</button></div>'
                    +'</div>';
            } else {
                item += '</div>'
            }
            if(userType == 0 && resultdata[i].boughtPrice != null){
                item +='<span class="had"><b>已购买</b></span>';
            }
            item += ('</a></div>')
        ulCommodityList.append(item);
    }
    ulCommodityList.show();
}

function getAllCommoditys(userID,userType){
    ulCommodityList.empty();
    //先加载数据
	var requestUrl ="/KoalaShoppingCenter/buyer/queryAllCommoditys";
    if (userType == 0) {
        requestUrl = "/KoalaShoppingCenter/buyer/queryAllCommodityWithStatus";
    } else if (userType == 1) {
        requestUrl = "/KoalaShoppingCenter/seller/queryAllCommoditysBySeller/"+ userID;
    }
    sendRequest(requestUrl,userType);
}

function getUnPurchasedCommoditys(userID) {
    ulCommodityList.empty();
    //先加载数据
    var requestUrl ="/KoalaShoppingCenter/buyer/queryUnPurchasedCommoditys/" +userID;
    sendRequest(requestUrl);
}


$(document).on("click",".good-unsold button",function (e){
    var commodityID = $(this).attr("commodityID");
    $.ajax({
        url:"/KoalaShoppingCenter/seller/deleteUnSellCommodity/"+commodityID,
        type:"DELETE",
        success:function (data) {
            if(data.code == 0){
                toastr.success("删除成功");
                setTimeout("location.href = 'index.jsp'",1000);
            }else {
                toastr.error(data.message);
            }
        }
    });
    e.preventDefault();
});