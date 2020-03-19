$(function(){
    $("#submit").click(function(){
        var form=$("#form");
        var data=getFormData(form);
        $.ajax({
            type: "post",
            url: "/user/modify",
            dataType: "text",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(data),
            success: function (msg) {
                alert(msg);
                window.location.href="/user/details"
            }
            //注意测试一下传输的data数据是js对象还是json对象格式
        })
    })
    // 获取表单数据
    function getFormData(form){
        var data=form.serialize();
        data=decodeURI(data);
        //name=张三&sex=male&area=heping&hobby[]=movie&hobby[]=music&intro=个人介绍一下吧
        var arr=data.split("&");
        //["name=张三", "sex=male", "area=heping", "hobby[]=movie", "hobby[]=music", "intro=个人介绍一下吧"]
        var item,key,value,newData={};
        for(var i=0;i<arr.length;i++){
            item=arr[i].split("=");
            key=item[0];
            value=item[1];
            if(key.indexOf("[]")!=-1){
                key=key.replace("[]","");
                if(!newData[key]){
                    newData[key]=[];
                }
                newData[key].push(value);
            }else{
                newData[key]=value;
            }
        }
        return newData;
        //{name: "张三", sex: "male", area: "heping", hobby: (2) ["movie", "music"], intro: "个人介绍一下吧"}
    }
})