!(function () {
    'use strict';

    angular
            .module('app.pages.private.funcionarioCliente')
            .controller('funcionarioClienteController', FuncionarioClienteController);

    /**
     * @ngInject
     * @author Andrey Moretti <andreymor@gmail.com>
     * @param {type} $scope
     * @param {type} $mdToast
     * @param {type} $stateParams
     * @param {type} $state
     * @param {type} $mdDialog
     * @param {type} UfService
     * @param {type} municipioService
     * @param {type} $log
     * @param {type} FuncionarioClienteService
     * @param {type} $translatePartialLoader
     * @param {type} $q
     * @returns {funcionarioClienteController_L1.FuncionarioClienteController}
     */
    function FuncionarioClienteController($scope,
            $mdToast,
            $stateParams,
            $state,
            $mdDialog,
            UfService,
            municipioService,
            $log,
            FuncionarioClienteService,
            $translatePartialLoader,
            $q) {

        /**
         * @desc Instancias de objetos
         */
        this.$scope = $scope;
        this.$mdToast = $mdToast;
        this.$state = $state;
        this.$stateParams = $stateParams;
        this.$mdDialog = $mdDialog;
        this.UfService = UfService;
        this.municipioService = municipioService;
        this.$log = $log;
        this.FuncionarioClienteService = FuncionarioClienteService;
        this.$q = $q;

        var $this = this;
        $this.nomeTeste = "Matheus";
        //

        /**
         * @desc Propriedades utilizadas como objetos na view
         * @type {{}}
         */
        this.pesquisa = this.FuncionarioClienteService.getPesquisaParams();

        /*
         * 
         * this.$scope.$watch('vm.pesquisa.nome', function(ov, nv){ // execuçao })
         * 
         */

        this.showGrid = Object.keys(this.pesquisa).length;

        if (this.showGrid && this.$state.current.name == 'app.funcionarioCliente') {
            this.carregarGrid();
        }

        /**
         * @desc Se editando ou cadastrando, carrega-se as UFs
         */
        if (this.$state.current.name == 'app.funcionarioCliente.incluir' || this.$state.current.name == 'app.funcionarioCliente.editar') {
            this.carregarDadosApoio();
            this.carregarFuncionario();
        }

        /**
         * @desc Retornando o funcionario em caso de edição
         */

        if (this.$state.current.name == 'app.funcionarioCliente.visualizar') {
            this.carregarFuncionario();
        }

        $log.info(this);

    }

    /**
     * @desc carrega a grid de clientes
     * @returns {undefined}
     */

    FuncionarioClienteController.prototype.teste = function () {
        var $this = this;

        alert();
    };

    FuncionarioClienteController.prototype.carregarGrid = function () {
        var $this = this;

        var sucesso = function (response) {
            $this.gridContent = response.data;
            $this.showGrid = true;
        }

        var erro = function (response) {
            $this.$log.error(response);
        }

        this.FuncionarioClienteService.search(this.pesquisa).then(sucesso, erro);
    }


    FuncionarioClienteController.prototype.carregarFuncionario = function () {
        var $this = this;
        if (this.$stateParams.id) {

            var sucessoEdit = function (response) {
                //Remover em caso de consulta à API.
                $this.funcionarioCliente = _.find(response.data, {'id': $this.$stateParams.id});
                //$this.funcionarioCliente = response.data;
            }

            var erroEdit = function (response) {
                $mdToast.show(
                        $mdToast.simple()
                        .textContent("Não foi possível localizar o funcionário.")
                        .position('right')
                        .hideDelay(5000)
                        );
            }



            $this.FuncionarioClienteService.search({id: $this.$stateParams.id}).then(sucessoEdit, erroEdit);
        }

    }

    FuncionarioClienteController.prototype.carregarDadosApoio = function () {
        var $this = this;
        var sucesso = function (response) {
            $this.uf = response.data;
        };

        var erro = function (response) {
            $this.$log.error("Falha ao recuperar UF");
        };

        $this.uf = $this.uf || this.UfService.get().then(sucesso, erro);
    }

    /**
     *
     */
    FuncionarioClienteController.prototype.limparPesquisa = function () {
        this.pesquisa = this.gridContent = {};
        this.showGrid = false
        this.FuncionarioClienteService.setPesquisaParams({});
    };

    /**
     *
     * @param funcionario
     */
    FuncionarioClienteController.prototype.showConfirm = function (funcionario) {
        var $this = this;
        var confirm = this.$mdDialog.confirm()
                .title('Deseja excluir o registro selecionado?')
                .textContent()
                .ariaLabel('Lucky day')
                .targetEvent(funcionario)
                .clickOutsideToClose(true)
                .parent(angular.element(document.body))
                .ok('Sim')
                .cancel('Não');

        this.$mdDialog.show(confirm).then(function () {

            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent("O(a) " + funcionario.nome + " foi excluído(a) com sucesso!")
                    .position('right')
                    .hideDelay(5000)
                    );

        }, function () {
            //$this.$log.error('Deu erro');
        });
    };

    /**
     *
     * @param idUf
     */
    FuncionarioClienteController.getMunicipio = function (idUf) {
        var $this = this;

        var sucesso = function (response) {
            $this.municipio = response.data;
        }

        var erro = function (response) {
            $this.$log('ERRO ===>>>', response);
        }

        this.municipioService.get(idUf).then(function (response) {

        }, function () {
            console.log("Deu ruim o municipio");
        });
    };

    /**
     *
     */
    FuncionarioClienteController.prototype.pesquisar = function () {
        var $this = this;
        if (!this.pesquisa.hasOwnProperty('cliente') &&
                !this.pesquisa.hasOwnProperty('nome') &&
                !this.pesquisa.hasOwnProperty('login') &&
                !this.pesquisa.hasOwnProperty('cpf') &&
                !this.pesquisa.hasOwnProperty('ativo')
                ) {
            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent('Por favor, informe ao menos algum dos par\u00e2metros!')
                    .position('right')
                    .hideDelay(5000)
                    );
        } else {
            this.FuncionarioClienteService.setPesquisaParams(this.pesquisa);
            this.carregarGrid();
        }
    };

    /**
     * @desc Método de validação do formulário
     * @returns {*}
     */
    FuncionarioClienteController.prototype.validateForm = function () {
        /**
         * Criação da instância do objeto deferido
         */
        var defer = this.$q.defer();

        var $this = this;

        if (this.clienteFormCadastro.$invalid) {
            angular.forEach($this.clienteFormCadastro.$error, function (val, index) {
                angular.forEach(val, function (obj) {
                    /**
                     * Atribuição de $touched = true para os objetos inválidos
                     */
                    $this.clienteFormCadastro[obj.$name].$setTouched();
                })
            });

            /**
             * Rejeição do objeto deferido
             */
            defer.reject();
        } else {
            /**
             * Aceitação do objeto deferido
             */
            defer.resolve();
        }

        /**
         * Retorno do promise do objeto deferido
         */
        return defer.promise;

    }

    /**
     * @desc Método que executa POST/PUT à API
     */
    FuncionarioClienteController.prototype.salvar = function () {
        var $this = this;
        /**
         * @desc função de sucesso da validação
         */
        var sucesso = function () {


            /**
             * @desc função de sucesso de chamada à API
             */
            var sucessoSave = function (response) {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent('Operação realizada com sucesso')
                        .position('right')
                        .hideDelay(5000)
                        );
                $this.$state.go('app.funcionarioCliente');
            }

            /**
             * @desc função de erro de chamada à API
             */
            var erroSave = function (response) {
                $this.$mdToast.show(
                        $this.$mdToast.simple()
                        .textContent('Ocorreu um erro ao enviar os dados ao servidor. ')
                        .position('right')
                        .hideDelay(5000)
                        );

                $this.$log.error(response);
            };

            /**
             * @desc Chamada à função de inserção da camada de service (API)
             */
            $this.FuncionarioClienteService.insert($this.funcionarioCliente).then(sucessoSave, erroSave);
        }

        /**
         * @desc função de erro da validação
         */
        var erro = function () {
            $this.$mdToast.show(
                    $this.$mdToast.simple()
                    .textContent('Por favor, verifique as obrigatoriedades')
                    .position('right')
                    .hideDelay(5000)
                    );
        }

        /**
         * @desc chamada à função de validação do form
         */
        this.validateForm().then(sucesso, erro);
    };


}());
