Instruction for the server
1.	Compile - javac Project2.java 
	Run 	- java Project2

2.GUI 
	GUI display the current bid value of the FB, VRTU, MSFT, GOOGL, YHOO, XLNX, TSLA, TXN
	If any user input a bid value that is greater than the current bid value corresponding bid value will change in the GUI.
	GUI can used to track all the changes done to the stock item by entering the symbol in the search box and click 'Enter' button.


Instruction for the clients
1.Connect to the auction server
	Client can use commandline and type 'nc localhost 2000' to connect to the server.

2.	Once the client connect to the server he/she has to enter his/her name to identify.
	Then the client has to enter the symbol of the company which is going to bid.
	If the provided Symbol is found, the server reply back with the current cost of the security or -1 to indicate that the Symbol is invalid.
	
3. 	Then the client can enter his/her bid value. If it is above the current value the bid is accepted or else he/she has to try again. Client can bid for several times and he/she can leave the server by typing 'quit'. 
