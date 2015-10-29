/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var specificView = function () {    
    var strClass;
    var parametersObject;
    var jsonDataViewModule;
};
specificView.prototype = new baseModule();
specificView.prototype.getViewTemplate_func = function (strClass, jsonDataViewModule) {

$("#broth_content").append('<br><h2>Tabla de datos del usuario</h2>');
$("#broth_content").append("<table><tr><td> Prueba de columna 1 </td><td> Prueba de columna 2</td></tr></table>");       
        
        
//    for(var i=1; i<=100; i++){
//
//        $("#broth_content").append('<br><h2>Tabla de '+i+"</h2>");
//        for(var k = 1; k<=10;k++){
//            $("#broth_content").append("<p>"+i+"x"+k+"="+i*k+"</p>");
//
//        }
//        $("#broth_content").append("</div>");
//    }
    
    
//    arr_meta_data = _.map(jsonDataViewModule.meta.message, function (value) {
//        return  {meta: value, data: jsonDataViewModule.bean.message[value.Name]};
//    });
//    arr_meta_data_table = _.map(arr_meta_data, function (value, key) {
//        return  '<tr><td><strong>' + value.meta.ShortName + '</strong></td><td>' + html.printPrincipal(value) + '</td></tr>';
//    });
//    return "<table class=\"table table table-bordered table-condensed\">"
//            + arr_meta_data_table.join('')
//            + '</table>';
};
specificView.prototype.initialize = function (oComponent) {
    strClass = oComponent.strOb;
    parametersObject = oComponent.strParams;
}
specificView.prototype.getPromise = function () {
    if (parametersObject['id'] && strClass) {
        return promise.getOne(strClass, parametersObject['id']);
    }
}
specificView.prototype.getData = function (jsonDataViewModuleReceived) {
    if (jsonDataViewModuleReceived) {
        if (jsonDataViewModuleReceived.status == "200") {
            jsonDataViewModule = jsonDataViewModuleReceived;
        } else {
            //informar error
        }
    } else {
        //informar error
    }
}
specificView.prototype.render = function () {
    if (!strClass) {
        return 'ERROR: No class defined';
    }
    if (!parametersObject['id']) {
        return 'ERROR: No id defined';
    }
    if (!jsonDataViewModule.message) {
        return 'ERROR: Server communication interrupted';
    }
    return this.getViewTemplate_func(strClass, jsonDataViewModule.message);
}