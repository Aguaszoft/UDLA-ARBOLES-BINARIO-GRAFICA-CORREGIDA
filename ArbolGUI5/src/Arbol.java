import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Arbol {
    private Nodo raiz;
    private ArrayList<Nodo> nodos;
    private int numNodos;

    public Arbol() {
        raiz = null;
        nodos = new ArrayList<>();
        numNodos = 0;
    }

    public void anadirNodo(Nodo nodo, Nodo padre, boolean esIzq) {
        if (padre == null) {
            if (raiz == null) {
                raiz= nodo;
            } else {
                throw new IllegalArgumentException("La raíz ya existe");
            }
        } else {
            if (esIzq) {
                if (padre.izquierda == null) {
                    padre.izquierda = nodo;
                } else {
                    throw new IllegalArgumentException("Hoja Izq ya existe");
                }
            } else {
                if (padre.derecha == null) {
                    padre.derecha = nodo;
                } else {
                    throw new IllegalArgumentException("Hoja Der ya existe");
                }
            }
        }
        nodos.add(nodo);
    }

    public ArrayList<Nodo> getNodos() {
            return nodos;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public String getEtiquetaNodoSiguiente() {
        return String.valueOf((char) ('A' + numNodos++));
    }

    public String bfs() {
        if (raiz== null) return "";

        StringBuilder resultado = new StringBuilder();
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);

        while (!queue.isEmpty()) {
            Nodo nodo = queue.poll();
            resultado.append(nodo.etiqueta).append(" ");
            if (nodo.izquierda != null) queue.add(nodo.izquierda );
            if (nodo.derecha != null) queue.add(nodo.derecha);
        }

        return resultado.toString().trim();
    }

    public String dfs() {
        if (raiz== null) return "";

        StringBuilder resultado = new StringBuilder();
        Stack<Nodo> stack = new Stack<>();
        stack.push(raiz);

        while (!stack.isEmpty()) {
            Nodo nodo = stack.pop();
            resultado.append(nodo.etiqueta).append(" ");
            if (nodo.derecha != null) stack.push(nodo.derecha );
            if (nodo.izquierda != null) stack.push(nodo.izquierda);
        }

        return resultado.toString().trim();
    }

    public String preorden() {
        return preordenImpresion(raiz).trim();
    }

    private String preordenImpresion(Nodo nodo) {
        if (nodo == null) return "";
        return nodo.etiqueta + " " + preordenImpresion(nodo.izquierda) + preordenImpresion(nodo.derecha);
    }

    public String inorden() {
        return inordenImpresion(raiz).trim();
    }

    private String inordenImpresion(Nodo nodo) {
        if (nodo == null) return "";
        return inordenImpresion(nodo.izquierda) + nodo.etiqueta + " " + inordenImpresion(nodo.derecha);
    }

    public String postorden() {
        return postordenImpresion(raiz).trim();
    }

    private String postordenImpresion(Nodo nodo) {
        if (nodo == null) return "";
        return postordenImpresion(nodo.izquierda) + postordenImpresion(nodo.derecha) + nodo.etiqueta + " ";
    }

    public Object[][] getMatrizAdyacencia() {
        int tam = nodos.size();
        Object[][] matriz = new Object[tam][tam];
        Map<String, Integer> etiquetaAIndice = new HashMap<>();

        for (int i = 0; i < tam; i++) {
            etiquetaAIndice.put(nodos.get(i).etiqueta, i);
            for (int j = 0; j < tam; j++) {
                matriz[i][j] = 0;
            }
        }

        for (Nodo nodo : nodos) {
            int desdeIndice = etiquetaAIndice.get(nodo.etiqueta);
            if (nodo.izquierda != null) {
                int hastaIndice = etiquetaAIndice.get(nodo.izquierda.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
            if (nodo.derecha!= null) {
                int hastaIndice = etiquetaAIndice.get(nodo.derecha.etiqueta);
                matriz[desdeIndice][hastaIndice] = 1;
            }
        }

        return matriz;
    }
}
