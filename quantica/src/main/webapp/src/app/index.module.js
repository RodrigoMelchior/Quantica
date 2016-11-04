(function ()
{
    'use strict';

    /**
     * Main module of the Fuse
     */
    angular
        .module('fuse', [
            'LocalStorageModule',
            'app.core',
            'app.navigation',
            'app.toolbar',
            'app.quick-panel',
            'app.components',
            'app.pages'
        ]);
})();
