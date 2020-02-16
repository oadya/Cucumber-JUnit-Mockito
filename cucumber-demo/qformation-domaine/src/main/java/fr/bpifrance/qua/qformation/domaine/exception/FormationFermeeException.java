package fr.bpifrance.qua.qformation.domaine.exception;


public class FormationFermeeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String code;
	private final int httpCode;
	   
	public FormationFermeeException(final String code, int httpCode) {
		this.code = code;
		this.httpCode = httpCode;
	}
	 public String getCode() {
		 return code;
	 }
	 
	 public int getHttpCode() {
		 return httpCode;
	 }


}
