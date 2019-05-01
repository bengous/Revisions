# Revisions

Java RMI - ring server FileManager

You have sold your expertise in distributed systems and have succeeded in obtaining a well-paid developer position. Your first task is to design and develop a file sharing system, that we will call MyNap.
The specification is the following :
• the technology to use is Java RMI
• the MyNap infrastructure should manage multiple file management servers
• the MyNap servers should be organized in a ring, with one server playing the role of a master and the others being slaves
• the MyNap master is to contain the index of the files in the system, each file being replicated on one or more slave servers.
• a client can connect to MyNap and share a file. This should trigger a replication of the file on the system and the update of the index.
• a client can connect and ask for a file. If the file is in MyNap, the client should receive it.
