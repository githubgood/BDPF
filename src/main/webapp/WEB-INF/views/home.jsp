<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<title>大数据-首页</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
	<meta name="renderer" content="webkit">
	<script src="${ctx}/resources/js/echarts.js"></script>
	<script src="${ctx}/resources/js/home.js"></script>
</head>
<body class="ly-bg">
<%@ include file="/common/head.jsp"%>
<!-- /公共头引入 -->
<!--main-->
<article>
	<!--banner-->
	<div class="banner">
		<div id="oSlideFun">
			<div class="bannerbox">
				<div class="banner-img" href="">
					<img src="${ctx}/resources/images/index/index-banner.png" alt="banner"/>
				</div>
				<div class="w1100 pr">
					<div class="pa banner-btn" style="display:none;">
						<a class="new_vm_btn" href="javascript:void(0)">新建虚拟机</a>
						<a class="deploy_vm_btn ml20" href="javascript:void(0)">使用模板部署虚拟机</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/banner-end-->
	<!--content(begin)-->
	<section class="w1100 pt40">
		<div class="statistics-box clearfix">
			<p class="sta-title tac">资源统计</p>
			<input type="hidden" id="run" >
			<input type="hidden" id="stop" >
			<input type="hidden" id="other" >
			<div class="statistics-item">
				<div class="vm-box  mr40">
					<p class="vm-title">
						<span class="fsize16">虚拟机状态</span>
						<span class="fr fsize14" >总数<i class="ml15" id="cloudNub"></i></span>
					</p>
					<div class="vm-pie">
						<div id="vm-pic" ></div>
						<div class="vm-data-stat">
							<div class="of">
								<p class="fl fsize14 c-2b3" ><i class="opera_box mr10"></i>运行<i style="margin-left: 1em;"></i><i class="mr10" id="prun"></i></p>
								<p class="fl fsize14 c-2b3" ><i class="stop_box mr10"></i>停止<i style="margin-left: 1em;"></i><i class="mr10" id="pstop"></i></p>
							</div>
							<div class="of mt15">
								<p class="fl fsize14 c-2b3" ><i class="other_box mr10"></i>其他<i style="margin-left: 1em;"></i><i class="mr10" id="pother"></i></p>
							</div>
						</div>
					</div>
				</div>
				<div class="host-box mr40">
					<p class="vm-title">
						<span class="fsize16">网络状态</span>
						<span class="fr fsize14">总数<i class="ml15" id="netNub"></i></span>
					</p>
					<div class="host-stat">
						<div id="host-stat" ></div>
						<div class="host-data-stat">
							<div class="of">
								<p class="fl fsize14 c-2b3"><i class="opera_box mr10"></i>运行<i class="mr10" id="nstart"></i></p>
								<p class="fl fsize14 c-2b3"><i class="stop_box mr10"></i>停止<i class="mr10" id="nstop"></i></p>
							</div>
							<div class="of mt15">
								<p class="fl fsize14 c-2b3"><i class="other_box mr10"></i>其他<i class="mr10" id="nother"></i></p>
							</div>
						</div>
					</div>
				</div>
				<div class="other-box">
					<p class="vm-title">
						<span class="fsize16">资源使用情况</span>
					</p>
					<div class="other-item">
						<ul>
							<li class="of">
								<dl class="fl">
									<dt class="other-line-1 mr15">
										<i class="bt-icon bt-icon-2"></i>
									</dt>
									<dd class="other-line-2" >
										<p class="fsize14 c-2b3">内存使用情况</p>
										<p class="fsize14 c-0b1" id="ram_text"></p>
									</dd>
								</dl>
							</li>
							<li class="of mt50">
								<dl class="fl">
									<dt class="other-line-1 mr15">
										<i class="bt-icon bt-icon-3"></i>
									</dt>
									<dd class="other-line-2" >
										<p class="fsize14 c-2b3">cpu使用情况</p>
										<p class="fsize14 c-0b1" id="cpu_text"></p>
									</dd>
								</dl>
								
							</li>
							<li class="mt50">
								<dl>
									<dt class="other-line-1 mr15">
										<i class="bt-icon bt-icon-5"></i>
									</dt>
									<dd class="other-line-2">
			 							<p class="fsize14 c-2b3">虚拟机信息</p>
										<p class="fsize14 c-0b1" id="instances_text"></p>
									</dd>
								</dl>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		 <div class="config-box">
			<!-- <p class="conf-title tac">资源配置</p>
			<div class="config-item">
				<p class="config-box-num tac">5</p>
				<div class="conf-pool pool-1 tac">
					<p class="mt20">
						<i class="pool-icon pool-icon-1"></i>
					</p>
					<p class="fsize14 mt10 c-2b3">计算池</p>
					<p class="base-color fsize24 mt5">1</p>
				</div>
				<div class="conf-pool pool-2 tac">
					<p class="mt20">
						<i class="pool-icon pool-icon-2"></i>
					</p>
					<p class="fsize14 mt10 c-2b3">存储池</p>
					<p class="base-color fsize24 mt5">2</p>
				</div>
				<div class="conf-pool pool-3 tac">
					<p class="mt20">
						<i class="pool-icon pool-icon-3"></i>
					</p>
					<p class="fsize14 mt10 c-2b3">网络池</p>
					<p class="base-color fsize24 mt5">2</p>
				</div>
				<div class="conf-pool pool-4 tac">
					<p class="mt20">
						<i class="pool-icon pool-icon-4"></i>
					</p>
					<p class="fsize14 mt10 c-2b3">主机</p>
					<p class="base-color fsize24 mt5">2</p>
				</div>
				<div class="conf-pool pool-5 tac">
					<p class="mt20">
						<i class="pool-icon pool-icon-5"></i>
					</p>
					<p class="fsize14 mt10 c-2b3">数据存储</p>
					<p class="base-color fsize24 mt5">2</p>
				</div>
				<div class="conf-pool pool-6 tac">
					<p class="mt20">
						<i class="pool-icon pool-icon-6"></i>
					</p>
					<p class="fsize14 mt10 c-2b3">分布式交换机</p>
					<p class="base-color fsize24 mt5">2</p>
				</div>
			</div> -->
		</div> 
	</section>
	<!--content(end)-->
</article>
<!--/main-->
<!--footer(begin)-->
<%@ include file="/common/foot.jsp"%>
<!--footer(end)-->
	<!--loading-->
 <div class="cd-mask">
	<img src="${ctx}/resources/images/loading.gif" alt="">
</div> 
<!--/loading-->
</body>
</html>