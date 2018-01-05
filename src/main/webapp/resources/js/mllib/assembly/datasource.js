$(function(){	
	//创建表
	popBox("btn-crate-table","createTable-box","600");
	//创建表(初始化)
	$(".btn-crate-table").on("click",function(){
		$(".createTable-box .selected-tab li").removeClass("selected").first().addClass("selected");
		$(".createTable-box .dms-prop-page").hide().first().show();
		
	});
	
	/*创建表(添加,删除表结构)*/
	function addTable(){
		//获取添加按钮，tbody
		var $addBtn = $(".btn-add");
		var $tbody = $(".btn-add").parents(".table-schema-editor").find("tbody");
		//追加表结构
		$addBtn.on("click",function(){
			$tbody.append('<tr class="detail"><td><input class="form-control"  name="columnName" placeholder="请输入列名" type="text"></td>'+
					'<td>'+
					'<label class="select-hacker">'+$('.typeDemo').html()+
					/*'<select name="columnType" class="form-control field-type">'+
					'<option value="bigint" selected="">bigint</option>'+
					'<option value="double">double</option>'+
					'<option value="decimal">decimal</option>'+
					'</select>'+*/
					'</label>'+
					'</td>'+
					/*'<td class="pr">'+
					'<div class="ui-checkbox">'+
					'<input class="checkBox" type="checkbox" ><span class="checkBox"></span>'+
					'</div>'+
					'</td>'+*/
					'<td><span class="btn-delete icon-close-o"></span></td>'+
					'</tr>')
			/*//隐藏tfoot
			$addBtn.parents(".table-schema-editor").find("tfoot").hide()*/
			//删除表结构
			var $delBtn = $(".btn-delete")
			$delBtn.on("click",function(){
				$(this).parents("tr").remove();
				
			})
		})
	}
	addTable();
	
	$('#datasource_btn_save').on('click',save_datasource);
	$('#tableName').on('blur',checkName);
	$('#file-btn').on('change', fileRead);
	    

});

/**
 * 表单数据提交校验
 */
function checkForm(){
	var f_tname = true;
	var f_cname = true;
	var f_file = true;
	var tableName = $('input[name="name"]').val();
	var fileName = $('#file-btn').val();
	var reg =/^[a-zA-Z][a-zA-Z0-9_]*$/;
	
	if(tableName == "" || tableName == null || tableName == undefined){
		f_tname = false;
	}else{
		f_tname = reg.test(tableName);//表名校验
	}
	
	var l = $('#add-datasource-form .table-tbody').children().length
	if(l > 0){
		$("#add-datasource-form .table-tbody .detail").each(function(){//列名校验
			var r = true;
			var columnName = $(this).find('input[name="columnName"]').val();
			r = reg.test(columnName);
			if(!r){
				f_cname = r;
				return false;
			}
		});
	}else{
		return false;
	} 
	
	if(fileName==null || fileName == "" || fileName == undefined){
		f_file = false;
	}
	console.log("tableName->"+f_tname+" columnName->"+f_cname+" file->"+f_file);
	return f_tname && f_cname && f_file;	
}

/**
 * 数据源数据加载预览
 */
function fileRead(){
	var url = window.ctx + '/datasource/fileRead';
	var form = new FormData(document.getElementById("add-datasource-form"));
	console.log(form);
	 $.ajax({
         url:url,
         type:"post",
         data:form,
         processData:false,
         contentType:false,
         success:function(re){
        	 if(re.successful == true){
        		 fileDataAppend(re.data);
        	 }else{
        		 alert(re.message);
        		 fileDataAppend(re.data);
        	 }
         },
         error:function(e){
        	 alert("服务器错误");
             
         }
     });    

}
/**
 * 数据源文件数据预览拼接
 * @param obj
 */
function fileDataAppend(obj){
	$('#fileRead-tbody').children().remove();
	var trs ='';
	$.each(obj,function(n,value){
		var tds ='';
		for(var i = 0;i<value.split(',').length;i++){
			//console.log(value.split(',')[i]);
			tds += '<td>'+value.split(',')[i].replace(/\"/g, "")+'</td>'
		}
		trs += '<tr>'+tds+'</tr>'
	});
	$('#fileRead-tbody').append(trs);
	$('.file-name').text($('#file-btn').val());
}

/**
 * 表结构数据拼接回显
 * @param obj
 */
function appendTr(obj){
	var html = '';
	 $.each(obj,function(n,value) {   
         var trs = "";  
           trs += '<tr>'+
	        	   	  '<td><input type="hidden" value='+value.name+' name="columnName"/>' + value.name +'</td>'+
	           		  '<td><input type="hidden" value='+value.type+' name="columnType"/>' + value.type +'</td>'+
	           		  '<td>'+'</td>'+
           		  '</tr>';  
           html += trs;         
         });  
	   $('#table-stru').hide();//回显时需要隐藏字段编辑按钮
       $(".table-tbody").append(html);  
}
/**
 * 
 */
function removeTr(){
	var $child = $($('.table-tbody').children());
	console.log($child);
	var l = $child.length;
	if(l > 0) $child.remove();
	
}

/**
 * 表校验提示信息
 * @param obj
 */
function tableInfo(obj){
	var html='';
	$('#span-mess').remove();
	html += '<span class="form-item-tips name-tip" id="span-mess">'+obj.message+'</span>';
	$('#table-info').append(html);
}

/**
 * 表名校验
 */
function checkName(){
	var tableName = $('input[name="name"]').val();
	$('#tableName-error').removeClass('has-error');
	if(tableName == "" || tableName == null || tableName == undefined){
			$('#tableName-error').addClass('has-error');
		return;
	}
	
	var url = window.ctx + '/datasource/checkTableName';
	var tableName = $('input[name = "name"]').val();
	$.post(url,{'name':tableName},function(re){
		if(re.successful == false){
			tableInfo(re);
			removeTr();
			$('#table-stru').show();
		}
		if(re.successful == true){
			tableInfo(re);
			appendTr(re.data);
		}	
	});
}

/**
 * 数据源存储
 */
function save_datasource(){
	if(!checkForm()){		
		alert("请检查建表数据【表名，列名，数据文件】");
		return;
	}
	
	
	var url = window.ctx + '/datasource/save';
	var form = new FormData(document.getElementById("add-datasource-form"));
	console.log(form);
	 $.ajax({
         url:url,
         type:"post",
         data:form,
         processData:false,
         contentType:false,
         success:function(re){
        	 if(re.successful == true){
        		 alert("数据源创建成功");
        		 location.href=  window.ctx + '/assembly/';
        	 }else{
        		 alert(re.message);
        	 }
         },
         error:function(e){
        	 alert("服务器错误");
             
         }
     });    
	
}



