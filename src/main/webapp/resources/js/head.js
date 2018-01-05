/**
 * 系统菜单跳转js
 * @param obj 菜单别名
 */
function menus(obj,userType,sid){
	//机器学习、课程、考试模块直接弹出
	if(obj == '/assembly' || obj == '/curriculum' || obj == '/exam'){
		if(obj == '/curriculum'){//课程
			if(userType == '0'){//教师
				window.open(window.itcAddress+'/without/getinto/ictilearning?userType='+userType+'&sid='+sid);
			}else{//学生
				window.open(window.itcAddress+'/without/getinto/ictilearning?userType='+userType+'&sid='+sid);
			}
		}else if(obj == '/exam'){//考试
			if(userType == '0'){//教师
				window.open(window.itcAddress+'/without/getinto/ictilearning?userType='+userType+'&sid='+sid);
			}else{//学生
				window.open(window.itcAddress+'/without/getinto/examilearning?userType='+userType+'&sid='+sid);
			}
		}else{//机器学习
			window.open(window.ctx+obj);
		}
	}else{//其它模块
		window.location.href = window.ctx+obj;
	}
}