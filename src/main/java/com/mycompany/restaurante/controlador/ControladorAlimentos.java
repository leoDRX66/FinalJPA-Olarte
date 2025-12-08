package com.mycompany.restaurante.controlador;

import com.mycompany.restaurante.modelo.*;
import com.mycompany.restaurante.vista.VistaAlimentos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorAlimentos implements ActionListener {
    
    private VistaAlimentos vista;
    private Controlador control; 

    public ControladorAlimentos(VistaAlimentos vista, Controlador control) {
        this.vista = vista;
        this.control = control;
        
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        
        listarEnTabla();
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            guardarAlimento();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminarAlimento();
        }
    }

    private void guardarAlimento() {
        try {
            String nombre = vista.txtNombre.getText();
            String textoPrecio = vista.txtPrecio.getText();
            String tipo = (String) vista.cbTipo.getSelectedItem();

            if (nombre.isEmpty() || textoPrecio.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
                return;
            }

            double precio = Double.parseDouble(textoPrecio);

            Alimento nuevo;
            if (tipo.equals("Bebida")) {
                nuevo = new Bebida(nombre, precio);
            } else if (tipo.equals("Plato Fuerte")) {
                nuevo = new PlatoFuerte(nombre, precio);
            } else if (tipo.equals("Postre")) {
                nuevo = new Postres(nombre, precio);
            } else {
                nuevo = new Adicionales(nombre, precio);
            }

            control.guardarAlimento(nuevo);
            
            JOptionPane.showMessageDialog(vista, "¡Alimento Guardado!");
            listarEnTabla();
            limpiarFormulario();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + ex.getMessage());
        }
    }

    private void eliminarAlimento() {
        int fila = vista.tabla.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un alimento de la tabla para eliminar.");
            return;
        }
        
        int id = (int) vista.tabla.getValueAt(fila, 0);
        
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Deseas eliminar este alimento?", "Eliminar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            control.eliminarAlimento(id);
            listarEnTabla();
            JOptionPane.showMessageDialog(vista, "Alimento eliminado.");
        }
    }

    private void listarEnTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();
        modelo.setRowCount(0);
        
        List<Alimento> lista = control.traerAlimentos();
        
        for (Alimento a : lista) {
            modelo.addRow(new Object[]{
                a.getId(), 
                a.getNombre(), 
                a.getPrecio(), 
                a.getClass().getSimpleName()
            });
        }
    }
    
    private void limpiarFormulario() {
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.cbTipo.setSelectedIndex(0);
    }
}