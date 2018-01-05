<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/assembly_base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>机器学习</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
<meta name="renderer" content="webkit">
<link rel="stylesheet" href="${ctx}/resources/css/jquery-ui-1.11.4.css">
<link rel="stylesheet" href="${ctx}/resources/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/resources/js/mllib/assembly/datapretreatment.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/mllib/assembly/featureengineering.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/mllib/assembly/machinelearning.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/mllib/assembly/sourcetarget.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/mllib/assembly/statisticalanalysis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/mllib/assembly/textanalysis.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/mllib/assembly/datasource.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/mllib/assembly/assembly.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/dialog.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/mllib/assembly/assembly_exam.js"></script>

<style>
	svg.active path:nth-child(2){
		stroke-dasharray: 10;
		animation: dash 8s infinite linear;
	}
	@keyframes dash {
		to {
			stroke-dashoffset: 1000;
		}
	}
</style>
	
</head>
<body>
<article class="pai-container">
	<!--left-nav-->
	<nav class="pai-nav">
		<div class="pai-logo">
			<span class="pai-logo-icon"></span>
		</div>
		<ul class="pai-nav-list">
			<li class="btn-home-page">
				<span class="icon icon-home-1"></span>
				<span class="text">首页</span>
			</li>
			<li class="btn-experiment">
				<span class="icon icon-lib-24"></span>
				<span class="text">实验</span>
			</li>
			<li class="btn-data-origin">
				<span class="icon icon-data-source-24"></span>
				<span class="text">数据源</span>
			</li>
			<li class="btn-module">
				<span class="icon icon-component-24"></span>
				<span class="text">组件</span>
			</li>
		</ul>
		<ul class="pai-nav-bottom pai-nav-list">
			<li class="pai-nav-dropdown pai-nav-account">
				<span class="icon icon-user-o-24"></span>
				<span class="text" data-locale="lgyydx">${loginuser.name}</span>
			</li>
			<li class="pai-nav-exit">
				<a href="javascript:window.close();">关闭</a>
			</li>
		</ul>
	</nav>
	<!--/left-nav-->
	<!--main-->
	<div class="pai-workspace">
		<!--left-DropDown-->
		<aside  id="demo" class="pai-side-left">
			<div id="leftMenu" style="height: 100%">
			<section class="pai-toolbox-sec ">
			<div class="leftList">
					<div class="pai-project-list">
						<div class="pai-project-select">
							<span class="pai-search-icon"></span>
							<input class="pai-search-input" type="text" placeholder="搜索">
						</div>
					</div>
					<div class="pai-dt-list-box" >
					<c:forEach items="${fileList}" var="fileList">
							 <div  class="pai-dt-list">
								<div class="pai-test-box of ">
									<i class="tree-node-expander tree-node-bottom active"></i>
									<span class="tree-node-label-wrapper-branch">
											<span class="tree-node-icon icon-star"></span>
											<span>
												${fileList.name}
											</span>
									</span>
								</div>
								<c:forEach items="${libraryAssemblyMap}" var="libraryMap">
								     <c:if test="${libraryMap.key.id == fileList.id}">
								         <c:forEach items="${libraryMap.value}" var="librarys" varStatus="status">
											<dl class="pai-menu-list" style="margin-bottom: 0;display:none;">
												<dd class="item" data-index="${status.index+1}">
													<span class="tree-node-indenter"></span>
													<span class="element-default"><span class="tree-node-icon"></span>
													<span class="element-text"  id="${librarys.id}"  >${librarys.name}</span>
													</span>
												</dd>
											</dl>
										</c:forEach>
								     </c:if>
								</c:forEach>
							</div>
				    </c:forEach>
					</div>
			    </div>
			</section>
		    <!-- 数据源部分 begin-->
			<section class="pai-toolbox-sec pai-left-menu">
				<div class="pai-project-list">
					<div class="pai-project-select">
						<span class="pai-search-icon"></span>
						<input class="pai-search-input" type="text" placeholder="搜索">
					</div>
				</div>
				<div  class="pai-dt-list">
					<div class="pai-test-box of">
						<i class="tree-node-expander tree-node-bottom active"></i>
						<span class="tree-node-label-wrapper-branch ">
								<span class="tree-node-icon icon-star"></span>
								<span>
									公共表
								</span>
							</span>
					</div>
					<dl class="pai-menu-list" style="margin-bottom: 30px;display:none;">
						<c:forEach items="${tables}" var="table">
								<dd>
									<span class="tree-node-indenter"></span>
									<span class="element-default">
											<span class="tree-node-icon"></span>
											<span class="element-text"  id="3">${table.name}</span>
									</span>
								</dd>
						</c:forEach>
					</dl>
				</div>
				<div class="pai-btn-create of">
					<div class="fr mr10">
						<span class="pai-create-et btn-crate-table">
							<span class="icon icon-plus-o"></span>
							<span class="text">创建表</span>
						</span>
					</div>
				</div>
			</section>
			<!-- 数据源部分 end-->
			<!--实验-->
				<section class="pai-toolbox-sec pai-left-menu" style="display: none;">
					<div class="pai-project-list">
						<div class="pai-project-select">
							<span class="pai-search-icon"></span>
							<input class="pai-search-input" type="text" placeholder="搜索">
						</div>
					</div>
					<div class="pai-dt-list">
						<div class="pai-test-box pai-btn-test of">
							<i class="tree-node-expander tree-node-bottom active"></i>
							<span class="tree-node-label-wrapper-branch ">
								<span class="tree-node-icon icon-star"></span>
								<span>
									我的实验
								</span>
							</span>
						</div>
						<div class="pai-menu-list" style="margin-bottom: 30px;display:none;" id="myexp">
							<!-- <div class="pai-et-box">
								<span class="tree-node-indenter"></span>
								<span class="element-default">
									<span class="tree-node-icon"></span>
									<span class="tree-node-label">Tensorflow图片分类</span>
								</span>
								<span class="pai-del">
									<span class="icon icon-del-o"></span>
								</span>
							</div>
							<div class="pai-et-box">
								<span class="tree-node-indenter"></span>
								<span class="element-default">
									<span class="tree-node-icon"></span>
									<span class="tree-node-label">Tensorflow图片分类</span>
								</span>
								<span class="pai-del">
									<span class="icon icon-del-o"></span>
								</span>
							</div> -->
						</div>
					</div>
					<div class="pai-btn-create of">
						<div class="fr mr10">
							<span class="pai-create-et new-et-btn">
								<span class="icon icon-plus-o"></span>
								<span class="text">新建实验</span>
							</span>
						</div>
					</div>
				</section>
				<!--/实验-->
			</div>
		</aside>
		<!--/left-DropDown-->
		<!--project-region-->
		<section class="pai-bpmn" >
			<div class="pai-bpmn-metadata">
				<div class="pai-bpmn-left">
						<span class="box">
							<span class="text">${experiments[0].name}</span>
							<input name="expid"  type="hidden" value="${experiments[0].id}"/>
						</span>
			<%--	<ul class="pai-dropdown" style="display: none;">
					    <c:forEach  items="${experiments}"  var="item">
					    		<li class="selected">
									<span class="icon i-checked" ></span>
									<input name="expid"  type="hidden" value="${item.id}"/>
									<span class="text-item">${item.name}</span>
									<span class="btn-close icon icon-close-l"></span>
								</li>
					    </c:forEach>
					</ul>--%>
				</div>
				<div class="pai-bpmn-right" >
					<ul style="margin-bottom: -7px">
						<li>
							<span class="icon icon-group" title="框选节点"></span>
						</li>
						<li>
							<span class="icon icon-zoom-in" title="放大"></span>
						</li>
						<li>
							<span class="icon icon-zoom-out" title="缩小"></span>
						</li>
						<li>
							<span class="icon icon-real-size" title="实际尺寸"></span>
						</li>
						<li>
							<span class="icon icon-fitscreen" title="适应画布"></span>
						</li>
						<li>
							<span class="icon icon-fullscreen" title="全屏"></span>
						</li>
						<li class="w_5">
							<i class="pai-right-line" ></i>
						</li>
						<li>
							<span class="icon icon-operation" title="运行" onclick="run()"></span>
						</li>
						<li>
							<span class="icon icon-deploy" title="部署"></span>
						</li>
						<li>
							<span class="icon icon-share" title="分享"></span>
						</li>
					</ul>
				</div>
			</div>
			<div  class="pai-bpm-paper ">
				<div id="container" class="pane-scroll">

				</div>
			</div>
		</section>
		<!--/project-region-->
		<!--right-parameter-->
		<aside class="pai-side-right">
			<!--实验属性-->
			<div class="dms-prop-wrap">
				<header class="dms-prop-header has-tab">
					<ul class="dms-prop-tab">
						<li class="selected">实验属性</li>
					</ul>
				</header>
				<section class="dms-prop-body">
					<div class="dms-prop-page dms-prop-input">
						<form>
							<%-- <div class="dms-prop-label">
								<label>
									<span class="dms-prop-label-text">项目名称</span>
									<span class="dms-prop-tooltip" title="lgy">${experiments[0].name}</span>
								</label>
							</div> --%>
							<div class="dms-prop-label mt10">
								<label>
									<span class="dms-prop-label-text">创建日期</span>
									<span class="dms-prop-tooltip" id="experimentDate" >${experiments[0].createDate}</span> 
								</label>
							</div>
							<div class="form-group mt10">
								<label>名称</label>
								<input type="text"  placeholder="" class="form-control" id="experimentName" value="${experiments[0].name}" readonly="readonly" />
							</div>
							<div class="form-group mt10">
								<label>描述</label>
								<div>
									<textarea class="dms-describe-box" id="experimentDescription" name="experimentDes" readonly="readonly">${experiments[0].description}</textarea>
								</div>
							</div>
						</form>
					</div>
				</section>
			</div>
			<!--/实验属性-->
			<!--参数设置-->
			<div class="dms-prop-wrap model_2_box" style="display: none">
				<header class="dms-prop-header has-tab">
					<ul class="dms-prop-tab">
						<li class="selected">参数设置</li>
					</ul>
				</header>
				<section class="dms-prop-body">
					<div class="dms-prop-page">
						<div class="dms-prop-item  dms-prop-item-r-dataset">
							<div class="dms-prop-label">
								<label>输入源</label>
							</div>
							<div class="dms-prop-input">
								<ul>
									<li>
										<span>t1</span>
										<em title='fromName"pai_online_project.heart_disease_prediction-1"'>fromName"pai_online_project.heart_disease_prediction-1"</em>
									</li>
									<li>
										<span>t1</span>
										<em title='fromName"pai_online_project.heart_disease_prediction-1"'>fromName"pai_online_project.heart_disease_prediction-1"</em>
									</li>
								</ul>
							</div>
						</div>
						<div class="dms-prop-item">
							<div class="dms-prop-label has-long-tooltip">
								<label>
									SQL脚本
									<i>最后一句必须为Select语句</i>
								</label>
							</div>
							<div class="dms-prop-text">
								<textarea  name=""  cols="30" rows="10"></textarea>
							</div>
						</div>
					</div>
				</section>
			</div>
			<!--/参数设置-->
			<!--字段(参数)设置-->
			<div class="dms-prop-wrap model_3_box" style="display: none;">
				<header class="dms-prop-header has-tab">
					<ul class="dms-prop-tab dms-field-tab">
						<li class="selected">字段设置</li>
						<li>参数设置</li>
					</ul>
				</header>
				<section class="dms-prop-body dms-field-box">
					<div class="dms-prop-page dms-prop-input">
						<form>
							<div class="form-group">
								<label>选择特征列 <span class="dms-prop-tooltip">必选</span></label>
								<a  class="btn pai-select-field">已选择<span class="number">14</span>个字段</a>
							</div>
							<div class="form-group">
								<label>选择目标列 <span class="dms-prop-tooltip">必选</span></label>
								<input  placeholder="" class="form-control" >
								<i class="icon dms-target-list"></i>
							</div>
							<div class="form-group">
								<label>枚举类特征 <span class="dms-prop-tooltip">可选</span></label>
								<button class="btn">选择<span class="number"></span>字段</button>
							</div>
							<div class="ui-checkbox">
								<input class="checkBox" type="checkbox" name="" value="">
								<span class="checkBox">是k:v,k:v稀疏特征</span>
							</div>
						</form>
					</div>
					<div class="dms-prop-page" style="display: none;">
						<div class="dms-prop-page dms-prop-input">
							<form>
								<div class="form-group">
									<label>特征选择方法 <span class="dms-prop-tooltip">可选</span></label>
									<select class="form-control">
										<option>IV</option>
										<option>Gini增益</option>
										<option>信息增益</option>
									</select>
								</div>
								<div class="form-group">
									<label>挑选TopN特征 <span class="dms-prop-tooltip">可选</span></label>
									<input type="number" step="1" value="0.0" placeholder="" class="form-control" >
								</div>
								<div class="form-group">
									<label>连续特征分区方式</label>
									<select class="form-control">
										<option>自动化分区</option>
										<option>等距离分区</option>
									</select>
								</div>
							</form>
						</div>
					</div>
				</section>
			</div>
			<!--/字段(参数)设置-->
			<!--字段设置，参数设置，执行调优-->
			<div class="dms-prop-wrap model_6_box" style="display: none;">
				<header class="dms-prop-header has-tab">
					<ul class="dms-prop-tab dms-setTuning-tab">
						<li class="selected">字段设置</li>
						<li>参数设置</li>
						<li>执行调优</li>
					</ul>
				</header>
				<section class="dms-prop-body dms-setTuning-box">
					<div class="dms-prop-page dms-prop-input">
						<form>
							<div class="form-group">
								<label>训练特征列 <span class="dms-prop-tooltip">必选 支持Double/Int类型字段</span></label>
								<a  class="btn pai-select-field">已选择<span class="number">13</span>个字段</a>
							</div>
							<div class="form-group">
								<label>目标列 <span class="dms-prop-tooltip">必选</span></label>
								<input  placeholder="" class="form-control" >
								<i class="icon dms-target-list"></i>
							</div>
							<div class="form-group">
								<label>正类值 <span class="dms-prop-tooltip">必选 eg. 0/1分类中1是正类</span></label>
								<input  placeholder="" class="form-control" >
							</div>
							<div class="ui-checkbox">
								<input class="checkBox" type="checkbox" name="" value="">
								<span class="checkBox">是k:v,k:v稀疏特征</span>
							</div>
						</form>
					</div>
					<div class="dms-prop-page" style="display: none">
						<form>
							<div class="form-group">
								<label>正则项<span class="dms-prop-tooltip">可选</span></label>
								<select class="form-control">
									<option>按比例拆分</option>
									<option>按阈值拆分</option>
								</select>
							</div>
							<div class="form-group">
								<label>最大迭代次数<span class="dms-prop-tooltip">可选</span></label>
								<input type="number" step="1" value="100" min="0" placeholder="" class="form-control" >
							</div>
							<div class="form-group">
								<label>正则系数<span class="dms-prop-tooltip">可选 正则类型为None时此值无效</span></label>
								<input type="number" step="0.1" value="2" placeholder="" class="form-control" >
							</div>
							<div class="form-group">
								<label>最小收敛误差</label>
								<input type="number" step="0.01" value="0.000001" placeholder="" class="form-control" >
							</div>
						</form>
					</div>
					<div class="dms-prop-page" style="display: none;">
						<div class="dms-prop-page dms-prop-input">
							<form>
								<div class="form-group">
									<label>核数目</label>
									<input type="number"  step="1" value="" placeholder="默认自动调整" class="form-control">
								</div>
								<div class="form-group">
									<label>每个核的内存大小(MB)</label>
									<input type="number"  step="1" value="" placeholder="默认自动调整" class="form-control" >
								</div>
							</form>
						</div>
					</div>
				</section>
			</div>
			<!--/字段设置，参数设置，执行调优-->
		</aside>
		<!--/right-parameter-->
	</div>
	<!--/main-->
	<!--index-->
	<section class="pai-home">
		<div class="pai-home-template pt30">
			<div class="pai-template-list">
				<div class="pai-template-item template-empty">
					<div class="template-wrap">
						<span class="icon icon-plus-o"></span>
						<span class="text">新建案例</span>
					</div>
				</div>
		    </div>
			<div class="btn-more-template tac">
				<a href="javascript:void(0)">更多模板</a>
			</div>
		</div>
	</section>
	<!--/index-->
</article>
<!--弹窗-->
<!--创建表-->
<div class="hidden typeDemo">
            <select class="form-control field-type" name="columnType" id="columnType">
                <c:forEach items="${typeDemo}" var="type">
                    <option value="${type.name}">${type.name}</option>
                </c:forEach>
            </select>
 </div>
 <form id="add-datasource-form" enctype="multipart/form-data">
	<div class="modal createTable-box fade" tabindex="-1" role="dialog" style="z-index: 1112">
	<div class="modal-dialog modal-w" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">创建表</h4>
			</div>
			<div class="modal-body h450">

				<div class="wrap of mt20">
					<div class="right_wrap">
						<header class="wrap-header">
							<ul class="selected-tab" style="left:2px">
								<li class="selected">表结构</li>
								<li>数据</li></ul>
						</header>
						<section class="wrap-content">
							<div class="dms-prop-page selected-list is-overflow">
								<div>
									<div id="tableName-error" class="">
										<label id="table-info" style="font-weight: 400">
											表名
											<!-- <span class="form-item-tips name-tip">表不存在, 请先设计表结构</span> -->
										</label>
										<div class="form-group pr">
											<input type="text" class="form-control w230" name="name" value="" placeholder="请输入表名" id="tableName">
											<!-- <label  class="form-control-error icon-close-c" >请输入表名</label> -->
										</div>
									</div>
									<!-- 
									<div class="form-group table-schema-editor">
										<label for="tableLifecycle">保存时长</label>
										<div class="input-group">
											<input class="form-control" id="tableLifecycle" step="1" min="0" value="180" name="lifecycle" type="number">
											<div class="input-group-addon">天</div>
										</div>
									</div>
									 -->
									<div class="form-group table-schema-editor flex-grow-1">
											表结构
										<label id="table-stru">
											<span class="ml10" style="color:#289de9">点击 + 添加一项 (主键列id默认创建)</span>
											<span class="btn-add icon-plus-o"></span>
										</label>
										<div class="flex-grow-1">
											<div class="flex-grow-inner schema-wrap">
												<table class="table table-bordered">
													<thead>
														<tr>
															<th>列名</th>
															<th>类型</th>
															<!-- <th>是否分区字段</th> -->
															<th></th>
														</tr>
													</thead>
													<tbody class="table-tbody">
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="dms-prop-page selected-raw" style="display: none">
								<div class="form-group">
									<p class="attention-text">注意：上传的数据将追加搭到原表, 请上传 .txt 或 .csv 文件</p>
								</div>
								<div class="form-group pr">
									<div class="btn btn-upload btn-block btn-primary">选择文件</div>
									<input id="file-btn" class="form-control fill-hide" accept="text/csv" name="file" id="file" type="file">
								</div>
								 
								<div id="file-Read" class="row delimiter">
									<div class="col-sm-6">
										<div class="form-group">
											<label>行分隔符（只能是 \r，\n 或 \r\n）</label>
											<input  readonly class="form-control" value="\n" name="rowDelimiter"  aria-invalid="false" type="text">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<label>列分隔符</label>
											<input readonly class="form-control" value="," maxlength="1" name="columnDelimiter" aria-invalid="false" type="text">
										</div>
									</div>
								</div>
								<div class="form-group data-show">
									<label>数据预览<span class="file-name"></span><span class="data-tips">只显示前一百条数据</span></label>
									<div class="flex-grow-1">
										<div class="flex-grow-inner preview-wrap">
											<div class="table-wrap">
												<table class="table table-bordered table-striped table-hover">
													<tbody id="fileRead-tbody">
														<!-- <tr>
															<td>1</td>
															<td>2</td>
															<td>3</td>
															<td>4</td>
															<td>5</td>
															<td>6</td>
														</tr> -->
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								 
							</div>
						</section>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="datasource_btn_save">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</form>
<!--/创建表-->
<!--选择字段-->
<form id="add-field-form">
<div class="modal createPortGroup-box fade" tabindex="-1" role="dialog" style="z-index: 1112"  id="my-modal">
	<div class="modal-dialog modal-w" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">选择字段</h4>
			</div>
			<div class="modal-body h450">
				<div class="form-group pr">
					<input type="text" class="form-control w230" value="" id="choose_field_id" placeholder="输入关键字搜索列，包含关键字即可">
					<i class="pai-input-search"></i>
				</div>
				<div class="wrap of mt20">
					<div class="left_wrap">
						<div class="of">
							<header class="wrap-header">
								<div class="ui-checkbox">
									<input class="checkBox" type="checkbox" name="" value="">
									<span class="checkBox">全选</span>
								</div>
							</header>
							<section class="wrap-content zero-accordion">
								<!-- <ul class="zero-accordion">
									<li class="zero-accordion-item">
										<div class="ui-checkbox">
											<input class="checkBox" type="checkbox" data-name="check-id" name="" value="">
											<span class="checkBox">trestbps</span>
										</div>
									</li>
								</ul> -->
							</section>
						</div>
					</div>
					<div class="right_wrap">
						<header class="wrap-header">
							已选
							<ul class="selected-tab">
								<li class="selected">列表</li>
								<li>编辑</li></ul>
						</header>
						<section class="wrap-content">
							<div class="dms-prop-page selected-list is-overflow">
								<div class="table-header">
									<table class="table table-bordered">
										<thead>
										<tr>
											<th><span class="icon icon-delete btn-delete"></span></th>
											<th>字段</th>
											<th>类型</th>
										</tr>
										</thead>
									</table>
								</div>
								<div class="table-body" id="">
									<table class="table table-bordered table-hover">
										<tbody class="check-list" id="">
										</tbody>
									</table>
								</div>
							</div>
							<div class="dms-prop-page selected-raw" style="display: none">
								<textarea class="form-control txtRaw"></textarea>
							</div>
						</section>
					</div>
				</div>
			</div>
			<div class="modal-footer choose-save">
				<button type="button" class="btn btn-primary" id="">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</form>
<!--/选择字段-->
<!--选择目标列-->
<div class="modal targetListBox fade" tabindex="-1" role="dialog" style="z-index: 1112">
	<div class="modal-dialog modal-w" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">选择目标列</h4>
			</div>
			<div class="modal-body h450">
				<form class="modal-form">
					<div class="form-group pr">
						<input type="text" class="form-control w230" value="" placeholder="输入关键字搜索列，包含关键字即可">
						<i class="pai-input-search"></i>
					</div>
				</form>
				<div class="wrap of mt20">
					<div class="left_wrap">
						<div class="of">
							<header class="wrap-header">
								<div>
									<span class="checkBox">全选</span>
								</div>
							</header>
							<section class="wrap-content">
								<!-- <ul class="zero-accordion">
									<li>
										<div class="zero-accordion-title active">
											<a class="J-btn-arrow icon-arrow-down-l"></a>
											<span>
													BIGINT
												</span>
										</div>
									</li>
									<li class="zero-accordion-item">
										<div class="ui-radiobox">
											<input class="radioBox" type="radio" name="" value="">
											<span class="radioBox">trestbps</span>
										</div>
									</li>
								</ul> -->
							</section>
						</div>
					</div>
					<div class="right_wrap">
						<header class="wrap-header">
							已选
							<ul class="selected-tab">
								<li class="selected">列表</li>
								<li>编辑</li>
							</ul>
						</header>
						<section class="wrap-content wrap-right-wrap">
							<div class="dms-prop-page selected-list">
								<div class="table-header">
									<table class="table table-bordered">
										<thead>
										<tr>
											<th><span class="icon icon-delete btn-delete"></span></th>
											<th>字段</th>
											<th>类型</th>
										</tr>
										</thead>
									</table>
								</div>
								<div class="table-body">
									<table class="table table-bordered table-hover">
										<tbody>
										<!-- <tr>
											<td>
												<span class="icon icon-delete btn-delete"></span>
											</td>
											<td>sex</td>
											<td>STRING</td>
										</tr> -->
										</tbody>
									</table>
								</div>
							</div>
							<div class="dms-prop-page selected-raw" style="display: none">
								<textarea class="form-control txtRaw"></textarea>
							</div>
						</section>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/选择目标列-->
<!--查看数据-->
<div class="modal view-data-box fade" tabindex="-1" role="dialog" style="z-index: 1112">
	<div class="modal-dialog modal-w" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">数据探查</h4>
			</div>
			<div class="modal-body h450">
				<div class="exploration-table">
					<div class="ui-datatable-head">
						<table class="table">
							<!-- <thead>
								<tr>
									<th title="序号">
										序号
									</th>
									<th title="sex">
										sex
									</th>
									<th title="cp">
										cp
									</th>
								</tr>
							</thead> -->
						</table>
					</div>
					<div class="ui-datatable-body">
						<table class="table">
							<!-- <tbody>
								<tr>
									<td>1</td>
									<td>1</td>
									<td>1</td>
								</tr>
							</tbody> -->
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="data_submit">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal view-more-data-box fade" tabindex="-1" role="dialog" style="z-index: 1112">
	<div class="modal-dialog modal-w" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">数据探查</h4>
			</div>
			<div class="modal-body h450">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#one" aria-controls="home" role="tab" data-toggle="tab">训练数据</a></li>
					<li role="presentation"><a href="#two" aria-controls="profile" role="tab" data-toggle="tab">测试数据</a></li>
				</ul>
				<!-- Tab panes -->
				<div class="tab-content mt10">
					<div role="tabpanel" class="tab-pane active" id="one">
						<div class="exploration-table" id="show-data-left">
							<div class="ui-datatable-head">
								<table class="table">
									<!-- <thead>
									<tr>
										<th title="序号">
											序号
										</th>
									</tr>
									</thead> -->
								</table>
							</div>
							<div class="ui-datatable-body">
								<table class="table">
									<!-- <tbody>
									<tr>
										<td>1</td>
									</tr>
									</tbody> -->
								</table>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="two">
						<div class="exploration-table" id="show-data-right">
							<div class="ui-datatable-head">
								<table class="table">
									<!-- <thead>
									<tr>
										<th title="序号">
											序号
										</th>
									</tr>
									</thead> -->
								</table>
							</div>
							<div class="ui-datatable-body">
								<table class="table">
									<!-- <tbody>
									<tr>
										<td>1</td>
									</tr>
									</tbody> -->
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="more_data_submit">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/查看数据-->
	<!--查看分析报告-->
	<div class="modal view-data-chart fade" tabindex="-1" role="dialog"
		style="z-index: 1112">
		<div class="modal-dialog modal-w" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">特征工程</h4>
				</div>
				<div class="modal-body h450" style="width: 500px; float: left;">
					<div class="exploration-table">
						<div class="ui-datatable-head">
							<table class="table">
								<thead>
									<tr>
										<th style="width: 50px;"></th>
										<th style="width: 110px;">字段</th>
										<th>图表</th>
									</tr>
								</thead>
							</table>
						</div>
						<div class="ui-datatable-body">
							<table class="table">
								<tbody id="tbodyID">
									<%-- <c:forEach items="${columns}" var="col" varStatus="status">
										<tr>
											<td style="width: 50px; cursor: pointer" id="statusID"
												align="center" title="${col.column}"
												onclick="tdButton(this)"><br>
											<br>${col.rownum}</td>
											<td style="width: 110px;">
												<div id="buttonEcharts${status.count}"
													class="modal-body h450"
													style="width: 100px; height: 99px; line-height:99px;cursor: pointer;"
													onclick="columnButton(this)">${col.column}</div>
											</td>
											<td>
												<div id="dy${status.index}" class="modal-body h450"
													style="width: 320px; height: 100px; cursor: pointer; background: #fff; box-shadow: none;"
													title="${col.column}" onclick="divButton(this)"></div>
											</td>
										</tr>
									</c:forEach> --%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div id="dy" class="modal-body h450"
					style="width: 500px; height: 450px; float: left;"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!--重命名-->
<div class="modal rename-box fade" tabindex="-1" role="dialog" style="z-index: 1112">
	<div class="modal-dialog modal-w" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">重命名</h4>
			</div>
			<div class="modal-body">
				<form class="modal-form">
					<div class="form-group">
						<input type="text" class="form-control" maxlength="33" placeholder="请输入文件夹名称">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/重命名-->
<!--评估报告-->
<div class="modal assessReport-box fade" tabindex="-1" role="dialog" style="z-index: 1112">
	<div class="modal-dialog modal-w" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">评估报告</h4>
			</div>
			<div class="modal-body">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">统计信息</a></li>
					<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">图表</a></li>
				</ul>
				<!-- Tab panes -->
				<!--指标数据-->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="home">
						<div class="ui-data-head">
							<table class="table table-bordered">
								<!-- <thead>
									<tr>
										<th>
											<span class="dt-name">index</span>
										</th>
										<th>
											<span class="dt-name">value</span>
										</th>
									</tr>
								</thead> -->
							</table>
						</div>
						<div class="ui-data-body">
							<table class="table table-bordered">
								<!-- <tbody>
									<tr class="empty">
										<td colspan="10000">
											<div class="ui-datatable-empty">暂无数据</div>
										</td>
									</tr>
									<tr>
										<td class="animated" title="AUC">AUC</td>
										<td class="animated" title="0.8712">0.8712</td>
									</tr>
									<tr>
										<td class="animated" title="KS">KS</td>
										<td class="animated" title="0.6197">0.6197</td>
									</tr>
									<tr>
										<td class="animated" title="F1 Score">F1 Score</td>
										<td class="animated" title="0.7586">0.7586</td>
									</tr>
								</tbody> -->
							</table>
						</div>
					</div>
					<!--/指标数据-->
					<!--图表-->
					<div role="tabpanel" class="tab-pane" id="profile">
						<div class="evaluate-charts">
							<div class="chart-detail" id="charts-report">
							</div>
						</div>
					</div>
					<!--/图表-->
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-defau
				lt" data-dismiss="modal">关闭</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/评估报告-->
<!--新建案列-->
<div class="modal copy-test-box fade" tabindex="-1" role="dialog" style="z-index: 1112">
	<div class="modal-dialog modal-w" role="document">
		<div class="modal-content">
		<form class="modal-form">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">新建案例</h4>
			</div>
			<div class="modal-body">
					<div class="form-group" style="height:20%">
						<label class="col-sm-2 control-label">名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" maxlength="33" placeholder="请输入案例名称" name="title">
						</div>
					</div>
					<div class="form-group mt10">
						<label class="col-sm-2 control-label">描述</label>
						<div class="col-sm-10">
							<textarea class="form-control" maxlength="33" placeholder="请输入案例描述" name="description"></textarea>
						</div>
					</div>
					<div class="form-group mt10" style="margin-left:75px;margin-right: 10px;">
						<div class="form-group pr">
							<div class="btn btn-upload btn-block btn-primary">选择文件</div>
							<input id="file-btn" class="form-control fill-hide"  name="file"  type="file" onchange="fileText.value=this.value; "/>
							<input type="text" class="form-control" id="fileText" name="fileText" readonly="readonly" style="width: 300px; background:#FFF " placeholder="请选择上传文件" />		
						</div>
					</div>
					<div class="form-group" style="height:20%;margin-top:5px">	
						<label class="col-sm-2 control-label"></label>					
						<div class="col-sm-10">
							<input type="text" class="fileExplain"  placeholder="支持文件类型：/.doc/.txt/.xls/.pdf" readonly="readonly" style="width: 300px;border:0px"/>
						</div>
					</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/新建案列-->
<!--新建实验-->
<div class="modal copy-experiment-box fade" tabindex="-1" role="dialog" style="z-index: 1112">
	<div class="modal-dialog modal-w" role="document">
		<div class="modal-content">
		<form class="modal-form">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">新建实验</h4>
			</div>
			<div class="modal-body">
					<div class="form-group" style="height:20%">
						<label class="col-sm-2 control-label">名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" maxlength="33" placeholder="请输入实验名称" name="title">
						</div>
					</div>
					<div class="form-group mt10">
						<label class="col-sm-2 control-label">描述</label>
						<div class="col-sm-10">
							<textarea class="form-control" maxlength="33" placeholder="请输入实验描述" name="description"></textarea>
						</div>
					</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--/新建实验-->
<!--/弹窗-->
<!--我的实验(右键)-->
<div class="ui-context-menu-layer" style="display: none">
	<ul class="ui-context-menu">
		<li class="ui-context-menu-item new-test-btn">
			<i class="icon icon-rename"></i>
			<span class="item-name">新建空白实验</span>
		</li>
		<li class="ui-context-menu-item new-template-btn">
			<i class="icon icon-rename"></i>
			<span class="item-name">新建模板实验</span>
		</li>
	</ul>
</div>
<!--/我的实验(右键)-->
<!--我的实验工作区(右键)-->
<div class="ui-workarea-menu-layer"  style="display: none">
	<ul class="ui-context-menu">
		<li class="ui-context-menu-item btn-rename">
			<i class="icon icon-rename"></i>
			<span class="item-name">重命名</span>
		</li>
		<li class="ui-context-menu-item removeElement">
			<i class="icon icon-del"></i>
			<span class="item-name">删除</span>
		</li>
		<li class="ui-context-menu-item">
			<span class="item-name icon-copy">复制</span>
		</li>
		<li class="ui-context-menu-item">
			<i class="icon icon-test"></i>
			<span class="item-name">添加注释</span>
		</li>
		<li class="ui-context-menu-item separator" role="menuItem"></li>
		<li class="ui-context-menu-item btn-execute-begin">
			<i class="icon icon-execute-begin"></i>
			<span class="item-name">从此处开始执行</span>
		</li>
		<li class="ui-context-menu-item">
			<i class="icon icon-execute-end"></i>
			<span class="item-name" onclick="exethispoint()">执行到此处</span>
		</li>
		<li class="ui-context-menu-item">
			<i class="icon icon-execute-node"></i>
			<span class="item-name">执行该节点</span>
		</li>
		<li class="ui-context-menu-item btn-report" style="display: none;">
			<i class="icon icon-execute-node"></i>
			<span class="item-name">查看评估报告</span>
		</li>
		<li class="ui-context-menu-item separator" role="menuItem"></li>
		<li class="ui-context-menu-item btn-view-data">
			<i class="icon icon-check-data"></i>
			<span class="item-name">查看数据</span>
		</li>
		<li class="ui-context-menu-item separator" role="menuItem"></li>
		<li  class="ui-context-menu-item btn-view-chart">
			<i class="icon icon-check-chart"></i>
			<span class="item-name">查看分析报告</span>
		</li>
	</ul>
</div>
<!--/我的实验工作区(右键)-->
	<c:import url="/common/dialog.jsp"></c:import>
</body>
</html>