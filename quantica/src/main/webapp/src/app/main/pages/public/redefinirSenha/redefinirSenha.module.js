(function ()
{
    'use strict';

    angular
            .module('app.pages.public.redefinirSenha', [])
            .config(config);

    /** @ngInject */
    function config($stateProvider, $translatePartialLoaderProvider, msNavigationServiceProvider)
    {
        $stateProvider
                .state('app.redefinirSenha', {
                    url: '/redefinirSenha',
                    views: {
                        'main@': {
                            templateUrl: 'app/core/layouts/content-only.html',
                            controller: 'MainController as vm'
                        },
                        'content@app.redefinirSenha': {
                            templateUrl: 'app/main/pages/public/redefinirSenha/redefinirSenha.html',
                            controller: 'RedefinirSenhaController as vm'
                        }
                    },
                    bodyClass: 'login2'
                });

        // Translation
        $translatePartialLoaderProvider.addPart('app/main/pages/public/redefinirSenha');
    }

})();