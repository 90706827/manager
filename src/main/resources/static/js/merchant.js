// JavaScript Document
//loading
function LoadingFun() {

}
/*
*@autor：wangliping
*Desccription:页面加载，菜单回显，根据每个页面的pageTitle的值进行判断是哪个菜单
*Created on 2017年12月12日
*Modified date 2017年12月12日
*/
function menuFun() {
	$('.lpsearch-td-wranIcon').popover('toggle')//！！！提示信息
	$('.lpsearch-td-wranIcon').popover('hide')//！！！提示信息

	var menuVal = $("#pageTitle").val();
	if (menuVal != "" && menuVal != null) {
		var $currentMenu = $(".lppanel a[lp-menu='" + menuVal + "']");
		if ($(".lppanel-collapse")) {
			$(".lppanel-collapse").removeClass("in");
			$currentMenu.parent(".lppanel-collapse").addClass("in");
			$(".lppanel-collapse a").removeClass("lppanel-collapse-active");
			$currentMenu.addClass("lppanel-collapse-active");
			$(".lppanel-heading").removeClass("lppanel-heading-active");
			$currentMenu.parent(".lppanel-collapse").prev(".lppanel-heading").addClass("lppanel-heading-active");
			$currentMenu.parent(".lppanel-collapse").prev(".lppanel-heading").find(".lpmenu-ui-icon").removeClass("lpmenu-ui-icon-left").addClass("lpmenu-ui-icon-down");

		}
		$currentMenu.parent('.lppanel-heading').addClass("lppanel-heading-active");
	}
}
/*
*@autor：wangliping
*Desccription:菜单收起的
*Created on 2017年12月12日
*Modified date 2017年12月12日
*/
var MenuNum = 0
function menuBoxToggle() {
	if (MenuNum == 0) {
		MenuNum = 1
		$(".lpmenu-box").animate({ "width": "15px" });
		$(".lpmenu-boxbg").animate({ "width": "15px" });
		$(".lpmenu-content-box").hide();
		$(".lpmenu-contraction").animate({ "left": "0px" });
		$(".lpcontent-box").animate({ "width": $(window).width() - 75 + "px", "left": "45px" })
	} else {
		MenuNum = 0
		$(".lpmenu-box").animate({ "width": "240px" });
		$(".lpmenu-boxbg").animate({ "width": "240px" });
		$(".lpmenu-content-box").show();
		$(".lpmenu-contraction").animate({ "left": "225px" });
		$(".lpcontent-box").animate({ "width": $(window).width() - 300 + "px", "left": "270px" })
	}
}

function contentWith() {
	if (MenuNum == 0) {
		$(".lpcontent-box").css({ "width": $(window).width() - 300 + "px", "left": "270px" })
	} else {
		$(".lpcontent-box").css({ "width": $(window).width() - 75 + "px", "left": "45px" })
	}
}
/*
*@autor：wangliping
*Desccription:操作提示
*Created on 2018年3月22日
*Modified date 2018年3月22日
*/
function commonWarnFun(ems) {
	$('#commonWarn').modal('show');
	$("#commonWarnText").text("确定要" + ems + "吗？");
	$("#commonWarnYes").attr("onClick", "YesFun('" + ems + "')")
}
function YesFun(content) {
	$('#Loading').show();
	setTimeout(function () {
		$('#LoadingBlack').modal('hide');
		$('#Loading').hide();
		$(".lpoperate-tipbox").slideDown();
		$(".lpoperate-tip-text").text(content + "成功");
		setTimeout(function () {
			$(".lpoperate-tipbox").slideUp();
		}, 3000)
	}, 2000);
}

/*
*@autor：wangliping
*Desccription:机构数
*Created on 2018年3月22日
*Modified date 2018年3月22日
*/
var zNodes = [
	{ id: 1, pId: 0, name: "[core] 基本功能 演示666", open: true },
	{ id: 101, pId: 1, name: "最简单的树 --  标准 JSON 数据最简单的树 --  标准 JSON 数据", file: "core/standardData" },
	{ id: 102, pId: 1, name: "最简单的树 --  简单 JSON 数据", file: "core/simpleData" },
	{ id: 11, pId: 1, name: "自定义字体", file: "core/custom_font" },
	{ id: 108, pId: 11, name: "异步加载 节点数据", file: "core/async" },
	{ id: 109, pId: 11, name: "用 zTree 方法 异步加载 节点数据", file: "core/async_fun" },
	{ id: 13, pId: 1, name: "不显示 连接线", file: "core/noline" },
	{ id: 114, pId: 13, name: "其他 鼠标 事件监听", file: "core/otherMouse" },
	{ id: 2, pId: 0, name: "[excheck] 复/单选框功能 演示", open: false },
	{ id: 201, pId: 2, name: "Checkbox 勾选操作", file: "excheck/checkbox" },
	{ id: 206, pId: 2, name: "Checkbox nocheck 演示", file: "excheck/checkbox_nocheck" },
	{ id: 3, pId: 0, name: "[exedit] 编辑功能 演示", open: false },
	{ id: 301, pId: 3, name: "拖拽 节点 基本控制", file: "exedit/drag" },
	{ id: 302, pId: 3, name: "拖拽 节点 高级控制", file: "exedit/drag_super" },
	{ id: 303, pId: 3, name: "用 zTree 方法 移动 / 复制 节点", file: "exedit/drag_fun" }
];
function InstitutionTree() {
	var t = $("#tree");
	t = $.fn.zTree.init(t, setting, zNodes);
	demoIframe = $("#testIframe");
	demoIframe.bind("load", loadReady);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(zTree.getNodeByParam("id", 101));
}

function loadReady() {
	var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH : maxH;
	if (h < 530) h = 530;
	demoIframe.height(h);
}
var zTree;
var demoIframe;
var setting = {
	view: {
		dblClickExpand: false,
		showLine: true,
		selectedMulti: false
	},
	data: {
		key: {
			title: "t"
		},
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function (treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				$("#Institution").text(treeNode.name)
				$('.lpztree-box').toggle()
				demoIframe.attr("src", treeNode.file + ".html");
				return true;
			}
		}
	}
};

$(function () {
	menuFun()
	contentWith();
})
$(window).resize(function () {
	contentWith();
});