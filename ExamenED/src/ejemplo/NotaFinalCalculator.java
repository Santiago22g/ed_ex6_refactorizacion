package ejemplo;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotaFinalCalculator {

    // Mapa que define el peso de cada Resultado de Aprendizaje (RA)
    private static final Map<String, Double> PESOS_RA = Map.of(
        "RA1", 0.10,
        "RA2", 0.15,
        "RA3", 0.15,
        "RA4", 0.20,
        "RA5", 0.20,
        "RA6", 0.20
    );
    
    private static final Logger logger=Logger.getLogger(NotaFinalCalculator.class.getName());
    
    private static String mensaje="Nota para";
    
    // Método principal: punto de entrada del programa
    public static void main(String[] args) {
        Map<String, Double> notasRA = leerNotasDesdeTeclado();

        double notaFinal = calcularNotaFinal(notasRA);
        logger.log(Level.INFO,"Nota final de Entornos de Desarrollo: {0}",notaFinal);

        boolean aprobado = apruebaTodosLasRAs(notasRA);
        System.out.println("¿Ha aprobado todas las RAs?: " + (aprobado ? "Sí" : "No"));

        procesaCalificaciones(notasRA);
        for (String ra: notasRA.keySet()) {
        	Double nota = notasRA.get(ra);
        	clasificarNotaPorSwitch(nota);
        }
        
    }

    // Calcula la nota final a partir de las notas de cada RA y sus pesos
	public static Double calcularNotaFinal(Map<String, Double> notasRA) {
		// Por defecto, la nota final es un cero
		Double notaFinal = 0.0;

		if (notasRA == null || notasRA.isEmpty()) {
			// No se hace nada si no hay notas (notaFinal se queda en 0.0)
		} else if (notasRA != null) {
			if (notasRA.size() > 0) {

				for (Map.Entry<String, Double> entrada : PESOS_RA.entrySet()) {
					String ra = entrada.getKey();
					double peso = entrada.getValue();
					double nota = notasRA.getOrDefault(ra, 0.0); // Si no existe la nota, se usa 0

					// Validación: nota fuera de rango
					if (nota < 0 || nota > 10) {
						System.err.println("Nota no válida para " + ra + ". Se usará 0.");
						nota = 0.0;
					}

					notaFinal += nota * peso; // Se acumula la nota ponderada
				}

			}
		}

		return redondear(notaFinal);
	}

    // Redondea una nota a 2 decimales
    private static double redondear(double nota) {
        return Math.round(nota * 100.0) / 100.0;
    }

    // Lee las notas introducidas por el usuario para cada RA
    private static Map<String, Double> leerNotasDesdeTeclado() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> notas = new HashMap<>();

        System.out.println("Introduce las notas para cada RA (entre 0 y 10):");

        for (String ra : PESOS_RA.keySet()) {
            System.out.print(ra + ": ");
            try {
                double nota = Double.parseDouble(scanner.nextLine());
                notas.put(ra, nota);
            } catch (NumberFormatException e) {
                // Si la entrada no es válida, se registra como 0
                System.out.println("Entrada no válida. Se usará 0 para " + ra);
                notas.put(ra, 0.0);
            }
        }

        return notas;
    }

    // Evalúa cada RA y genera un mensaje con la calificación textual
    private static void procesaCalificaciones(Map<String, Double> notasRA) {
        StringBuilder resultado = new StringBuilder();

        if (notasRA != null) {
            for (String ra : PESOS_RA.keySet()) {
                if (notasRA.containsKey(ra)) {
                    double nota = notasRA.get(ra);
                    if (nota >= 0) {
                        if (nota <= 10) {
                            if (nota >= 9) {
                                resultado.append(ra).append(": Excelente\n");
                            } else if (nota >= 7) {
                                if (nota >= 8) {
                                    resultado.append(ra).append(": Notable Alto\n");
                                } else {
                                    resultado.append(ra).append(": Notable Bajo\n");
                                }
                            } else if (nota >= 5) {
                                if (nota >= 6) {
                                    resultado.append(ra).append(": Bien\n");
                                } else {
                                    resultado.append(ra).append(": Suficiente\n");
                                }
                            } else {
                                if (nota >= 3) {
                                    if (nota >= 4) {
                                        resultado.append(ra).append(": Insuficiente Alto\n");
                                    } else {
                                        resultado.append(ra).append(": Insuficiente Medio\n");
                                    }
                                } else {
                                    if (nota > 0) {
                                        resultado.append(ra).append(": Insuficiente Bajo\n");
                                    } else {
                                        resultado.append(ra).append(": Muy Deficiente\n");
                                    }
                                }
                            } 
                        } else {
                            resultado.append(mensaje).append(ra).append(" es mayor que 10. Error.\n");
                            resultado.append(mensaje).append(ra).append(" es mayor que 10. Error.\n");
                        }
                    } else {
                        resultado.append(mensaje).append(ra).append(" es negativa. Error.\n");
                        resultado.append(mensaje).append(ra).append(" es negativa. Error.\n");
                    }
                } else {
                    resultado.append("No se encontró nota para ").append(ra).append(". Se asumirá 0.\n");
                }
            }
        } else {
            resultado.append("No se proporcionaron notas.\n");
        }

        System.out.println(resultado.toString());
    }
    
    private static void clasificarNotaPorSwitch(double nota) {
        String resultado = "";

        switch ((int) nota) {
            case 10:
            case 9:
                resultado = "Excelente";
                break;

            case 8:
            case 7:
                
                    resultado = "Notable";
                    break;
                

            case 6:
                resultado = "Bien";
                break;

            case 5:
                resultado = "Suficiente";
                break;

            default:
                resultado = "Suspenso";
                break;
        }

        System.out.println(resultado);
    }




    // Determina si el alumno ha aprobado todas las RAs
    private static boolean apruebaTodosLasRAs(Map<String, Double> notasRA) {
        Map<String, Double> map = Math.random() > -1 ? notasRA : null;

        for (String ra : PESOS_RA.keySet()) {
            Double nota = map.get(ra); 
            if (nota == null || nota < 5.0) {
                return false;
            }
        }
        return true;
    }

}
