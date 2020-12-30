/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshop.GUI.category;

import coffeeshop.DAO.CategoryDao;
import coffeeshop.DTO.Category;
import coffeeshop.Util.Common;
import coffeeshop.Util.DbUtil;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Minh
 */
public final class JDModifyCategory extends javax.swing.JDialog {

    /**
     * Creates new form JDCategoryCreate
     */
    CallbackModify callback;
    Category category;
    DbUtil dbUtil;
    CategoryDao categoryDao;

    interface CallbackModify {

        public void actionCategoryModify();
    }

    /**
     *
     * @param parent
     * @param modal
     * @param dbUtil
     * @param callback
     * @param category
     */
    public JDModifyCategory(java.awt.Frame parent, boolean modal, DbUtil dbUtil, CallbackModify callback, Category category) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        lblNameError.setVisible(false);
        this.callback = callback;
        this.dbUtil = dbUtil;
        this.categoryDao = new CategoryDao(dbUtil);

        if (!Common.isNullOrEmpty(category)) {
            this.category = category;
            loadData();
        }
    }

    public void loadData() {
        lblTitle.setText("Sửa đổi danh mục");
        btnModify.setText("Sửa đổi");
        txtName.setText(category.getName());
        rdoActive.setSelected(category.getStatus());
        rdoNonActive.setSelected(category.getStatus() == false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        rdoActive = new javax.swing.JRadioButton();
        rdoNonActive = new javax.swing.JRadioButton();
        btnModify = new javax.swing.JButton();
        lblNameError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeeshop/assets/img/icons8_categorize_50px.png"))); // NOI18N
        lblTitle.setText("THÊM MỚI DANH MỤC");

        lblName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblName.setText("Tên danh mục");

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        lblStatus.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        lblStatus.setText("Trạng thái");

        rdoActive.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoActive);
        rdoActive.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        rdoActive.setSelected(true);
        rdoActive.setText("Hoạt động");

        rdoNonActive.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNonActive);
        rdoNonActive.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        rdoNonActive.setText("Không hoạt động");

        btnModify.setBackground(new java.awt.Color(0, 204, 106));
        btnModify.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModify.setForeground(new java.awt.Color(255, 255, 255));
        btnModify.setText("Thêm mới");
        btnModify.setBorderPainted(false);
        btnModify.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        lblNameError.setForeground(new java.awt.Color(240, 71, 71));
        lblNameError.setText("Tên danh mục không được để trống");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 427, Short.MAX_VALUE)
                                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdoActive, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNonActive, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lblNameError)
                .addGap(18, 18, 18)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoActive)
                    .addComponent(rdoNonActive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        String name = txtName.getText().trim();
        boolean status = rdoActive.isSelected();

        if (name.equals("")) {
            txtName.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(240, 71, 71)),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)));
            lblName.setForeground(new Color(240, 71, 71));
            lblNameError.setVisible(true);
        } else {
            try {
                if (this.category == null) {
                    category = new Category();
                    category.setName(name);
                    category.setStatus(status);

                    Map<String, Object> result = categoryDao.create(category);

                    if ((boolean) result.get("status") == true) {
                        JOptionPane.showMessageDialog(null, "Thêm danh mục thành công!");
                        callback.actionCategoryModify();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm danh mục thất bại, lỗi: " + result.get("message") + "!");
                    }
                } else {
                    category.setId(this.category.getId());
                    category.setName(name);
                    category.setStatus(status);

                    Map<String, Object> result = categoryDao.update(category);

                    if ((boolean) result.get("status") == true) {
                        JOptionPane.showMessageDialog(null, "Sửa đổi danh mục thành công!");
                        callback.actionCategoryModify();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa đổi danh mục thất bại, lỗi: " + result.get("message") + "!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnModifyActionPerformed

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnModify.doClick();
        }
    }//GEN-LAST:event_txtNameKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModify;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameError;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JRadioButton rdoActive;
    private javax.swing.JRadioButton rdoNonActive;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
