package controladores.admin;

import utilidades.UtilidadesBotones;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import vistas.admin.PanelSeccionesAdmin;
import vistas.admin.alumno.PanelSeccionAlumno;
import vistas.alumno.PanelAlumno;

/**
 *
 * @author JALEXISRDV
 */
public class CSecciones implements ActionListener {
    
    private final PanelSeccionesAdmin panelSecciones;
    
    public CSecciones(PanelSeccionesAdmin panelSecciones) {
        this.panelSecciones = panelSecciones;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] botones = panelSecciones.getPanelButtonSection().getComponents();
        JComponent boton = (JComponent) e.getSource();
        switch(boton.getName()) {
            case "buttonStudent":
                System.out.println("Entro a evento");
                PanelSeccionAlumno panelSeccionAlumno = new PanelSeccionAlumno();
                addPanelFuctionsSection(panelSeccionAlumno);
                break;
            case "buttonTeacher":
                break;
            case "buttonExit":
                
        }
        UtilidadesBotones.seleccionBotones(boton, botones);
    }
    
    private void addPanelFuctionsSection(JPanel panelSection) {
        JPanel panel = panelSecciones.getPanelFuctionsSection();
        panel.removeAll();
        panel.add(panelSection);
        panel.revalidate();
        panel.repaint();
    }

}
