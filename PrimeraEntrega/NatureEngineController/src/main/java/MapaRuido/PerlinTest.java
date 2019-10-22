package MapaRuido;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Surface extends JPanel implements ActionListener {

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(null);

        MapGenerator map = new MapGenerator();
        
        //Tamano del mapa
        map.mapHeight = 350;
		map.mapWidth = 350;
		
		//Escala de ruido - Lo deje entre 40 y 100 porque en las pruebas vi que eran los valores que mejor funcionaban
		map.noiseScale = (float) (40 + (Math.random()*60));
		//Numero de octavas - Lo deje entre 6 y 10 porque en las pruebas vi que eran los valores que mejor funcionaban
		map.octaves = 6+(int)(4*Math.random());
		
		System.out.println("octaves = " + map.octaves);
		
		float[][]  noiseMap = map.GenerateMap();
        
        Color color = null;
        
        for (int i = 0; i < map.mapWidth; i++) {
        	for (int j= 0; j < map.mapHeight; j++)
        	{
        		//System.out.print((noiseMap[i][j]) + " ");
        		color = new Color((int) ((noiseMap[i][j]) * 2048));
        		g2d.setColor(color);
        		g2d.drawLine(j, i, j, i);
        	}
    		System.out.println();
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

@SuppressWarnings("serial")
public class PerlinTest extends JFrame {

    public PerlinTest() {

        initUI();
    }

    private void initUI() {

        final Surface surface = new Surface();
        add(surface);

        setTitle("Noise");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                PerlinTest ex = new PerlinTest();
                ex.setVisible(true);
            }
        });
    }
}