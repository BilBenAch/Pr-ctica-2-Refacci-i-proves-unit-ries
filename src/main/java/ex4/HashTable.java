package ex4;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.


import java.util.ArrayList;

/**
 * Implementació d'una taula de hash sense col·lisions.
 * Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
 */
public class HashTable {
    private int SIZE = 16;
    private int ITEMS = 0;
    private HashEntry[] entries = new HashEntry[SIZE];

    //por cada elemento incrementamos el valor de los items en put o si borramos decrementamos en drop
    public int count() {
        return this.ITEMS;
    }


    public int size() {
        return this.SIZE;
    }

    /**
     * Permet afegir un nou element a la taula.
     *
     * @param key   La clau de l'element a afegir.
     * @param value El propi element que es vol afegir.
     */

    //    public void put(String key, String value) {
//        int hash = getHash(key);
//        final original.HashTable.HashEntry hashEntry = new original.HashTable.HashEntry(key, value);
//
//        if(entries[hash] == null) {
//            entries[hash] = hashEntry;
//        }
//        else {
//            original.HashTable.HashEntry temp = entries[hash];
//            while(temp.next != null)
//                temp = temp.next;
//
//            temp.next = hashEntry;
//            hashEntry.prev = temp;
//        }
//    }
    //put no incrementa items
    public void put(String key, Object value) {
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);
        if (entries[hash] == null) {
            //HashTable.HashEntry temp = entries[hash];
//            while (!temp.key.equals(key))
//                temp = temp.next;
            //metodo extraido
            entries[hash] = hashEntry;
            ++ITEMS;
        } else {
            HashEntry temp = entries[hash];
            if (temp.key.equals(key)) {
                temp.value = value;
                return;
            }
            //mientras temp no sea null voy aplicando el next y comprobando si son iguales, si lo son salgo del bucle y se lo asigno al newEntries con otro bracket para que no colisiones
            SIZE = SIZE * 2;
            HashEntry[] newEntries = new HashEntry[SIZE];
            copiaDades(entries, newEntries);
            entries = newEntries;
            //recursivamente le asigno la clave y el valor con la nueva size
            put(key, value);
        }
    }

    //metodo para asignar nuevo entry al key con colison con el nuevo size hecho previamente
    public void copiaDades(HashEntry[] oldEntries, HashEntry[] newEntries) {
        for (int i = 0; i < oldEntries.length; ++i) {
            HashEntry entry = oldEntries[i];
            while(entry != null) {
                int newHash = getHash(entry.key);
                newEntries[newHash] = entry;
                entry = entry.next;
            }
        }
    }

    /**
     * Permet recuperar un element dins la taula.
     *
     * @param key La clau de l'element a trobar.
     * @return El propi element que es busca (null si no s'ha trobat).
     */
//    public String get(String key) {
//        int hash = getHash(key);
//        if(entries[hash] != null) {
//            original.HashTable.HashEntry temp = entries[hash];
//
//            while( !temp.key.equals(key))
//                temp = temp.next;
//
//            return temp.value;
//        }
//
//        return null;
//    }
//

    //segon error, si la clau es de un valor major del esperat surt un nullpionterExeption y ho hem de corregir amb un try catch
    public Object get(String key) {
        int hash = getHash(key);
        try {
            if (entries[hash] != null) {
                HashEntry temp = find(key, entries[hash]);
                return temp.value;
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    /**
     * Permet esborrar un element dins de la taula.
     *
     * @param key La clau de l'element a trobar.
     */

    //    public void drop(String key) {
//        int hash = getHash(key);
//        if(entries[hash] != null) {
//
//            original.HashTable.HashEntry temp = entries[hash];
//            while( !temp.key.equals(key))
//                temp = temp.next;
//
//            if(temp.prev == null) entries[hash] = null;             //esborrar element únic (no col·lissió)
//            else{
//                if(temp.next != null) temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
//                temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
//            }
//        }
//    }
    public void drop(String key) {
        int hash = getHash(key);
        //mientras el bucket no esté vacío
        if (entries[hash] != null) {
            HashEntry temp = entries[hash];
            while (!temp.key.equals(key)) {
                //si el siguiente no es null temporal vale el elemento siguiente si no me saca del bucle
                if (temp.next != null) {
                    temp = temp.next;
                }
                else {
                    return;
                }
            }
            //antes si era el primer elemento te borraba el bucket entero

            if (temp.prev == null) {  //esborrar element únic (no col·lissió) hay que comprobar si tiene elementos despues para que no borre todo el bucket
                if (temp.next != null) { //si el siguiente tampoco es vacío, lo desenlazamos y el recolector lo borra
                    temp.next.prev = null;
                }
                temp = temp.next;
                entries[hash] = temp; //actualizamos nuestro hashentry con los elementos
            } else { //comprobamos casos en que no sea el primer elemento
                if (temp.next != null)
                    temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
                temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
            }
            ITEMS--; // desencrementamos items
        }
    }

    //extraemos este metodo que se repite tanto en put como drop y sirve para buscar el elemento con la key esperada
    private HashEntry find(String key, HashEntry entry) {
        HashEntry temp = entry;
        while (!temp.key.equals(key))
            temp = temp.next;
        return temp;
    }

    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % SIZE;
    }

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : entries) {
            if (entry == null) {
                bucket++;
                continue;
            }

            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while (temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

    /**
     * Permet calcular quants elements col·lisionen (produeixen la mateixa posició dins la taula de hash) per a la clau donada.
     *
     * @param key La clau que es farà servir per calcular col·lisions.
     * @return Una clau que, de fer-se servir, provoca col·lisió amb la que s'ha donat.
     */
    public String getCollisionsForKey(String key) {
        return getCollisionsForKey(key, 1).get(0);
    }

    /**
     * Permet calcular quants elements col·lisionen (produeixen la mateixa posició dins la taula de hash) per a la clau donada.
     *
     * @param key      La clau que es farà servir per calcular col·lisions.
     * @param quantity La quantitat de col·lisions a calcular.
     * @return Un llistat de claus que, de fer-se servir, provoquen col·lisió.
     */

    //te devuelve un metodo una clave con colision
    public ArrayList<String> getCollisionsForKey(String key, int quantity) {
        /*
          Main idea:
          alphabet = {0, 1, 2}

          Step 1: "000"
          Step 2: "001"
          Step 3: "002"
          Step 4: "010"
          Step 5: "011"
           ...
          Step N: "222"

          All those keys will be hashed and checking if collides with the given one.
        * */

        final char[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        ArrayList<Integer> newKey = new ArrayList();
        ArrayList<String> foundKeys = new ArrayList();

        newKey.add(0);
        int collision = getHash(key);
        int current = newKey.size() - 1;

        while (foundKeys.size() < quantity) {
            //building current key
            String currentKey = "";
            for (int i = 0; i < newKey.size(); i++)
                currentKey += alphabet[newKey.get(i)];

            if (!currentKey.equals(key) && getHash(currentKey) == collision)
                foundKeys.add(currentKey);

            //increasing the current alphabet key
            newKey.set(current, newKey.get(current) + 1);

            //overflow over the alphabet on current!
            if (newKey.get(current) == alphabet.length) {
                int previous = current;
                do {
                    //increasing the previous to current alphabet key
                    previous--;
                    if (previous >= 0) newKey.set(previous, newKey.get(previous) + 1);
                }
                while (previous >= 0 && newKey.get(previous) == alphabet.length);

                //cleaning
                for (int i = previous + 1; i < newKey.size(); i++)
                    newKey.set(i, 0);

                //increasing size on underflow over the key size
                if (previous < 0) newKey.add(0);

                current = newKey.size() - 1;
            }
        }

        return foundKeys;
    }

}
