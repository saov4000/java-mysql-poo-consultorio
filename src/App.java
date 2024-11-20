import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class App extends JFrame{
    private JPanel panel;
    private JPanel buttons;
    private JButton btnCadastrar;
    private JButton btnListar;
    private JButton btnAtualizar;
    private JButton btnExluir;
    private JScrollPane scroll;
    private JTable tabela;
    List<Paciente> lista;// criando uma lista do tipo List -
    private int id; //variavel que vai pegar o ID

    public App(){
        setContentPane(panel);
        setTitle("App");
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        showData(); //chamando o método showdata para exibir os dados

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cadastro();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Atualizacao(id);
            }
        });

        btnExluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAO d = new DAO();
                int opcao = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir?");
                if(opcao == JOptionPane.YES_OPTION) {
                    if (d.excluir(id)) {
                        JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir");
                    }
                }
            }
        });

        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow(); //pega o index da linha
                //passa o valor da linha e retorna o valor da coluna 0 (coluna que tem o ID) dessa linha
                id = Integer.parseInt(tabela.getModel().getValueAt(linha,0).toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public void showData(){
        DAO d = new DAO();
        lista = d.listar();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();// DefaultTableModel é uma class padrao para tabela, ela que é manipulada
        model.setColumnIdentifiers(new Object[]{"Id","Nome","Cidade","Telefone"});
        model.setRowCount(0);
        for(int i=0;i<lista.size();i++){
            model.insertRow(i,new Object[]{
                    lista.get(i).getId(),
                    lista.get(i).getNome(),
                    lista.get(i).getCidade(),
                    lista.get(i).getTelefone()
            });
        }
    }
}
