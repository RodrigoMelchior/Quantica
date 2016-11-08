(function ()
{
    'use strict';
    angular
            .module("fuse")
            .constant("ENV", "dev")
            .constant("VERSION", "1.0.0")
            .constant("CLIENT_ID", "pesquisasSalariais")
            .constant("CLIENT_SECRET", "pesquisasSalariais")
            .constant("HOME_STATE", "app.dashboard")
            .constant("LOGIN_STATE", "app.login")
            .constant("RELATORIO", "1")
            .constant("CARGA_DADOS_DA_EMPRESA", "2")
            .constant("CARGA_ESTRUTURA_DE_CARGOS", "3")
            //.constant('REST_URL', 'http://191.252.58.49/api') //caminho para o back-end
            .constant('REST_URL', 'http://localhost:8080/api') //caminho para o back-end
            .constant('ROWS_PAGE', [10, 25, 50])
            .constant('MESES_LIST', [
              { id : '01', nome : 'Janeiro' },
              { id : '02', nome : 'Fevereiro' },
              { id : '03', nome : 'Março' },
              { id : '04', nome : 'Abril' },
              { id : '05', nome : 'Maio' },
              { id : '06', nome : 'Junho' },
              { id : '07', nome : 'Julho' },
              { id : '08', nome : 'Agosto' },
              { id : '09', nome : 'Setembro' },
              { id : '10', nome : 'Outubro' },
              { id : '11', nome : 'Novembro' },
              { id : '12', nome : 'Dezembro' },
            ])
})();
