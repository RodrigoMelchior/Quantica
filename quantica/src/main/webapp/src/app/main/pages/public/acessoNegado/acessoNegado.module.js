(function ()
{
    'use strict';

    angular
        .module('app.pages.public.acessoNegado', [])
        .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        // State
        $stateProvider.state('app.accessDenied', {
            url      : '/acessoNegado',
            views    : {
                'main@'                        : {
                    templateUrl: 'app/core/layouts/content-only.html',
                    controller : 'MainController as vm'
                },
                'content@app.accessDenied': {
                    templateUrl: 'app/main/pages/public/acessoNegado/acessoNegado.html',
                    controller : 'AcessoNegadoController as vm'
                }
            },
            bodyClass: 'maintenance'
        });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/pages/public/acessoNegado');

    }

})();