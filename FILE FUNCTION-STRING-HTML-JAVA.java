FILE FUNCTION-STRING-HTML-JAVA

	//renvoie la premiere apparition de la balise/(baslise comportant attribut) nomVariable
	public static String getBy(String nomVariable, String sourceString) { 
			  int i=0;
			  while(i<tabBalise.length) {
					if(nomVariable==tabBalise[i])
						return getByTag(nomVariable,sourceString);
					i++;
			  }
			  return getByAttribute(nomVariable,sourceString);
		  }
	//renvoie toutes les apparitions des balise/(baslise comportant attribut) nomVariable
	public static String getAllBy(String nomVariable, String sourceString) { 
			  int i=0;
			  while(i<tabBalise.length) {
					if(nomVariable==tabBalise[i])
						return getAllByTag(nomVariable,sourceString);
					i++;
			  }
			  return getAllByAttribute(nomVariable,sourceString);
	}	

	//renvoie la premiere apparition de la balise nomVariable
	public static String getByTag(String nomVariable, String sourceString) {
			String longueu="</"+nomVariable+">";
			int longueur=longueu.length();
			
			if(sourceString.contains("<"+nomVariable) && sourceString.contains("</"+nomVariable+">")) {
				
				int index1=sourceString.indexOf("<"+nomVariable);
				int cptDeb=index1;
				int index2=sourceString.indexOf("</"+nomVariable,index1);	
				int incre=1;

				if(index1<=index2 ) {
				    String sourceString2=sourceString.substring(index1,index2+longueur);					
					while(compteur_coherence(nomVariable,sourceString2)==false) {
						
						if(sourceString.indexOf("<" + nomVariable,cptDeb+longueur)<index2) {
							
							while(sourceString2.indexOf("<" + nomVariable,cptDeb+longueur-1)<index2 && sourceString2.indexOf("<" + nomVariable,cptDeb+longueur-1)!=-1) {
								incre+=1;
								cptDeb=sourceString2.indexOf(nomVariable,cptDeb+longueur-1);
							}
							while(incre>0 && sourceString.indexOf("</"+nomVariable,index2+longueur)!=-1 ) {
								index2=sourceString.indexOf("</"+nomVariable,index2+longueur);
								incre-=1;
							}	
							cptDeb=index1;
							incre=1;
							sourceString2=sourceString.substring(index1,index2+longueur);
							if(sourceString.indexOf("</"+nomVariable,index2+longueur)==-1)return sourceString2;
						}
					
					}
					return sourceString2;					
				}
			}
			return "";
	}

	//renvoi toutes les apparition des balises nomVariable concatené a la suite
	public static String getAllByTag(String nomVariable, String sourceString) {
		String chaineVariable=new String("");
		String longueur="</"+nomVariable+">";
		int longueur1=longueur.length();
		int index2=0;
		while(  sourceString.contains("</"+nomVariable+">") && (sourceString.length()>0) && sourceString.contains("<"+nomVariable)) {
			System.out.println("1");
			String chainePrinc=getByTag(nomVariable,sourceString);
			System.out.println("2");
			if(chainePrinc.length()<2)return chaineVariable;
			chaineVariable=chaineVariable.concat(chainePrinc);
			index2 =sourceString.indexOf( chainePrinc )+chainePrinc.length();
			sourceString=sourceString.substring(index2);
			System.out.println("3");
			
		}
		return chaineVariable;
	}
	
	//recupere la premiere apparition de l id nomVariable ou class 
	public static String getByAttribute(String nomVariable, String sourceString) {
				int index0_0=sourceString.indexOf(nomVariable);
				int index0_1=sourceString.lastIndexOf("<",index0_0);
				int itest=sourceString.lastIndexOf(">",index0_0);
				int index0_2=sourceString.indexOf(">",index0_0);
				int itest2=sourceString.indexOf("<",index0_0);
				if(index0_0==-1) return "";
				if( itest2<index0_2 || itest>index0_1 || index0_1>index0_2) {
					String sourceString2=sourceString.substring(index0_0+nomVariable.length());
					return getByAttribute(nomVariable,sourceString2);
				} 
				String variableComplete=sourceString.substring(index0_1,index0_2+1);
				
				int longueur=variableComplete.length();
				String varFin=new String();
				int z=variableComplete.indexOf(" ");
				String variableComplete1=variableComplete.substring(1,z);
				
				for(int i=0;i<tabBalise.length;i++) {
					if(variableComplete1.equals(tabBalise[i]))
						varFin=tabBalise[i];
				}
				String varlong="</"+varFin+">";
				int longueur2=varlong.length();
				int index1=sourceString.indexOf(variableComplete);
				int cptDeb=index1;
				int index2=sourceString.indexOf("</"+varFin+">",index1+longueur);					
				if(sourceString.contains(variableComplete) && index2!=-1) {
					
					int incre=1;
					
					String sourceString2=sourceString.substring(index1,index2+longueur2);
					
					while(compteur_coherence(varFin,sourceString2)==false) {
						
						if(sourceString.indexOf("<"+varFin,cptDeb+longueur)<index2) {
							
							while(sourceString2.indexOf("<"+varFin,cptDeb+longueur)<index2 && sourceString2.indexOf("<"+varFin,cptDeb+longueur)!=-1) {
								incre+=1;
								cptDeb=sourceString2.indexOf("<"+varFin+">",cptDeb+longueur);
							}
							while(incre>0 && sourceString.indexOf("</"+varFin,index2+longueur2)!=-1 ) {
								index2=sourceString.indexOf("</"+varFin,index2+longueur2);
								incre-=1;
							}
							cptDeb=index1;
							incre=1;
							sourceString2=sourceString.substring(index1,index2);
						}
					
					}
					return sourceString2;
				}
				else return "";	
	}

	//renvoie toutes les apparitions des balises comportant les attributs nomvariable concatenées
	public static String getAllByAttribute(String nomVariable, String sourceString) {
			String chaineVariable=new String("");
			int index0_0=sourceString.indexOf(nomVariable);
			int index0_1=sourceString.lastIndexOf("<",index0_0);
			int itest=sourceString.lastIndexOf(">",index0_0);
			int index0_2=sourceString.indexOf(">",index0_0);
			int itest2=sourceString.indexOf("<",index0_0);
			if(index0_0==-1) return "";
			if( itest2<index0_2 || itest>index0_1 || index0_1>index0_2) {
				String sourceString2=sourceString.substring(index0_0+nomVariable.length());
				return getByAttribute(nomVariable,sourceString2);
			} 
			String variableComplete=sourceString.substring(index0_1,index0_2+1);

			int z=variableComplete.indexOf(" ");
			String variableComplete1=variableComplete.substring(1,z);
			String varFin=new String();
			for(int i=0;i<tabBalise.length;i++) {
				if(variableComplete1.equals(tabBalise[i]))
					varFin=tabBalise[i];
			}
			String varlong="</"+varFin+">";
			int longueur2=varlong.length();
			
			while( sourceString.contains(nomVariable) && sourceString.contains(varlong) && (sourceString.length()>0)) {
				chaineVariable=chaineVariable.concat(getByAttribute(nomVariable,sourceString));
				int index3 = sourceString.indexOf(varlong)+longueur2;
				sourceString=sourceString.substring(index3);
			}
			return chaineVariable;
	}	
	
	//compte le nombre de </balise> <balise> pour savoir si le string est cohérent ou non
	public static boolean compteur_coherence(String nomVariable, String sourceString) {
			String longueur="</"+nomVariable+">";
			int compteurDeb=1;
			int compteurFin=1;
			int index1=sourceString.indexOf("<"+nomVariable);
			int longueurpage=sourceString.length();
			while (sourceString.indexOf("<"+nomVariable,index1+longueur.length()-1)!=-1 && index1<=longueurpage) {
				compteurDeb+=1;
				index1=sourceString.indexOf("<"+nomVariable,index1+longueur.length()-1);
			}
			int index2=sourceString.indexOf("</"+nomVariable);
			while (sourceString.indexOf("</"+nomVariable,index2+longueur.length())!=-1 && index2<=longueurpage) {
				compteurFin+=1;
				index2=sourceString.indexOf("</"+nomVariable,index2+longueur.length());	
			}		
			if(compteurDeb==compteurFin) {
			return true;
			}
			else 
				return false;
	}