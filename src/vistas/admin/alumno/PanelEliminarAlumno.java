/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.admin.alumno;

import controladores.admin.alumno.CEliminarAlumno;
import modelos.admin.sql.SQLAlumno;

/**
 *
 * @author JALEXISRDV
 */
public class PanelEliminarAlumno extends javax.swing.JPanel {

    /**
     * Creates new form PanelModificarCliente
     */
    public PanelEliminarAlumno() {
        initComponents();
        addEventos(new CEliminarAlumno(new SQLAlumno(), this));
    }
    
    private void addEventos(CEliminarAlumno c) {
        this.buttonDeleteStudent.addActionListener(c);
        this.fieldSearchStudent.getDocument().addDocumentListener(c);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInfo = new javax.swing.JPanel();
        labelDeleteStudent = new javax.swing.JLabel();
        labelInfo = new javax.swing.JLabel();
        panelSearch = new javax.swing.JPanel();
        panelTable = new javax.swing.JScrollPane();
        tablaStudent = new javax.swing.JTable();
        fieldSearchStudent = new javax.swing.JTextField();
        panelButton = new javax.swing.JPanel();
        buttonDeleteStudent = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 10));
        setLayout(new java.awt.BorderLayout());

        panelInfo.setLayout(new java.awt.GridLayout(2, 0));

        labelDeleteStudent.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelDeleteStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDeleteStudent.setText("ELIMINAR ALUMNO");
        panelInfo.add(labelDeleteStudent);

        labelInfo.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        labelInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInfo.setText("Tecleé el número de control del alumno");
        panelInfo.add(labelInfo);

        add(panelInfo, java.awt.BorderLayout.PAGE_START);

        panelSearch.setLayout(new java.awt.BorderLayout());

        panelTable.setPreferredSize(new java.awt.Dimension(454, 200));

        tablaStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellidos", "Email", "Teléfono", "Id Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelTable.setViewportView(tablaStudent);

        panelSearch.add(panelTable, java.awt.BorderLayout.CENTER);
        panelSearch.add(fieldSearchStudent, java.awt.BorderLayout.PAGE_START);

        add(panelSearch, java.awt.BorderLayout.CENTER);

        panelButton.setLayout(new java.awt.BorderLayout(0, 10));

        buttonDeleteStudent.setText("Eliminar Alumno");
        buttonDeleteStudent.setPreferredSize(new java.awt.Dimension(97, 50));
        panelButton.add(buttonDeleteStudent, java.awt.BorderLayout.PAGE_END);

        add(panelButton, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDeleteStudent;
    private javax.swing.JTextField fieldSearchStudent;
    private javax.swing.JLabel labelDeleteStudent;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelSearch;
    private javax.swing.JScrollPane panelTable;
    private javax.swing.JTable tablaStudent;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the fieldSearchStudent
     */
    public javax.swing.JTextField getFieldSearchStudent() {
        return fieldSearchStudent;
    }

    /**
     * @return the tablaStudent
     */
    public javax.swing.JTable getTablaStudent() {
        return tablaStudent;
    }

}
