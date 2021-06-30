// 判断是否具有某button权限
function hasPermission(permissionName) {
    var allButton = layui.data('adminManager').allButton;
    for (var i = 0; i < allButton.length; i++) {
        if (allButton[i].name == permissionName)
            return true;
    }
    return false;
}

function initBtnPermission() {
    $(".permissionBtn").each(function () {
        if (!hasPermission($(this).attr('lay-event')))
            $(this).hide();
    });
}


function downLoadFileGet(url, fileName, fun) {
    var result = 0;
    var xhr = new XMLHttpRequest();
    xhr.open('get', url, true);
    xhr.responseType = 'blob';
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8');
    xhr.onload = function () {
        result = this.status;
        if (result == 200) {
            var blob = this.response;
            var aLink = document.createElement('a');
            document.body.appendChild(aLink);
            aLink.style.display = 'none';
            var objectUrl = window.URL.createObjectURL(blob);
            aLink.href = objectUrl;
            aLink.download = fileName;
            aLink.click();
            document.body.removeChild(aLink);
            if (fun != "")
                fun();
        }
    }
    xhr.send(null);
}

function downLoadFilePost(url, fileName, sendData) {
    var xhr = new XMLHttpRequest();
    xhr.open('post', url, true);
    xhr.responseType = 'blob';
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8');
    xhr.onload = function () {
        if (this.status == 200) {
            var blob = this.response;
            var aLink = document.createElement('a');
            document.body.appendChild(aLink);
            aLink.style.display = 'none';
            var objectUrl = window.URL.createObjectURL(blob);
            aLink.href = objectUrl;
            aLink.download = fileName;
            aLink.click();
            document.body.removeChild(aLink);
        }
    }
    xhr.send(sendData); //sendData必须是String，如果是json，则JSON.stringify(json对象) 后台用requestBody接
}

/**
 * 获取某个Form中所有的字段值，并返回Json格式
 * @param formId
 * @returns {string}
 */
function getFormField(formId) {
    var formFeild = {};
    var t = $('#'+formId+' [name]').serializeArray();
    $.each(t, function() {
        formFeild[this.name] = this.value;
    });
    var jsonData = JSON.stringify(formFeild);
    return jsonData;
}
