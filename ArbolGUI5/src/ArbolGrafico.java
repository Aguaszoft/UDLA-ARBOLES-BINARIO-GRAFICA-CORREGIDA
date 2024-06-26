import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ArbolGrafico extends JPanel {
    private Arbol arbol;

    public ArbolGrafico (Arbol arbol) {
        this.arbol = arbol;
    }

    @Override

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtener el tamaño del panel
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Calcular la posición inicial (centrado en el panel)
        int x = panelWidth / 2;
        int y = 50; // Un valor fijo para la raíz del árbol

        // Dimensiones para los nodos hijos
        int dimensionX = panelWidth / 4;
        int dimensionY = 50;

        // Dibujar el árbol
        dibujarArbol(g2d, arbol.getRaiz(),x, y, dimensionX, dimensionY);
    }

    public void dibujarArbol(Graphics2D g2d, Nodo nodo, int x, int y, int dimensionX, int dimensionY) {
        if (nodo != null) {
            // Dibuja el nodo actual (puedes personalizar cómo se dibuja el nodo aquí)
            g2d.fillOval(x - 15, y - 15, 30, 30);
            g2d.drawString(nodo.etiqueta, x - 10, y + 5);

            // Asigna las coordenadas del nodo actual
            nodo.x = x;
            nodo.y = y;

            // Dibuja la línea y llama recursivamente para el nodo izquierdo
            if (nodo.izquierda != null) {
                g2d.drawLine(x, y, x - dimensionX, y + dimensionY);
                dibujarArbol(g2d, nodo.izquierda, x - dimensionX, y + dimensionY, dimensionX / 2, dimensionY);
            }

            // Dibuja la línea y llama recursivamente para el nodo derecho
            if (nodo.derecha != null) {
                g2d.drawLine(x, y, x + dimensionX, y + dimensionY);
                dibujarArbol(g2d, nodo.derecha, x + dimensionX, y + dimensionY, dimensionX / 2, dimensionY);
            }
        }
}
}
