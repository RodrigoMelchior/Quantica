(function ()
{
    'use strict';


angular.module('app.components.security')
    .factory('Authentication', Authentication);

/** @ngInject */
function Authentication($rootScope, $state, $q, $translate, Principal, AuthServerProvider, Account, Activate, Password, PasswordResetInit, PasswordResetFinish, HOME_STATE) {
        return {
            login: function (credentials, callback) {
                var cb = callback || angular.noop;
                var deferred = $q.defer();

                AuthServerProvider.login(credentials).then(function (data) {
                    // retrieve the logged account information
                    Principal.identity(true).then(function(account) {
                        // After the login the language will be changed to
                        // the language selected by the user during his registration
                        //$translate.use(account.langKey).then(function(){
                        //    $translate.refresh();
                        //});
                        deferred.resolve(data);
                    });
                    return cb();
                }).catch(function (err) {
                    this.logout();
                    deferred.reject(err);
                    return cb(err);
                }.bind(this));

                return deferred.promise;
            },

            logout: function () {
                AuthServerProvider.logout();
                Principal.authenticate(null);
                // Reset state memory
                $rootScope.previousStateName = undefined;
                $rootScope.previousStateNameParams = undefined;
                $state.go('app.login');
            },

            authorize: function(force) {
                return Principal.identity(force)
                    .then(function() {
                        var isAuthenticated = Principal.isAuthenticated();

                        if (!isAuthenticated && ($rootScope.toState.data.publicRoute === undefined || $rootScope.toState.data.publicRoute === false)){
                            $rootScope.previousStateName = $rootScope.toState;
                            $rootScope.previousStateNameParams = $rootScope.toStateParams;

                            $state.go('app.login');
                        }

                        // an authenticated user can't access to login and register pages
                        if (isAuthenticated && $rootScope.toState.name === 'app.login') {
                            $state.go(HOME_STATE);
                        }



                        if (isAuthenticated && ($rootScope.toState.data.publicRoute === undefined || $rootScope.toState.data.publicRoute === false) && $rootScope.toState.data.authorities && $rootScope.toState.data.authorities.length > 0 && !Principal.hasAnyAuthority($rootScope.toState.data.authorities)) {

                                $state.go('app.accessDenied');
                        }

                        }
                    );
            },
            createAccount: function (account, callback) {
                var cb = callback || angular.noop;

                return Register.save(account,
                    function () {
                        return cb(account);
                    },
                    function (err) {
                        this.logout();
                        return cb(err);
                    }.bind(this)).$promise;
            },

            updateAccount: function (account, callback) {
                var cb = callback || angular.noop;

                return Account.save(account,
                    function () {
                        return cb(account);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },

            activateAccount: function (key, callback) {
                var cb = callback || angular.noop;

                return Activate.get(key,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },

            changePassword: function (newPassword, callback) {
                var cb = callback || angular.noop;

                return Password.save(newPassword, function () {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            },

            resetPasswordInit: function (mail, callback) {
                var cb = callback || angular.noop;

                return PasswordResetInit.save(mail, function() {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            },

            resetPasswordFinish: function(keyAndPassword, callback) {
                var cb = callback || angular.noop;

                return PasswordResetFinish.save(keyAndPassword, function () {
                    return cb();
                }, function (err) {
                    return cb(err);
                }).$promise;
            }
        };
    };



})();
