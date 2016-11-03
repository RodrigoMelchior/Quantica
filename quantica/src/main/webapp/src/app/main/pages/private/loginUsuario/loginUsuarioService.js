(function ()
{
    'use strict';

    angular
            .module('app.pages.private.loginUsuario.service', [])
            .service("LoginUsuarioService", loginUsuarioService)

    /** @ngInject */
    function loginUsuarioService()
    {
        var pesquisa = {}; //manter os parametros da pesquisa

        this.getPesquisaParams = function () {
            return pesquisa;
        };
        this.setPesquisaParams = function (valor) {
            pesquisa = valor;
        };


    }
})();