package utilitarios;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JOptionPane;
/**
 *
 * @author  mauro
 */
public class jTextMoeda extends javax.swing.JPanel {
    /** Creates new form jTextMoeda */
    public jTextMoeda() {
        initComponents();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" C�digo Gerado ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jTextMoeda = new javax.swing.JTextField();
        jTextMoeda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextMoedaActionPerformed(evt);
            }
        });
        jTextMoeda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextMoedaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextMoedaFocusLost(evt);
            }
        });
        jTextMoeda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextMoedaKeyReleased(evt);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextMoeda, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextMoeda)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void jTextMoedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextMoedaActionPerformed
        jTextMoeda.transferFocus();
    }//GEN-LAST:event_jTextMoedaActionPerformed
    private void jTextMoedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextMoedaKeyReleased
        double valor = 0;
        if (jTextMoeda.getText().length() != 0) {
            String str = jTextMoeda.getText();
            str = str.replace(',','.');
            try {
                valor = Double.parseDouble(str);
            } catch (java.lang.NumberFormatException ex) {
                jTextMoeda.setText("");
                JOptionPane.showMessageDialog(null, "Esse campo s� aceita n�meros \n Ex.: 380,52", "ATEN��O",
                        JOptionPane.INFORMATION_MESSAGE);
                jTextMoeda.grabFocus();
            }
        }
    }//GEN-LAST:event_jTextMoedaKeyReleased
    private void jTextMoedaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextMoedaFocusLost
        if (jTextMoeda.getText().trim().compareTo("") != 0){
            String str = jTextMoeda.getText();
            str = str.replaceAll("\\.","");
            str = str.replace(',','.');
            str = mascaraDinheiro(Double.parseDouble(str),DINHEIRO_REAL);
            jTextMoeda.setText(str);
        }else
            jTextMoeda.setText("0,00");
    }//GEN-LAST:event_jTextMoedaFocusLost
    private void jTextMoedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextMoedaFocusGained
        if (jTextMoeda.getText().trim().compareTo("") != 0){
            String str = jTextMoeda.getText();
            str = str.replaceAll("\\.","");
            jTextMoeda.setText(str);
            jTextMoeda.selectAll();
        }
    }//GEN-LAST:event_jTextMoedaFocusGained
    // Declara��o de vari�veis - n�o modifique//GEN-BEGIN:variables
    private javax.swing.JTextField jTextMoeda;
    // Fim da declara��o de vari�veis//GEN-END:variables
    private static final Locale BRAZIL = new Locale("pt","BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("###,###,##0.00",REAL);
    // � 
    public static String mascaraDinheiro(double valor, DecimalFormat moeda){
        return moeda.format(valor);
    }
}