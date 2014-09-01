package javaapplication36;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javaapplication36.Ciudad;

public class Permutar
{
	public static void main ( String args [])
	{
		String p ="0123456789abcdefghijklmnopqrstuvwxyz";
 
                Random rnd = new Random(111);
                
                Map<String, Ciudad> lista = new HashMap<String, Ciudad>();
                for (int i=0; i < p.length(); i++){
                    String  id= p.charAt(i)+ "";
       
                    lista.put(id, 
                            new Ciudad(id, rnd.nextInt(100), rnd.nextInt(100)));
                }
            //System.out.println(lista);
            //p="04z8ul36gd9ne5ywvo72xhsakfmtpjr1ibcq";
         int solu=fitness (lista, p);
            //System.out.println(solu);
           
            String newSol = newSolAleatoria(p,rnd);
            double fitne = fitness(lista, newSol);
            double mejor=fitne;
            String opt=newSol;
            for (int i=1; i < 1000000; i++)
            {
                    newSol = newSolAleatoria(p,rnd);
                    fitne = fitness(lista, newSol);
                    if(fitne<mejor){
                        mejor=fitne;
                        opt=newSol;
                    }
                    
                    
                   // System.out.println(newSol + " f=" + fitne);
                }
             System.out.println("--------------");
             System.out.println("aleatoria:"+opt +" fitness:"+ fitness(lista,opt));
             
             for(int j=0; j < 5; j++){
                 int i=0;
             while (i < 100)
            {
                i++;
             String ne = Vecina( opt,lista, rnd);
             double fitne1= fitness (lista,opt);
             double fitne2= fitness (lista,ne);
//             System.out.println("entro1:"+opt+" fitness:"+ fitne1);
//             System.out.println("entro2:"+opt+" fitness:"+ fitne2);
             if (fitne2<fitne1){
                 System.out.println("Cambio");
                 opt= ne;
                 i=0;
             }
             }
             }
             System.out.println("Mejor Vecino:"+opt+" fitness:"+ fitness(lista,opt));   
                
                              
//            int pr = getFactorial(p.length()); 
//            String[] ne = permutar(p,pr);
// 
//            
//            String mejorT = p;
//            double mejorF = fitness(lista, p);
//            
//            System.out.println("Permutaciones = " + ne.length);
//            
//            for(int i= 0; i< ne.length;i++)
//            {
//                   // System.out.print(ne[i] + " : ");
//                    double temp = fitness(lista, ne[i]);
//                 //   System.out.println(temp);
//                    if (temp < mejorF){
//                        mejorT = ne[i];
//                        mejorF = temp;
//                    }
//                    if (i % 100 == 0)
//                        System.out.print("." + i);
//                    
//            }
            //System.out.print( "======== ");
//            System.out.print(mejorT + " : " + mejorF);
         
	}
       public static  String Vecina(String opt ,Map<String, Ciudad> lista, Random rnd){// Genera una solucion 'vecina', es similar a la solucion original (Intercambia dos ciudades)
           
           double fitne1= fitness (lista,opt);
           int uno=rnd.nextInt(opt.length() - 1);
           int dos=rnd.nextInt(opt.length() - 1);
           
/* convertimos el String a un array de caracteres */
char[] tmp = opt.toCharArray();

/* cambiamos el carácter en la posición deseada */
char temp1 = tmp[uno];
char temp2 = tmp[dos];
tmp [uno] = temp2;
tmp [dos] = temp1;

/* reconvertimos a String */
 String opt2 = new String(tmp);
 System.out.println("Vecino:"+opt2+" fitness:"+ fitness(lista,opt2));
      
       return opt2; }
        
        public static String newSolAleatoria(String p, Random rnd){
            String temp = p;
            String newSol = "";
            while (temp.length()>1){
                int dado = rnd.nextInt(temp.length());
                char ch = temp.charAt(dado);
                newSol += ch;
                temp = temp.replaceAll(ch+"", "");         
            }
            return newSol + temp;    
        }
        
        public static int fitness(Map<String, Ciudad> lista, String p){
             int tour = 0;
           //  int[]  mat = {0,4,35,8,30,21,3,6,16,13,9,23,14,5,34,32,31,24,7,2,33,17,28,10,20,15,22,29,25,19,27,1,18,11,12,26};
                for (int i=0; i<p.length()-1; i++){
                    String inicio = p.charAt(i) + "";
                    String fin = p.charAt(i+1) + "";
                    tour += lista.get(inicio) .distanceTo(lista.get(fin));
                           
                }
                String inicio = p.charAt(0) + "";
                String fin = p.charAt(35) + "";
                
                tour += lista.get(inicio)
                            .distanceTo(lista.get(fin));
                return tour;
        }
        
        
	public static String[]  permutar(String cadena,int p)
	{
		String[] per=new String[p];
		int l = cadena.length();
		int d=p/l;
		String[] aux = permutacion(cadena);
		int pos =0;
 
		if(p==1||l==1)
		{
			per[0] = cadena;
			return per;
		}
 
		for(int i=0;i<aux.length;i++)
		{
			String[] auxiliar = permutar(aux[i].substring(1),getFactorial(l-1)); 
			for(int j=0;j<auxiliar.length;j++)
			{
				per[pos]=aux[i].charAt(0)+auxiliar[j];
				pos++;
			}			
		}
		return per;
 
	}
	public static String[] permutacion(String cadena)
	{
		int n = cadena.length();
		String temporal="";
		String[] vector = new String[n];
		vector[0]=cadena;
		for(int i=1;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(j==n-1)
						temporal = cadena.charAt(j)+temporal;
				else temporal += cadena.charAt(j);
			}
			cadena=temporal;
			vector[i]=temporal;
			temporal="";
		}
		return vector;
	}
	public static int getFactorial (int n)
	{
		int result;
		if(n==1||n==0)
			return 1;
 
		result = getFactorial(n-1)*n;
		return result;
	}
	public static void mostrar (String[] vector)
	{
		for(int i= 0; i< vector.length;i++)
		{
			System.out.println(vector[i]);
		}
	}
}
