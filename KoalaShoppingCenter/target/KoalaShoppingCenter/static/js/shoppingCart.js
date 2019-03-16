function queryShoppingCartItem(userID) {
    $.ajax({
        url:"/KoalaShoppingCenter/buyer/queryShoppingCartCommodity/"+userID,
        type:"GET",
        success:function (data) {
            if(data.code == 0){
                build_shoppingCart_table(data.res.result);
            }else {
                toastr.error(data.message);
            }
        }
    });
}

function build_shoppingCart_table(result) {
    //每次生成表格之前先清空
    var itemIndex=0;
    $("#shoppingCartContent tbody").empty();
    $.each(result,function (index,item) {

        var countIcon = $("<ul class='count'></ul>").append($("<li></li>").append($("<span class='num-reduce'>-</span>")))
            .append($("<li></li>").append($("<input type='text' class='input-num' value='"+item.amount+"'/>")))
            .append($("<li></li>").append($("<span class='num-plus'>+</span>")));
        var title = $("<td></td>").append(item.title);
        var amount = $("<td></td>").append(countIcon);
        var price = $("<td></td>").append(item.currentPrice);
        var delButton = $("<td></td>").append($('<button type="button" class="btn btn-warning delItem-btn" commodityID="'+item.commodityID+'">删除</button>'));


        //append方法返回后还是原来的元素
        $("<tr></tr>").append(title).append(amount).append(price).append(delButton).appendTo("#shoppingCartContent tbody");
    });
}


//为购物车中的删除按钮添加绑定事件
$(document).on("click",".delItem-btn",function (){
    //获取需要删除的购物车栏目的商品ID
    var commodityID = $(this).attr("commodityID");
    console.log(commodityID)
    //发送ajax请求
    $.ajax({
        url:"/KoalaShoppingCenter/buyer/deleteShoppingCartItem/"+commodityID,
        type:"DELETE",
        success:function (data) {
            if(data.code == 0){
                //删除成功
                toastr.success("删除成功");
                setTimeout("location.href = 'shoppingCart.jsp'",1000);
            }else {
                toastr.error(data.message);
            }
        }
    });
});
//数量加减
$(document).on("click",".num-reduce",function (){
    var num=$(this);
    var input = num.parent().next().find(".input-num");
    if(input.attr("disabled") == "disabled"){
        return ;
    }
    var inputNum=parseInt(input.val())  ;
    if(inputNum <= 1){
        inputNum=1;
    }else{
        inputNum=inputNum-1;
    }
    input.val(inputNum);
});

$(document).on("click",".num-plus",function (){
    var num=$(this);
    var input = num.parent().prev().find(".input-num");
    if(input.attr("disabled") == "disabled"){
        return;
    }
    var inputNum=parseInt(input.val());
        inputNum=inputNum+1;
    input.val(inputNum);
});

//input框只能输入数字
$(document).on("change",".input-num",function (){
    var value = $(this).val();
    var reg = /^[1-9]\d*$/;
    if(!reg.test(value)){
        toastr.error("购买数量必须为整数");
        $(this).val(1);
    }
});



//清空购物车(购买)
function buy() {
    //判断购物车是否为空
    if($("#shoppingCartContent tbody td").length<=0) {
        toastr.warning("购物车为空");
        return;
    }
    //将table中的数据转换成json数组 传递给后台
    var att={};
    var itemID,itemNum,itemPrice;//分别对应ID，商品购买数量，购买单价
    var Array=[];
    $("#shoppingCartContent tbody tr").each(function () {
        console.log("bijiben")
        var str=[];
        var childTD=$(this).children("td");
        itemID=childTD.eq(3).find("button").attr("commodityID");
        itemPrice=childTD.eq(2).text();
        itemNum=childTD.eq(1).find("input").val();
        console.log("一条数据为"+itemID+itemPrice+itemNum)
        att={
            'commodityID':itemID,
            'amount':itemNum,
            'boughtPrice':itemPrice
        };
        Array.push(att);
    })
    $.ajax({
        url:"/KoalaShoppingCenter/buyer/insertIntoFinance",
        type:"POST",
        dataType:"json",
        contentType:"application/json",
        data:JSON.stringify(Array),
        success:function (result) {
            if(result.code == 0){
                toastr.success("购买成功");
                setTimeout("location.href = 'shoppingCart.jsp'",1000);
            } else {
                toastr.error("购买失败,失败的原因可能是"+result.message);
            }
        }

    })


}