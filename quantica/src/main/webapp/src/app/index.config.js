(function ()
{
    'use strict';

    angular
        .module('fuse')
        .config(config);

    /** @ngInject */
    function config(localStorageServiceProvider)
    {
        localStorageServiceProvider
            .setPrefix('Quantica')
            .setStorageType('localStorage')
            .setNotify(true, true);
    }



})();
