/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajero;
import java.io.*;
/**
 *
 * @author franklinfuentes
 */
public class gestiontxt {
    //bien compas ahora explicare la funcion REcalco que una vez sepan las instrucciones lo borren del archivo para evitar problemas
    //primeramente esta clase no necesita ser instansiada solo es de ponerla al apar de sus demas clases java y se llamara
    //con su nombre gestiontxt."cualquier funcion existente aqui" 
    //primeramente explicare los parametros que se piden en cada una
    //ruta : hace referencia a la direccion en su pc o disco virtual donde se encuentra o quieren crear el archivo txt es preferible que vaya completa por si acaso
    //text : representa el texto que desean agregar al archivo txt
    //identificador: este es para modificar este se refiere a lo que quieren mantener de la linea que modificaran por ejemplo:
    //tienen en su txt la linea "Cliente juan codigo 1 saldo $20" si aqui quieren solo cambiar el saldo de juan codigo 1 
    //identificador seria "Cliente juan codigo 1 saldo" ya que esto se mantiene es para que el algoritmo busque esta frase
    //reemplazo: esto es lo que se queria modificar en el anterior por ejemplo en el anterior se queria cambiar el saldo de juan codigo 1
    // reemplazo seria el nuevo valor que tendra digamos "$10" de esta manera se cambiaria el saldo del identificador por este reemplazo puede tener mas usos..
  
    static BufferedWriter bfwr;//este es un bufer de escritura es usado para que con solo un llamado al archivo pueda hacerse varias escrituras
    static BufferedReader bfrd;//es un bufer de lectura es usado para que con un solo llamado al archivo se pueda leer varias lineas
    //************************************************************************************************************************************************************************************************************************************
    //******************Funcion para agregar una linea al final de un archivo*************************************************************************************************************************************************************
    public static void AgregarArchivofr(String ruta,String texto) throws FileNotFoundException, IOException{
        File archivotxt = new File(ruta);//esto inicializar el archivo especificado en la ruta 
        
        if(archivotxt.exists()) {//verifica si ya esta creado el archivo
            String lineas;String anterior;//se inicializan el que guardara cada linea y el que guardara todo el contenido previo
            bfrd = new BufferedReader(new FileReader(archivotxt));//se inicializa un bufer de lectura del archivo que especificamos en la ruta
           lineas = bfrd.readLine();//se iguala lineas al primer dato en el buffer de lectura
           anterior = lineas+"\n";//anterior almacena este primer dato mas un salto de linea ya que el bufer llega hasta el salto de linea
            while((lineas = bfrd.readLine())!=null) {//se iran iterando cada una de las lineas hasta que haya una en blanco
                anterior += lineas +"\n";//a anterior se iran agregando cada linea separada por saltos de linea
            }
            bfrd.close();//se libera el buffer de lectura dando chanse a iniciar otro bufer
            bfwr = new BufferedWriter(new FileWriter(archivotxt));//se inicia el bufer de escritura del mismo archivo
            bfwr.write(anterior);//se borra todo y se escribe los datos que ya tenian
            bfwr.write(texto+"\n");//se agrega el dato que queriamos
        }else{//en el caso que el archivo no exista se crea
            bfwr = new BufferedWriter(new FileWriter(archivotxt));//se inicia el bufer de escritura
            bfwr.write(texto+"\n");           //se agrega de un solo la linea que queriamos mas un salto de linea
        }
        bfwr.close();//se libera el bufer de escritura
        
    }
    //***************************************************************************************************************************************************************************
    //****************************************FUNCION PARA BORRAR TODO UN ARCHIVO Y AGREGAR OTRA INFORMACION*******************************************
    public static void ReescribirArchivoan(String ruta,String texto) throws FileNotFoundException, IOException{
        File archivotxt = new File(ruta);//se inicializa un archivo en la ruta espeficicada
        bfwr = new BufferedWriter(new FileWriter(archivotxt));//se crea el bufer de escritura en ese archivo
        bfwr.write(texto+"\n");           //se agrega directamente la linea que queremos mas un salto de linea
        bfwr.close();//se libera el bufer de escritura
    }
    //***********************************************************************************************************************************************************
    //****************************FUNCION PARA LEER LO QUE CONTIENE UN ARCHIVO************************************************************
    public static String leerArchivokl(String ruta) throws FileNotFoundException, IOException{
        File archivotxt = new File(ruta);//se inicializa el archivo en la ruta
        String lineas;String total;//se cran variables para lineas y para total
            bfrd = new BufferedReader(new FileReader(archivotxt));
           lineas = bfrd.readLine();
           total = lineas+"\n";//se agrega la primera linea
            while((lineas = bfrd.readLine())!=null) {
                total += lineas +"\n";//se agregan las demas
            }
            bfrd.close();
            return total;//se retorna un string de todas las lineas del documento para que puedan imprimirse o mostrarse
    }
    //*************************************************************************************************************************************************************************
    //********************************************FUNCION PARA MODIFICAR UN DATO DE UN TXT*********************************************************
    public static void modificarlineaArchivoin(String ruta,String identificador,String reemplazo) throws FileNotFoundException, IOException{
        File archivotxt = new File(ruta);//basicamente es igual a lo de agregar a la fila exepto que hay 2 variables que ya mencionamos al incio
        if(archivotxt.exists()) {
            String lineas;String anterior;
            bfrd = new BufferedReader(new FileReader(archivotxt));
           lineas = bfrd.readLine();
           if(lineas.contains(identificador)){//aqui compara si en la primera linea esta el identicador del dato que queremos modificar
               anterior = identificador+" "+reemplazo+"\n";//si es asi el dato total sera el identificador mas un espacio mas el dato que queremos reemplazar seguido de un salto de linea
           }else{
               anterior = lineas+"\n";//si no esta en la primera linea el identificador del dato a modificar se guarda este dato sin modificaciones
           }
            while((lineas = bfrd.readLine())!=null) {
                
                if(lineas.contains(identificador)){//se busca el dato a modificar en las demas lineas del documento
                    anterior += identificador+" "+reemplazo+"\n";//si se encuentra se agrega al total pero con la modificacion realizada
                }else{
                   anterior += lineas +"\n";//si no es asi se agrega al total tal y como estaba orignalmente
                }
            }
            bfrd.close();
            bfwr = new BufferedWriter(new FileWriter(archivotxt));
            bfwr.write(anterior);
            bfwr.close();
        }else{
            System.out.println("el archivo debe existir almenos..");//si el archivo no existe no podes modificar ninguna linea
            
        }
    }
    //fin :v si quieren alguna otra funcion avisan :v
    
    
}
