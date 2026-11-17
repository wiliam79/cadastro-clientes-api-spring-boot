package br.com.exemplo.cadastro.validation;
public final class DocumentoValidator {
private DocumentoValidator(){}
public static String normalizarEValidar(String valor){
if(valor==null)throw new IllegalArgumentException("CPF ou CNPJ obrigatorio");
String d=valor.replaceAll("[.\\/\\-\\s]","");
if(!d.matches("\\d{11}|\\d{14}"))throw new IllegalArgumentException("Documento deve conter 11 digitos (CPF) ou 14 (CNPJ)");
if(d.chars().allMatch(c->c==d.charAt(0)))throw new IllegalArgumentException("CPF/CNPJ invalido");
if(d.length()==11&&!cpfValido(d))throw new IllegalArgumentException("CPF invalido");
if(d.length()==14&&!cnpjValido(d))throw new IllegalArgumentException("CNPJ invalido");
return d;
}
private static int dig(String s,int i){return s.charAt(i)-'0';}
private static boolean cpfValido(String s){for(int tamanho=9;tamanho<=10;tamanho++){int soma=0;for(int i=0;i<tamanho;i++)soma+=dig(s,i)*(tamanho+1-i);int resto=(soma*10)%11;int dv=resto==10?0:resto;if(dv!=dig(s,tamanho))return false;}return true;}
private static boolean cnpjValido(String s){int[] w1={5,4,3,2,9,8,7,6,5,4,3,2};int[] w2={6,5,4,3,2,9,8,7,6,5,4,3,2};return calcula(s,w1)==dig(s,12)&&calcula(s,w2)==dig(s,13);}
private static int calcula(String s,int[] pesos){int soma=0;for(int i=0;i<pesos.length;i++)soma+=dig(s,i)*pesos[i];int resto=soma%11;return resto<2?0:11-resto;}
}
