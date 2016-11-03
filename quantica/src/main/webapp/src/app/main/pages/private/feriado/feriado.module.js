(function ()
{
    'use strict';

    angular
            .module('app.pages.private.feriado', [
                'app.pages.private.feriado.feriadoService',
                'app.pages.private.feriado.feriadoController'])
            .config(config)


    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider)
    {


        // State
        $stateProvider
                .state('app.feriado', {
                    url: '/feriado',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/feriado/feriado.html',
                            controller: 'FeriadoController as vm'
                        }
                    }

                })
                .state('app.feriado.visualizar', {
                    url: '/:id/visualizar',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/feriado/visualizarFeriado.html',
                            controller: 'FeriadoController as vm'
                        }
                    }

                })
                .state('app.feriado.incluir', {
                    url: '/incluir',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/feriado/incluirFeriado.html',
                            controller: 'FeriadoController as incluirCTRL'
                        }
                    }
                })
                .state('app.feriado.editar', {
                    url: '/:id/editar',
                    views: {
                        'content@app': {
                            templateUrl: 'app/main/pages/private/feriado/editarFeriado.html',
                            controller: 'FeriadoController as editarCTRL'
                        }
                    }
                });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/pages/private/feriado');


    }
})();
