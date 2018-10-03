package br.com.hotelEstadaFeliz.cnt;

public enum  TipoAcao {

	CONSULTAR(0,"Indica a��o de consultar"),
	INSERIR(1,"Indica a a��o de inserir/registrar"),
	ATUALIZAR(2,"Indica a a��o de atualizar determinado registro"),
	DELETAR(3,"Indica a a��o de deletar determinado registro"),
	TIPO_ACAO_INVALIDO(-1,"Filtro n�o mapeado!");
	
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