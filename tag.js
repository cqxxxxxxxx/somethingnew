var $container;
var $selector;
var tagArray;
var allow_duplicate = false; //默认不允许重复标签

function tag_data_init(containerId, selectorId, array) {
    $container = $(document.getElementById(containerId));
    $selector = $(document.getElementById(selectorId));
    tagArray = array;

    tag_selector_init();    //初始化下拉框

    //绑定改变事件
    $selector.on("change", function () {
        var name = $(this).children('option:selected').text();
        var id = $(this).children('option:selected').val();
        tag_add(name, id);
    })
}

/**
 * 初始化下拉框
 */
function tag_selector_init() {
    $.each(tagArray, function (data, item) {
        $selector.append('<option value="' + item.id + '">' + item.name + '</option>')
    });
}

/**
 * 初始化 tag容器
 * @param arrayIds 数组[1,2,3,4]
 */
function tag_container_init(arrayIds) {
    $container.empty(); //先清空原来的
    $.each(arrayIds, function (index, id) {
        var obj = binarySearchTag(id, tagArray); //进行折半查找
        $container.append('<span class="badge bg-green" tag_id="' + obj.id + '">' + obj.name +
            '<i class="fa fa-times" style="cursor: pointer" onclick="tag_removeself()"></i></span>');
    })
}

/**
 *
 * 折半查找
 * array是从小到大排列的
 * @param id
 * @param array
 * @returns {*}
 */
function binarySearchTag(id, array) {
    var length = array.length;
    var beginIndex = 0;
    var endIndex = length;
    var half;
    for (var i = 0; i < length / 2; i++) {
        half = beginIndex + Math.round((endIndex - beginIndex) / 2);  //取中值
        if (id == array[half].id) {
            return array[half];
        }

        if (id < array[half].id)
            endIndex = half;
        else
            beginIndex = half;
    }
}

/**
 * 添加标签
 * @param name 标签名
 * @param id    标签id
 */
function tag_add(name, id) {
    var temp = '<span class="badge bg-green" tag_id="' + id + '">' + name +
        '<i class="fa fa-times" style="cursor: pointer" onclick="tag_removeself()"></i></span>';

    if (!allow_duplicate) {      //不准重复
        if (!tag_exist(id))      //不存在
            $container.append(temp);
        else
            return false;
    } else
        $container.append(temp);

}

/**
 * 删除标签
 */
function tag_removeself() {
    var srcElement = window.event.srcElement;
    $(srcElement).parents('span').remove();
}

/**
 * 判断标签是否存在
 * @param id
 * @returns {boolean}  已存在则返回true,
 */
function tag_exist(id) {
    var condition = "[tag_id = " + id + "]";
    var tags = $container.find(condition);
    if (tags.size() != 0)
        return true;
    else
        return false;
}