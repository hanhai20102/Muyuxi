function queryFinanceItem(userID) {
    $.ajax({
        url:"/KoalaShoppingCenter/buyer/queryPurchasedCommoditys/",
        type:"GET",
        data:"userID="+userID,
        success:function (data) {
            if(data.code == 0){
                build_finance_table(data.res);
            }else {
                alert(data.message);
            }
        }
    });
}

function build_finance_table(res) {
    //每次生成表格之前先清空
    $("#financeContent tbody").empty();
    var total = res.total;
    var data = res.result;
    $.each(data, function (index, item) {
        var picture = $("<td></td>").append($('<a href="commodityInfo.jsp?commodityID='+item.commodityID+'"></a>').append($('<img width="100px" height="100px" src="' + item.picture + '">')));
        var title = $("<td></td>").append($('<a href="commodityInfo.jsp?commodityID='+item.commodityID+'">'+item.title+'</a>'));
        var amount = $("<td></td>").append(item.amount);
        var purchaseTime = $("<td></td>").append(item.purchaseTime);
        var price = $("<td></td>").append(item.boughtPrice);
        //append方法返回后还是原来的元素
        $("<tr></tr>").append(picture).append(title).append(amount).append(purchaseTime).append(price).appendTo("#financeContent tbody");
    });
    //为总计赋值
    var totalPrice = $('<td>总计:' + total + '元</td>');
    $("<tr></tr>").append($("<td colspan='4'></td>")).append(totalPrice).appendTo("#financeContent tbody");
}

