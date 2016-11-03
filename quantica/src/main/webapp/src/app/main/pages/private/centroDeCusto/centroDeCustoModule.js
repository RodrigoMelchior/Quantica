(function ()
{
    'use strict';

    angular
            .module('app.pages.private.centroDeCusto', ['app.pages.private.centroDeCusto.centroDeCustoService'])
            .config(config)
            .constant('COSTCENTER_PERMISSIONS', {
                view: 'GR_COSTCENTER_VIEW',
                edit: 'GR_COSTCENTER_EDIT',
                insert: 'GR_COSTCENTER_INSERT',
                delete: 'GR_COSTCENTER_DELETE'
            })

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider, COSTCENTER_PERMISSIONS)
    {
        // State
        $stateProvider
                .state('app.centroDeCusto', {
                    url: '/centroDeCusto',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/centroDeCusto/centroDeCusto.html',
                            controller: 'CentroDeCustoController as vm'
                        }
                    },
                    data: {
                        publicRoute: false,
                        authorities: [COSTCENTER_PERMISSIONS.view, COSTCENTER_PERMISSIONS.edit, COSTCENTER_PERMISSIONS.insert, COSTCENTER_PERMISSIONS.delete]
                    }
                }).state('app.centroDeCusto.vizualizar', {
            url: '/centroDeCusto/:id/vizualizar',
            views: {
                'content@app': {
                    templateUrl: 'app/main/pages/private/centroDeCusto/visualizarCentroDeCusto.html',
                    controller: 'CentroDeCustoController as vizualizarCTRL'
                }
            },
            data: {
                publicRoute: false,
                authorities: [COSTCENTER_PERMISSIONS.view, COSTCENTER_PERMISSIONS.edit, COSTCENTER_PERMISSIONS.insert, COSTCENTER_PERMISSIONS.delete]
            }
        }).state('app.centroDeCusto.incluir', {
            url: '/centroDeCusto/incluir',
            views: {
                'content@app': {
                    templateUrl: 'app/main/pages/private/centroDeCusto/incluirCentroDeCusto.html',
                    controller: 'CentroDeCustoController as incluirCTRL',
                }
            },
            data: {
                publicRoute: false,
                authorities: [COSTCENTER_PERMISSIONS.view, COSTCENTER_PERMISSIONS.edit, COSTCENTER_PERMISSIONS.insert, COSTCENTER_PERMISSIONS.delete]
            }
        }).state('app.centroDeCusto.editar', {
            url: '/centroDeCusto/:id/editar',
            views: {
                'content@app': {
                    templateUrl: 'app/main/pages/private/centroDeCusto/editarCentroDeCusto.html',
                    controller: 'CentroDeCustoController as editarCTRL'
                }
            },
            data: {
                publicRoute: false,
                authorities: [COSTCENTER_PERMISSIONS.view, COSTCENTER_PERMISSIONS.edit, COSTCENTER_PERMISSIONS.insert, COSTCENTER_PERMISSIONS.delete]
            }
        });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/pages/private/centroDeCusto');


    }
})();