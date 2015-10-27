/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function fUsuarioRoutes() {
    Path.map("#/usuario/new(/:url)").to(function () {
       
        $('#broth_panel_heading').empty().append('<h1>new de usuario ejec</h1>');
        ausiasFLOW.reset();
       
        ausiasFLOW.newModule_frontOperation="new";
        ausiasFLOW.newModule_class="usuario";
        ausiasFLOW.initialize(newModule, $('#broth_content'));
       
         });
   
    Path.map("#/usuario/view(/:url)").to(function () {
        $('#broth_panel_heading').empty().append('<h1>view de usuario ejec</h1>');
        ausiasFLOW.reset();
        ausiasFLOW.viewModule_paramsObject=parameter.getUrlObjectFromUrlString(this.params["url"]);
        ausiasFLOW.viewModule_frontOperation="view";
        ausiasFLOW.viewModule_class="usuario";
        ausiasFLOW.initialize(viewModule, $('#broth_content'));
       
       
    });
    Path.map("#/usuario/delete").to(function () {
        $('#broth_content').empty().append('<h1>he ejecutado delete</h1>');
    });
   
   
    Path.map("#/usuario/list").to(function () {
       
        $('#broth_panel_heading').empty().append('<h1>list de usuario ejec</h1>');
        ausiasFLOW.reset();
        ausiasFLOW.listModule_paramsObject=parameter.getUrlObjectFromUrlString(this.params["url"]);
        ausiasFLOW.listModule_frontOperation="new";
        ausiasFLOW.listModule_class="usuario";
        ausiasFLOW.initialize(listModule, $('#broth_content'));
       
         });
}