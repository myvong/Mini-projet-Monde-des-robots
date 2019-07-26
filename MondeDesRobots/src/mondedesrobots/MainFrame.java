package mondedesrobots;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author My
 */
public class MainFrame extends javax.swing.JFrame {

    private Robot robot;
    private Monde m;
 
    private void uploadMondeToRobot() {
        if (m == null)
            return;
        for (int i = 0; i < this.m.nbL; i++)
        {
            for (int j = 0; j < this.m.nbC; j++)
            {
                robot.m.Mat[i][j] = this.m.Mat[i][j];
            }
        }
    }
    
    private void saveMonde() {
        for (int i = 0; i < robot.m.nbL; i++)
        {
            for (int j = 0; j < robot.m.nbC; j++)
            {
                this.m.Mat[i][j] = robot.m.Mat[i][j];
            }
        }
    }
    public void setTableValue() {
        for (int i = 0; i < robot.m.nbL; i++)
        {
            for (int j = 0; j < robot.m.nbC; j++)
            {
                if (robot.m.Mat[i][j] == true) //avec papier gras
                {
                    this.tblContent.setValueAt(1, i, j);
                }
                else //case nettoye
                {
                    this.tblContent.setValueAt(0, i, j);
                }
            }
        }
        if (robot.m.Mat[robot.posx][robot.posy] == true)
            this.tblContent.setValueAt(3, robot.posx, robot.posy);
        else
            this.tblContent.setValueAt(2, robot.posx, robot.posy);
    }
    private void robotMove() {
        setTableValue();
        
        Thread runner;
        runner = new Thread() {
            public void run() {
                while (true) { 
                   try {
                        robot.parcourir();
                        setTableValue();
                        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
                        saveMonde();     // save state of current monde to monde, use for change robot type
                        sleep(500);
                    }
                    catch (Exception e) {
                        setTableValue(); // update state of last cell
                        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
                        saveMonde();     // save state of current monde to monde, use for change robot type
                        return;
                    }
                }
            }
        };
        
        // run
        runner.start();
    }
    
    class CustomCellRenderer extends JLabel implements TableCellRenderer {
        public CustomCellRenderer() {
            setOpaque(true);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
//            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (value == null || value.equals(0)) {
                this.setBackground(Color.yellow);
                this.setText("");
            }
            else if (value.equals(1)) {
                this.setBackground(Color.black);
                this.setText("");
            }
            else if (value.equals(2)) {
                this.setBackground(Color.yellow);
                this.setForeground(Color.red);
                this.setHorizontalAlignment(SwingConstants.CENTER);
                this.setVerticalAlignment(SwingConstants.CENTER);
                this.setText("R");
            }
            else {
                this.setBackground(Color.black);
                this.setForeground(Color.red);
                this.setHorizontalAlignment(SwingConstants.CENTER);
                this.setVerticalAlignment(SwingConstants.CENTER);
                this.setText("R");
            }
            return this;
        }
    };
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        this.setTitle("Monde des robots");
        // cell data:
        // 0: Faux (sans papier gras)
        // 1: vrai (papier gras)
        // 2: robot + Faux (robots dans le case papier gras)
        // 3: robot + vrai (robot dans le case nettoye)
        // valuer de cell = vrai/faux + robot 
        
        this.tblContent.setDefaultRenderer(Object.class, new CustomCellRenderer());
        this.tblContent.setRowHeight(38);
        this.tblContent.setEnabled(false);
        this.tblContent.setTableHeader(null);
        this.m = new Monde(10, 10);
        
        //disable buttons Nettoyers
        this.btnNettoyerIntelligent.setEnabled(false);
        this.bntNettoyerLibre.setEnabled(false);
        this.bntNettoyerQuitter.setEnabled(false);
        this.bntNettoyerDroit.setEnabled(false);
        
        //disable buttons Polluers
        this.bntPolluerDroit.setEnabled(false);
        this.bntPolluerLibre.setEnabled(false);
        this.bntPolluerQuitter.setEnabled(false);
        this.bntPolluerSauteur.setEnabled(false);
        
        //mettre en place le background des boutons nettoyer et polluer: up, right, down, left 
        btnArrowUpPolluer.setIcon(new ImageIcon(getClass().getResource("../images/up-chevron.png")));
        btnArrowRightPolluer.setIcon(new ImageIcon(getClass().getResource("../images/right-chevron.png")));
        btnArrowDownPolluer.setIcon(new ImageIcon(getClass().getResource("../images/down-chevron.png")));
        btnArrowLeftPolluer.setIcon(new ImageIcon(getClass().getResource("../images/left-chevron.png")));
        
        btnArrowUpNettoyer.setIcon(new ImageIcon(getClass().getResource("../images/up-chevron.png")));
        btnArrowRightNettoyer.setIcon(new ImageIcon(getClass().getResource("../images/right-chevron.png")));
        btnArrowDownNettoyer.setIcon(new ImageIcon(getClass().getResource("../images/down-chevron.png")));
        btnArrowLeftNettoyer.setIcon(new ImageIcon(getClass().getResource("../images/left-chevron.png")));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        panelLeft = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        bntPolluerDroit = new javax.swing.JButton();
        bntPolluerSauteur = new javax.swing.JButton();
        bntPolluerLibre = new javax.swing.JButton();
        bntPolluerQuitter = new javax.swing.JButton();
        btnArrowUpPolluer = new javax.swing.JButton();
        btnArrowLeftPolluer = new javax.swing.JButton();
        btnArrowRightPolluer = new javax.swing.JButton();
        btnArrowDownPolluer = new javax.swing.JButton();
        panelContent = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContent = new javax.swing.JTable();
        NomPapierGras = new javax.swing.JLabel();
        NomCaseNettoye = new javax.swing.JLabel();
        lblCaseNettoye = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnNettoyer = new javax.swing.JButton();
        bntPolluer = new javax.swing.JButton();
        bntReinstallerMonde = new javax.swing.JButton();
        btnQuitterHeader = new javax.swing.JButton();
        lblNombreGras = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        bntNettoyerDroit = new javax.swing.JButton();
        btnNettoyerIntelligent = new javax.swing.JButton();
        bntNettoyerLibre = new javax.swing.JButton();
        bntNettoyerQuitter = new javax.swing.JButton();
        btnArrowLeftNettoyer = new javax.swing.JButton();
        btnArrowRightNettoyer = new javax.swing.JButton();
        btnArrowDownNettoyer = new javax.swing.JButton();
        btnArrowUpNettoyer = new javax.swing.JButton();
        txtNomMonde = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        bntPolluerDroit.setBackground(new java.awt.Color(0, 0, 255));
        bntPolluerDroit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bntPolluerDroit.setForeground(new java.awt.Color(255, 255, 255));
        bntPolluerDroit.setText("POLLUER droit");
        bntPolluerDroit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPolluerDroitActionPerformed(evt);
            }
        });

        bntPolluerSauteur.setBackground(new java.awt.Color(0, 0, 255));
        bntPolluerSauteur.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bntPolluerSauteur.setForeground(new java.awt.Color(255, 255, 255));
        bntPolluerSauteur.setText("POLLUER Sauteurs");
        bntPolluerSauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPolluerSauteurActionPerformed(evt);
            }
        });

        bntPolluerLibre.setBackground(new java.awt.Color(0, 0, 255));
        bntPolluerLibre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bntPolluerLibre.setForeground(new java.awt.Color(255, 255, 255));
        bntPolluerLibre.setText("ROBOTS LIBRES");
        bntPolluerLibre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPolluerLibreActionPerformed(evt);
            }
        });

        bntPolluerQuitter.setBackground(new java.awt.Color(255, 0, 51));
        bntPolluerQuitter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bntPolluerQuitter.setForeground(new java.awt.Color(255, 255, 255));
        bntPolluerQuitter.setText("Quitter");
        bntPolluerQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPolluerQuitterActionPerformed(evt);
            }
        });

        btnArrowUpPolluer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrowUpPolluerActionPerformed(evt);
            }
        });

        btnArrowLeftPolluer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrowLeftPolluerActionPerformed(evt);
            }
        });

        btnArrowRightPolluer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrowRightPolluerActionPerformed(evt);
            }
        });

        btnArrowDownPolluer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrowDownPolluerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bntPolluerQuitter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bntPolluerLibre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bntPolluerDroit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bntPolluerSauteur, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnArrowDownPolluer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnArrowLeftPolluer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addComponent(btnArrowUpPolluer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnArrowRightPolluer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bntPolluerDroit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntPolluerSauteur, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(btnArrowUpPolluer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArrowRightPolluer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArrowLeftPolluer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowDownPolluer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(bntPolluerLibre, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntPolluerQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelContent.setBackground(new java.awt.Color(255, 255, 255));

        tblContent.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tblContent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Column 1", "Column 1", "Column 1", "Column 1", "Column 1", "Column 1", "Column 1", "Column 1", "Column 1", "Column 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblContent.setShowGrid(true);
        tblContent.setSurrendersFocusOnKeystroke(true);
        tblContent.setTableHeader(null);
        jScrollPane1.setViewportView(tblContent);
        if (tblContent.getColumnModel().getColumnCount() > 0) {
            tblContent.getColumnModel().getColumn(0).setResizable(false);
            tblContent.getColumnModel().getColumn(1).setResizable(false);
            tblContent.getColumnModel().getColumn(2).setResizable(false);
            tblContent.getColumnModel().getColumn(3).setResizable(false);
            tblContent.getColumnModel().getColumn(4).setResizable(false);
            tblContent.getColumnModel().getColumn(5).setResizable(false);
            tblContent.getColumnModel().getColumn(6).setResizable(false);
            tblContent.getColumnModel().getColumn(7).setResizable(false);
            tblContent.getColumnModel().getColumn(8).setResizable(false);
            tblContent.getColumnModel().getColumn(9).setResizable(false);
        }

        NomPapierGras.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NomPapierGras.setText("Papier Gras");

        NomCaseNettoye.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NomCaseNettoye.setText("Case nettoyé");

        lblCaseNettoye.setBackground(new java.awt.Color(255, 255, 0));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));

        jButton2.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NomPapierGras)
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NomCaseNettoye)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCaseNettoye, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCaseNettoye, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(NomPapierGras)
                    .addComponent(NomCaseNettoye, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnNettoyer.setBackground(new java.awt.Color(0, 0, 255));
        btnNettoyer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNettoyer.setForeground(new java.awt.Color(255, 255, 255));
        btnNettoyer.setText("Nettoyer");
        btnNettoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNettoyerActionPerformed(evt);
            }
        });

        bntPolluer.setBackground(new java.awt.Color(0, 0, 255));
        bntPolluer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bntPolluer.setForeground(new java.awt.Color(255, 255, 255));
        bntPolluer.setText("Polluer");
        bntPolluer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPolluerActionPerformed(evt);
            }
        });

        bntReinstallerMonde.setBackground(new java.awt.Color(0, 0, 255));
        bntReinstallerMonde.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bntReinstallerMonde.setForeground(new java.awt.Color(255, 255, 255));
        bntReinstallerMonde.setText("Réinstaller le monde");
        bntReinstallerMonde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntReinstallerMondeActionPerformed(evt);
            }
        });

        btnQuitterHeader.setBackground(new java.awt.Color(255, 0, 51));
        btnQuitterHeader.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnQuitterHeader.setForeground(new java.awt.Color(255, 255, 255));
        btnQuitterHeader.setText("Quitter");
        btnQuitterHeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitterHeaderActionPerformed(evt);
            }
        });

        lblNombreGras.setBackground(new java.awt.Color(204, 204, 255));
        lblNombreGras.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        lblNombreGras.setText("Nombre des papiers gras: 0");
        lblNombreGras.setToolTipText("");
        lblNombreGras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblNombreGras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblNombreGras.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnNettoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(bntPolluer, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(bntReinstallerMonde)
                .addGap(79, 79, 79)
                .addComponent(btnQuitterHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(lblNombreGras, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bntReinstallerMonde, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                        .addComponent(btnQuitterHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                    .addComponent(bntPolluer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNettoyer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombreGras, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        bntNettoyerDroit.setBackground(new java.awt.Color(0, 0, 255));
        bntNettoyerDroit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bntNettoyerDroit.setForeground(new java.awt.Color(255, 255, 255));
        bntNettoyerDroit.setText("Nettoyers standard");
        bntNettoyerDroit.setActionCommand("Nettoyers standard");
        bntNettoyerDroit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNettoyerDroitActionPerformed(evt);
            }
        });

        btnNettoyerIntelligent.setBackground(new java.awt.Color(0, 0, 255));
        btnNettoyerIntelligent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNettoyerIntelligent.setForeground(new java.awt.Color(255, 255, 255));
        btnNettoyerIntelligent.setToolTipText("");
        btnNettoyerIntelligent.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNettoyerIntelligent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNettoyerIntelligent.setLabel("Nettoyer intelligent (smart)");
        btnNettoyerIntelligent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNettoyerIntelligentActionPerformed(evt);
            }
        });

        bntNettoyerLibre.setBackground(new java.awt.Color(0, 0, 255));
        bntNettoyerLibre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bntNettoyerLibre.setForeground(new java.awt.Color(255, 255, 255));
        bntNettoyerLibre.setText("ROBOTS LIBRES");
        bntNettoyerLibre.setToolTipText("");
        bntNettoyerLibre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNettoyerLibreActionPerformed(evt);
            }
        });

        bntNettoyerQuitter.setBackground(new java.awt.Color(255, 0, 51));
        bntNettoyerQuitter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bntNettoyerQuitter.setForeground(new java.awt.Color(255, 255, 255));
        bntNettoyerQuitter.setText("Quitter");
        bntNettoyerQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntNettoyerQuitterActionPerformed(evt);
            }
        });

        btnArrowLeftNettoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrowLeftNettoyerActionPerformed(evt);
            }
        });

        btnArrowRightNettoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrowRightNettoyerActionPerformed(evt);
            }
        });

        btnArrowDownNettoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrowDownNettoyerActionPerformed(evt);
            }
        });

        btnArrowUpNettoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrowUpNettoyerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bntNettoyerLibre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNettoyerIntelligent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bntNettoyerDroit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bntNettoyerQuitter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnArrowLeftNettoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnArrowDownNettoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnArrowUpNettoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(btnArrowRightNettoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bntNettoyerDroit, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNettoyerIntelligent, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowUpNettoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnArrowRightNettoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArrowLeftNettoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArrowDownNettoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(bntNettoyerLibre, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntNettoyerQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtNomMonde.setEditable(false);
        txtNomMonde.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNomMonde.setText("Monde");
        txtNomMonde.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(227, 227, 227)
                                .addComponent(txtNomMonde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNomMonde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNettoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNettoyerActionPerformed
        //cliquer nettoyer button en-tete pour enable buttons nettoyers a droit
        this.bntPolluerDroit.setEnabled(false);
        this.bntPolluerLibre.setEnabled(false);
        this.bntPolluerQuitter.setEnabled(false);
        this.bntPolluerSauteur.setEnabled(false);
        this.btnNettoyerIntelligent.setEnabled(true);
        this.bntNettoyerLibre.setEnabled(true);
        this.bntNettoyerQuitter.setEnabled(true);
        this.bntNettoyerDroit.setEnabled(true);
    }//GEN-LAST:event_btnNettoyerActionPerformed

    private void bntPolluerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPolluerActionPerformed

        //cliquer polluer button en-tete pour enable buttons nettoyers a gauche
        this.btnNettoyerIntelligent.setEnabled(false);
        this.bntNettoyerLibre.setEnabled(false);
        this.bntNettoyerQuitter.setEnabled(false);
        this.bntNettoyerDroit.setEnabled(false);
        this.bntPolluerDroit.setEnabled(true);
        this.bntPolluerLibre.setEnabled(true);
        this.bntPolluerQuitter.setEnabled(true);
        this.bntPolluerSauteur.setEnabled(true);
        
    }//GEN-LAST:event_bntPolluerActionPerformed

    private void btnQuitterHeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitterHeaderActionPerformed
        // TODO add your handling code here:
        //quitter la programme
        this.dispose();
    }//GEN-LAST:event_btnQuitterHeaderActionPerformed

    private void bntPolluerQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPolluerQuitterActionPerformed
        // TODO add your handling code here:
        //disable tous les buttons polluers a gauche
        this.bntPolluerDroit.setEnabled(false);
        this.bntPolluerLibre.setEnabled(false);
        this.bntPolluerQuitter.setEnabled(false);
        this.bntPolluerSauteur.setEnabled(false);
        robot = null;
    }//GEN-LAST:event_bntPolluerQuitterActionPerformed

    private void bntNettoyerQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNettoyerQuitterActionPerformed
        // TODO add your handling code here:
        //disable tous les buttons nettoyers a droit
        this.btnNettoyerIntelligent.setEnabled(false);
        this.bntNettoyerLibre.setEnabled(false);
        this.bntNettoyerQuitter.setEnabled(false);
        this.bntNettoyerDroit.setEnabled(false);
        robot = null;
    }//GEN-LAST:event_bntNettoyerQuitterActionPerformed

    private void bntPolluerDroitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPolluerDroitActionPerformed
        // TODO add your handling code here:
        robot = new RobotPolluerToutDroit();
        this.uploadMondeToRobot(); 
        this.robotMove();
    }//GEN-LAST:event_bntPolluerDroitActionPerformed

    private void bntPolluerSauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPolluerSauteurActionPerformed
        // TODO add your handling code here:
        robot = new RobotPolluerSauteur();
        this.uploadMondeToRobot();
        this.robotMove();
    }//GEN-LAST:event_bntPolluerSauteurActionPerformed

    private void bntPolluerLibreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPolluerLibreActionPerformed
        // TODO add your handling code here:
        robot = new RobotPolluerLibre();
        this.uploadMondeToRobot();
        robot.m.metPapierGras(robot.posx, robot.posy);
        setTableValue();
        saveMonde();
    }//GEN-LAST:event_bntPolluerLibreActionPerformed

    private void btnNettoyerIntelligentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNettoyerIntelligentActionPerformed
        // TODO add your handling code here:
        robot = new RobotNettoyer_intelligent();
        // load gras from monde to robot
        this.uploadMondeToRobot();    
        this.robotMove();
    }//GEN-LAST:event_btnNettoyerIntelligentActionPerformed

    private void bntNettoyerDroitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNettoyerDroitActionPerformed
        // TODO add your handling code here:
        robot = new RobotNettoyer_standard(0, 0);
        this.uploadMondeToRobot();
        this.robotMove();
    }//GEN-LAST:event_bntNettoyerDroitActionPerformed

    private void btnArrowLeftPolluerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrowLeftPolluerActionPerformed
        // TODO add your handling code here:
        if (robot == null || !(robot instanceof RobotPolluerLibre))
            return;
        if (robot.posx == 0)
            return;
        robot.seDeplace(robot.posx, robot.posy - 1);
        robot.m.metPapierGras(robot.posx, robot.posy);
        setTableValue();
        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
        saveMonde();     // save state of current monde to monde, use for change robot type
    }//GEN-LAST:event_btnArrowLeftPolluerActionPerformed

    private void bntReinstallerMondeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntReinstallerMondeActionPerformed
        // TODO add your handling code here:
        robot = null;
        this.btnNettoyerIntelligent.setEnabled(false);
        this.bntNettoyerLibre.setEnabled(false);
        this.bntNettoyerQuitter.setEnabled(false);
        this.bntNettoyerDroit.setEnabled(false);
        this.bntPolluerDroit.setEnabled(false);
        this.bntPolluerLibre.setEnabled(false);
        this.bntPolluerQuitter.setEnabled(false);
        this.bntPolluerSauteur.setEnabled(false);
        for (int i = 0; i < this.m.nbL; i++)
        {
            for (int j = 0; j < this.m.nbC; j++)
            {
                this.m.Mat[i][j] = false;
                this.tblContent.setValueAt(0, i, j);
            }
        }
        lblNombreGras.setText("Nombre des papiers gras: 0");
    }//GEN-LAST:event_bntReinstallerMondeActionPerformed

    private void btnArrowUpPolluerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrowUpPolluerActionPerformed
        // TODO add your handling code here:
        if (robot == null || !(robot instanceof RobotPolluerLibre))
            return;
        if (robot.posx == 0)
            return;
        robot.seDeplace(robot.posx - 1, robot.posy);
        robot.m.metPapierGras(robot.posx, robot.posy);
        setTableValue();
        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
        saveMonde();     // save state of current monde to monde, use for change robot type
    }//GEN-LAST:event_btnArrowUpPolluerActionPerformed

    private void btnArrowRightPolluerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrowRightPolluerActionPerformed
        // TODO add your handling code here:
        if (robot == null || !(robot instanceof RobotPolluerLibre))
            return;
        if (robot.posy == 9)
            return;
        robot.seDeplace(robot.posx, robot.posy + 1);
        robot.m.metPapierGras(robot.posx, robot.posy);
        setTableValue();
        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
        saveMonde();     // save state of current monde to monde, use for change robot type
    }//GEN-LAST:event_btnArrowRightPolluerActionPerformed

    private void btnArrowDownPolluerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrowDownPolluerActionPerformed
        // TODO add your handling code here:
        if (robot == null || !(robot instanceof RobotPolluerLibre))
            return;
        if (robot.posx == 9)
            return;
        robot.seDeplace(robot.posx + 1, robot.posy);
        robot.m.metPapierGras(robot.posx, robot.posy);
        setTableValue();
        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
        saveMonde();     // save state of current monde to monde, use for change robot type
    }//GEN-LAST:event_btnArrowDownPolluerActionPerformed

    private void btnArrowUpNettoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrowUpNettoyerActionPerformed
        // TODO add your handling code here:
        if (robot == null || !(robot instanceof RobotNettoyer_libre))
            return;
        if (robot.posx == 0)
            return;
        robot.seDeplace(robot.posx - 1, robot.posy);
        robot.m.prendPapierGras(robot.posx, robot.posy);
        setTableValue();
        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
        saveMonde();     // save state of current monde to monde, use for change robot type
    }//GEN-LAST:event_btnArrowUpNettoyerActionPerformed

    private void btnArrowRightNettoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrowRightNettoyerActionPerformed
        // TODO add your handling code here:
        if (robot == null || !(robot instanceof RobotNettoyer_libre))
            return;
        if (robot.posy == 9)
            return;
        robot.seDeplace(robot.posx, robot.posy + 1);
        robot.m.prendPapierGras(robot.posx, robot.posy);
        setTableValue();
        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
        saveMonde();     // save state of current monde to monde, use for change robot type
    }//GEN-LAST:event_btnArrowRightNettoyerActionPerformed

    private void btnArrowDownNettoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrowDownNettoyerActionPerformed
        // TODO add your handling code here:
        if (robot == null || !(robot instanceof RobotNettoyer_libre))
            return;
        if (robot.posx == 9)
            return;
        robot.seDeplace(robot.posx + 1, robot.posy);
        robot.m.prendPapierGras(robot.posx, robot.posy);
        setTableValue();
        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
        saveMonde();     // save state of current monde to monde, use for change robot type
    }//GEN-LAST:event_btnArrowDownNettoyerActionPerformed

    private void btnArrowLeftNettoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrowLeftNettoyerActionPerformed
        // TODO add your handling code here:
        if (robot == null || !(robot instanceof RobotNettoyer_libre))
            return;
        if (robot.posx == 0)
            return;
        robot.seDeplace(robot.posx, robot.posy - 1);
        robot.m.prendPapierGras(robot.posx, robot.posy);
        setTableValue();
        lblNombreGras.setText("Nombre des papiers gras: " + robot.m.compteGras());
        saveMonde();     // save state of current monde to monde, use for change robot type
    }//GEN-LAST:event_btnArrowLeftNettoyerActionPerformed

    
    private void bntNettoyerLibreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntNettoyerLibreActionPerformed
        // TODO add your handling code here:
        robot = new RobotNettoyer_libre();
        this.uploadMondeToRobot(); 
        robot.m.prendPapierGras(robot.posx, robot.posy);
        setTableValue();
    }//GEN-LAST:event_bntNettoyerLibreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NomCaseNettoye;
    private javax.swing.JLabel NomPapierGras;
    private javax.swing.JButton bntNettoyerDroit;
    private javax.swing.JButton bntNettoyerLibre;
    private javax.swing.JButton bntNettoyerQuitter;
    private javax.swing.JButton bntPolluer;
    private javax.swing.JButton bntPolluerDroit;
    private javax.swing.JButton bntPolluerLibre;
    private javax.swing.JButton bntPolluerQuitter;
    private javax.swing.JButton bntPolluerSauteur;
    private javax.swing.JButton bntReinstallerMonde;
    private javax.swing.JButton btnArrowDownNettoyer;
    private javax.swing.JButton btnArrowDownPolluer;
    private javax.swing.JButton btnArrowLeftNettoyer;
    private javax.swing.JButton btnArrowLeftPolluer;
    private javax.swing.JButton btnArrowRightNettoyer;
    private javax.swing.JButton btnArrowRightPolluer;
    private javax.swing.JButton btnArrowUpNettoyer;
    private javax.swing.JButton btnArrowUpPolluer;
    private javax.swing.JButton btnNettoyer;
    private javax.swing.JButton btnNettoyerIntelligent;
    private javax.swing.JButton btnQuitterHeader;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCaseNettoye;
    private javax.swing.JLabel lblNombreGras;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JTable tblContent;
    private javax.swing.JTextField txtNomMonde;
    // End of variables declaration//GEN-END:variables
}


//author: Vong Khai My 1752004