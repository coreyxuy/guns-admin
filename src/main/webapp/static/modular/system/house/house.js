/**
 * 管理初始化
 */
var House = {
    id: "HouseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
House.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '房屋编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '业主名称', field: 'houseUser', visible: true, align: 'center', valign: 'middle'},
            {title: '房屋地址', field: 'houseDate', visible: true, align: 'center', valign: 'middle'},
            {title: '房屋描述', field: 'houseDesc', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
House.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        House.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
House.openAddHouse = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/house/house_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
House.openHouseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/house/house_update/' + House.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
House.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/house/delete", function (data) {
            Feng.success("删除成功!");
            House.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("houseId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
House.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    House.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = House.initColumn();
    var table = new BSTable(House.id, "/house/list", defaultColunms);
    table.setPaginationType("client");
    House.table = table.init();
});
