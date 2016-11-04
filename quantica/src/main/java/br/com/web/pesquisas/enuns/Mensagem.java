package br.com.web.pesquisas.enuns;

public enum Mensagem {
    PARAMETRO_PESQUISA_OBRIGATORIO("obrigatorio");
    
    private String key;
    
    private Mensagem(String key){
        this.key = key;
    }
    
    public String getKey(){
        return this.key;
    }

}
