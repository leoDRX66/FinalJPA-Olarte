package com.mycompany.restaurante.controlador;

import com.mycompany.restaurante.modelo.*;
import com.mycompany.restaurante.vista.VistaPedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorPedido implements ActionListener {
    
    private VistaPedido vista;
    private Controlador control; 

    public ControladorPedido(VistaPedido vista, Controlador control) {
        this.vista = vista;
        this.control = control;
        
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEntregar.addActionListener(this);
        
        cargarCombos();
        actualizarTabla();
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            guardarPedido();
        } else if (e.getSource() == vista.btnEntregar) {
            entregarPedido();
        }
    }

    private void guardarPedido() {
        try {
            Chef chef = (Chef) vista.cbChef.getSelectedItem();
            Mesero mesero = (Mesero) vista.cbMesero.getSelectedItem();
            Alimento plato = (Alimento) vista.cbPlato.getSelectedItem();
            Alimento bebida = (Alimento) vista.cbBebida.getSelectedItem();
            
            String txtMesa = vista.tfCliente.getText();


            if (chef == null || mesero == null || plato == null || txtMesa.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Faltan datos obligatorios (Chef, Mozo, Plato o Mesa).");
                return;
            }

            int numeroMesa = Integer.parseInt(txtMesa);

            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setChef(chef);
            nuevoPedido.setMesero(mesero);
            nuevoPedido.setPlato(plato);
            nuevoPedido.setBebida(bebida);
            nuevoPedido.setNumeroMesa(numeroMesa);

            double total = plato.getPrecio();
            if (bebida != null) {
                total += bebida.getPrecio();
            }
            nuevoPedido.setTotal(total);


            control.guardarPedido(nuevoPedido);

            JOptionPane.showMessageDialog(vista, "¡Pedido Registrado!");
            actualizarTabla();
            vista.tfCliente.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El número de mesa debe ser numérico.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void entregarPedido() {
        int fila = vista.tablaResumen.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un pedido para entregar (eliminar).");
            return;
        }
        
        int idPedido = (int) vista.tablaResumen.getValueAt(fila, 0);
        
        control.eliminarPedido(idPedido);
        
        JOptionPane.showMessageDialog(vista, "Pedido Entregado/Eliminado.");
        actualizarTabla();
    }

    private void cargarCombos() {
        try {
            vista.cbChef.removeAllItems();
            vista.cbMesero.removeAllItems();
            vista.cbPlato.removeAllItems();
            vista.cbBebida.removeAllItems();

            List<Persona> chefs = control.traerPersonal("CHEF");
            for (Persona p : chefs) vista.cbChef.addItem(p);

            List<Persona> mozos = control.traerPersonal("MESERO");
            for (Persona p : mozos) vista.cbMesero.addItem(p);

            List<Alimento> alimentos = control.traerAlimentos();
            for (Alimento a : alimentos) {
                if (a instanceof Bebida) {
                    vista.cbBebida.addItem(a);
                } else {
                    vista.cbPlato.addItem(a);
                }
            }
            
            vista.cbBebida.insertItemAt(null, 0);
            vista.cbBebida.setSelectedIndex(0);

        } catch (Exception e) {
            System.out.println("Error cargando combos: " + e.getMessage());
        }
    }
    
    private void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaResumen.getModel();
        modelo.setRowCount(0);
        
        List<Pedido> lista = control.traerPedidos();
        
        for (Pedido p : lista) {
            String nombreBebida = (p.getBebida() != null) ? p.getBebida().getNombre() : "Sin bebida";
            
            Object[] fila = {
                p.getId(),
                p.getPlato().getNombre(),
                nombreBebida,
                p.getMesero().getNombre(),
                p.getNumeroMesa(),
                p.getChef().getNombre(),
                p.getTotal()
            };
            modelo.addRow(fila);
        }
    }
}