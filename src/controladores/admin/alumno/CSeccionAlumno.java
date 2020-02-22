package controladores.admin.alumno;

import utilidades.UtilidadesBotones;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import vistas.admin.alumno.PanelEliminarAlumno;
import vistas.admin.alumno.PanelModificarAlumno;
import vistas.admin.alumno.PanelNuevoAlumno;
import vistas.admin.alumno.PanelSeccionAlumno;

/**
 *
 * @author JALEXISRDV
 */
public class CSeccionAlumno implements ActionListener {
    
    private final PanelSeccionAlumno panelSeccionAlumno;
    
    public CSeccionAlumno(PanelSeccionAlumno panelSeccionAlumno) {
        this.panelSeccionAlumno = panelSeccionAlumno;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] botones = panelSeccionAlumno.getPanelFuctionsButtons().getComponents();
        JComponent boton = (JComponent) e.getSource();
        switch(boton.getName()) {
            case "buttonNewStudent":
                PanelNuevoAlumno panelNuevoAlumno = new PanelNuevoAlumno();
                addPanelFieldFuction(panelNuevoAlumno);
                break;
            case "buttonModifyStudent":
                PanelModificarAlumno panelModificarAlumno = new PanelModificarAlumno();
                addPanelFieldFuction(panelModificarAlumno);
                break;
            case "buttonDeleteStudent":
                PanelEliminarAlumno panelEliminarAlumno = new PanelEliminarAlumno();
                addPanelFieldFuction(panelEliminarAlumno);
                
        }
        UtilidadesBotones.seleccionBotones(boton, botones);
    }
    
    private void addPanelFieldFuction(JPanel panelSection) {
        JPanel panel = panelSeccionAlumno.getPanelFieldFuction();
        panel.removeAll();
        panel.add(panelSection);
        panel.revalidate();
        panel.repaint();
    }

}
