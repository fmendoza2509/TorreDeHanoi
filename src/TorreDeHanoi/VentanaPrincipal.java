package TorreDeHanoi;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class VentanaPrincipal extends javax.swing.JFrame {
    int ContNumMov = 0;
    int Objetivo = 0;
    double NumNimMov = 0;
    boolean Stop = false;
    Pila PilaTorreA;
    Pila PilaTorreB;
    Pila PilaTorreC;
    DefaultTableModel TablaTorreA,TablaTorreB,TablaTorreC;
    public VentanaPrincipal() {
        initComponents();
        //casteamos las torres
        TablaTorreA = (DefaultTableModel)TorreA.getModel();
        TablaTorreA.setRowCount(10);
        TablaTorreB = (DefaultTableModel)TorreB.getModel();
        TablaTorreB.setRowCount(10);
        TablaTorreC = (DefaultTableModel)TorreC.getModel();
        TablaTorreC.setRowCount(10);
        //Rederizamos las celdas para que queden centradas
        DefaultTableCellRenderer renderA = new DefaultTableCellRenderer();
        renderA.setHorizontalAlignment(SwingConstants.CENTER);
        TorreA.getColumnModel().getColumn(0).setCellRenderer(renderA);
        DefaultTableCellRenderer renderB = new DefaultTableCellRenderer();
        renderB.setHorizontalAlignment(SwingConstants.CENTER);
        TorreB.getColumnModel().getColumn(0).setCellRenderer(renderB);
        DefaultTableCellRenderer renderC = new DefaultTableCellRenderer();
        renderC.setHorizontalAlignment(SwingConstants.CENTER);
        TorreC.getColumnModel().getColumn(0).setCellRenderer(renderC);
    }
    private void Limpiar(){
        cbNumDiscos.setSelectedItem(String.valueOf(Objetivo));
        ContNumMov = 0;
        NumNimMov = 0;
    }
    private void PresentarCantidadDeMOvimientos(){
        ContNumMov++;
        lblNumMov.setText(String.valueOf(ContNumMov));
    }
    //Método para Reiniciar el juego
    private void Reiniciar(){
        try {
            if(!lblMinMov.getText().equals("")){
                Limpiar();
                Iniciar();
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    //Método para presentar la torre a
    private void PresentarTorreA(){
        ((DefaultTableModel)TorreA.getModel()).setRowCount(0);
        TablaTorreA.setRowCount(10);
        Nodo k;
        int RowDisco = (10 - PilaTorreA.getContNodo());
        if(PilaTorreA.getContNodo()>0){
            for(k=PilaTorreA.getCabeza();k.getAbajo() != null; k = k.getAbajo()){
                String[] VectorNormal = {k.getDato()};
                TablaTorreA.insertRow(RowDisco, VectorNormal);
                RowDisco++;
            }
            if(k.getAbajo() == null){
                String[] VectorNormal = {k.getDato()};
                TablaTorreA.insertRow(RowDisco, VectorNormal);
            }
        }
        TorreA.setModel(TablaTorreA);
        TablaTorreA.setRowCount(10);
    }
    //Método para presentar la torre b
    private void PresentarTorreB(){
        ((DefaultTableModel)TorreB.getModel()).setRowCount(0);
        TablaTorreB.setRowCount(10);
        Nodo k;
        int RowDisco = (10 - PilaTorreB.getContNodo());
        if(PilaTorreB.getContNodo()>0){
            for(k=PilaTorreB.getCabeza();k.getAbajo() != null; k = k.getAbajo()){
                String[] VectorNormal = {k.getDato()};
                TablaTorreB.insertRow(RowDisco, VectorNormal);
                RowDisco++;
            }
            if(k.getAbajo() == null){
                String[] VectorNormal = {k.getDato()};
                TablaTorreB.insertRow(RowDisco, VectorNormal);
            }
        }
        TorreB.setModel(TablaTorreB);
        TablaTorreB.setRowCount(10);
    }
    //Método para presentar torre c
    private void PresentarTorreC(){
        ((DefaultTableModel)TorreC.getModel()).setRowCount(0);
        TablaTorreC.setRowCount(10);
        Nodo k;
        int RowDisco = (10 - PilaTorreC.getContNodo());
        if(PilaTorreC.getContNodo()>0){
            for(k=PilaTorreC.getCabeza();k.getAbajo() != null; k = k.getAbajo()){
                String[] VectorNormal = {k.getDato()};
                TablaTorreC.insertRow(RowDisco, VectorNormal);
                RowDisco++;
            }
            if(k.getAbajo() == null){
                String[] VectorNormal = {k.getDato()};
                TablaTorreC.insertRow(RowDisco, VectorNormal);
            }
        }
        TorreC.setModel(TablaTorreC);
        TablaTorreC.setRowCount(10);
    }
    //Método para Iniciar el juego
    private void Iniciar(){
        try {
            PilaTorreA = new Pila();
            PilaTorreB = new Pila();
            PilaTorreC = new Pila();
            Objetivo = Integer.parseInt(cbNumDiscos.getSelectedItem().toString());
            NumNimMov = Math.pow(2, Objetivo) - 1;
            lblNumMov.setText(String.valueOf(ContNumMov));
            lblMinMov.setText(String.format("%.0f", NumNimMov));//Con esto le decimos que nos mande los numeros enteros
            for (int x = Objetivo; x >= 1; x--) {
                Nodo Plataforma = new Nodo();
                String Disco = "";
                //Esto es para ir dibujando los discos con los #
                for (int y = x; y > 0; y--) {
                    Disco += "#";
                }
                Plataforma.setDato(Disco);
                PilaTorreA.Push(Plataforma);
            }
            PresentarTorreA();
            PresentarTorreB();
            PresentarTorreC();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    //Método para mover de A-b
    private void MoverAB(){
        try {
            if(PilaTorreA.getContNodo()>0){
                Nodo Plataforma =  new Nodo();
                Plataforma.setDato(PilaTorreA.Peek());
                //Verifico para ver si puedo hacer el movimiento a b
                if(PilaTorreB.getContNodo()>0){
                    if(Plataforma.getDato().compareTo(PilaTorreB.Peek())>0){
                        return;
                    }
                }
                PilaTorreA.Pop();//Quitamos de la torre a
                PilaTorreB.Push(Plataforma);//Colocamos lo que contiene plataforma
                PresentarTorreA();
                PresentarTorreB();
                PresentarCantidadDeMOvimientos();
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    //Método para mover de la torre a-c
    private void MoverAC(){
        try {
            if(PilaTorreA.getContNodo()>0){
                Nodo Plataforma =  new Nodo();
                Plataforma.setDato(PilaTorreA.Peek());
                //Verifico para ver si puedo hacer el movimiento a b
                if(PilaTorreC.getContNodo()>0){
                    if(Plataforma.getDato().compareTo(PilaTorreC.Peek())>0){
                        return;
                    }
                }
                PilaTorreA.Pop();//Quitamos de la torre a
                PilaTorreC.Push(Plataforma);//Colocamos lo que contiene plataforma
                PresentarTorreA();
                PresentarTorreC();
                PresentarCantidadDeMOvimientos();
                //Ahora evaluamos si tenemos que detener el juego
                if(PilaTorreC.getContNodo()==Objetivo && ContNumMov == NumNimMov){
                    JOptionPane.showMessageDialog(null, "Felicidades haz alcanzado el objetivo de movimientos minimos"
                    +"\n\nIntenta con otro nivel del juego","Felicitaciones",JOptionPane.WARNING_MESSAGE);
                }else if(PilaTorreC.getContNodo()==Objetivo && ContNumMov != NumNimMov){
                    JOptionPane.showMessageDialog(null, "Felicidades haz alcanzado el objetivo del juego"
                    +"\n\nIntenta de nuevo y trata de conseguir el \nnúmero minimo de movimientos","Felicitaciones",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    //Método para mover de la torre b-a
    private void MoverBA(){
        try {
            if(PilaTorreB.getContNodo()>0){
                Nodo Plataforma =  new Nodo();
                Plataforma.setDato(PilaTorreB.Peek());
                //Verifico para ver si puedo hacer el movimiento a b
                if(PilaTorreA.getContNodo()>0){
                    if(Plataforma.getDato().compareTo(PilaTorreA.Peek())>0){
                        return;
                    }
                }
                PilaTorreB.Pop();//Quitamos de la torre a
                PilaTorreA.Push(Plataforma);//Colocamos lo que contiene plataforma
                PresentarTorreA();
                PresentarTorreB();
                PresentarCantidadDeMOvimientos();
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    //Método para mover de la torre b - c
    private void MoverBC(){
        try {
            if(PilaTorreB.getContNodo()>0){
                Nodo Plataforma =  new Nodo();
                Plataforma.setDato(PilaTorreB.Peek());
                //Verifico para ver si puedo hacer el movimiento a b
                if(PilaTorreC.getContNodo()>0){
                    if(Plataforma.getDato().compareTo(PilaTorreC.Peek())>0){
                        return;
                    }
                }
                PilaTorreB.Pop();//Quitamos de la torre a
                PilaTorreC.Push(Plataforma);//Colocamos lo que contiene plataforma
                PresentarTorreB();
                PresentarTorreC();
                PresentarCantidadDeMOvimientos();
                //Ahora evaluamos si tenemos que detener el juego
                if(PilaTorreC.getContNodo()==Objetivo && ContNumMov == NumNimMov){
                    JOptionPane.showMessageDialog(null, "Felicidades haz alcanzado el objetivo de movimientos minimos"
                    +"\n\nIntenta con otro nivel del juego","Felicitaciones",JOptionPane.WARNING_MESSAGE);
                }else if(PilaTorreC.getContNodo()==Objetivo && ContNumMov != NumNimMov){
                    JOptionPane.showMessageDialog(null, "Felicidades haz alcanzado el objetivo del juego"
                    +"\n\nIntenta de nuevo y trata de conseguir el \nnúmero minimo de movimientos","Felicitaciones",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    //Método para mover de c-a
    private void MoverCA(){
        try {
            if(PilaTorreC.getContNodo()>0){
                Nodo Plataforma =  new Nodo();
                Plataforma.setDato(PilaTorreC.Peek());
                //Verifico para ver si puedo hacer el movimiento a b
                if(PilaTorreA.getContNodo()>0){
                    if(Plataforma.getDato().compareTo(PilaTorreA.Peek())>0){
                        return;
                    }
                }
                PilaTorreC.Pop();//Quitamos de la torre a
                PilaTorreA.Push(Plataforma);//Colocamos lo que contiene plataforma
                PresentarTorreA();
                PresentarTorreC();
                PresentarCantidadDeMOvimientos();
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    //Método para mover de la torre c - b
    private void MoverCB(){
        try {
            if(PilaTorreC.getContNodo()>0){
                Nodo Plataforma =  new Nodo();
                Plataforma.setDato(PilaTorreC.Peek());
                //Verifico para ver si puedo hacer el movimiento a b
                if(PilaTorreB.getContNodo()>0){
                    if(Plataforma.getDato().compareTo(PilaTorreB.Peek())>0){
                        return;
                    }
                }
                PilaTorreC.Pop();//Quitamos de la torre a
                PilaTorreB.Push(Plataforma);//Colocamos lo que contiene plataforma
                PresentarTorreB();
                PresentarTorreC();
                PresentarCantidadDeMOvimientos();
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TorreB = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TorreC = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TorreA = new javax.swing.JTable();
        btnA_B = new javax.swing.JButton();
        btnA_C = new javax.swing.JButton();
        btnB_A = new javax.swing.JButton();
        btnB_C = new javax.swing.JButton();
        btnC_A = new javax.swing.JButton();
        btnC_B = new javax.swing.JButton();
        cbNumDiscos = new javax.swing.JComboBox<>();
        lblNumMov = new javax.swing.JLabel();
        lblMinMov = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnReiniciar = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TorreB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Torre B"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TorreB.setAutoscrolls(false);
        TorreB.setFocusable(false);
        TorreB.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(TorreB);
        if (TorreB.getColumnModel().getColumnCount() > 0) {
            TorreB.getColumnModel().getColumn(0).setResizable(false);
        }

        TorreC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Torre C"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TorreC.setAutoscrolls(false);
        TorreC.setFocusable(false);
        TorreC.setRowSelectionAllowed(false);
        jScrollPane2.setViewportView(TorreC);
        if (TorreC.getColumnModel().getColumnCount() > 0) {
            TorreC.getColumnModel().getColumn(0).setResizable(false);
        }

        TorreA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Torre A"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TorreA.setAutoscrolls(false);
        TorreA.setFocusable(false);
        TorreA.setRowSelectionAllowed(false);
        jScrollPane3.setViewportView(TorreA);
        if (TorreA.getColumnModel().getColumnCount() > 0) {
            TorreA.getColumnModel().getColumn(0).setResizable(false);
        }

        btnA_B.setText("B");
        btnA_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnA_BActionPerformed(evt);
            }
        });

        btnA_C.setText("C");
        btnA_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnA_CActionPerformed(evt);
            }
        });

        btnB_A.setText("A");
        btnB_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnB_AActionPerformed(evt);
            }
        });

        btnB_C.setText("C");
        btnB_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnB_CActionPerformed(evt);
            }
        });

        btnC_A.setText("A");
        btnC_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC_AActionPerformed(evt);
            }
        });

        btnC_B.setText("B");

        cbNumDiscos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "4", "5", "6", "7", "8", "9", "10" }));

        lblNumMov.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNumMov.setForeground(new java.awt.Color(255, 0, 51));
        lblNumMov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumMov.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMinMov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinMov.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Número de discos:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Número de mov:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Número min. de mov:");

        btnReiniciar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnReiniciar.setText("Reiniciar");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        btnIniciar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbNumDiscos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMinMov, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNumMov, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(122, 122, 122))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(244, 244, 244)
                                    .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(8, 8, 8))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnA_B)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnA_C)
                                    .addGap(107, 107, 107)
                                    .addComponent(btnB_A)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnB_C)
                                    .addGap(95, 95, 95)
                                    .addComponent(btnC_A)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnC_B))))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnA_B)
                    .addComponent(btnA_C)
                    .addComponent(btnB_A)
                    .addComponent(btnB_C)
                    .addComponent(btnC_A)
                    .addComponent(btnC_B))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNumDiscos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lblMinMov, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNumMov, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        ContNumMov = 0;
        Iniciar();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        Reiniciar();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnA_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnA_BActionPerformed
        MoverAB();
    }//GEN-LAST:event_btnA_BActionPerformed

    private void btnA_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnA_CActionPerformed
        MoverAC();
    }//GEN-LAST:event_btnA_CActionPerformed

    private void btnB_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnB_AActionPerformed
        MoverBA();
    }//GEN-LAST:event_btnB_AActionPerformed

    private void btnB_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnB_CActionPerformed
        MoverBC();
    }//GEN-LAST:event_btnB_CActionPerformed

    private void btnC_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnC_AActionPerformed
        MoverCA();
    }//GEN-LAST:event_btnC_AActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TorreA;
    private javax.swing.JTable TorreB;
    private javax.swing.JTable TorreC;
    private javax.swing.JButton btnA_B;
    private javax.swing.JButton btnA_C;
    private javax.swing.JButton btnB_A;
    private javax.swing.JButton btnB_C;
    private javax.swing.JButton btnC_A;
    private javax.swing.JButton btnC_B;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JComboBox<String> cbNumDiscos;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMinMov;
    private javax.swing.JLabel lblNumMov;
    // End of variables declaration//GEN-END:variables
}
