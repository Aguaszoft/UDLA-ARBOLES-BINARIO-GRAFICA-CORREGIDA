import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class ArbolGUI {
    private JTable tbMatrizAdyacencia;
    private JTextArea textArea;
    private JTextField txtRaiz;
    private JButton btnAgregarNodo;
    private JButton btnDibujarArbol;
    private JButton btnRecorridoAnchura;
    private JButton btnRecorridoProfundidad;
    private JButton btnPreorden;
    private JButton btnInorden;
    private JLabel lblNodo;
    private JLabel lblRaiz;
    private JLabel lblHoja;
    private JComboBox cbIzqDer;
    private JPanel panelArbol;
    private JPanel panelGeneral;
    private JPanel panelDatos;
    private JLabel lblRecorridos;
    private JButton btnPostorden;
    private JButton btnMatrizAdyacencia;

    private Arbol arbol=new Arbol();
    private ArbolGrafico arbolGrafico=new ArbolGrafico(arbol);
    private DefaultTableModel modeloTabla=new DefaultTableModel();

    public ArbolGUI() {

        btnAgregarNodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String etiqueta = arbol.getEtiquetaNodoSiguiente();
                    Nodo nuevoNodo = new Nodo(0, 0, etiqueta);
                    String etiquetaPadre = txtRaiz.getText().trim();
                    boolean esIzquierda = Boolean.parseBoolean(cbIzqDer.getSelectedItem().toString());

                    Nodo nodoPadre= null;
                    for (Nodo nodo : arbol.getNodos()) {
                        if (nodo.etiqueta.equals(etiquetaPadre)) {
                            nodoPadre = nodo;
                            break;
                        }
                    }
                    arbol.anadirNodo(nuevoNodo, nodoPadre, esIzquierda);
                    imprimirArbol();
                    dibujarArbolEnPanel();


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar nodo: " + ex.getMessage());
                }
            }
        });


        btnRecorridoAnchura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.bfs();
                textArea.append("Recorrido en Anchura (BFS): " + resultado + "\n");
            }
        });
        btnRecorridoProfundidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.dfs();
                textArea.append("Recorrido en Profundidad (DFS): " + resultado + "\n");
            }
        });
        btnPreorden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.preorden();
                textArea.append("Preorden: " + resultado + "\n");
            }
        });
        btnInorden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.inorden();
                textArea.append("Inorden: " + resultado + "\n");
            }
        });
        btnPostorden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = arbol.postorden();
                textArea.append("Postorden: " + resultado + "\n");
            }
        });
        btnMatrizAdyacencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    mostrarMatrizAdyacencia();
            }
        });
        btnDibujarArbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dibujarArbolEnPanel();
            }
        });
    }
    private void dibujarArbolEnPanel() {
        // Obtén el Graphics del panel
        Graphics g = panelArbol.getGraphics();

        // Verifica si el panel tiene un tamaño válido
        if (g != null) {
            // Convierte el Graphics a Graphics2D para más opciones de dibujo
            Graphics2D g2d = (Graphics2D) g;

            // Borra el panel antes de volver a dibujar el árbol (opcional)
            // g2d.clearRect(0, 0, panelArbol.getWidth(), panelArbol.getHeight());

            // Obtén el tamaño del panel
            int panelWidth = panelArbol.getWidth();
            int panelHeight = panelArbol.getHeight();

            // Calcular la posición inicial (centrado horizontalmente y en la parte superior del panel)
            int x = panelWidth / 2;
            int y = 50; // Posición vertical inicial, puedes ajustar este valor según tus necesidades

            // Dimensiones para los nodos hijos
            int dimensionX = panelWidth / 4;
            int dimensionY = 50;

            // Dibujar el árbol centrado verticalmente
           arbolGrafico.dibujarArbol(g2d, arbol.getRaiz(), x, y, dimensionX, dimensionY);
        }
    }
    private void imprimirArbol() {
        textArea.setText("");
        textArea.append("Nodos:\n");
        for (Nodo nodo : arbol.getNodos()) {
            textArea.append(nodo.etiqueta + ": " + nodo.toString() + "\n");
        }
    }

    private void mostrarMatrizAdyacencia() {


       Object[][] matriz = arbol.getMatrizAdyacencia();
        String[] nombreColumnas = new String[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            nombreColumnas[i] = String.valueOf((char) ('A' + i));
        }

        modeloTabla.setDataVector(matriz , nombreColumnas);
       tbMatrizAdyacencia.setModel(modeloTabla);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ArbolGUI");
        frame.setContentPane(new ArbolGUI().panelGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
