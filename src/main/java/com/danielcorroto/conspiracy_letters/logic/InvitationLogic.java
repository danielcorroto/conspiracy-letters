package com.danielcorroto.conspiracy_letters.logic;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Logica de
 * 
 * @author Daniel Corroto
 *
 */
public class InvitationLogic {
	private static final Logger LOGGER = Logger.getLogger(InvitationLogic.class);

	/**
	 * Género del personaje
	 */
	private enum GENDER {
		IS_MALE, IS_FEMALE, IS_BOTH
	};

	/**
	 * Rol del personaje
	 */
	private static final String[] ROLE_NAME = { "Guardia", "Sacerdote", "Barón", "Doncella", "Príncipe", "Rey", "Condesa", "Princesa" };
	/**
	 * Género del rol del personaje
	 */
	private static final GENDER[] ROLE_GENDER = { GENDER.IS_BOTH, GENDER.IS_MALE, GENDER.IS_MALE, GENDER.IS_FEMALE, GENDER.IS_MALE,
			GENDER.IS_MALE, GENDER.IS_FEMALE, GENDER.IS_FEMALE };
	/**
	 * Adjetivo masculino para el personaje
	 */
	private static final String[] MALE_ADJ = { "aburrido", "afectuoso", "ágil", "agradable", "amable", "amargado", "ambicioso",
			"angelical", "apasionado", "apático", "ardiente", "atento", "atento", "atontado", "atrevido", "aventurero", "avispado",
			"basto", "beligerante", "brillante", "burlón", "cascarrabias", "cauto", "celoso", "cínico", "coherente", "considerado",
			"consternado", "cordial", "cortante", "cruel", "cuidadoso", "curioso", "de buen trato", "de mal genio", "débil", "dependiente",
			"deprimido", "desaliñado", "desconfiado", "desconsiderado", "descuidado", "desocupado", "diligente", "diplomático", "directo",
			"discreto", "distante", "distraído", "divertido", "dubitativo", "dulce", "educado", "eficiente", "egoísta", "elegante",
			"emprendedor", "encantador", "energético", "enfadado", "engreído", "entrometido", "entusiasmado", "equilibrado", "escandaloso",
			"estoico", "estúpido", "excéntrico ", "exigente", "experimentado", "extravertido", "fervoroso", "fiable", "fofo", "formal",
			"frío", "generoso", "gracioso", "gruñón", "hábil", "hermoso", "histérico", "holgazán", "horrible", "huraño", "imaginativo",
			"imbécil", "impaciente", "impetuoso", "impulsivo", "independiente", "infantil", "ingenuo", "inmaduro", "inquieto",
			"insensible", "inteligente", "interesante", "intolerante", "intrépido", "juguetón", "listo", "llorón", "maduro", "maleducado",
			"malhumorado", "malicioso", "malo", "maternal", "melancólico", "meticuloso", "mezquino", "modesto", "molesto", "mordaz",
			"nervioso", "odioso", "orgulloso", "paciente", "pacífico", "pasional", "pensativo", "perezoso", "perseverante", "perturbador",
			"pícaro", "poderoso", "positivo", "práctico", "pretencioso", "provocador", "prudente", "puntual", "quisquilloso", "reacio",
			"realista", "receloso", "reservado", "responsable", "ridículo", "sagaz", "seguro", "sensato", "sensible", "sereno", "serio",
			"servicial", "severo", "simpático", "sincero", "sofisticado", "somnoliento", "sucio", "superficial", "tacaño", "taciturno",
			"talentoso", "tímido", "tonto", "torpe", "tranquilizador", "tranquilo", "travieso", "triste", "valiente", "vivaz" };
	/**
	 * Adjetivo femenino para el personaje
	 */
	private static final String[] FEMALE_ADJ = { "aburrida", "afectuosa", "ágil", "agradable", "amable", "amargada", "ambiciosa",
			"angelical", "apasionada", "apática", "ardiente", "atenta", "atenta", "atontada", "atrevida", "aventurera", "avispada",
			"basta", "beligerante", "brillante", "burlona", "cascarrabias", "cauta", "celosa", "cínica", "coherente", "considerada",
			"consternada", "cordial", "cortante", "cruel", "cuidadosa", "curiosa", "de buen trato", "de mal genio", "débil", "dependiente",
			"deprimida", "desaliñada", "desconfiada", "desconsiderada", "descuidada", "desocupada", "diligente", "diplomática", "directa",
			"discreta", "distante", "distraída", "divertida", "dubitativa", "dulce", "educada", "eficiente", "egoísta", "elegante",
			"emprendedora", "encantadora", "energética", "enfadada", "engreída", "entrometida", "entusiasmada", "equilibrada",
			"escandalosa", "estoica", "estúpida", "excéntrica", "exigente", "experimentada", "extravertida", "fervorosa", "fiable", "fofa",
			"formal", "fría", "generosa", "graciosa", "gruñona", "hábil", "hermosa", "histérica", "holgazana", "horrible", "huraña",
			"imaginativa", "imbécil", "impaciente", "impetuosa", "impulsiva", "independiente", "infantil", "ingenua", "inmadura",
			"inquieta", "insensible", "inteligente", "interesante", "intolerante", "intrépida", "juguetona", "lista", "llorona", "madura",
			"maleducada", "malhumorada", "maliciosa", "mala", "maternal", "melancólica", "meticulosa", "mezquina", "modesta", "molesta",
			"mordaz", "nerviosa", "odiosa", "orgullosa", "paciente", "pacífica", "pasional", "pensativa", "perezosa", "perseverante",
			"perturbadora", "pícara", "poderosa", "positiva", "práctica", "pretenciosa", "provocadora", "prudente", "puntual",
			"quisquillosa", "reacia", "realista", "recelosa", "reservada", "responsable", "ridícula", "sagaz", "segura", "sensata",
			"sensible", "serena", "seria", "servicial", "severa", "simpática", "sincera", "sofisticada", "somnolienta", "sucia",
			"superficial", "tacaña", "taciturna", "talentosa", "tímida", "tonta", "torpe", "tranquilizadora", "tranquila", "traviesa",
			"triste", "valiente", "vivaz" };

	/**
	 * Para generar valores aleatorios
	 */
	private Random r = new Random();

	/**
	 * Genera un nombre aleatorio para nombre de la partida
	 * 
	 * @return Nombre aleatorio para la partida
	 */
	public String generateGameName() {
		// Rol
		int roleValue = r.nextInt(ROLE_NAME.length);
		String role = ROLE_NAME[roleValue];

		// Adjetivo
		String adjetive1 = "";
		String adjetive2 = "";
		int genderValue = r.nextInt(2);
		if (ROLE_GENDER[roleValue].equals(GENDER.IS_MALE) || (ROLE_GENDER[roleValue].equals(GENDER.IS_BOTH) && genderValue == 0)) {
			int adjetiveValue1 = r.nextInt(MALE_ADJ.length);
			adjetive1 = MALE_ADJ[adjetiveValue1];
			int adjetiveValue2;
			do {
				adjetiveValue2 = r.nextInt(MALE_ADJ.length);
				adjetive2 = MALE_ADJ[adjetiveValue2];
			} while (adjetiveValue1 == adjetiveValue2);
		} else {
			int adjetiveValue1 = r.nextInt(FEMALE_ADJ.length);
			adjetive1 = FEMALE_ADJ[adjetiveValue1];
			int adjetiveValue2;
			do {
				adjetiveValue2 = r.nextInt(FEMALE_ADJ.length);
				adjetive2 = FEMALE_ADJ[adjetiveValue2];
			} while (adjetiveValue1 == adjetiveValue2);
		}

		String gamename = role + " " + adjetive1;
		if (adjetive2.startsWith("i") || adjetive2.startsWith("hi")) {
			gamename += " e ";
		} else {
			gamename += " y ";
		}
		gamename += adjetive2;
		LOGGER.debug("Creating game name " + gamename);
		return gamename;
	}
}
