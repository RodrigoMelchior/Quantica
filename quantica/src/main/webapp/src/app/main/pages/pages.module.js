(function ()
{
    'use strict';

    angular
        .module('app.pages', [
            'app.pages.public',
            'app.pages.private'
        ])
        .config(config);

    /** @ngInject */
    function config(msNavigationServiceProvider)
    {
    
    }
})();
