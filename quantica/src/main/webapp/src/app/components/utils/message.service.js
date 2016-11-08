(function() {
  'use strict';

  angular
    .module('app.components.utils.messages', [])
    .service('MessageService', MessageService);

  /** @ngInject */
  function MessageService ($mdToast) {

    var showMessage = function (message) {
      $mdToast.show(
        $mdToast.simple()
        .textContent(message)
        .position('right')
        .hideDelay(5000)
      );
    };

    return {
      showMessage : showMessage
    };
  }
})();
