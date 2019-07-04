layui.use(['table','common','jquery'], function(){
    var table = layui.table;
    var common = layui.common;
    var $=layui.jquery;
    table.render({
        elem: '#test-table-demoEvent'
        ,height: 313
        ,url: '/xyGlobal/getList'
        ,cols: [[
            {field:'key', title: '数据库KEY', width:'20%'}
            ,{field:'value', title: '值',event: 'setSign', width:'40%'}
            ,{field:'name', title: '说明', width:'40%'}
        ]]
    });
    table.render({
        elem: '#wx-table-demoEvent'
        ,height: 313
        ,url: '/xyGlobal/getWxList'
        ,cols: [[
            {field:'key', title: '数据库KEY', width:'20%'}
            ,{field:'value', title: '值',event: 'setSign', width:'34%'}
            ,{field:'name', title: '说明', width:'40%'}
        ]]

    });
    //监听初始化按钮
    $(document).on('click','#initglobal',function(){
        layer.confirm('初始化global会把现有的参数覆盖掉是否确认初始化?', {
            btn: ['确定','取消'] //按钮
        }, function(index){
            layer.close();
            common.ajax('/xyGlobal/initGlobal', 'post', 'json', {}, function (res) {
                if(res.code=="0"){
                    //同步更新表格和缓存对应的值
                    layer.alert(res.message);
                    table.reload("test-table-demoEvent", { //此处是上文提到的 初始化标识id
                    });
                }else{
                    layer.alert(res.message);
                }
            });
        }, function(){
        });
    });
    //监听微信初始化按钮
    $(document).on('click','#initWxConfig',function(){
        layer.confirm('初始化global会把现有的参数覆盖掉是否确认初始化?', {
            btn: ['确定','取消'] //按钮
        }, function(index){
            layer.close();
            common.ajax('/xyGlobal/initWx', 'post', 'json', {}, function (res) {
                if(res.code=="0"){
                    //同步更新表格和缓存对应的值
                    layer.alert(res.message);
                    table.reload("wx-table-demoEvent", { //此处是上文提到的 初始化标识id
                    });
                }else{
                    layer.alert(res.message);
                }
            });
        }, function(){
        });
    });

    //监听单元格事件
    table.on('tool(test-table-demoEvent)', function(obj){
        var data = obj.data;
        if(obj.event === 'setSign'){
            layer.prompt({
                formType: 2
                ,title: '修改 数据库KEY 为 ['+ data.key +'] 初始化数据'
                ,value: data.value
            }, function(value, index){
                layer.close(index);
                //这里一般是发送修改的Ajax请求
                common.ajax('/xyGlobal/update', 'post', 'json', {
                    'key': data.key,
                    'value': value
                }, function (res) {
                    if(res.code=="0"){
                        //同步更新表格和缓存对应的值
                        layer.alert(res.message);
                        obj.update({
                            value: value
                        });
                    }else{
                        layer.alert(res.message);
                    }
                });

            });
        }
    });

    //监听微信配置单元格事件
    table.on('tool(wx-table-demoEvent)', function(obj){
        var data = obj.data;
        if(obj.event === 'setSign'){
            layer.prompt({
                formType: 2
                ,title: '修改 数据库KEY 为 ['+ data.key +'] 初始化数据'
                ,value: data.value
            }, function(value, index){
                layer.close(index);
                //这里一般是发送修改的Ajax请求
                common.ajax('/xyGlobal/updateWx', 'post', 'json', {
                    'key': data.key,
                    'value': value
                }, function (res) {
                    if(res.code=="0"){
                        //同步更新表格和缓存对应的值
                        layer.alert(res.message);
                        obj.update({
                            value: value
                        });
                    }else{
                        layer.alert(res.message);
                    }
                });

            });
        }
    });

});
