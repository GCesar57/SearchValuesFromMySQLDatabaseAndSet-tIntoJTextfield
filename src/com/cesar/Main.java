/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesar;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

/**
 *
 * @author phoenix055
 */
public class Main extends JFrame implements ActionListener{
    JLabel JL_fName, JL_Genero, JL_Interes, JL_Edad;
    JTextField JT_fName, JT_Genero, JT_Interes, JT_Edad;
    JButton btn_Search;

    public Main() {
        super("Search");
        JL_fName = new JLabel("Enter Name: ");
        JL_fName.setBounds(20, 20, 200, 20);
        JT_fName = new JTextField(20);
        JT_fName.setBounds(130, 20, 150, 20);
        
        btn_Search = new JButton("Search");
        btn_Search.setBounds(340, 20, 80, 20);
        btn_Search.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 200);
        //
        JL_fName = new JLabel("Nombre: ");
        JL_fName.setBounds(20, 50, 100, 20);
        JT_fName = new JTextField(20);
        JT_fName.setBounds(130, 50, 150, 20);
        JL_Genero = new JLabel("Genero: ");
        JL_Genero.setBounds(20, 80, 100, 20);
        JT_Genero = new JTextField(20);
        JT_Genero.setBounds(130, 80, 150, 20);
        JL_Edad = new JLabel("Edad: ");
        JL_Edad.setBounds(20, 110, 100, 20);
        JT_Edad = new JTextField(20);
        JT_Edad.setBounds(130, 110, 150, 20);
        //
        setLayout(null);
        add(btn_Search);
        add(JL_fName);
        add(JT_fName);
        add(JL_Genero);
        add(JT_Genero);
        add(JL_Edad);
        add(JT_Edad);
    }
    
    //
        @Override
    public void actionPerformed(ActionEvent e) {
        Function f = new Function();
        ResultSet rs = null;
        String fn = "Nombre";
        String ln = "genero";
        String ag = "edad";
        //
        rs = f.find(JT_fName.getText());
        try{
            if(rs.next()){
                JT_fName.setText(rs.getString("nombre"));
                JT_Genero.setText(rs.getString("genero"));
                JT_Edad.setText(rs.getString("edad"));
            }else{
                JOptionPane.showMessageDialog(null, "No data found...");
            }//
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }//
    }
    //
    public class Function{
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement ps = null;
       //
       public ResultSet find(String s){
           try {
               conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baseexamen", "root", "");
               ps = conn.prepareStatement("SELECT * FROM empleado WHERE nombre = ?");
               ps.setString(1, s);
               rs = ps.executeQuery();
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
           }
           return rs;
       }//
    }//FUNCTION
    public static void main(String[] args) {
        //
        new Main();
    }
}
