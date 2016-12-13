(function() {
  'use strict';

  angular
    .module('app.pages.private.importarArquivo')
    .service('ImportarArquivoService', ImportarArquivoService);

  /** @ngInject */
  function ImportarArquivoService($http, $mdToast, REST_URL) {
    var url = REST_URL + '/registro-arquivos';
    var urlArquivo = REST_URL + '/arquivos';

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
        var fd = new FormData();
          
        fd.append('idUsuario', idUsuario);
        fd.append('idPesquisa', idPesquisa);
        fd.append('tipoArquivo', tipoArquivo);
        fd.append('file', file);
        
      return $http.post(url + "/upload", fd, {
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

    var getArquivo = function (idUser, idPesquisa) {
      return $http.get(urlArquivo + '/' + idUser + '/' + idPesquisa);
    };
      
    var getRegistroArquivo = function (id) {
      return $http.get(url + '/arquivo/' + id);
    };
      
    var getItemPesquisa = function (id) {
      return $http.get(url + '/item-pesquisa/' + id);
    };
      
    return {
      validaArquivo : validaArquivo,
      uploadArquivo : uploadArquivo,
      getArquivo    : getArquivo,
      getRegistroArquivo : getRegistroArquivo,
      getItemPesquisa : getItemPesquisa
    };
  }
})();
