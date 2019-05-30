# file-information-server-using-socket-programming
Designing a Peer to Peer File Download System Using Socket Programming (Java)

In a Peer-to-Peer system, many computers are interconnected through a network to share information and resources among themselves either through a centralized server or without a centralized system, like the interconnected hub that we use in DC++. In a client-server model, server has the information about all clients and the clients can contact the server for the file information of clients, and then contact the corresponding clients to download the required files. 

In this assignment you’ll be implementing a small scale peer-to-peer communication system for file sharing, using socket programming API’s in JAVA. There will be a file information server that contains the peer information, and the files available to every peer. The system architecture looks like following:
 
implement the system for two peers and a single file information server. The design details are as follows.
 1) You need to implement a File Information Server (FIS). The FIS maintains a table that has information about the all the peers’ IP addresses and the files available to those peers.
 2) You also need to implement server and client processes at the individual peers. The communication protocol between the peers and between a peer and the FIS is as follows. 
	a) Let peer A wants to download a file named abc.pdf. The file is available at Peer B.
	 b) Peer A communicates with the FIS to get the details of Peer B 
	c) Then Peer A communicates with Peer B to download the file abc.pdf. 
	d) The communication between a peer and the FIS is through Datagram sockets.
	 e) The communication between two peers is through Stream sockets.


1. Server.java – the server code for FIS 
2.Client.java – the server code for peers (for downloading files) 
3 Server_final.java – the client code for peers (this includes two clients – one datagram client to get the information from FIS)
4.Client_file_receive.java (this includes two clients-  stream client to download the file from other peers)


