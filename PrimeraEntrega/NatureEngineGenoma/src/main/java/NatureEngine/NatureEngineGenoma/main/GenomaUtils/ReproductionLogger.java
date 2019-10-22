package NatureEngine.NatureEngineGenoma.main.GenomaUtils;

public class ReproductionLogger {
	
	// constructor privado para evitar ser llamado ya que solo tiene metodos estaticos
	private ReproductionLogger() {
	}
	
	public static void ReproductionDebugLog(String txt) {
		System.out.println("[Debug] "+txt);
	}
	
	public static void ReproductionLog(String txt) {
		System.out.println(txt);
	}
	
	public static void ReproductionWarning(String component, String warningtxt) {
		System.out.println("[Warning - "+component+"] "+warningtxt);
	}
	
	public static void ReproductionNotImplemented(String component) {
		System.out.println("[Warning - "+component+"] Fin del camino. Falta implementar");
	}
	
	public static void ReproductionError(String component, Exception exception) {
		System.out.println("[Error - "+component+"] "+exception.getMessage()+exception.getStackTrace()[0]);
	}
	
}
