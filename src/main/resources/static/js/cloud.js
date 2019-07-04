layui.use(['table','common','jquery'], function(){
    var table = layui.table;
    var common = layui.common;
    var $=layui.jquery;
    table.render({
      elem: '#test-table-demoEvent'
      ,height: 800
      ,url: '/cloud/getList'
      ,cols: [[
        {field:'key', title: '数据库KEY', width:'20%'}
        ,{field:'value', title: '值',event: 'setSign', width:'30%'}
        ,{field:'name', title: '说明', width:'50%'}
      ]]
    });
    //监听初始化按钮
    $(document).on('click','#initglobal',function(){
    	 layer.confirm('初始化global会把现有的参数覆盖掉是否确认初始化?', {
    	        btn: ['确定','取消'] //按钮
    	    }, function(index){
    	        layer.close();
    	        common.ajax('/cloud/initGlobal', 'post', 'json', {}, function (res) {
    	        	if(res.code=="0"){
    	        		//同步更新表格和缓存对应的值
    	        		layer.alert(res.data);
    	        		table.reload("test-table-demoEvent", { //此处是上文提到的 初始化标识id
    	                });
    	        	}else{
    	        		layer.alert(res.data);
    	        	}
    	        });
    	    }, function(){
    	    });
    });

    //监听初始化其他数据
    $(document).on('click','#initOther',function(){
        layer.confirm('初始化媒体云初始数据,部署项目时只需要初始化一次,是否确认初始化?', {
            btn: ['确定','取消'] //按钮
        }, function(index){
            layer.close();
            common.ajax('/cloud/initOther', 'post', 'json', {}, function (res) {
                if(res.code=="0"){
                    //同步更新表格和缓存对应的值
                    layer.alert(res.data);
                    table.reload("test-table-demoEvent", { //此处是上文提到的 初始化标识id
                    });
                }else{
                    layer.alert(res.data);
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
          ,title: '修改 数据库KEY 为 ['+ data.key +'] 的用户签名'
          ,value: data.value
        }, function(value, index){
          layer.close(index);
          //这里一般是发送修改的Ajax请求
			common.ajax('/cloud/update', 'post', 'json', {
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
