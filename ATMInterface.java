package ATMInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ATMInterface extends JFrame implements ActionListener {

    JLabel l1, l2, l3;
    JTextArea tArea;
    JButton b1, b2, b3, b4;
    JTextField t1, t2;
    JScrollPane scrollPane;

    private int amount;
    SimpleDateFormat timeFormat;
    Date dateObj;
    private String date, time;

    TransactionHistory tHistory;
    Withdraw wdraw;
    Deposit deposit;
    Transfer transfer;

    ATMInterface() {
        setTitle("ATM INTERFACE");
        setSize(720, 480);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dateObj = new Date();
        tHistory = new TransactionHistory();
        wdraw = new Withdraw();
        deposit = new Deposit();
        transfer = new Transfer();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        timeFormat = new SimpleDateFormat("HH:mm:ss");

        date = dateFormat.format(dateObj);

        l1 = new JLabel("ATM");
        l2 = new JLabel();
        l3 = new JLabel();
        l1.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
        l1.setForeground(Color.BLACK);

        tArea = new JTextArea();
        tArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        scrollPane = new JScrollPane(tArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();

        t1 = new JTextField();
        t2 = new JTextField();
        t1.setFont(new Font("Courier New", Font.PLAIN, 12));
        t2.setFont(new Font("Courier New", Font.PLAIN, 12));

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        add(l1);
        add(l2);
        add(l3);
        add(scrollPane);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(t1);
        add(t2);

        getContentPane().setBackground(Color.GRAY);
        setInvisible();
        homepage();

    }

    private void setInvisible() {
        t1.setText("");
        t2.setText("");
        tArea.setVisible(true);
        l2.setVisible(false);
        l3.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        t1.setVisible(false);
        t2.setVisible(false);
        scrollPane.setVisible(false);
    }

    private void homepage() {
        l1.setText("ATM");
        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b4.setVisible(true);

        b1.setText("Withdraw");
        b2.setText("Deposit");
        b3.setText("Transfer");
        b4.setText("Balance and History");

        l1.setBounds(250, 30, 200, 80);
        b1.setBounds(250, 140, 200, 50);
        b2.setBounds(250, 200, 200, 50);
        b3.setBounds(250, 260, 200, 50);
        b4.setBounds(250, 320, 200, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = e.getActionCommand();
        switch (value) {
            case "Withdraw":
                setInvisible();
                withdraw();
                break;
            case "Deposit":
                setInvisible();
                deposit();
                break;
            case "Transfer":
                setInvisible();
                transfer();
                break;
            case "Balance and History":
                setInvisible();
                balanceHistory();
                break;
            case "< Back":
                setInvisible();
                homepage();
                break;
            case "Deposit Amount":
                try {
                    int no = Integer.parseInt(t1.getText().toString());
                    time = timeFormat.format(dateObj);
                    if (deposit.deposit(no, date, time)) {
                        JOptionPane.showMessageDialog(null, "Deposit Successfully", "Transaction Successful",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    setInvisible();
                    homepage();
                } catch (Exception exp) {
                    time = timeFormat.format(dateObj);
                    System.out.println(exp);
                    JOptionPane.showMessageDialog(null, "PLease enter amount in numbers only", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Withdraw Amount":
                try {
                    int no = Integer.parseInt(t1.getText().toString());
                    time = timeFormat.format(dateObj);

                    if (wdraw.withdraw(no, date, time)) {
                        JOptionPane.showMessageDialog(null, "Withdraw Successfully", "Transaction Successful",
                                JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "inefficient Balance", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                    setInvisible();
                    homepage();
                } catch (Exception exp) {
                    System.out.println(exp);
                    JOptionPane.showMessageDialog(null, "PLease enter amount in numbers only", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Send":
                try {
                    int no = Integer.parseInt(t2.getText().toString());
                    if (t1.getText().toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please Enter name of reciever", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        time = timeFormat.format(dateObj);
                        if (transfer.transferTo(t1.getText().toString(), no, date, time)) {
                            JOptionPane.showMessageDialog(null, "Send Successfully", "Transaction Successful",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "inefficient Balance", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        }
                        setInvisible();
                        homepage();
                    }
                } catch (Exception exp) {
                    System.out.println(exp);
                    JOptionPane.showMessageDialog(null, "PLease enter amount in numbers only", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    private void balanceHistory() {
        amount = tHistory.getBalance();

        b1.setVisible(true);
        scrollPane.setVisible(true);
        l2.setVisible(true);

        l2.setFont(new Font("", Font.PLAIN, 32));

        scrollPane.setBounds(120, 200, 500, 200);
        b1.setBounds(50, 130, 100, 50);
        l2.setBounds(200, 130, 350, 50);
        l1.setBounds(200, 30, 300, 80);

        l1.setText("History");
        l2.setText("Balance : Rs " + amount);
        b1.setText("< Back");
        tArea.setEditable(false);

        tArea.setText(tHistory.getHistory());
    }

    private void transfer() {
        tArea.setVisible(true);
        t1.setVisible(true);
        t2.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        b1.setText("< Back");
        l2.setText("Enter Reciever Name : ");
        l3.setText("Enter Amount : ");
        b2.setText("Send");

        l2.setFont(new Font("", Font.PLAIN, 18));
        l3.setFont(new Font("", Font.PLAIN, 18));

        b1.setBounds(50, 130, 100, 50);
        l2.setBounds(200, 190, 200, 60);
        l3.setBounds(200, 260, 200, 60);
        t1.setBounds(400, 200, 100, 40);
        t2.setBounds(400, 270, 100, 40);
        b2.setBounds(300, 340, 100, 60);

    }

    private void deposit() {

        tArea.setVisible(true);
        t1.setVisible(true);
        l3.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        b1.setText("< Back");
        l3.setText("Enter Amount : ");
        b2.setText("Deposit Amount");

        l3.setFont(new Font("", Font.PLAIN, 18));

        b1.setBounds(50, 130, 100, 50);
        l3.setBounds(200, 190, 200, 60);
        t1.setBounds(400, 200, 100, 40);
        b2.setBounds(270, 260, 150, 60);
    }

    private void withdraw() {

        tArea.setVisible(true);
        t1.setVisible(true);
        l3.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        b1.setText("< Back");
        l3.setText("Enter Amount : ");
        b2.setText("Withdraw Amount");

        l3.setFont(new Font("", Font.PLAIN, 18));

        b1.setBounds(50, 130, 100, 50);
        l3.setBounds(200, 190, 200, 60);
        t1.setBounds(400, 200, 100, 40);
        b2.setBounds(270, 260, 150, 60);
    }

    public static void main(String[] args) {
        new ATMInterface();
    }
}
