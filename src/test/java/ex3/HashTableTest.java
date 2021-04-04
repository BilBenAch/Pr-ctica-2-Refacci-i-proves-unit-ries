package ex3;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

//Compruebo que el hastable funcione bien
class HashTableTest {
    /************ TEST METODO PUT  ************/

//    Inserir un element que no col·lisiona dins una taula vuida.
    @Test
    public void put() {
        System.out.println("/************ TEST METODO PUT  ************/");
        HashTable hashTable = new HashTable();
        System.out.println("Inserir un element que no col·lisiona dins una taula vuida.");
        hashTable.put("22", 22);
        Assertions.assertEquals("\n bucket[0] = [22, 22]", hashTable.toString());
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un element que no col·lisiona dins una taula no vuida.");
        hashTable.put("01", "01");
        Assertions.assertEquals("\n bucket[0] = [22, 22]\n bucket[1] = [01, 01]", hashTable.toString());
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.");
        hashTable.put("33", "33");
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33]\n bucket[1] = [01, 01]", hashTable.toString());
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.");
        hashTable.put("44", 44.0);
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44.0]\n bucket[1] = [01, 01]", hashTable.toString());
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula vuida.");
        System.out.print(" antes update");
        hashTable.drop("22");
        hashTable.drop("33");
        hashTable.drop("44");
        hashTable.put("01", "01");
        System.out.println(hashTable.toString());
        System.out.println();
        hashTable.put("01", "77");
        System.out.print(" despues del update");
        Assertions.assertEquals("\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.");
        hashTable.put("00", "00");
        System.out.print(" antes update");
        System.out.println(hashTable.toString());
        hashTable.put("00","80");
        Assertions.assertEquals("\n bucket[0] = [00, 80]\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println();
        System.out.print(" despues update");
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no vuida.");
        System.out.print(" antes update");
        hashTable.put("22", "22");
        hashTable.put("33", "33");
        System.out.println(hashTable.toString());
        hashTable.put("22", "555");
        Assertions.assertEquals("\n bucket[0] = [00, 80] -> [22, 555] -> [33, 33]\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println();
        System.out.print(" despues update");
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no vuida.");
        System.out.print(" antes update");
        System.out.println(hashTable.toString());
        hashTable.put("33", "800");
        Assertions.assertEquals("\n bucket[0] = [00, 80] -> [22, 555] -> [33, 800]\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println();
        System.out.print(" despues update");
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();
    }

    /************ TEST METODO GET  ************/
//    Obtenir un element que no col·lisiona dins una taula vuida.
    @Test
    public void get() {
        System.out.println("/************ TEST METODO GET  ************/");
        HashTable hashTable = new HashTable();
        System.out.println("Obtenir un element que no col·lisiona dins una taula vuida.");
        hashTable.put("22", 22.0);
        Assertions.assertEquals(22.0, hashTable.get("22"));
        System.out.println(hashTable.toString());
        System.out.println(hashTable.get("22"));
        Assertions.assertEquals("\n bucket[0] = [22, 22.0]", hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Obtenir un element que col·lisiona dins una taula (1a posició dins el mateix bucket).");
        hashTable.put("33", "33");
        hashTable.put("44", 44);
        Assertions.assertEquals(22.0, hashTable.get("22"));
        System.out.println(hashTable.toString());
        System.out.println(hashTable.get("22"));
        Assertions.assertEquals("\n bucket[0] = [22, 22.0] -> [33, 33] -> [44, 44]", hashTable.toString());
        System.out.println();
        System.out.println();

        System.out.println("Obtenir un element que col·lisiona dins una taula (2a posició dins el mateix bucket).");
        hashTable.put("33", 33);
        hashTable.put("44", 44);
        Assertions.assertEquals(33, hashTable.get("33"));
        System.out.println(hashTable.toString());
        System.out.println(hashTable.get("33"));
        Assertions.assertEquals("\n bucket[0] = [22, 22.0] -> [33, 33] -> [44, 44]", hashTable.toString());
        System.out.println();
        System.out.println();

        System.out.println("Obtenir un element que col·lisiona dins una taula (3a posició dins el mateix bucket).");
        hashTable.put("33", "33");
        hashTable.put("44", "44");
        Assertions.assertEquals("44", hashTable.get("44"));
        System.out.println(hashTable.toString());
        System.out.println(hashTable.get("44"));
        Assertions.assertEquals("\n bucket[0] = [22, 22.0] -> [33, 33] -> [44, 44]", hashTable.toString());
        System.out.println();
        System.out.println();
        System.out.println("Obtenir un elements que no existeix perquè la seva posició està buida.");
        Assertions.assertNull(hashTable.get("0"));
        System.out.println(hashTable.get("0"));
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();
        System.out.println("Obtenir un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.");
        hashTable.put("1", "1");
        hashTable.drop("33");
        hashTable.drop("44");
        Assertions.assertNull(hashTable.get("87"));
        System.out.println(hashTable.get("87"));
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [22, 22.0]\n bucket[1] = [1, 1]", hashTable.toString());

        System.out.println();
        System.out.println();
        System.out.println(" Obtenir un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.");
        hashTable.put("33","33");
        hashTable.put("44","44");
        Assertions.assertNull(hashTable.get("55"));
        System.out.println(hashTable.get("55"));
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [22, 22.0] -> [33, 33] -> [44, 44]\n bucket[1] = [1, 1]", hashTable.toString());


    }

    /************ TEST METODO DROP  ************/

    //    Esborrar un element que no col·lisiona dins una taula.
    @Test
    public void drop() {
        System.out.println("/************ TEST METODO DROP ************/");
        HashTable hashTable = new HashTable();
        System.out.println("creamos tabla con 3 elementos en un bucket y otro bucket vacio");
        hashTable.put("22", "22");
        hashTable.put("33", "33");
        hashTable.put("44", "44");
        hashTable.put("12", "12");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]\n bucket[1] = [12, 12]", hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Esborrar un element que no col·lisiona dins una taula.");
        hashTable.drop("12");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]", hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).");
        hashTable.drop("22");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [33, 33] -> [44, 44]", hashTable.toString());
        System.out.println();
        System.out.println();


        //hago un put para que se aprecie que elimino el segundo elemento
        hashTable.put("22","22");
        System.out.println("Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).");
        hashTable.drop("44");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [33, 33] -> [22, 22]", hashTable.toString());
        System.out.println();
        System.out.println();


        //hago un put para que se aprecie que elimino el ultimo elemento
        hashTable.put("44","44");
        System.out.println("Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).");
        hashTable.drop("44");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [33, 33] -> [22, 22]", hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("eliminamos todo el bucket");
        hashTable.drop("22");
        hashTable.drop("33");
        hashTable.drop("44");
        Assertions.assertEquals("", hashTable.toString());
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Eliminar un elements que no existeix perquè la seva posició està buida");
        hashTable.drop("11");
        Assertions.assertEquals("", hashTable.toString());
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.");
        hashTable.put("22", 22);
        hashTable.put("12", 12);
        hashTable.drop("69");
        Assertions.assertEquals("\n bucket[0] = [22, 22]\n bucket[1] = [12, 12]", hashTable.toString());
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        System.out.println("Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.");
        //put 2 colisiones en el bucket 0
        hashTable.put("33", "33");
        hashTable.put("44", 44);
        hashTable.drop("55");
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]\n bucket[1] = [12, 12]", hashTable.toString());
        System.out.println(hashTable.toString());
        System.out.println();
        System.out.println();


        //pruba personal para eliminar primer y tercer elemento colision
        System.out.println("prueba personal para eliminar primer y tercer elemento colision");
        hashTable.drop("22");
        hashTable.drop("44");
        Assertions.assertEquals("\n bucket[0] = [33, 33]\n bucket[1] = [12, 12]", hashTable.toString());
        System.out.println(hashTable.toString());

    }

    /************ TEST METODO COUNT  ************/

    /************ TEST METODO COUNT PUT  ************/
    //   count  Inserir un element que no col·lisiona dins una taula vuida.
    @Test
    public void count_put() {
        System.out.println("/************ TEST METODO COUNT PUT  ************/");
        HashTable hashTable = new HashTable();
        System.out.println("Inserir un element que no col·lisiona dins una taula vuida.");
        hashTable.put("22", 22);
        Assertions.assertEquals("\n bucket[0] = [22, 22]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(1,hashTable.count());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un element que no col·lisiona dins una taula no vuida.");
        hashTable.put("01", "01");
        Assertions.assertEquals("\n bucket[0] = [22, 22]\n bucket[1] = [01, 01]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(2,hashTable.count());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.");
        hashTable.put("33", "33");
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33]\n bucket[1] = [01, 01]", hashTable.toString());
        Assertions.assertEquals(3,hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.");
        hashTable.put("44", "44");
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]\n bucket[1] = [01, 01]", hashTable.toString());
        Assertions.assertEquals(4,hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula vuida.");
        System.out.print(" antes update");
        hashTable.drop("22");
        hashTable.drop("33");
        hashTable.drop("44");
        hashTable.put("01", "01");
        System.out.println(hashTable.toString());
        System.out.println();
        hashTable.put("01", 77);
        System.out.print(" despues del update");
        Assertions.assertEquals("\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(1,hashTable.count());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.");
        hashTable.put("00", "00");
        System.out.print(" antes update");
        System.out.println(hashTable.toString());
        hashTable.put("00","80");
        Assertions.assertEquals("\n bucket[0] = [00, 80]\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println();
        System.out.print(" despues update");
        Assertions.assertEquals(2,hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no vuida.");
        System.out.print(" antes update");
        hashTable.put("22", "22");
        hashTable.put("33", "33");
        System.out.println(hashTable.toString());
        hashTable.put("22", "555");
        Assertions.assertEquals("\n bucket[0] = [00, 80] -> [22, 555] -> [33, 33]\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println();
        System.out.print(" despues update");
        Assertions.assertEquals(4,hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no vuida.");
        System.out.print(" antes update");
        System.out.println(hashTable.toString());
        hashTable.put("33", "800");
        Assertions.assertEquals("\n bucket[0] = [00, 80] -> [22, 555] -> [33, 800]\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println();
        System.out.print(" despues update");
        Assertions.assertEquals(4,hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();
    }

    /************ TEST METODO COUNT DROP  ************/

    //   count_ Esborrar un element que no col·lisiona dins una taula.
    @Test
    public void count_drop() {
        System.out.println("************ TEST METODO COUNT DROP  ************");
        HashTable hashTable = new HashTable();
        System.out.println("creamos tabla con 3 elementos en un bucket y otro bucket vacio");
        hashTable.put("22", "22");
        hashTable.put("33", 33);
        hashTable.put("44", "44");
        hashTable.put("12", 12);
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]\n bucket[1] = [12, 12]", hashTable.toString());
        Assertions.assertEquals(4, hashTable.count());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Esborrar un element que no col·lisiona dins una taula.");
        hashTable.drop("12");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]", hashTable.toString());
        Assertions.assertEquals(3, hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).");
        hashTable.drop("22");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [33, 33] -> [44, 44]", hashTable.toString());
        Assertions.assertEquals(2, hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        //hago un put para que se aprecie que elimino el segundo elemento
        hashTable.put("22","22");
        System.out.println("Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).");
        hashTable.drop("44");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [33, 33] -> [22, 22]", hashTable.toString());
        Assertions.assertEquals(2, hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        //hago un put para que se aprecie que elimino el ultimo elemento
        hashTable.put("44","44");
        System.out.println("Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).");
        hashTable.drop("44");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [33, 33] -> [22, 22]", hashTable.toString());
        Assertions.assertEquals(2, hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("eliminamos todo el bucket");
        hashTable.drop("22");
        hashTable.drop("33");
        hashTable.drop("44");
        Assertions.assertEquals("", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(0, hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Eliminar un elements que no existeix perquè la seva posició està buida");
        hashTable.drop("11");
        Assertions.assertEquals("", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(0, hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.");
        hashTable.put("22", "22");
        hashTable.put("12", "12");
        hashTable.drop("69");
        Assertions.assertEquals("\n bucket[0] = [22, 22]\n bucket[1] = [12, 12]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(2, hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        System.out.println("Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.");
        //put 2 colisiones en el bucket 0
        hashTable.put("33", 33);
        hashTable.put("44", 44);
        hashTable.drop("55");
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]\n bucket[1] = [12, 12]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(4, hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println();
        System.out.println();


        //pruba personal para eliminar primer y tercer elemento colision
        System.out.println("prueba personal para eliminar primer y tercer elemento colision");
        hashTable.drop("22");
        hashTable.drop("44");
        Assertions.assertEquals("\n bucket[0] = [33, 33]\n bucket[1] = [12, 12]", hashTable.toString());
        Assertions.assertEquals(2, hashTable.count());
        System.out.println(hashTable.toString());
        System.out.println("count ---> "+hashTable.count());
        System.out.println(hashTable.toString());

    }

//    aqui
    /************ TEST METODO SIZE PUT ************/

    /************ TEST METODO SIZE PUT  ************/
    //   count  Inserir un element que no col·lisiona dins una taula vuida.
    @Test
    public void size_put() {
        System.out.println("/************ TEST METODO COUNT PUT  ************/");
        HashTable hashTable = new HashTable();
        System.out.println("Inserir un element que no col·lisiona dins una taula vuida.");
        hashTable.put("22", "22");
        Assertions.assertEquals("\n bucket[0] = [22, 22]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(16,hashTable.size());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un element que no col·lisiona dins una taula no vuida.");
        hashTable.put("01", "01");
        Assertions.assertEquals("\n bucket[0] = [22, 22]\n bucket[1] = [01, 01]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(16,hashTable.size());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 2a posició dins el mateix bucket.");
        hashTable.put("33", 33);
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33]\n bucket[1] = [01, 01]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(16,hashTable.size());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un element que col·lisiona dins una taula no vuida, que es col·locarà en 3a posició dins el mateix bucket.");
        hashTable.put("44", "44");
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]\n bucket[1] = [01, 01]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(16,hashTable.size());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula vuida.");
        System.out.print(" antes update");
        hashTable.drop("22");
        hashTable.drop("33");
        hashTable.drop("44");
        hashTable.put("01", "01");
        System.out.println(hashTable.toString());
        System.out.println();
        hashTable.put("01", "77");
        System.out.print(" despues del update");
        Assertions.assertEquals("\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(16,hashTable.size());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que no col·lisiona dins una taula no vuida.");
        hashTable.put("00", "00");
        System.out.print(" antes update");
        System.out.println(hashTable.toString());
        hashTable.put("00","80");
        Assertions.assertEquals("\n bucket[0] = [00, 80]\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println();
        System.out.print(" despues update");
        Assertions.assertEquals(16,hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (2a posició) dins una taula no vuida.");
        System.out.print(" antes update");
        hashTable.put("22", "22");
        hashTable.put("33", 33);
        System.out.println(hashTable.toString());
        hashTable.put("22", "555");
        Assertions.assertEquals("\n bucket[0] = [00, 80] -> [22, 555] -> [33, 33]\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println();
        System.out.print(" despues update");
        Assertions.assertEquals(16,hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Inserir un elements que ja existeix (update) sobre un element que si col·lisiona (3a posició) dins una taula no vuida.");
        System.out.print(" antes update");
        System.out.println(hashTable.toString());
        hashTable.put("33", "800");
        Assertions.assertEquals("\n bucket[0] = [00, 80] -> [22, 555] -> [33, 800]\n bucket[1] = [01, 77]", hashTable.toString());
        System.out.println();
        System.out.print(" despues update");
        Assertions.assertEquals(16,hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();
    }

    /************ TEST METODO SIZE DROP  ************/

    //   count_ Esborrar un element que no col·lisiona dins una taula.
    @Test
    public void size_drop() {
        System.out.println("************ TEST METODO SIZE DROP  ************");
        HashTable hashTable = new HashTable();
        System.out.println("creamos tabla con 3 elementos en un bucket y otro bucket vacio");
        hashTable.put("22", "22");
        hashTable.put("33", "33");
        hashTable.put("44", "44");
        hashTable.put("12", "12");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]\n bucket[1] = [12, 12]", hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Esborrar un element que no col·lisiona dins una taula.");
        hashTable.drop("12");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]", hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Esborrar un element que si col·lisiona dins una taula (1a posició dins el mateix bucket).");
        hashTable.drop("22");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [33, 33] -> [44, 44]", hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        //hago un put para que se aprecie que elimino el segundo elemento
        hashTable.put("22","22");
        System.out.println("Esborrar un element que si col·lisiona dins una taula (2a posició dins el mateix bucket).");
        hashTable.drop("44");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [33, 33] -> [22, 22]", hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        //hago un put para que se aprecie que elimino el ultimo elemento
        hashTable.put("44","44");
        System.out.println("Esborrar un element que si col·lisiona dins una taula (3a posició dins el mateix bucket).");
        hashTable.drop("44");
        System.out.println(hashTable.toString());
        Assertions.assertEquals("\n bucket[0] = [33, 33] -> [22, 22]", hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("eliminamos todo el bucket");
        hashTable.drop("22");
        hashTable.drop("33");
        hashTable.drop("44");
        Assertions.assertEquals("", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Eliminar un elements que no existeix perquè la seva posició està buida");
        hashTable.drop("11");
        Assertions.assertEquals("", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.");
        hashTable.put("22", "22");
        hashTable.put("12", "12");
        hashTable.drop("69");
        Assertions.assertEquals("\n bucket[0] = [22, 22]\n bucket[1] = [12, 12]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        System.out.println("Eliminar un elements que no existeix, tot i que la seva posició està ocupada per 3 elements col·lisionats.");
        //put 2 colisiones en el bucket 0
        hashTable.put("33", "33");
        hashTable.put("44", "44");
        hashTable.drop("55");
        Assertions.assertEquals("\n bucket[0] = [22, 22] -> [33, 33] -> [44, 44]\n bucket[1] = [12, 12]", hashTable.toString());
        System.out.println(hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println();
        System.out.println();


        //pruba personal para eliminar primer y tercer elemento colision
        System.out.println("prueba personal para eliminar primer y tercer elemento colision");
        hashTable.drop("22");
        hashTable.drop("44");
        Assertions.assertEquals("\n bucket[0] = [33, 33]\n bucket[1] = [12, 12]", hashTable.toString());
        Assertions.assertEquals(16, hashTable.size());
        System.out.println(hashTable.toString());
        System.out.println("size ---> "+hashTable.size());
        System.out.println(hashTable.toString());

    }



    //test personal para comprobar las el metodo de getCollisionsForKey
    @Test
    void test_colisions() {
        HashTable hashTable = new HashTable();
        List<String> listaColisiones = hashTable.getCollisionsForKey("0", 10);
        for (String claves : listaColisiones) {
            hashTable.put(claves, claves);
        }
        List<String> listaColisiones2 = hashTable.getCollisionsForKey("3", 10);
        for (String claves : listaColisiones2) {
            hashTable.put(claves, claves);
        }
        Assertions.assertEquals(20, hashTable.count());
        System.out.println(hashTable.toString());
    }
}
