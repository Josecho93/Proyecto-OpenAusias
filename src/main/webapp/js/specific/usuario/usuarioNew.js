/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var usuarioNew = function () {

};
usuarioNew.prototype = new newModule();
usuarioNew.prototype.doEventsLoading = function () {
    $("#usuarioForm #obj_tipousuario_button").unbind("click");
    $("#usuarioForm #obj_tipousuario_button").click(function () {

        $("#usuarioForm").append(modal.getEmptyModal());
        modal.loadModal('#modal01', modal.getModalHeader('Elección del tipo de usuario'),
                "", modal.getModalFooter(), true);


//ausiasFLOW.initialize(viewModule, $('#modal-body'), 'tipousuario', 'view', {'id':3});

        ausiasFLOW.initialize(ebpListModule, $('#modal-body'), 'tipousuario', 'ebplist', {}, function (id) {
            $('#obj_tipousuario').val(id);
            promise.getOne("tipousuario",id).done(function(json) {
            $('#obj_tipousuario_desc').html(
                    html.printObject2('usuario',json.message));    
            });
            $('#modal01').modal('hide');
        });

//$('#modal-body').append('<h2> Aquí se van a mostrar los tipos de usuario</h2>')

    });
};