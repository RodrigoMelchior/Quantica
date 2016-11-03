(function ()
{
    'use strict';
    angular
            .module('app.pages.private.centroDeCusto')
            .controller('CentroDeCustoController', CentroDeCustoController);
    /** @ngInject 
     * @author Matheus Herculano <matheus95df@gmail.com>
     * @param {type} ROWS_PAGE
     * @param {type} centroDeCustoService
     * @param {type} UfService
     * @param {type} $stateParams
     * @param {type} $scope
     * @param {type} $mdDialog
     * @param {type} $mdMedia
     * @param {type} $state
     * @param {type} $mdToast
     * @param {type} $log
     * @param {type} $q
     * @returns {centroDeCustoController_L1.CentroDeCustoController}
     */
    function CentroDeCustoController(
            ROWS_PAGE,
            centroDeCustoService,
            UfService,
            $stateParams,
            $scope,
            $mdDialog,
            $mdMedia,
            $state,
            $mdToast,
            $log,
            $q) {

        /**
         * @desc instancias do obj
         */
        this.ROWS_PAGE = ROWS_PAGE;
        this.centroDeCustoService = centroDeCustoService;
        this.UfService = UfService;
        this.$stateParams = $stateParams;
        this.$scope = $scope;
        this.$mdDialog = $mdDialog;
        this.$mdMedia = $mdMedia;
        this.$state = $state;
        this.$mdToast = $mdToast;
        this.$log = $log;
        this.$q = $q;
        this.treeOptions = {
            nodeChildren: "centroscustosfilhos",
            dirSelectable: true
        };

        var $this = this;

        $this.cancel = function () {
            alert();
            $this.$mdDialog.cancel();
        };
        

        this.pesquisa = this.centroDeCustoService.getPesquisaParams();

        if ($this.$state.current.name == 'app.centroDeCusto.vizualizar') {
            $this.vizualizar($this.$stateParams.id);
            $this.carregarArvore($this.$stateParams.id);
        }

        if ($this.$state.current.name == 'app.centroDeCusto.editar') {
            $this.carregarEdicao($this.$stateParams.id);
//            $this.$scope.autocomplete = {"id":704,"nome":"asdasdasd","ativo":true,"numerocentrocusto":"123123","numerocentrocustopai":null};

        }


        this.onPaginate = function (page, limit) {
            var sucesso = function (response) {
                $this.totalCount = response.headers('x-total-count');
                $this.gridContent = $this.toSituacao(response.data);
                $this.showGrid = true;
            };

            var erro = function (response) {
                $this.$log.error(response);
            };

            $this.centroDeCustoService.paginate(page, limit).then(sucesso, erro);
        };

        /**
         * Perguntar ao Andrey
         * autcomplete
         * @param {type} query
         * @returns {unresolved}
         */
        this.autoComplete = function (query) {
            return  centroDeCustoService.autoComplete(query).then(function (response) {
                return response.data;
            }, function () {
                console.log("Erro no autocomplete");
            });
        };


    }

    /**
     * Tirar duvida Andrey
     */
//    CentroDeCustoController.prototype.onPaginate = function (page, limit){
//        var $this = this;
//        
//        var sucesso = function (response) {
//                $this.totalCount = response.headers('x-total-count');
//                $this.gridContent = $this.toSituacao(response.data);
//                $this.showGrid = true;
//            };
//
//            var erro = function (response) {
//                $this.$log.error(response);
//            };
//
//            $this.centroDeCustoService.search($this.pesquisa, page, limit).then(sucesso, erro);
//    };


    /**
     * Pesquisa filtrada
     * @returns {undefined}
     */
    CentroDeCustoController.prototype.pesquisar = function () {
        var $this = this;

        if (!$this.pesquisa.hasOwnProperty('numerocentrocusto') &&
                !$this.pesquisa.hasOwnProperty('nome') &&
                !$this.pesquisa.hasOwnProperty('ativo')) {

            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent('Por favor, informe ao menos algum dos par\u00e2metros!')
                    .position('right')
                    .hideDelay(5000)
                    );
        } else {
            this.carregarGrid();
        }
    };

    CentroDeCustoController.prototype.verificarCentroCusto = function (numero) {
        var $this = this;

        //validaÁ„o para a tela de incluir
        var existe = function () {
            $this.centroCustoExistente = true;
        };

        var naoExiste = function () {
            $this.centroCustoExistente = false;
        };

        this.centroDeCustoService.verificaCentroCusto(numero).then(existe, naoExiste);
    };

    /**
     * remo√ß√£o do aviso de campo requerido
     * @returns {undefined}
     */
    CentroDeCustoController.prototype.verificarAutocomplete = function () {
        var $this = this;
        if (!$this.$scope.hasOwnProperty('id')) {
            this.autocompleteInvalid = false;
        } else {
            this.autocompleteInvalid = true;
        }


    };

    CentroDeCustoController.prototype.validateForm = function () {
        /**
         * Cria√ß√£o da inst√¢ncia do objeto deferido
         */
        var defer = this.$q.defer();

        var $this = this;

        if (this.cdcForm.$invalid) {
            angular.forEach($this.cdcForm.$error, function (val, index) {
                angular.forEach(val, function (obj) {
                    if (obj.$name === 'autocomplete') {
                        $this.autocompleteInvalid = true;
                    } else {
                        $this.cdcForm[obj.$name].$setTouched();
                    }
                });
            });



            /**
             * Rejei√ß√£o do objeto deferido
             */
            defer.reject();
        } else {
            /**
             * Aceita√ß√£o do objeto deferido
             */
            defer.resolve();
        }

        /**
         * Retorno do promise do objeto deferido
         */
        return defer.promise;
    };

    CentroDeCustoController.prototype.save = function (obj) {
        var $this = this;
        /**
         * @desc fun√ß√£o de erro da valida√ß√£o
         */

        var erro = function () {
            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent('Por favor, verifique as obrigatoriedades')
                    .position('right')
                    .hideDelay(5000)
                    );
        }

        var sucesso = function () {
            var toSave = {
                "centroCustoPai": {
                    "id": $this.$scope.autocomplete.id
                },
                "nome": obj.nome,
                "numerocentrocusto": obj.numeroCentroCusto,
                "ativo": obj.ativo
            };

            var sucesso = function () {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("O(a) " + obj.nome + " foi incluido(a) com sucesso!")
                        .position('right')
                        .hideDelay(5000)
                        );
                $this.$state.go('app.centroDeCusto');
            };

            var erro = function () {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("Falha ao salvar o Centro de Custo")
                        .position('right')
                        .hideDelay(5000)
                        );
            };

            $this.centroDeCustoService.save(toSave).then(sucesso, erro);
        };

        if ($this.centroCustoExistente === true) {
            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent("N\u00famero do Centro de Custo existente")
                    .position('right')
                    .hideDelay(5000)
                    );
        } else {

            $this.validateForm().then(sucesso, erro);
        }
    };

    CentroDeCustoController.prototype.update = function (obj) {
        var $this = this;

        var erro = function () {
            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent('Por favor, verifique as obrigatoriedades')
                    .position('right')
                    .hideDelay(5000)
                    );
        };

        var sucesso = function () {
            var toSave = {//montar o objeto para salvar
                "id": obj.id,
                "centroCustoPai": {
                    "id": $this.$scope.autocomplete.id
                },
                "nome": obj.nome,
                "numerocentrocusto": obj.numerocentrocusto,
                "ativo": obj.ativo
            };

            $this.centroDeCustoService.update(obj.id, toSave).then(function (response) {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("O(a) " + obj.nome + " foi alterado(a) com sucesso!")
                        .position('right')
                        .hideDelay(7000)
                        );
                $this.$state.go('app.centroDeCusto');
            }, function () {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent("Falha ao alterar o Centro de Custo")
                        .position('center')
                        .hideDelay(7000)
                        );
            });
        };

        if ($this.centroCustoExistente === true) {
            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent("N\u00famero do Centro de Custo existente")
                    .position('right')
                    .hideDelay(5000)
                    );
        } else {

            $this.validateForm().then(sucesso, erro);
        }
    };

    CentroDeCustoController.prototype.carregarArvore = function (id) {
        var $this = this;
        $this.centroDeCustoService.getArvore(id).then(function (response) {
            $this.jsonData = response.data;
            $this.expandedNodes = $this.jsonData.centroscustosfilhos;
        }, function () {
            $this.$log.error("Falha ao recuperar a arvore");
        });


    };

    CentroDeCustoController.prototype.limparCampos = function () {
        var $this = this;
        $this.cdc = {}; //campos do formul·rio
        $this.$scope.searchText = undefined; // autcomplete
    };


    /**
     * Carregar dados do filtro de pesquisa
     * @returns {undefined}
     */
    CentroDeCustoController.prototype.carregarGrid = function () {
        var $this = this;

        var sucesso = function (response) {
            $this.totalCount = response.headers('x-total-count');
            $this.gridContent = $this.toSituacao(response.data);
            $this.showGrid = true;
        };

        var erro = function (response) {
            $this.$log.error(response);
        };

        $this.showGrid = false;
        $this.centroDeCustoService.search(this.pesquisa).then(sucesso, erro);
        $this.showGrid = true;
    };


    /**
     * carregar dados para vizualizar
     * @param {type} id
     * @returns {undefined}
     */
    CentroDeCustoController.prototype.vizualizar = function (id) {
        var $this = this;

        var sucesso = function (response) {
            $this.cdc = response.data;
            $this.$log.info($this.cdc);
        };

        var erro = function (response) {
            $this.$log.error(response);
        };

        $this.centroDeCustoService.getById(id).then(sucesso, erro);
    };

    CentroDeCustoController.prototype.carregarEdicao = function (id) {
        var $this = this;
        
        var sucesso = function (response) {
            $this.cdc = response.data;
            
            var centroCustoPai = {numerocentrocusto: $this.cdc.numerocentrocustopai}; //parametro para buscar o PAI
            //setando dados no autocomplete
            $this.centroDeCustoService.search(centroCustoPai).then(function (response){
                $this.$scope.autocomplete = response.data[0];
            },function (){
                $this.$log.error("Falha ao recuperar o centro de custo pai");
            });
        };

        var erro = function (response) {
            $this.$log.error(response);
        };

        $this.centroDeCustoService.getById(id).then(sucesso, erro);
    };

    /**
     *  converter a situa√ß√£o de boolean 
     *  para ativo ou inativo
     * @param {type} situacao
     * @returns {unresolved}
     */
    CentroDeCustoController.prototype.toSituacao = function (situacao) {
        angular.forEach(situacao, function (dado) {
            dado.ativo === true ? dado.ativo = "Ativo" : dado.ativo = "Inativo";
        });
        return situacao;
    };

    //limpar pesquisa
    CentroDeCustoController.prototype.limparPesquisa = function () {
        var $this = this;

        $this.pesquisa = this.gridContent = {};
        $this.showGrid = false;
        $this.centroDeCustoService.setPesquisaParams($this.pesquisa);
    };

//funÁ„o para deletar
    CentroDeCustoController.prototype.showConfirm = function (cdc, evt) {
        var $this = this;

        /**
         * Controladora do modal de exclus„o
         * @param {type} $scope
         * @param {type} $mdDialog
         * @returns {undefined}
         */
        function DialogController($scope, $mdDialog) {
            $this.centroDeCustoService.getArvore(cdc.id).then(function (response) {
//            $scope.arvore = response.data;
                $scope.arvore = response.data;
                $scope.expandedNodes = response.data.centroscustosfilhos;
            }, function () {
                $this.$log.error("Falha ao recuperar a arvore");
            });
            $scope.treeOptions = {//opÁıes da tree
                nodeChildren: "centroscustosfilhos",
                dirSelectable: true
            };
            ;
            $scope.cancel = function () {
                $this.$mdDialog.cancel();
            };

            $scope.comfirmarDelete = function () {

                var sucesso = function () {
                    $this.carregarGrid();
                    $this.$mdToast.show(
                            $this.$mdToast.simple()
                            .textContent("O(a) " + cdc.nome + " foi exclu√≠do(a) com sucesso!")
                            .position('right')
                            .hideDelay(5000)
                            );

                    $this.$mdDialog.cancel();
                };
                var erro = function () {
                    $this.$mdToast.show(
                            $this.$mdToast.simple()
                            .textContent("Falha ao excluir " + cdc.nome)
                            .position('right')
                            .hideDelay(5000)
                            );
                };

                $this.centroDeCustoService.remove(cdc.id).then(sucesso, erro);
            };
        }

        //configuraÁıes do modal de exlclus„o
        var useFullScreen = ($this.$mdMedia('sm') || $this.$mdMedia('xs')) && $this.$scope.customFullscreen;
        $this.$mdDialog.show({
            controller: DialogController,
            templateUrl: 'app/main/pages/private/centroDeCusto/excluirCentroDeCusto.modal.html',
            parent: angular.element(document.body),
            targetEvent: evt,
            clickOutsideToClose: true,
            fullscreen: useFullScreen
        })



        $this.$scope.$watch(function () {
            return $this.$mdMedia('xs') || $this.$mdMedia('sm');
        }, function (wantsFullScreen) {
            $this.customFullscreen = (wantsFullScreen === true);
        });
    };
    
    CentroDeCustoController.prototype.cancelar = function (evt){
        var $this = this;
        var confirm = this.$mdDialog.confirm()
                .title('Gostaria de cancelar?')
                .textContent()
                .ariaLabel('Lucky day')
                .targetEvent(evt)
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .ok('Sim')
                .cancel('N√£o');
        this.$mdDialog.show(confirm).then(function () {
            $this.$state.go('app.centroDeCusto');
        });
    };



}());
