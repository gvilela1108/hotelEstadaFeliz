package br.com.hotelEstadaFeliz.cnt;

public enum  TipoAcao {

	CONSULTAR(0,"Indica acao de consultar"),
	INSERIR(1,"Indica a acao de inserir/registrar"),
	ATUALIZAR(2,"Indica a acaode atualizar determinado registro"),
	DELETAR(3,"Indica a acao de deletar determinado registro"),
	CONSULTAR_TODOS(4,"Indica a aacao de consultar todos os registros"),
	TIPO_ACAO_INVALIDO(-1,"Filtro nao mapeado!");
	
    private final int code;
    private final String label;

    private TipoAcao(int code, String label) {
        this.code = code;
        this.label = label;
    }
    
	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static TipoAcao getTipoAcaoByCode(int code) {
		for (TipoAcao ta  : TipoAcao.values()) {
			if(ta.code == code){
        	  return ta;
          }
      }
	  return TIPO_ACAO_INVALIDO;
   }    
}