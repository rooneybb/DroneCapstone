'''

             Client 
			                 '''

import socket
import json

data = {
    'name': 'Patrick Jane',
    'age': 45,
    'children': ['Susie', 'Mike', 'Philip']
  }
data_string = json.dumps(data)
host = 'ip here'
port = 1111
size = 1024
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((host,port))
s.send((data_string.encode('utf-8')))
data = s.recv(size)
data_loaded = json.loads(data.decode())
s.close()
print('Received:', data_loaded)

#////////////////////////////////////////////////////////////////

'''

             Server 
			                 '''

import socket, json

host = ''
port = 1111
backlog = 5
size = 1024
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((host,port))
s.listen(backlog)
while 1:
    client, address = s.accept()
    data = client.recv(size)
    data_loaded = json.loads(data.decode())
    print(data_loaded )
    print(data_loaded['children'] )
    if  data_loaded:
        data_string = json.dumps(data_loaded)
        client.send( (data_string.encode('utf-8')))
    client.close()
