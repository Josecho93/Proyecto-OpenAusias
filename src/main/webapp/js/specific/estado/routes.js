/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fEstadoRoutes() {
    var icon = '<i class="fa fa-file-text-o fa-5x"></i>';
    var fillDocumentoPageHeader = _.partial(html.getPageHeader, icon, 'Estado', _);
    var strClass = 'estado';
    var place = $('#broth_content');
    Path.map("#/" + strClass + "/new(/:url)").to(function () {
        $('#broth_panel_heading').empty().append(fillDocumentoPageHeader('New'));
        var strParam = parameter.getUrlObjectFromUrlString(this.params['url']);
        ausiasFLOW.reset();
        ausiasFLOW.initialize(newModule, place, strClass, 'new', strParam);
        return false;
    });
     Path.map("#/" + strClass + "/list(/:url)").to(function () {
        $('#broth_panel_heading').empty().append(fillDocumentoPageHeader('List'));
        var strParam = parameter.getUrlObjectFromUrlString(this.params['url']);
        ausiasFLOW.reset();
        ausiasFLOW.initialize(listModule, place, strClass, 'list', strParam);
        return false;
    });
    
    Path.map("#/" + strClass + "/view(/:url)").to(function () {
        $('#broth_panel_heading').empty().append(fillDocumentoPageHeader('View'));
        var strParam = parameter.getUrlObjectFromUrlString(this.params['url']);
        ausiasFLOW.reset();
        ausiasFLOW.initialize(viewModule, place, strClass, 'view', strParam);
        return false;
    });
}