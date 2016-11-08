(function() {
  'use strict';

  angular
    .module('app.pages.private.importarArquivo')
    .service('ImportarArquivoService', ImportarArquivoService);

  /** @ngInject */
  function ImportarArquivoService($http, $mdToast, REST_URL) {
    var url = REST_URL + '/registro-arquivos/upload';

    var validaArquivo = function(arquivo) {
      if (isExtensaoInvalida(arquivo.getExtension())) {
        $mdToast.show(
          $mdToast.simple()
          .textContent('A extensão do arquivo: ' + arquivo.name + ' é inválida!')
          .position('right')
          .hideDelay(5000)
        );

        return false;
      } else if (isTamanhoInvalido(arquivo.size)) {
        $mdToast.show(
          $mdToast.simple()
          .textContent('O tamanho do arquivo: ' + arquivo.name + ' excede o limite permitido!')
          .position('right')
          .hideDelay(5000)
        );

        return false;
      }

      return true;
    };

    var uploadArquivo = function (file, idUsuario, idPesquisa, tipoArquivo) {
        
        console.log("idUsuario",idUsuario);
        console.log("idPesquisa",idPesquisa);
        console.log("tipoArquivo",tipoArquivo);
        console.log("file", file);
        
        var fd = new FormData();
        fd.append('idUsuario', idUsuario);
        fd.append('idPesquisa', idPesquisa);
        fd.append('tipoArquivo', tipoArquivo);
        fd.append('file', file);
        return $http.post(url, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
            
        })
        .error(function(){
        });
        
    };

    var isExtensaoInvalida = function(extensao) {
      if (extensao !== "csv") {
        return true;
      }

      return false;
    };

    var isTamanhoInvalido = function(tamanho) {
      var tamanhoKB = tamanho / 1024;

      if (tamanhoKB > 10240) {
        return true;
      }

      return false;
    };

    return {
      validaArquivo : validaArquivo,
      uploadArquivo : uploadArquivo
    };
  }
})();
