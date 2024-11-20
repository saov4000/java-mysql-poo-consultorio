import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mysql.cj.util.StringUtils;

public class Cadastro extends JFrame{
    //atributo +-= variavel
    private JPanel panel;
    private JTextField txtNome;
    private JTextField txtCidade;
    private JTextField txtTelefone;
    private JButton btnCadastrar;

    //metodo +-= função
    public Cadastro(){
        //metodos para montar a tela
        setContentPane(panel);
        setTitle("Cadastro");
        setSize(400,200);
        setLocationRelativeTo(null);
        setVisible(true);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //1º validacao dos campos vazios
                if(txtNome.getText().isEmpty() || txtCidade.getText().isEmpty() || txtTelefone.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Existem campos vazios");
                }else{
                    try{
                        int n = Integer.parseInt(txtTelefone.getText()); // tentar convertar para int e guardar o valor em n
                        DAO dao = new DAO();
                        Paciente paciente = new Paciente();
                        paciente.setNome(txtNome.getText());
                        paciente.setCidade(txtCidade.getText());
                        paciente.setTelefone(n);
                        if (dao.cadastrar(paciente)){
                            JOptionPane.showMessageDialog(null,"Sucesso ao cadastrar");
                            txtNome.setText("");
                            txtCidade.setText("");
                            txtTelefone.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
                        }
                    }catch(NumberFormatException exception){
                        JOptionPane.showMessageDialog(null,"No campo telefone digite somente numeros");
                    }
                }
            }
        });
    }
}
