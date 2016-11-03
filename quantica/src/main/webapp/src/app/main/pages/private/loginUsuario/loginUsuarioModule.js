(function ()
{
    'use strict';

    angular
            .module('app.pages.private.loginUsuario', ['app.pages.private.loginUsuario.service'])
            .config(config);


    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider)
    {
        // State
        $stateProvider
                .state('app.loginUsuario', {
                    url: '/loginUsuario',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/loginUsuario/loginUsuario.html',
                            controller: 'loginUsuarioController as vm'
                        }
                    }
                })
                .state('app.loginUsuario.visualizar', {
                    url: '/:id/visualizar',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/loginUsuario/visualizarloginUsuario.html',
                            controller: 'loginUsuarioController as vizualizarCTRL'
                        }
                    }
                }).state('app.loginUsuario.incluir', {
            url: '/incluir',
            views: {
                'content@app': {
                    templateUrl: 'app/main/pages/private/loginUsuario/incluirLoginUsuario.html',
                    controller: 'loginUsuarioController as incluirCTRL'
                }
            }
        }).state('app.loginUsuario.editar', {
            url: '/:id/editar',
            views: {
                'content@app': {
                    templateUrl: 'app/main/pages/private/loginUsuario/editarLoginUsuario.html',
                    controller: 'loginUsuarioController as editarCTRL'
                }
            }
        });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/pages/private/loginUsuario');


    }
})();