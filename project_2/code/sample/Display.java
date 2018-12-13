import java.awt.*;
import javax.swing.Timer; //for timer

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.util.*; 

public class Display 
    extends JPanel implements ActionListener { 
    JTextArea textArea;
    VisualServer server; 

    public Display(VisualServer server) { 
        super(new GridBagLayout());

	textArea = new JTextArea(5, 20); 
	textArea.setEditable(false); 	
	JScrollPane scrollPane = new JScrollPane(textArea);
	
	 //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);

	Timer timer = new Timer(500, this); 
	timer.start(); 

	this.server = server; 
    }
    
    public void actionPerformed(ActionEvent e) { 
	String newline = server.getMSG(); 
	if(newline != null) { 
	    textArea.append(newline + "\n"); 

	    //Make sure the new text is visible, even if there
	    //was a selection in the text area.
	    textArea.setCaretPosition(textArea.getDocument().getLength());
	}

    }

    public static void main(String [] args) throws IOException { 
	//Create and set up the window.
        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	StudentDB allowedUsers = new StudentDB("e12.csv","Student RegNo","Name");
	VisualServer server = new VisualServer(MainServer.BASE_PORT,
					       allowedUsers); 
        //Add contents to the window.
        frame.add(new Display(server));

        //Display the window.
        frame.pack();
        frame.setVisible(true);

	server.server_loop(); 
    }
}
	