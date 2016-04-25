package cl.mtt.sgprt.sign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;

public class WinClave extends JFrame
{
  private static final long serialVersionUID = 5893908624718564419L;
  static WinClave win;
  private JPanel jPanel = null;
  private JLabel jLabel1 = null;
  private JPasswordField jPasswordField = null;
  private JButton jButton = null;
  private JLabel jLabel = null;

  public static void main(String[] args) {
    win = new WinClave();

    win.setVisible(true);
  }

  public WinClave()
  {
    initialize();
  }

  public void cerrar() {
    setVisible(false);
  }

  private void initialize()
  {
    setContentPane(getJPanel());
    this.jPanel.getRootPane().setDefaultButton(getJButton());

    int width = 466;
    int height = 209;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    int x = (screen.width - width) / 2;
    int y = (screen.height - height) / 2;
    setBounds(x, y, width, height);

    setSize(width, height);
    setTitle("Firma Digital de Certificados de Revisión Técnica V4.0");

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  private JPanel getJPanel()
  {
    if (this.jPanel == null) {
      this.jPanel = new JPanel();
      this.jPanel.setLayout(null);
      this.jPanel.add(getJLabel1(), null);
      this.jPanel.add(getJPasswordField(), null);
      this.jPanel.add(getJButton(), null);
      this.jPanel.add(getJLabel(), null);
    }
    return this.jPanel;
  }

  private JLabel getJLabel1()
  {
    if (this.jLabel1 == null) {
      this.jLabel1 = new JLabel();
      this.jLabel1.setBounds(12, 21, 132, 38);
      this.jLabel1.setText("Clave Secreta:");
      this.jLabel1.setFont(new Font("Dialog", 1, 18));
    }
    return this.jLabel1;
  }

  private JPasswordField getJPasswordField()
  {
    if (this.jPasswordField == null) {
      this.jPasswordField = new JPasswordField();
      this.jPasswordField.setBounds(145, 21, 304, 38);
      this.jPasswordField.setFont(new Font("Arial", 1, 20));
      this.jPasswordField.setEchoChar('*');

      this.jPasswordField.setText("");
    }

    return this.jPasswordField;
  }

  private JButton getJButton()
  {
    if (this.jButton == null) {
      this.jButton = new JButton();
      this.jButton.setBounds(155, 86, 165, 30);
      this.jButton.setText("Procesar");
      this.jButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e) {
          FirmarCrt firmar = null;
          try
          {
            firmar = new FirmarCrt(WinClave.this.jPasswordField.getPassword());
          } catch (Exception ex) {
            WinClave.this.jLabel.setText("No se puede ejecutar el proceso: " + ex.getMessage());
            return;
          }

          firmar.start();

          WinClave.this.jLabel.setText("Procesando archivos ...");

          WinClave.win.setVisible(false);
        }
      });
    }

    return this.jButton;
  }

  private JLabel getJLabel()
  {
    if (this.jLabel == null) {
      this.jLabel = new JLabel();
      this.jLabel.setBounds(15, 142, 432, 25);
      this.jLabel.setText("");
      this.jLabel.setBackground(new Color(238, 0, 0));
      this.jLabel.setForeground(new Color(255, 51, 51));
      this.jLabel.setName("");
    }
    return this.jLabel;
  }
}