/**
 * 字符串长度截取
 * @param str
 * @param length
 * @returns {string}
 */
function strLengthHandler(str, length) {
    if (str.length > length)
        return str.substring(0, length) + "...";
    else
        return str;
}

/**
 * 把阿拉伯数字转为中文数字
 * @param num
 * @returns {*}
 */
function getchinaNum(num) {
    var chinaNum = new Array('零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二', '十三', '十四', '十五', '十六', '十七', '十八', '十九', '二十');
    return chinaNum[num];
}

/**
 * unix timestamp 转 2017-01-23这种
 * @param time
 * @returns {string}
 */
function renderDate(time) {
    var date = new Date(time);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
        + date.getDate();
}

/**
 * 判断是否为空  空则返回true
 * @param obj
 * @returns {boolean}
 */
function isNull(obj) {
    var reg = new RegExp("^\s*$");  //匹配空白符
    if (reg.test(obj) || obj == null)   //"" 或者 null 返回ture
        return true;
    else
        return false;
}

/**
 * 判断是否不为空
 * 如果为空则返回false， 不空则true
 * @param obj
 * @returns {boolean}
 */
function isNotNull(obj) {
    var reg = new RegExp("^\s*$");  //匹配空白符
    if (reg.test(obj) || obj == null)   //"" 或者 null 返回ture
        return false;
    else
        return true;
}

/**
 * 手机号验证
 * @returns {boolean}
 */
function checkMobile(phone){
    if(!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(phone))){
        return false;
    }
    return true;
}


/**
 * 根据ID 返回DOM对象
 * @param id
 * @returns {Element} DOM对象
 */
function getById(id) {
    return document.getElementById(id);
}


/**
 * 折半查找  数组中找出指定值。
 *  可扩展成数组元素为json之类的
 * @param target
 * @param array
 * @param order     0数组从小到大， 1数组从大到小
 * @returns {*}
 */
function binarySearch(target, array, order) {
    var length = array.length;
    var beginIndex = 0;
    var endIndex = length;
    var half;
    if (order == 0) {       //从小到大排列
        for (var i = 0; i < length / 2; i++) {
            half = beginIndex + Math.round((endIndex - beginIndex) / 2);  //取中值
            if (target == array[half]) {
                return array[half];
            }

            if (target < array[half])
                endIndex = half;
            else
                beginIndex = half;
        }
    }
    if (order == 1) {       //从大到小排列
        for (var i = 0; i < length / 2; i++) {
            half = beginIndex + Math.round((endIndex - beginIndex) / 2);  //取中值
            if (target == array[half]) {
                return array[half];
            }

            if (target < array[half])
                beginIndex = half;
            else
                endIndex = half;
        }
    }
}