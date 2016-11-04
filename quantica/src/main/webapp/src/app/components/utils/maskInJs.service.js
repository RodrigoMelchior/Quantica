(function ()
{

    /*jshint bitwise: false*/
    'use strict';

    angular.module('app.components.utils', [])
            .factory('maskInJs', function () {
                return {
                    telefone: function (v) {
                        v = v.replace(/\D/g, "");             //Remove tudo o que não é dígito
                        v = v.replace(/^(\d{2})(\d)/g, "($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
                        v = v.replace(/(\d)(\d{4})$/, "$1-$2");    //Coloca hífen entre o quarto e o quinto dígitos
                        return v;
                    },
                    cep: function (v) {
                        v = v.replace(/\D/g, "");             //Remove tudo o que não é dígito
                        v = v.replace(/(\d{2})(\d)/, "$1.$2")  //Coloca um ponto entre o terceiro e o quarto dígitos
                        v = v.replace(/(\d)(\d{3})$/, "$1-$2");    //Coloca hífen entre o quarto e o quinto dígitos
                        return v;
                    },
                    cnpj: function (v) {
                        v = v.replace(/^(\d{2})(\d)/, "$1.$2"); //Coloca ponto entre o segundo e o terceiro dígitos
                        v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3") //Coloca ponto entre o quinto e o sexto dígitos
                        v = v.replace(/\.(\d{3})(\d)/, ".$1/$2"); //Coloca uma barra entre o oitavo e o nono dígitos
                        v = v.replace(/(\d{4})(\d)/, "$1-$2");  //Coloca um hífen depois do bloco de quatro dígitos
                        return v;
                    },
                    cpf: function (v) {

                        v = v.replace(/(\d{3})(\d)/, "$1.$2");  //Coloca um ponto entre o terceiro e o quarto dígitos
                        v = v.replace(/(\d{3})(\d)/, "$1.$2"); //Coloca um ponto entre o terceiro e o quarto dígitos
                        v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2"); //Coloca um hífen entre o terceiro e o quarto dígitos
                        return v;
                    }
                };
            });

})();
