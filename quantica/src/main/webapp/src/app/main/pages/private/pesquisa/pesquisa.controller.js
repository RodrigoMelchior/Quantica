(function() {
  'use strict';

  angular
    .module('app.pages.private.pesquisa')
    .controller('PesquisaController', PesquisaController);

  /** @ngInject */
  function PesquisaController($scope, $state, $stateParams, $mdToast, $mdDialog, $mdMedia, $q, PesquisaService, UtilService, EmpresaService, ItemService ) {
    var vm = this;
    vm.pesquisa = {};
    vm.form = {};
    vm.pesquisa.lista = [];
    vm.tituloForm = 'Cadastrar';
    vm.listaEmpresas = getEmpresas();
    vm.listaItens = getItens();
    vm.listaEmpresasSel = getEmpresasSelecionadas();
    vm.listaItensSel = getItensSelecionados();
    vm.form.paineis = [];
    vm.form.agrupadores = [];
    vm.form.perguntas = [];
    vm.form.perguntasLista = [];
    vm.mudaHabilitado = mudaHabilitado;
    vm.openPainelModal = openPainelModal;
    vm.excluirPainel = excluirPainel;
    vm.openAgrupadorModal = openAgrupadorModal;
    vm.excluirAgrupador = excluirAgrupador;
    vm.openPerguntaModal = openPerguntaModal;
    vm.excluirPergunta = excluirPergunta;
    vm.pesquisar = pesquisar;
    vm.excluir = excluir;
    vm.salvar = salvar;
    vm.atualizaEmpresaSel = atualizaEmpresaSel;
    vm.atualizaItensSel = atualizaItensSel;
    vm.voltar = voltar;
    vm.painelEditar = null;
    vm.indicePainel = null;
    vm.agrupadorEditar = null;
    vm.indiceAgrupador = null;
    vm.perguntaEditar = null;
    vm.indicePergunta = null;
    vm.empresasParticipantes = [];
    vm.banco = {
        "empresas" : null,
        "itens" : null
    };
        
      
    if (isEditar()) {
      // $stateParams.id
      vm.tituloForm = 'Editar';
      PesquisaService.getId($stateParams.id).then(function (response) {
console.log("pesquisa", response.data);
        // Carrega Pesquisa
        vm.form = response.data;
        vm.form.inicio = UtilService.dateConverte(vm.form.inicio);
        vm.form.termino = UtilService.dateConverte(vm.form.termino);
        vm.form.dataBase = UtilService.dateConverte(vm.form.dataBase);
          
        // Carrega Empresas
        var  lmEmpresa = [];
        vm.banco.empresas = vm.form.empresas;
        vm.empresasParticipantes = vm.form.empresas;
        angular.forEach(vm.form.empresas, function (obj) { 
            lmEmpresa.push(obj.empresa.id.toString());
            if (obj.relacionamento == 1){
                vm.form.empresaPtr = obj.empresa.id;
            }
        });
        vm.form.empresas = lmEmpresa;
        vm.listaEmpresasSel = getEmpresasSelecionadas();
          
        // Carrega Itens
        vm.banco.item = vm.form.item;
        var  lmItens = [];           
        var  lmItensOrdem = [];           
        angular.forEach(vm.form.itens, function (obj) { 
            lmItens.push(obj.item.id.toString());
            var addItem = true;
            angular.forEach(lmItensOrdem, function (obj1) {
                if (obj.item.id == obj1.item.id){
                    addItem = false;
                }
            });
            if (addItem){
                lmItensOrdem.push(obj);
            }
        });
        vm.form.itens = lmItens;
        vm.form.itensOrdem = lmItensOrdem;
        vm.listaItensSel = getItensSelecionados();
          
        // Carrega Paineis
        angular.forEach(vm.form.paineis, function (obj) { 
            obj.displayPainel = [];
            obj.idsEmp = [];
            angular.forEach(obj.painelEmpresa, function (pnlEmp) { 
                if (pnlEmp.empresa){
                    obj.displayPainel.push(pnlEmp.empresa.nome);
                    obj.idsEmp.push(pnlEmp.empresa.id);
                }
            });
            obj.modificado = false;
        });

        
        // Carrega Agrupadores
        vm.banco.agrupadores = vm.form.agrupadores;
          
        
        // Carrega Perguntas
        vm.banco.perguntas = vm.form.perguntas;
        var  lmPerguntas = [];
        angular.forEach(vm.form.perguntas, function (obj) { 
            var addPerguntas = true;
/*
            angular.forEach(lmPerguntas, function (objP) { 
                if (obj.id == objP.id){
                    addAgrupador = false;
                }
            });
            if (addAgrupador){
                var lmPnlFormulas = [];
                angular.forEach(obj.formulas, function (pnlFormula) { 
                    var addFormula = true;
                    angular.forEach(lmPnlFormulas, function (pnlFormulaF) { 
                        if (pnlFormula.id == pnlFormulaF.id){
                            addFormula = false;
                        }
                    });
                    if (addFormula){
                        lmPnlFormulas.push(pnlFormula);
                    }
                });
                obj.formulas = lmPnlFormulas;
                lmAgrupadores.push(obj);
            }
*/            
        });
        vm.form.perguntas = lmPerguntas;
          
console.log("form manipulado",vm.form);
      });
    }

    function atualizaEmpresaSel() {
        vm.listaEmpresasSel = getEmpresasSelecionadas();
    }
      
    function atualizaItensSel() {
        vm.listaItensSel = getItensSelecionados();
        
        angular.forEach(vm.listaItensSel, function (obj) {
            var addItem = true;
          angular.forEach(vm.form.itensOrdem, function (obj1) {
                if (obj.id == obj1.item.id){
                    addItem = false;
                }
            });
            if (addItem){
                var itemOrdem = {"item":obj, "ordem":null}
                vm.form.itensOrdem.push(itemOrdem);
            }
        });
        angular.forEach(vm.form.itensOrdem, function (obj1) {
            var removeItem = true;
            angular.forEach(vm.listaItensSel, function (obj) {
                if (obj.id == obj1.item.id){
                    removeItem = false;
                }
            });
            if (removeItem){
                var index = vm.form.itensOrdem.indexOf(obj1);
                vm.form.itensOrdem.splice(index, 1); 
            }
        });
    }
      
    function getEmpresasSelecionadas() {
        var empresasSel = [];
        var continua = true;
        angular.forEach(vm.listaEmpresas, function (obj1) {
            continua = true;
            angular.forEach(vm.form.empresas, function (obj2) {
                if (obj1.id == obj2 && continua){
                    empresasSel.push(obj1);
                    continua = false;
                }
            });
        });
        return empresasSel;
    }
    
    function getItensSelecionados() {
        var itensSel = [];
        var continua = true;
        angular.forEach(vm.listaItens, function (obj1) {
            continua = true;
            angular.forEach(vm.form.itens, function (obj2) {
                if (obj1.id == obj2 && continua){
                    itensSel.push(obj1);
                    continua = false;
                }
            });
        });
        return itensSel;
    }
      
    function openPainelModal(evt, painelEditar, indice) {
      if (painelEditar !== null) {
        vm.painelEditar = painelEditar;
        vm.indicePainel = indice;
      }
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
      $mdDialog.show({
        targetEvent: evt,
        controller: painelDialogController,
        templateUrl: 'app/main/pages/private/pesquisa/partials/painel-modal.html',
        parent: angular.element(document.body),
        clickOutsideToClose: true,
        fullscreen: useFullScreen
      });
      $scope.$watch(function () {
          return $mdMedia('xs') || $mdMedia('sm');
      }, function (wantsFullScreen) {
          $scope.customFullscreen = (wantsFullScreen === true);
      });
    }

    function openAgrupadorModal(evt, agrupadorEditar, indice) {
      vm.atualizaItensSel();
      if (agrupadorEditar !== null) {
        vm.agrupadorEditar = agrupadorEditar;
        vm.indiceAgrupador = indice;
      }
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
      $mdDialog.show({
        targetEvent: evt,
        controller: agrupadorDialogController,
        templateUrl: 'app/main/pages/private/pesquisa/partials/agrupador-modal.html',
        parent: angular.element(document.body),
        clickOutsideToClose: true,
        fullscreen: useFullScreen
      });
      $scope.$watch(function () {
          return $mdMedia('xs') || $mdMedia('sm');
      }, function (wantsFullScreen) {
          $scope.customFullscreen = (wantsFullScreen === true);
      });
    }

    function openPerguntaModal(evt, perguntaEditar, indice) {
      if (perguntaEditar !== null) {
        vm.perguntaEditar = perguntaEditar;
        vm.indicePergunta = indice;
      }
      var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
      $mdDialog.show({
        targetEvent: evt,
        controller: perguntaDialogController,
        templateUrl: 'app/main/pages/private/pesquisa/partials/pergunta-modal.html',
        parent: angular.element(document.body),
        clickOutsideToClose: true,
        fullscreen: useFullScreen
      });
      $scope.$watch(function () {
          return $mdMedia('xs') || $mdMedia('sm');
      }, function (wantsFullScreen) {
          $scope.customFullscreen = (wantsFullScreen === true);
      });
    }

    function excluirPainel(evt, painel) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir o painel?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
        vm.form.paineis.forEach(function(painelLista, index, lista) {
          if (painelLista.nome === painel.nome) {
            lista.splice(index, 1);
          }
        });
      });
    }

    function excluirAgrupador(evt, agrupador) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir o agrupador?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
        vm.form.agrupadores.forEach(function(agrupadorLista, index, lista) {
          if (agrupadorLista.nome === agrupador.nome) {
            lista.splice(index, 1);
          }
        });
      });
    }

    function excluirPergunta(evt, pergunta) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir a pergunta?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
        vm.form.perguntas.forEach(function(perguntaLista, index, lista) {
          if (perguntaLista.nome === pergunta.nome) {
            lista.splice(index, 1);
          }
        });
      });
    }

    function mudaHabilitado(pesquisa) {
        // UPDATE
        console.log("UPDATE",pesquisa);
            PesquisaService.editar(pesquisa).then(function (response) {
              $mdToast.show(
                $mdToast.simple()
                .textContent('Pesquisa atualizada com sucesso!')
                .position('right')
                .hideDelay(5000)
            );
        });
    }

    function pesquisar() {
/*
      if (
        (vm.pesquisa.nome === null || vm.pesquisa.nome === undefined || vm.pesquisa.nome === '') &&
        (vm.pesquisa.dataInicial === null || vm.pesquisa.dataInicial === undefined || vm.pesquisa.dataInicial === '') &&
        (vm.pesquisa.dataFinal === null || vm.pesquisa.dataFinal === undefined || vm.pesquisa.dataFinal === '')
      ) {
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha pelo menos um campo para pesquisa!')
          .position('right')
          .hideDelay(5000)
        );
      } else {
*/      
        var filtro = vm.pesquisa;
          filtro.page = 1;
          filtro.limit = 1000;
        PesquisaService.pesquisar(filtro).then(function (response) {
          vm.pesquisa.lista = response.data;
            if (vm.pesquisa.lista.length == 0){
              $mdToast.show(
                  $mdToast.simple()
                  .textContent('Nenum registro encontrado!')
                  .position('right')
                  .hideDelay(5000)
                );
            }
        });
//      }
    }

    function excluir(evt, pesquisa) {
      var confirm = $mdDialog.confirm()
        .title('Deseja excluir a pesquisa?')
        .targetEvent(evt)
        .clickOutsideToClose(true)
        .parent(angular.element(document.body))
        .ok('Sim')
        .cancel('Não');
      $mdDialog.show(confirm).then(function() {
        PesquisaService.deletar(pesquisa.id).then(function(response) {
            vm.pesquisa.lista.forEach(function(pesquisaLista, index, lista) {
              if (pesquisaLista.nome === pesquisa.nome) {
                lista.splice(index, 1);
              }
            });
        });
      });
        
    }

    function salvar() {
        var existe = false;
        
        //Prepara Empresas
        var  lmEmpresas = []; 
console.log("vm.form1",vm.form);
        angular.forEach(vm.form.empresas, function (obj) {
            var empresa = {"id":obj};
            var pesqEmpresa = null;
            existe = false;
            if (vm.form.empresaPtr == obj){
                pesqEmpresa = {"empresa":empresa, "relacionamento":1};
            }else{
                pesqEmpresa = {"empresa":empresa, "relacionamento":2};
            }
            angular.forEach(vm.banco.empresas, function (objB) {
                if (obj == objB.empresa.id){
                    pesqEmpresa.id = objB.id;
                }
            });
            angular.forEach(lmEmpresas, function (objA) {
                if (pesqEmpresa.empresa.id == objA.empresa.id){
                    existe = true;
                }
            });
            if (!existe){
                lmEmpresas.push(pesqEmpresa);
            }
        });
        vm.form.empresas = lmEmpresas;
        
        //Prepara Itens
        var  lmItens = []; 

/*
        angular.forEach(vm.form.itens, function (obj) {
            var item = {"id":obj};
            var pesqItem = {"item":item};
            existe = false;
            angular.forEach(vm.banco.itens, function (objB) {
                if (obj == objB.item.id){
                    pesqItem.id = objB.id;
                }
            });
            
            angular.forEach(pesqItem, function (objA) {
                if (pesqItem.id == objA.id){
                    existe = true;
                }
            });
            if (existe){
                lmItens.push(pesqItem);
            }

        });
        vm.form.itens = lmItens;
*/
        vm.form.itens = vm.form.itensOrdem;
        
        //Prepara Paineis
        angular.forEach(vm.form.paineis, function (pnl) {
            if (pnl.modificado){
                // Add or Update Empresas do Painel
                angular.forEach(pnl.painelEmpresa, function (emp, index) {
                    emp.remover = true;
                    angular.forEach(pnl.idsEmp, function (idEmp) {
                        if (emp.id == idEmp){
                            emp.remover = false;
                        }
                    });
                    if (emp.remover){
                        pnl.painelEmpresa.splice(index, 1);
                    }
                });
                angular.forEach(pnl.idsEmp, function (idEmp) {
                    var naoExiste = true;
                    angular.forEach(pnl.painelEmpresa, function (emp) {
                        if (emp.empresa != null && emp.empresa.id == idEmp){
                            naoExiste = false;
                        }
                    });
                    if (naoExiste){
                        var empresa = {"id":idEmp};
                        var painelEmpresa = {"empresa": empresa};
                        pnl.painelEmpresa.push(painelEmpresa);
                    }
                });
            }
        });
        
        //Prepara Agrupadores
        angular.forEach(vm.form.agrupadores, function (agrupador) {
            // Add or Update Agrupadores
            angular.forEach(agrupador.itens, function (item) {
                var formulaAgrupador = {"item":item};
                agrupador.formulas.push(formulaAgrupador);
            });
        });
        
      validarFormulario().then(function() {
        if (isEditar()) {
          // UPDATE
console.log("UPDATE",vm.form);
            PesquisaService.editar(vm.form).then(function (response) {
              $mdToast.show(
                $mdToast.simple()
                .textContent('Pesquisa atualizada com sucesso!')
                .position('right')
                .hideDelay(5000)
              );
            });
        } else {
          // SAVE
console.log("SAVE",vm.form);
            PesquisaService.salvar(vm.form).then(function (response) {
                $mdToast.show(
                $mdToast.simple()
                .textContent('Pesquisa cadastrada com sucesso!')
                .position('right')
                .hideDelay(5000)
              );
            });
        }
        voltar();
      }, function() {
        $mdToast.show(
          $mdToast.simple()
          .textContent('Preencha todos os dados obrigatórios!')
          .position('right')
          .hideDelay(5000)
        );
      });
    }

    function getEmpresas() {
      // GET EMPRESAS
      EmpresaService.getAllEmpresas().then(function (response) {
          vm.listaEmpresas = response.data;
          vm.listaEmpresasSel = getEmpresasSelecionadas();
        });
    }

    function getItens() {
      // GET ITENS
      ItemService.getAllItens().then(function (response) {
          vm.listaItens = response.data;
          vm.listaItensSel = getItensSelecionados();
        });
    }

    function voltar() {
      $state.go('app.pesquisa');
    }

    function isEditar() {
      if ($state.current.name === 'app.pesquisa.editar') {
        return true;
      }

      return false;
    }

    function validarFormulario() {
      var defer = $q.defer();

      if (vm.ceForm.$invalid) {
        angular.forEach(vm.ceForm.$error, function (val, index) {
          angular.forEach(val, function (obj) {
            vm.ceForm[obj.$name].$setTouched();
          });
        });
        defer.reject();
      } else {
//        if (vm.form.paineis.length <= 0 || vm.form.agrupadores.length <= 0 || vm.form.perguntas.length <= 0) {
//          defer.reject();
//        }
        defer.resolve();
      }

      return defer.promise;
    }

    function painelDialogController($scope, $mdDialog, $mdToast, PesquisaService) {
        $scope.form = {};
        $scope.listaEmpresasSelecionadas = vm.listaEmpresasSel;
        
        if (vm.painelEditar !== null) {
          $scope.form = angular.copy(vm.painelEditar);
        }

        $scope.cancel = function () {
          $mdDialog.cancel();
          vm.painelEditar = null;
          vm.indicePainel = null;
        };

        $scope.formularioInvalido = function() {
          if (
            ($scope.form.nome === null || $scope.form.nome === undefined || $scope.form.nome === '') ||
            ($scope.form.idsEmp === null || $scope.form.idsEmp === undefined || $scope.form.idsEmp === '')
          ) {
            return true;
          }
          return false;
        };

        $scope.adicionaPainel = function() {
            $scope.form.modificado = true;
            $scope.form.displayPainel = [];
            angular.forEach($scope.form.idsEmp, function (idp) { 
                angular.forEach(vm.listaEmpresasSel, function (emp) { 
                    if (idp == emp.id){
                        $scope.form.displayPainel.push(emp.nome);
                    }
                });
            });
            if(vm.painelEditar !== null){
                vm.form.paineis[vm.indicePainel] = angular.copy($scope.form);
            }else{
                vm.form.paineis.push($scope.form);
            };
            vm.painelEditar = null;
            vm.indicePainel = null;
            $mdDialog.cancel();
        };
    }
      

    function agrupadorDialogController($scope, $mdDialog, $mdToast, PesquisaService) {
        $scope.form = {};
        $scope.listaItensSelecionados = vm.listaItensSel;
        if (vm.agrupadorEditar !== null) {
            $scope.form = angular.copy(vm.agrupadorEditar);
            $scope.form.itens = [];
            angular.forEach($scope.form.formulas, function (obj) { 
                $scope.form.itens.push(obj.item.id);
            });
        }

        $scope.cancel = function () {
          $mdDialog.cancel();
          vm.agrupadorEditar = null;
          vm.indiceAgrupador = null;
        };

        $scope.formularioInvalido = function() {
          if (
            ($scope.form === undefined ) || 
            ($scope.form.nome === null || $scope.form.nome === undefined || $scope.form.nome === '') ||
            ($scope.form.itens === null || $scope.form.itens === undefined || $scope.form.itens === '')
          ) {
            return true;
          }
          return false;
        };

        $scope.adicionaAgrupador = function() {
          if (vm.agrupadorEditar !== null) {
            vm.form.agrupadores[vm.indiceAgrupador] = angular.copy($scope.form);
          } else {
            vm.form.agrupadores.push($scope.form);
          }
          vm.agrupadorEditar = null;
          vm.indiceAgrupador = null;
          $mdDialog.cancel();
        };
    }

    function perguntaDialogController($scope, $mdDialog, $mdToast, PesquisaService, PerguntaService) {
        $scope.form = {};
        $scope.listaPerguntas = [];

        PerguntaService.getAllPerguntas().then(function (response) {
                $scope.listaPerguntas = response.data;
        });


        if (vm.perguntaEditar !== null) {
          $scope.form.ordem = angular.copy(vm.perguntaEditar.ordem);
          $scope.form.pergunta = angular.copy(vm.perguntaEditar.pergunta.id);

//          var pergunta = angular.copy(vm.perguntaEditar.pergunta);
//          $scope.listaPerguntas.forEach(function(perguntaLista, index, lista) {
//            if (perguntaLista.id === pergunta.id) {
//              $scope.form.pergunta = perguntaLista;
//            }
//          });
        }

        $scope.cancel = function () {
          $mdDialog.cancel();
          vm.perguntaEditar = null;
          vm.indicePergunta = null;
        };

        $scope.formularioInvalido = function() {
          if (
            ($scope.form.pergunta === null || $scope.form.pergunta === undefined || $scope.form.pergunta === '') ||
            ($scope.form.ordem === null || $scope.form.ordem === undefined || $scope.form.ordem === '')
          ) {
            return true;
          }
          return false;
        };

        $scope.adicionaPergunta = function() {
            $scope.listaPerguntas.forEach(function(perguntaLista, index, lista) {
                if (perguntaLista.id === $scope.form.pergunta) {
                    $scope.form.pergunta = perguntaLista;
                }
            });
            
            if (vm.perguntaEditar !== null) {
                vm.form.perguntas[vm.indicePergunta] = angular.copy($scope.form);
            } else {
                vm.form.perguntas.push($scope.form);
            }
            vm.perguntaEditar = null;
            vm.indicePergunta = null;
            $mdDialog.cancel();
        };
    }
  }
})();