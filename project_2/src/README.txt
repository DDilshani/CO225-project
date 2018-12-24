Instruction for the Server

1. Compile 	- javac Project2.java 
    Run     		- java Project2

2.GUI 
	GUI displays the current bid value of the FB, VRTU, MSFT, GOOGL, YHOO, XLNX, TSLA, TXN
	If any user inputs a bid value that is greater than the current bid value, the corresponding bid value will be accepted.
	GUI can be used to track all the changes/ updates done to the stock items by entering the symbol in the search box and click 'Enter' button.


Instruction for the clients
1.Connect to the auction server
	The client can use the command line and type 'nc localhost 2000' (in linux) to connect to the server.

2. Once the client connects to the server he/she has to enter his/her name to identify.
	Then the client has to enter the symbol of the company which is going to bid.
	If the provided 'Symbol' is found, the server reply back with the current cost of the security or -1 to indicate that the Symbol is invalid.
    
3. Then the client can enter his/her bid value. If it is above the current value the bid is accepted or else he/she has to try again. The client can bid for several times and he/she can leave the server by typing 'quit'. 
